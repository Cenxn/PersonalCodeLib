/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner kw = new Scanner(System.in);
        int input = kw.nextInt();
        q1ans(input);
    }
    
    public static void q1ans(int a){
        if (a == 1){
            System.out.println(1);
            return;
        }
        int[] arr =new int[44724];
        arr[0] = 1;
        int count = 1;
        for(int i=1; i<44724;i++){
            for(int j = i;j>=1;j--){
                arr[j] += arr[j-1];
                if(arr[j] == a){
                    System.out.println(count + i-j +1);
                    return;
                }
            }
            count+=(i+1);
        }
        System.out.println(((1 + count)*count/2) + 2);
    }
    
    public static int q1(int a){
        if (a == 1)return 1;
        int ans = 1;
        List<List<Integer>> li = new ArrayList<>();
        for(int i=0;i<a;++i){
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<=i;++j){
                if(j == 0|| j == i)row.add(1);
                else{
                    int check = li.get(i-1).get(j-1) + li.get(i-1).get(j);
                    if (check == a)return ans;
                    else row.add(check);
                }
                ans++;
            }
            li.add(row);
        }
        return ans;
    }

}
