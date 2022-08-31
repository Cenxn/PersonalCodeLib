/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

/**
 *
 * @author 10174
 */
interface common{
    String run(String str);
}

public class Test1 {
    public static void main(String[] args) {
        Scanner keyword = new Scanner(System.in, "GBK");
        common FeiHua = (s) -> "大家可能很好奇关于" + s + "问题的答案，那么今天小编为大家整理了有关" + s + "的相关问题，那么"
                + s + "究竟是什么呢？好，以上就是小编为大家整理的有关" + s + "的相关内容，喜欢的话请关注小编哦！";
        System.out.println("废话生成器加载成功！\n请输入你的问题，按下回车结束。");
        String question = keyword.nextLine();
//        System.out.println(FeiHua.run(question));
        printSth(question, FeiHua);
    }
    public static void printSth(String str, common lamda){
        String res = lamda.run(str);
        System.out.println(res);
    }
    
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0? quickMul(x, N) : 1 / quickMul (x, N);
    }
    public double quickMul(double x, long N){
        if(N==0)return 1;
        double y = quickMul(x, N/2);
        return N % 2 == 0? y*y:y*y*x;
    }
    
    public int strStr(String haystack, String needle) {//未完成
        Map<String,Integer> table = new HashMap<>();
        char[] P = haystack.toCharArray(), T = needle.toCharArray();
        int n = P.length, m = T.length;
        for(int i=0;i<26;i++)table.put(""+(char)(96+i), m);
        for(int j=0;j<m-2;j++){
            table.remove(""+T[j], m);
            table.put(""+T[j], m-1-j);
        }
        int i = m-1;
        while(i <= n-1){
            int k = 0;
            while(k <= m-1 && P[m-1-k] == T[i-k]){
                k++;
                if(k==m)return i-m+1;
                else i+=table.get(""+T[i]);
            }
        }
        return -1;
    }
    
    public static int zerooo(){
        return 0;
    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while(p1!=p2){
            p1 = p1 == null? p1 = headB: p1.next;
            p2 = p2 == null? p2 = headA: p2.next;
        }
        return p1;
    }
    
    public int removeDuplicates(int[] nums) {
        if(nums.length == 1) return 1;
        int i=0;
        for(int j=1; j<nums.length; j++){
            if(nums[i]!=nums[j]){
                i++;
                nums[i]=nums[j];
            }
        }
        return ++i;
    }
    
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int n = nums.length;
        int mid = n/2;
        for (int i=0;i<mid;i++){
            int m = nums[i]+nums[n-i-1];
            if(m>max)max=m;
        }
        return max;
    }
    
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int l=0,ans = 1;
        long total = 0;
        for(int r=1;r<nums.length;++r){
            total += (long) (nums[r] - nums[r-1]) * (r - l);
            while (total > k){
                total -= nums[r] - nums[l];
                ++l;
            }
            ans = Math.max(ans, r-l+1);
        }
        return ans;
    }
    
    public boolean isPalindrome(int x){
        if (x<0 || (x%10 == 0 && x!=0)) return false;
        int rever = 0;
        while(x>rever){
            rever = rever*10 + x%10;
            x /= 10;
        }
        return x == rever || x == rever/10;
    }
    
    public int romanToInt(String s) {
        char[] ss = s.toCharArray();
        int ans=0;
        for(int i=0;i<s.length();i++){
            switch(ss[i]){
                case 'I':
                    ans+=1;
                    break;
                case 'V':
                    ans+=5;
                    break;
                case 'X':
                    ans+=10;
                    break;
                case 'L':
                    ans+=50;
                    break;
                case 'C':
                    ans+=100;
                    break;
                case 'D':
                    ans+=500;
                    break;
                case 'M':
                    ans+=1000;
                    break;
            }
            if(i > 0){
                if((ss[i]=='V' || ss[i]=='X')&&ss[i-1]=='I')ans -= 2;
                if((ss[i]=='L' || ss[i]=='C')&&ss[i-1]=='X')ans -= 20;
                if((ss[i]=='D' || ss[i]=='M')&&ss[i-1]=='C')ans -= 200;
            }
        }
        return ans;
    }
    
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int count=0;
        while(right>left){
            int mid = (left+right)/2;
            if(nums[mid]>=target)right=mid;
            if(nums[mid]<target)left=mid+1;
        }
        while(left<nums.length&&nums[left++]==target)
            count++;
        return count;
    }
    
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for(int i=1;i<arr.length;i++){
            if ((arr[i]-arr[i-1])>=1) {
                arr[i]=arr[i-1]+1;
            }
        }
        return arr[arr.length-1];
    }
    
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int[] position={-1,-1};
        if(nums == null || nums.length == 0)return position;
        while(right>left){
            int mid = (left+right)/2;
            if(nums[mid]>=target)right=mid;
            if(nums[mid]<target)left=mid+1;
        }
        position[0]=left;
        while(left<nums.length&&nums[left++]==target)
            position[1]=left-1;
        if(position[0] > position[1]){
            position[0]=-1;
            position[1]=-1;
        }
        return position;
    }
    
    public int lengthOfLongestSubstring1(String s) {
        char[] ss = s.toCharArray();
        String ls = "";
        int length=0, maxlength=0;
        //char pre = ' ';
        if(s.length()!=0){
            for(int i=0;i<ss.length;i++){
                //pre = ss[i];
                if(i==0){
                    ls+="" + ss[0];
                    length++;
                }else if(ls.contains(""+ss[i])){
                    int j = ls.indexOf(ss[i])+1;
                    if(j<ls.length()){
                        ls = ls.substring(j,ls.length());
                        ls+="" + ss[i];
                    }else{
                        ls = "" + ss[i];
                    }
                    length=ls.length();
                    if(length>maxlength)maxlength=length;
                    //System.out.print(2+" "+j+"   ");
                }else if(!ls.contains(""+ss[i])){
                    ls+="" + ss[i];
                    length++;
                    if(length>maxlength)maxlength=length;
                    //System.out.print(1+"  ");
                }
                
                //System.out.println(ss[i]+","+ls+","+ls.contains(""+ss[i])+","+length+",ss"+ls.length());
            }
        }
        return maxlength>length?maxlength:length;
    }
    
    public int reverse(int x) {
        long ans = 0;
        while(x!=0){
            ans=ans*10+x%10;
            x/=10;
        }
        return (int)ans==ans?(int)ans:0;
    }
    
    public List<List<Integer>> getSkyline(int[][] buildings) {
        //太难了写不出来 以后再说8
        return null;
    }
    
    public int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
   
    }
    
    
    public int majorityElement(int[] nums) {
        int count=0;//创建计数器；
        int recNum=0;//创建当前计数的数字；
        for(int num:nums){
            if(count==0){
                recNum = num;
            }
            if(num==recNum && count>=0){
                count++;
            }else if (num!=recNum && count>0){
                count--;
            }
        }
        count = 0;//验证答案是否正确,避免产生该算法return最后一个数的情况
        for(int num:nums){
            if(num==recNum){
                count++;
            }
        }
        return count * 2>nums.length?recNum:-1;
    }
    
    public int[] twoSum(int[] nums, int target) {
        int anotherNum=0;
        Map<Integer,Integer> findNums = new HashMap();//一个以数组第i位的值为key，index为value的映射。
        for(int i=0;i<nums.length;i++){
            anotherNum=target-nums[i];
            if(findNums.containsKey(anotherNum)){//只能用containsKey来找index，如果用index设置为value则无法查找。
                return new int[] {findNums.get(anotherNum),i};
            }
            findNums.put(nums[i], i);
        }
        return new int[0];
    }
    
    public boolean isMatch(String s, String p) {
        char[] lg;
        char[] st;
        
        if(p.length()>=s.length()){
            st = s.toCharArray();
            lg = p.toCharArray();
        }else{
            st = p.toCharArray();
            lg = s.toCharArray();
    }
        
        if(lg==st){
            return true;
        }else{
            int i=0;
            for(char j:st){
                
            }
        }
        return false;
    }
    
    public int countPairs(int[] d) {
        final int Mod=1000000007;
        int max=0,pair=0;
        for(int i:d){
            max=Math.max(max,i);
            }
        max*=2;
        Map<Integer,Integer> map=new HashMap();
        for(int i=0;i<d.length;i++){
            for(int sum=1;sum<=max;sum<<=1){
                int count = map.getOrDefault(sum-d[i],0);
                pair=(pair+count)%Mod;
            }
            map.put(d[i], map.getOrDefault(d[i],0)+1);
        }
        printHashMap(map);
        System.out.println(pair);
        return pair;
    }

    public static void printArray(int[] nums){
        int i;
        for(i=0;i<nums.length;i++){
            if(i==0){System.out.print("["+nums[i]+",");}
            else if(i==(nums.length-1)){System.out.println(nums[nums.length-1]+"]");}
            else{System.out.print(nums[i]+",");}
        }
            System.out.print("");
    }
    
    public int numSubarraysWithSum(int[] nums, int goal) {
        int[] presum = new int[nums.length + 1];
        int ans = 0, sum = 0;
        for (int i:nums){
            presum[sum]++;//save presum of nums. Hint: nums[i] is 0 or 1;
            sum += i;
            if((sum-goal) >= 0)ans += presum[sum-goal];//sum-goal < 0 is invalid
        }
        return ans;
    }
    
    
    /**
     *
     * @param map
     */
    public void printHashMap(Map map){
        Set<Map.Entry> ms=map.entrySet();
        ms.forEach((entry) -> {
            System.out.println(entry.getKey()+","+entry.getValue());
        });
    }
    
    public int[] findErrorNums(int[] nums) {
        int[] count = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            count[nums[i]-1]++;
        }
        int[] sol = new int[2];
        for(int i=0;i<nums.length;i++){
            if(count[i]==2)sol[0]=i+1;
            if(count[i]==0)sol[1]=i+1;
        }
        return sol;
    }
    
}
