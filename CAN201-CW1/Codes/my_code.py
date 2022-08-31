from socket import *
import os
import json
import struct
import argparse
import time

check_log = './check_log.txt'  # can not send files in it unless it has been changed, will be clear when main.py start.
share_dir = r'./share'
send_log = './sending_log.txt'  # when file start to transmit will be added into send_log, when finish transmit, remove.
chunk_folder = r'./chunk'  # used by server to save chunk file.
temp_folder = r'./temp_chunk'  # used by client to save received chunk file
kilobytes = 1024
megabytes = kilobytes*1000
chunksize = int(200*megabytes)  # default chunk size, used to calculate chunk size


def _argparse():  # get IP address
    parser = argparse.ArgumentParser()
    parser.add_argument('--ip', action='store', required=True, dest='ip', help='IP address')
    return parser.parse_args()


def check_in_log(log_name, check_info):  # check whether file name in the log, return boolean
    with open(log_name, 'r') as f:
        for line in f:
            if check_info == line:
                return True
        return False


def add_file_to_log(log_name, add_info):
    with open(log_name, 'a') as f:
        f.write(add_info + '\n')


def del_file_in_log(log_name, del_info):
    temp_li = []
    with open(log_name, 'r') as f:
        for line in f:
            if del_info not in line:
                temp_li.append(line)
    with open(log_name, 'w') as f:
        for line in temp_li:
            f.write(line + '\n')


def scan_log(log_name):  # scan log.txt as list, return the list.
    result_li = []
    with open(log_name, 'r') as f:
        for line in f:
            result_li.append(line)
    return result_li


def split_file(from_file, to_dir, chunk_size=chunksize):  # split file into chunk and store in to_dir
    if not os.path.exists(to_dir):  # check whether to_dir exists or not
        os.mkdir(to_dir)
    else:
        for file_name in os.listdir(to_dir):
            os.remove(os.path.join(to_dir, file_name))
    part_num = 0
    input_file = open(from_file, 'rb')  # open the fromfile
    while True:
        chunk = input_file.read(chunk_size)
        if not chunk:  # check the chunk is empty
            break
        part_num += 1
        filename = os.path.join(to_dir, ('part%04d' % part_num))
        fileobject = open(filename, 'wb')  # make partfile
        fileobject.write(chunk)  # write data into partfile
        fileobject.close()
    return part_num


