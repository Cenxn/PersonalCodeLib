
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10174
 */
public class Solution {
    
    public String replaceSpaces(String S, int length) {
        StringBuilder res = new StringBuilder();
        char[] s = S.trim().toCharArray();
        int count = s.length;
        for(int i=0;i<count;i++){
            if(s[i] != ' ')
                res.append(s[i]);
            else
                res.append("%20");
        }
        while(count < length){
            res.append("%20");
            count++;
        }
        return res.toString();
    }
    
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440)return 0;
        int min = Integer.MAX_VALUE;
        int[] times = new int[timePoints.size()];
        for(int i=0;i<timePoints.size();i++)
            times[i] = Integer.parseInt(timePoints.get(i).substring(0, 2))*60 + Integer.parseInt(timePoints.get(i).substring(3));
        Arrays.sort(times);
        for(int i=1;i<times.length;i++){
            min = Math.min(min, times[i] - times[i-1]);
        }
        return Math.min(min, times[0] + 1440 - times[times.length - 1]);//因为00：00是相当于24：00
    }
    
    public boolean isUnique(String astr) {
        // only lowercase letters
        if (astr == null)return true;
        if (astr.length() > 26)return false;
        int[] count = new int[26];
        for(int i=0; i<astr.length();i++){
            if (count[astr.charAt(i) - 'a'] == 0)count[astr.charAt(i) - 'a']++;
            else return false;
        }
        return true;
    }
    
    public boolean CheckPermutation(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
