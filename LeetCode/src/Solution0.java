
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10174
 */
public class Solution0 {
    
    public static void main(String[] args) {
        int[] nums = {};
        System.out.println(2^4);
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        final int maxV = Integer.MAX_VALUE/2 ;
        int[][] graph = new int[n][n];
        for(int i=0;i<n;i++)
            Arrays.fill(graph[i],maxV);
        
        for(int[] t:times){
            int x= t[0] - 1,y = t[1] - 1;//每个节点编号减小， 这样节点就位于[0, n-1]的范围，便于计算。
            graph[x][y] = t[2];//x为源点， y为目标点， t[2]为它们之间的距离。
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, maxV);//Dijkstra算法， 初始化所有距离为无穷
        dist[k-1] = 0;//k为出发点，由此遍历全图
        boolean[] used = new boolean[n];//已经扫描过了。
        for(int i=0;i<n;++i){
            int x = -1;
            for(int y=0;y<n;++y)
                if(!used[y] && (x == -1|| dist[y] < dist[x]))
                    x=y;
            used[x] = true;
            for(int y=0;y<n;++y)
                dist[y] = Math.min(dist[y], dist[x]+graph[x][y]);
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == maxV? -1 : ans;
    }
    
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char a: s.toCharArray()){
            if(a == '(')stack.push(')');
            else if(a == '[')stack.push(']');
            else if(a == '{')stack.push('}');
            else if(stack.isEmpty() || a!=stack.pop())return false;
        }
        return stack.isEmpty();
    }
    
    public int titleToNumber(String s) {
        char[] charArray = s.toCharArray();
        int res = 0;
        for(int i = 0; i < charArray.length; i++)
            res = res*26 + (charArray[i] - 'A' + 1);
        return res;
    }
    
        public class Tree{
            
            public int sumRootToLeaf(TreeNode root) {
                if(root == null)return 0;
                return helper(0, root);
            }
            int helper(int ans, TreeNode node){
                if(node == null)return 0;
                ans = ans*2 + node.val;//二进制乘以二
                if(node.left == null && node.right == null)
                    return ans;
                return helper(ans, node.left) + helper(ans, node.right);
            }
            
            public boolean hasPathSum(TreeNode root, int targetSum) {
                if(root == null)return false;
                if(root.left == null && root.right == null)//leaf node
                    return targetSum - root.val == 0;
                return hasPathSum(root.left, targetSum - root.val)||hasPathSum(root.right, targetSum - root.val);
            }
            
            public TreeNode invertTree(TreeNode root) {
                if(root == null)return null;
                
                TreeNode rightTree = root.right;
                root.right = invertTree(root.left);
                root.left = invertTree(rightTree);
                return root;
            }
            
            public boolean isSymmetric(TreeNode root) {
                if(root == null)return true;
                return sym(root.left, root.right);
            }
            boolean sym(TreeNode l, TreeNode r){
                if(l == null && r == null)return true;
                if(l != null && r == null)return false;
                if(l == null && r != null)return false;
                if(l.val == r.val && sym(l.left, r.right) && sym(l.right, r.left))
                    return true;
                return false;
            }

            public int maxDepth(TreeNode root) {
                return root == null?0:Math.max(maxDepth(root.left), maxDepth(root.right))+1;
            }
            public List<List<Integer>> levelOrder(TreeNode root) {
                if(root == null)return new ArrayList<>();
                List<List<Integer>> ans = new ArrayList<>();
                Queue<TreeNode> queue = new LinkedList<TreeNode>();
                
                queue.add(root);
                while(!queue.isEmpty()){
                    int count = queue.size();
                    List<Integer> row = new ArrayList<>();
                    while(count > 0){
                    TreeNode node = queue.poll();
                    row.add(node.val);
                    if(node.left != null)queue.add(node.left);
                    if(node.right != null)queue.add(node.right);
                    count--;
                    }
                    ans.add(row);
                }
                return ans;
            }
            
            public List<Integer> postorderTraversal(TreeNode root) {
                List<Integer> post = new ArrayList<>();
                if(root == null)return post;
                List<Integer> l = postorderTraversal(root.left);
                List<Integer> r = postorderTraversal(root.right);
                post.addAll(l);
                post.addAll(r);
                post.add(root.val);
                return post;
        }
            
            public List<Integer> inorderTraversal(TreeNode root) {
                List<Integer> inor = new ArrayList<>();
                if(root == null)return inor;
                List<Integer> l = inorderTraversal(root.left);
                List<Integer> r = inorderTraversal(root.right);
                inor.addAll(l);
                inor.add(root.val);
                inor.addAll(r);
                return inor;
        }
            
            public List<Integer> preorderTraversal(TreeNode root) {
                List<Integer> pre = new ArrayList<>();
                if(root == null)return pre;
                pre.add(root.val);
                List<Integer> l = preorderTraversal(root.left);
                List<Integer> r = preorderTraversal(root.right);
                pre.addAll(l);
                pre.addAll(r);
                return pre;
        }
            
        int N = 1010, M = N * 2;
        int[] he = new int[N], e = new int[M], ne = new int[M];
        int idx;
       /* add存图方式：一种在图论中十分常见的存图方式。
        *   idx：对边进行编号。
        *   he：储存是某个加点所对应的边集合（链表）的头节点。
        *   ne：由于以链表形式存边，该数组用于找下一条边。
        */
        void add(int a, int b) {
            e[idx] = b;//e[idx]对应一条边
            ne[idx] = he[a];//ne[idx]下一个连接结点索引
            he[a] = idx++;//he的下表表示节点值为索引idx
        }
        boolean[] vis = new boolean[N];
        public List<Integer> distanceK(TreeNode root, TreeNode t, int k) {
            List<Integer> ans = new ArrayList<>();
            Arrays.fill(he, -1);
            dfs(root);
            vis[t.val] = true;
            find(t.val, k, 0, ans);
            return ans;
        }
        void find(int root, int max, int cur, List<Integer> ans) {
            if (cur == max) {
                ans.add(root);
                return ;
            }
            for (int i = he[root]; i != -1; i = ne[i]) {//最后i = -1说明没有下一个结点了。
                int j = e[i];
                if (!vis[j]) {
                    vis[j] = true;
                    find(j, max, cur + 1, ans);
                }
            }
        }
        void dfs(TreeNode root) {
            if (root == null) return;
            if (root.left != null) {
                add(root.val, root.left.val);
                add(root.left.val, root.val);
                dfs(root.left);
            }
            if (root.right != null) {
                add(root.val, root.right.val);
                add(root.right.val, root.val);
                dfs(root.right);
            }
        }
    
    }
        
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null)return head;
            ListNode header = new ListNode(-1);
            header.next = head;
            ListNode cur = head;
            while(cur.next != null){
                ListNode temp = cur.next;
                if(cur.val == temp.val)
                    cur.next = cur.next.next;
                else
                    cur = cur.next;
            }
            return header.next;
    }
        
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        
        while (cur != null){
            ListNode nextTemp = cur.next;
            cur.next =  prev;
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }
    
    public ListNode removeElements(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while(cur.next != null){
            if(cur.next.val == val ){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return header.next;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode ans = head;
        while(l1 != null && l2!=null){
            if(l1.val < l2.val){
                ans.next = l1;
                ans = ans.next;
                l1 = l1.next;
            }else{
                ans.next = l2;
                ans = ans.next;
                l2 = l2.next;
            }
        }
        if(l1 == null) ans.next = l2;
        else ans.next = l1;
        return head.next;
    }
    
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode quick = head.next;
        while(slow!=quick){
            if(quick == null || quick.next == null)return false;
            slow = slow.next;
            quick = quick.next.next;
        }
        return true;
    }
    
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] rans = new int[26];
        int[] maga = new int[26];
        for(int i=0;i<ransomNote.length();i++)rans[ransomNote.charAt(i)-'a']++;
        for(int i=0;i<magazine.length();i++)maga[magazine.charAt(i)-'a']++;
        for(int i=0;i<26;i++)if(rans[i]>maga[i])return false;
        return true;
    }
    
    public boolean isAnagram(String ransomNote, String magazine) {
        int[] rans = new int[26];
        int[] maga = new int[26];
        for(int i=0;i<ransomNote.length();i++)rans[ransomNote.charAt(i)-'a']++;
        for(int i=0;i<magazine.length();i++)maga[magazine.charAt(i)-'a']++;
        for(int i=0;i<26;i++)if(rans[i]!=maga[i])return false;
        return true;
    }
    
    public int firstUniqChar1(String s) {
        Map<Character, Integer> findAns = new HashMap<>();
        char[] sent = s.toCharArray();
        for(char item : sent){
            if(findAns.containsKey(item))findAns.put(item, findAns.getOrDefault(item,1)+1);
            findAns.putIfAbsent(item, 1);
        }
        for(int i=0;i<sent.length;i++){
            if(findAns.get(sent[i])==1)return i;
        }
        return -1;
    }
    
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i)-'a']++ ;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i)-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
    
    int TreeSecMinans = -1;
    
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return TreeSecMinans;
    }
    
    void dfs(TreeNode root, int val){
        if(root == null) return;
        if(root.val !=val){
            if(TreeSecMinans == -1) TreeSecMinans = root.val;
            else TreeSecMinans = Math.min(TreeSecMinans, root.val);
            return;
        }
        dfs(root.left, val);
        dfs(root.right, val);
    }
    
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        for (int i = 0; i < m; i++) {//第一行是否有零
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {//第一列是否有0
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        for (int i = 1; i < m; i++) {//第一行第一列作为标志位
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {//开始放0
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
    
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        for(int i=0;i<numRows;++i){
            List<Integer> row = new ArrayList<Integer>();
            for(int j=0;j<=i;++j){
                if( j == 0 || j == i) row.add(1);
                else row.add(ans.get(i-1).get(j-1) + ans.get(i-1).get(j));
            }
            ans.add(row);
        }
        
        return ans;
    }
    
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> rec = new HashMap<Integer,Integer>();
        for(int i=0; i<target.length; i++)rec.put(target[i], i);
        
        List<Integer> n = new ArrayList<Integer>();//used to created new ArrayList
        
        for(int i:arr){
            if(rec.containsKey(i)){
                int idx = rec.get(i);
                int item = binarySearch(n, idx);
                if(item != n.size())n.set(item, idx);
                else n.add(idx);
            }
        }
        return target.length - n.size();
    }
    
    public int binarySearch(List<Integer> n, int target){
        int size = n.size();
        if(size == 0||n.get(size-1) < target) return size;
        int low = 0, high = size - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (n.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0)return 0;
        int max=0;
        int before=0;
        for(int num:nums){
            
        }
        return max;
    }
    
    public Node copyRandomList(Node head) {
        if (head == null)return null;
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while(node!=null){
            Node copy = new Node(node.val, null, null);
            map.put(node, copy);
            node = node.next;
        }
        node = head;
        while(node!=null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
    
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int[][] ans = new int[r][c];
        int matIn = mat[0].length;
        
        if(mat.length*mat[0].length != r*c)return mat;
        
        int indexOut = 0, indexIn = 0;
        for (int[] an : ans) {
            for (int j = 0; j < an.length; j++) {
                if(indexIn < matIn){
                    an[j] = mat[indexOut][indexIn];
                    indexIn++;
                }else{
                    indexOut++;
                    indexIn=0;
                    an[j] = mat[indexOut][indexIn];
                    indexIn++;
                }
            }
        }
        return ans;
    }
    
    public int maxProfit(int[] prices) {
        int max=0;
        int minBefore=prices[0];
        for(int i: prices){
            if((i-minBefore) > max)max = i-minBefore;
            if(i<minBefore)minBefore=i;
        }
        return max;
    }
    
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] ans = new int[Math.min(nums1.length, nums2.length)];
        int index1=0,index2=0,index=0;
        while(index1<nums1.length && index2<nums2.length){
            if(nums1[index1] > nums2[index2])index2++;
            else if(nums1[index1] < nums2[index2])index1++;
            else{
                ans[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(ans, 0, index);
    }
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
    
    public class sudoku{

        private boolean[][] line = new boolean[9][9];//储存每一行是否存在1-9
        private boolean[][] column = new boolean[9][9];//储存每一列是否存在1-9
        private boolean[][][] block = new boolean[3][3][9];//储存每个九宫格是否存在1-9
        private boolean valid = false;
        private List<int[]> spaces = new ArrayList<int[]>();//记录哪些格子需要填满

        public boolean isValidSudoku(char[][] board) {
            boolean[][] rows = new boolean[9][9];
            boolean[][] cols = new boolean[9][9];
            boolean[][] boxs = new boolean[9][9];
            
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    if(board[i][j]!='.'){
                        int num = board[i][j] -'1';
                        int boxIndex = (i/3)*3 + (j/3);
                        if(rows[i][num]  || cols[j][num] || boxs[boxIndex][num])
                        return false;
                        rows[i][num] = true;
                        cols[j][num] = true;
                        boxs[boxIndex][num] = true;
                    }
                }
            }
            
            return true;
    }
        
        public void solveSudoku(char[][] board){
            for(int i=0; i<9;i++){
                for(int j=0; j<9;j++){
                    if(board[i][j] == '.')spaces.add(new int[]{i,j});
                    else{
                        int digit = board[i][j] - '0' - 1;//字符转整形
                        line[i][digit] = column[j][digit] = block[i/3][j/3][digit] = true;
                        //表示这个位置已经有数字
                    }
                }
            }
            dfs(board, 0);//遍历结束，开始填数字，用dfs算法。
        }


        public void dfs(char[][] board, int pos){
            if(pos == spaces.size()){//所有的格子都被填满了！直接return结束循环，不然无尽。
                valid =true;
                return;
            }
            int[] space = spaces.get(pos);//找到第一个空位的位置，之后填满
            int i = space[0], j = space[1];//横纵坐标
            for (int digit = 0; digit < 9 && !valid; ++digit) {//检测没有被填的数字
                if(!line[i][digit] && !column[j][digit] && !block[i/3][j/3][digit]){
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                    board[i][j] = (char) (digit + '0' + 1);//整形转字符
                    dfs(board, pos + 1);//向后移动一位空位
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
                }
            }
        }
        
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        List<Integer> output = new ArrayList<Integer>();//ans里每一个答案的载体
        for(int num:nums) output.add(num);
        
        backtrack(nums.length, output, ans, 0);
        return ans;
    }
    
    public void backtrack(int n, List<Integer> input, List<List<Integer>> ans, int first){
        //base case
        if(first == n)ans.add(new ArrayList<Integer>(input));//将循环的尽头放入ans中
        //loop case
        for(int i = first; i<n; i++){
            Collections.swap(input, first, i);//每次循环交换不同的i位
            backtrack(n, input, ans, first+1);//针对这个i位开始循环
            Collections.swap(input, first, i);//结束每一次循环前将数字放回原位，方便下一次交换新的i的位置。
        }
    }
    
    public boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges,(o1,o2)->o1[0]-o2[0]);
        for(int[] nums:ranges){
            if(nums[0]>left)return false;
            if(nums[1]>=left)left = nums[1]+1;
        }
        return left>right;
    }
    
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> noDup = new HashSet<>();
        for(int num: nums)
            if(!noDup.add(num))return true;//HashSet不可重复元素，add为false直接如return
        return false;
    }
    
    public int maxSubArray1(int[] nums) {//动态规划
        int sum=0, ans=nums[0];
        for(int num:nums){
            if(sum>0)sum+=num;
            else sum = num;
            ans = Math.max(ans, sum);
        }
        return ans;
    }
    
}