def start_server():

    all_check_dic = {}  # file : last modify time of file

    parser = _argparse()
    this_server = parser.ip

    info_port = 25000
    file_port = 25001

    server_info_socket = socket(AF_INET, SOCK_STREAM)
    server_file_socket = socket(AF_INET, SOCK_STREAM)

    server_info_socket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
    server_file_socket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)

    server_info_socket.bind(('', info_port))
    server_file_socket.bind(('', file_port))

    server_info_socket.listen(1)
    server_file_socket.listen(1)

    while True:
        try:
            print('Server want accept', this_server)
            info_conn, info_addr = server_info_socket.accept()
            file_conn, file_addr = server_file_socket.accept()

            print('Server is ready:', this_server)

            def this_is_a_file(file_path):
                file_path = file_path.split('\n')[0]
                abs_path = os.path.abspath(file_path)

                file_size = os.path.getsize(file_path)
                if file_size > 10485760:
                    add_file_to_log(send_log, abs_path)
                    split_file(abs_path, chunk_folder, 10485760)
                    # print_num = split_file(abs_path, chunk_folder, 10485760)
                    # print(file_path, 'is too big to trans. Split it to %s, trans later.' % print_num)
                    send_chunk_file(chunk_folder, file_path, file_size)

                    del_file_in_log(send_log, abs_path)
                    add_file_to_log(check_log, abs_path)
                    return

                header = {
                    'file_type': 1,  # file
                    'file_path': file_path,  # relative path
                    'file_size': file_size
                }
                header_dup = json.dumps(header)
                header_bir = header_dup.encode()

                info_conn.send(struct.pack('i', len(header_bir)))
                info_conn.send(header_bir)

                msg = info_conn.recv(2).decode()
                if msg == 'ok':
                    # print('Im server:', this_server, 'Im going to send file', file_path, 'to my client.')
                    add_file_to_log(send_log, abs_path)
                    with open(file_path, 'rb') as f:
                        for line in f:
                            file_conn.send(line)
                    ack_msg = info_conn.recv(3).decode()
                    if ack_msg == 'ACK':
                        # print('Im server', this_server, 'trans success', file_path)
                        del_file_in_log(send_log, abs_path)
                        add_file_to_log(check_log, abs_path)  # add sent file

                elif msg == 'no':
                    print('Im server:', this_server, 'my Client refuse to recv:', file_path)

            def send_chunk_file(folder_path, file_full_name, file_total_size):
                real_info_header = {
                    'file_type': 3,
                    'file_path': file_full_name,  # full name before split
                    'real_size': file_total_size,
                }
                real_header_dup = json.dumps(real_info_header)
                real_header_bir = real_header_dup.encode()

                info_conn.send(struct.pack('i', len(real_header_bir)))
                info_conn.send(real_header_bir)

                msg = info_conn.recv(2).decode()
                if msg == 'go':  # go means go send your chunk file
                    for chunk_file in os.listdir(folder_path):

                        chunk_path = os.path.join(folder_path, chunk_file)

                        header = {
                            'file_type': 3,  # chunk_file
                            'file_path': chunk_file,  # only the file name, no path
                            'file_size': os.path.getsize(chunk_path),
                        }
                        header_dup = json.dumps(header)
                        header_bir = header_dup.encode()

                        info_conn.send(struct.pack('i', len(header_bir)))
                        info_conn.send(header_bir)

                        msg = info_conn.recv(2).decode()
                        if msg == 'ck':  # ck = chunk_ok
                            with open(chunk_path, 'rb') as f:
                                for line in f:
                                    file_conn.send(line)
                            ack_msg = info_conn.recv(3).decode()
                            if ack_msg == 'ACK':
                                os.remove(chunk_path)
                        else:
                            print('Client don\'t want my file 555', this_server)
                # print('finish trans chunk of', file_full_name)

            def this_is_a_folder(folder_path):
                header = {
                    'file_type': 0,  # folder
                    'file_path': folder_path,  # relative path
                }

                header_dup = json.dumps(header)
                header_bir = header_dup.encode()

                info_conn.send(struct.pack('i', len(header_bir)))
                info_conn.send(header_bir)

            def traverse(dir_path):
                for file_folder_name in os.listdir(dir_path):
                    f_n = os.path.join(dir_path, file_folder_name)
                    if os.path.isfile(f_n):
                        end_fix = file_folder_name.split('.')[1]
                        if end_fix == 'temp':  # .temp stand for this file is in transmitting
                            continue
                        else:
                            if f_n not in all_check_dic.keys():
                                all_check_dic[f_n] = os.path.getmtime(f_n)
                            if check_in_log(check_log, os.path.abspath(f_n)):
                                return
                            else:
                                this_is_a_file(f_n)
                    else:
                        this_is_a_folder(f_n)
                        traverse(f_n)

            def check_chang_file(check_dic):  # if final modify time has been change, retransmit.
                for key, value in check_dic.items():
                    if os.path.getmtime(key) != value:
                        print("**************** re-transmit changed:", key, "******************")
                        this_is_a_file(key)

            # Server Logic:
            send_fir_list = scan_log(send_log)
            if send_fir_list:
                for file_n in send_fir_list:
                    this_is_a_file(file_n)
            traverse(share_dir)
            # check all files after finish transmit, if sth changed, retransmit.
            check_chang_file(all_check_dic)

        except ConnectionResetError:
            print('Client stop transfer:', this_server)
            break


