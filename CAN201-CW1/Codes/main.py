# from multiprocessing import Process
import os
import my_code
import threading
import time


def main():

    check_folder(r'./share')
    check_folder(r'./chunk')
    check_folder(r'./temp_chunk')
    create_log_txt()
    create_sending_log_txt()

    p1 = threading.Thread(target=my_code.start_server, args=())
    p1.start()
    # p2 = threading.Thread(target=my_code.start_client, args=())
    # p2.start()
    while True:  # Periodically maintain the client startup status
        p2 = threading.Thread(target=my_code.start_client, args=())
        p2.start()
        time.sleep(15)


def create_log_txt():
    file = open('./check_log.txt', 'w+')
    file.truncate()
    file.close()
    print('check_log.txt on set.')


def create_sending_log_txt():
    file = open('./sending_log.txt', 'w+')
    file.close()
    print('./sending_log.txt on set.')


def check_folder(path):
    is_exists = os.path.exists(path)
    if not is_exists:
        os.makedirs(path)
        print('\'%s\' folder does not exist!\n New \'share\' folder created successfully!' % path)
        return True
    else:
        print('\'%s\' folder exists!' % path)
        return False


if __name__ == '__main__':
    main()
