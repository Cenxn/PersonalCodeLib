
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10174
 */
public class Solution1 {
    public static void printArray(int[] nums){
        System.out.print("[");
        for(int num:nums)System.out.print(", "+num);
        System.out.println("]");
    }
    
    public static void main(String args[]){
        Solution1 sol = new Solution1();
        int[] nums = {0,1,2,2,3,0,4,2};
        sol.removeElement(nums, 2);
    }
    
    public int searchInsert(int[] nums, int target) {
        int right = nums.length-1, left = 0;
        while(right >= left){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target)right = mid-1;
            else if(nums[mid] < target)left = mid+1;
            else return mid;
        }
        return right+1;
    }
    
    public int removeElement(int[] nums, int val) {
        int res = 0;
        for(int i=0;i<nums.length;i++)
            if(nums[i] != val){
                nums[res] = nums[i];
                res++;
            }
        return res;
    }
    
    
    
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int h_idx = 0;
        int res = 0;
        
        for(int house:houses){
            while(h_idx < heaters.length && heaters[h_idx] < house){
                //find the right most heater
                h_idx++;
            }
            if(h_idx == 0){
                res = Math.max(res, heaters[h_idx] - house);
            }else if(h_idx == heaters.length){
                //the last heater
                return Math.max(res, houses[houses.length - 1] - heaters[h_idx - 1]);
            }else{
                //idx != 0 && != length, 所以房屋两侧都有热水器，取距离最小者。
                res = Math.max(res, Math.min(heaters[h_idx] - house, house - heaters[h_idx - 1]));
            }
        }
        return res;
    }
    
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for(char a: s.toCharArray()){
            count[a]++;
        }
        int ans = 0;
        for(int i :count){
            ans +=i/2 *2;
            if (i % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
    
    public String addStrings(String num1, String num2) {
        StringBuilder s = new StringBuilder();
        int i = num1.length()-1, j = num2.length()-1;
        int cur = 0, addOne = 0;
        while(i>=0 || j>=0){
            cur = 0;
            if(i>=0)cur += num1.charAt(i--) - '0';
            if(j>=0)cur += num2.charAt(j--) - '0';
            if(addOne == 1){
                cur += 1;
                addOne = 0;
            }
            if(cur>=10){
                cur = 10 % cur;
                addOne = 1;
            }
            s.append(cur);
        }
        if (addOne == 1)s.append(1);
        return s.reverse().toString();
    }
    
    public List<Integer> getRow(int rowIndex) {
        long cur = 1;
        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        for(int i=0;i<=rowIndex;i++){
            ans.add((int)cur);
            cur = cur * (rowIndex - i)/(i + 1);
        }
        return ans;
    }
    
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1 || intervals == null)return intervals;
        
        //Arrays.sort(arr,(a,b)->a[0]-b[0]);
        
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[]a, int[] b){
                return a[0]-b[0];
            }
        });
        
        List<int[]> ans = new ArrayList<>();
        int n = intervals.length, i=0;
        
        while(i<n){
            int l = intervals[i][0];
            int r = intervals[i][1];
            while(i < n-1 && r >= intervals[i+1][0]){
                r = Math.max(r, intervals[i+1][1]);
                i++;
            }
            ans.add(new int[]{l, r});
            i++;
        }
        return ans.toArray(new int[ans.size()][2]);
    }
    
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums.length < 3|| nums == null)return 0;
        int ans = 0;
        int add = 0;
        for(int i=2;i<nums.length;i++){
            if(nums[i-1]-nums[i] == nums[i-2]-nums[i-1]){
                ans += ++add;
            }else{
                add = 0;
            }
        }
        return ans;
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        
        if(nums == null || nums.length<3)return ans;
        
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]>0)break;
            if(i>0 && nums[i] == nums[i-1])continue;
            int target = -nums[i];
            
            int left = i+1, right = nums.length -1;
            while(left < right){
                if(nums[left] + nums[right] == target){//避免重复
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    
                    left++;right--;
                    
                    while (left < right && nums[left] == nums[left - 1]) left++;//避免重复
                    while (left < right && nums[right] == nums[right + 1]) right--;
                    
                } else if(nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
    
    public int majorityElement(int[] nums) {
        int count = 0;
        for(int i:nums){
            if(i == nums[0])
                count++;
            else
                count--;
            if(count == -1){
                nums[0] = i;
                count = 1;
            }
        }
        return nums[0];
    }
    
    public int singleNumber(int[] nums) {
        if(nums.length == 1)return nums[0];
            int n = 0;
            for(int i:nums){
                n = n ^ i;
            }
            return n;
    }
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int n =graph.length;
        int[] color = new int[n];
        
        for(int i=0; i<n; i++)
            if(safe(graph, color,i))
                ans.add(i);
        
        return ans;
    }
    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }
    
    TreeNode ansAnce = new TreeNode();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)return root;
        lca(root,p,q);
        return ansAnce;
    }
    public void lca(TreeNode root, TreeNode p, TreeNode q){
        if((p.val - root.val)*(q.val - root.val) <= 0){//二叉树特性
            ansAnce = root;
        } else if(p.val > root.val && q.val > root.val){
            lca(root.right, p, q);
        } else {
            lca(root.left, p, q);
        }
    }
    
    Set < Integer > set = new HashSet();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
    
    TreeNode max = new TreeNode();
    public boolean isValidBST(TreeNode root) {
        if(root == null)return true;
        boolean left = isValidBST(root.left);
        if(!left)return false;
        if(max != null && root.val <= max.val)return false;
        max = root;
        boolean right = isValidBST(root.right);
        return right;
    }
    
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)return new TreeNode(val);
        if(root.val < val){
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
    
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)return null;
        if(root.val == val)return root;
        return root.val>val?searchBST(root.left, val):searchBST(root.right, val);
    }
    
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = i;
            for (int j = i + 1; j < n; ++j) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    ++k;
                }
                ans += Math.max(k - j, 0);
            }
        }
        return ans;
    }
    
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