def start_client():
    parser = _argparse()
    this_server = parser.ip

    info_port = 25000
    file_port = 25001

    client_info_socket = socket(AF_INET, SOCK_STREAM)
    client_file_socket = socket(AF_INET, SOCK_STREAM)

    while True:
        try:
            client_info_socket.connect((this_server, info_port))
            client_file_socket.connect((this_server, file_port))
            print('Info and file client connection success:', this_server)
            break
        except ConnectionRefusedError:
            continue

    while True:
        try:
            def join_file(fromdir, filename, todir):
                if not os.path.exists(todir):
                    os.mkdir(todir)
                if not os.path.exists(fromdir):
                    print('Wrong directory')
                outfile = open(os.path.join(todir, filename), 'wb')
                files = os.listdir(fromdir)  # list all the part files in the directory
                files.sort()  # sort part files to read in order
                for file in files:
                    filepath = os.path.join(fromdir, file)
                    infile = open(filepath, 'rb')
                    data = infile.read()
                    outfile.write(data)
                    infile.close()
                    os.remove(filepath)
                outfile.close()

            def rcv_file(file_name, file_size):
                client_info_socket.send('ok'.encode())

                file_temp_name = file_name.split('.')[0] + '.temp'
                # print('Im client:', this_server, 'Im going to recv file:', file_name)
                with open(file_temp_name, 'wb') as f:
                    rev_size = 0
                    while rev_size < file_size:
                        lene = client_file_socket.recv(1024)
                        f.write(lene)
                        rev_size += len(lene)
                        # print('Total_size:%s  recv_size:%s  filename:%s' % (file_size, rev_size, file_name))
                    # print('finish data trans,', file_temp_name, ' become ', file_name)
                os.rename(file_temp_name, file_name)
                client_info_socket.send('ACK'.encode())
                # print('Finished:', file_name, 'From server:', this_server)
                add_file_to_log(check_log, file_name)

            def rcv_folder(folder_name):
                if not os.path.exists(folder_name):
                    os.mkdir(folder_name)
                    # print('Im Client of:', this_server, 'Received new folder successfully:', folder_name)
                else:
                    print(folder_name, 'already exist!')

            def rcv_chunk(real_name, real_size):
                client_info_socket.send('go'.encode())
                chunk_rcv_size = 0

                while chunk_rcv_size < real_size:
                    client_info_socket.send('ck'.encode())

                    chunk_header_len = struct.unpack('i', client_info_socket.recv(4))[0]
                    chunk_header = json.loads(client_info_socket.recv(chunk_header_len))
                    this_file_type = chunk_header.get('file_type')

                    if this_file_type == 3:

                        chunk_file_name = chunk_header.get('file_path')
                        chunk_file_size = chunk_header.get('file_size')
                        chunk_abs_path = os.path.abspath(os.path.join(temp_folder, chunk_file_name))

                        with open(chunk_abs_path, 'wb') as f:
                            rev_size = 0
                            while rev_size < chunk_file_size:
                                lene = client_file_socket.recv(1024)
                                f.write(lene)
                                rev_size += len(lene)
                        chunk_rcv_size += chunk_file_size
                        client_info_socket.send('ACK'.encode())
                        # print('finish chunk trans:', chunk_abs_path, ' ', this_server)
                    else:
                        print('error file type of chunk!!!!')
                        break

                join_file(temp_folder, real_name, share_dir)
                add_file_to_log(check_log, real_name)
                # print(this_server, 'Im client, finish recv all the chunk file:', real_name)

            # Determine whether to accept as a file or folder
            header_len = struct.unpack('i', client_info_socket.recv(4))[0]
            header = json.loads(client_info_socket.recv(header_len))
            file_type = header.get('file_type')
            file_path = header.get('file_path')
            abs_path = os.path.abspath(file_path)

            if file_type == 0:
                rcv_folder(abs_path)
            elif file_type == 1:
                file_s = header.get('file_size')
                rcv_file(abs_path, file_s)
            elif file_type == 3:
                file_s = header.get('real_size')
                rcv_chunk(abs_path, file_s)

        except ConnectionResetError:
            print('Server stop.')
            break
        except struct.error:
            # print('what happend?', this_server)
            break
