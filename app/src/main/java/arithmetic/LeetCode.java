package arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class LeetCode {

    public static void main(String[] args) {
        int[] arrs = {4, 4, 4, 5, 6};
        boolean i = new Solution_2369().validPartition(arrs);
        System.out.println(i);
    }

    static class Solution_232{
        class MyQueue {
            private Stack<Integer> stack1 = new Stack<Integer>();
            private Stack<Integer> stack2 = new Stack<Integer>();

            public MyQueue() {

            }

            public void push(int x) {
                stack2.push(x);
            }

            public int pop() {
                if (stack1.size() <= 0) {
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                }
                return stack1.pop();
            }

            public int peek() {
                if (stack1.size() <= 0) {
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                }
                return stack1.peek();
            }

            public boolean empty() {
                return stack1.empty() && stack2.empty();
            }
        }
    }

    /**
     * 2369. 检查数组是否存在有效划分
     */
    static class Solution_2369 {
        // 使用动态规划的解决方案
        // 长度位n的数组是否存在有效划分的结果依赖
        // 长度n-2的数组划分结果
        // || 长度n-3的数组划分结果
        public boolean validPartition(int[] nums) {
            int n = nums.length + 1;
            boolean[] cacheArr = new boolean[n];
            cacheArr[0] = true;
            for (int i = 1; i < n; i++) {
                if (i - 2 >= 0 && cacheArr[i - 2] && nums[i - 1] == nums[i - 2]) {
                    cacheArr[i] = true;
                } else if (i - 3 >= 0 && cacheArr[i - 3] && nums[i - 1] == nums[i - 2] && nums[i - 1] == nums[i - 3]) {
                    cacheArr[i] = true;
                } else if (i - 3 >= 0 && cacheArr[i - 3] && nums[i - 1] == nums[i - 2] + 1 && nums[i - 1] == nums[i - 3] + 2) {
                    cacheArr[i] = true;
                }
            }
            return cacheArr[nums.length];
        }

        // 超时了
        public boolean validPartition_planB(int[] nums) {
            // 2个相同 3个相同 3连续
            return dfs(nums, 0);
        }

        private boolean dfs(int[] nums, int startIndex) {
            if (startIndex == nums.length) {
                // 一组有效遍历结束
                return true;
            }
            if (startIndex == nums.length - 1 || startIndex < 0) {
                return false;
            }
            if (nums[startIndex] == nums[startIndex + 1]) {
                System.out.println("[" + nums[startIndex] + "," + nums[startIndex + 1] + "]");
                // 找到2个相同,继续往下
                if (dfs(nums, startIndex + 2)) {
                    return true;
                }
            }

            if (startIndex + 2 < nums.length && nums[startIndex] == nums[startIndex + 1] && nums[startIndex] == nums[startIndex + 2]) {
                System.out.println("[" + nums[startIndex] + "," + nums[startIndex + 1] + "," + nums[startIndex + 2] + "]");
                // 找到3个相同,继续往下
                if (dfs(nums, startIndex + 3)) {
                    return true;
                }
            }

            if (startIndex + 2 < nums.length && nums[startIndex] + 1 == nums[startIndex + 1] && nums[startIndex] + 2 == nums[startIndex + 2]) {
                System.out.println("[" + nums[startIndex] + "," + nums[startIndex + 1] + "," + nums[startIndex + 2] + "]");
                //找到三个连续,继续往下
                if (dfs(nums, startIndex + 3)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 2581. 统计可能的树根数目
     */
    static class Solution_2581 {

        public int rootCount(int[][] edges, int[][] guesses, int k) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int[] guess : guesses) {
                map.put(guess[0] + "," + guess[1], 1);
            }
            int n = edges.length + 1;

            List<Integer>[] G = new List[n];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                int i = edge[0];
                int j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }

            int result = 0;

            // 广度遍历获取动态规划第一步结果,初始节点为0时
            int kCount = df_search(G, 0, -1, map);
            if (kCount >= k) {
                result++;
            }

            // 从0节点开始进行广度偏移
            result += df_move(G, 0, -1, map, kCount, k);

            return result;
        }

        private int df_move(List<Integer>[] g, int start, int exclude, HashMap<String, Integer> map, int curKCount, int k) {
            int result = 0;
            for (Integer i : g[start]) {
                if (i != exclude) {
                    String oldKey = start + "," + i;
                    String newKey = i + "," + start;
                    boolean containsOldKey = map.containsKey(oldKey);
                    boolean containsNewKey = map.containsKey(newKey);
                    int newKCount = curKCount;
                    if (containsOldKey && !containsNewKey) {
                        newKCount -= 1;
                    } else if (!containsOldKey && containsNewKey) {
                        newKCount += 1;
                    }
                    if (newKCount >= k) {
                        result += 1;
                    }
                    result += df_move(g, i, start, map, newKCount, k);
                }
            }
            return result;
        }

        private int df_search(List<Integer>[] g, int start, int exclude, HashMap<String, Integer> map) {
            int result = 0;
            for (Integer i : g[start]) {
                if (i != exclude) {
                    String key = start + "," + i;
                    if (map.get(key) != null) {
                        result += 1;
                    }
                    result += df_search(g, i, start, map);
                }
            }
            return result;
        }

        /**
         * 时间上不合格
         *
         * @param edges
         * @param guesses
         * @param k
         * @return
         */
        public int rootCount_PlanB(int[][] edges, int[][] guesses, int k) {
            int n = edges.length + 1;

            List<Integer>[] G = new List[n];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                int i = edge[0];
                int j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }

            int result = 0;
            int[] floor = new int[n];
            for (int i = 0; i < n; i++) {
                int count = 0;
                //构建节点层数关系
                bfs(G, i, -1, floor, 1);
                for (int[] guess : guesses) {
                    if (floor[guess[0]] < floor[guess[1]]) {
                        count++;
                        if (count >= k) {
                            result++;
                            break;
                        }
                    }
                }
            }

            return result;
        }

        private void bfs(List<Integer>[] g, int root, int exclude, int[] floor, int curFloor) {
            floor[root] = curFloor;
            for (Integer i : g[root]) {
                if (i != exclude) {
                    bfs(g, i, root, floor, curFloor + 1);
                }
            }
        }


    }

    /**
     * 2673. 使二叉树所有路径值相等的最小代价
     * 中等
     * 相关标签
     * 相关企业
     * 提示
     * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。
     * <p>
     * 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。
     * <p>
     * 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。
     * <p>
     * 注意：
     * <p>
     * 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个子节点，且所有叶子节点距离根节点距离相同。
     * 路径值 指的是路径上所有节点的值之和。
     * <p>
     * <p>
     * 输入：n = 7, cost = [1,5,2,2,3,3,1]
     * 输出：6
     * 解释：我们执行以下的增加操作：
     * - 将节点 4 的值增加一次。
     * - 将节点 3 的值增加三次。
     * - 将节点 7 的值增加两次。
     * 从根到叶子的每一条路径值都为 9 。
     * 总共增加次数为 1 + 3 + 2 = 6 。
     * 这是最小的答案。
     */
    static class Solution_2673 {
        public int minIncrements(int n, int[] cost) {
            int result = 0;
            // 计算二叉树层数, 满二叉树层数一定是整数(层数从1开始)
            int floor = (int) (Math.log(n + 1) / Math.log(2));

            // 按层数倒序遍历,广度优先
            for (int curFloor = floor; curFloor > 1; curFloor--) {

                // 计算当前层第一个节点编号
                int i = ((int) Math.pow(2, curFloor - 1));
                int curFloorCount = i;
                for (int j = 0; j < curFloorCount / 2; j++) {
                    int indexLeft = i + 2 * j - 1;
                    int indexRight = i + 2 * j;
                    int parentIndex = (indexRight) / 2 - 1;
                    int abs = Math.abs(cost[indexLeft] - cost[indexRight]);
                    result += abs;
                    cost[parentIndex] += Math.max(cost[indexLeft], cost[indexRight]);
                }
            }
            return result;
        }
    }

    /**
     * 204. 计数质数
     * 尝试过
     * 中等
     * 相关标签
     * 相关企业
     * 提示
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     * <p>
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     * <p>
     * 输入：n = 1
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= n <= 5 * 106
     */
    static class Solution_204 {
        public int countPrimes(int n) {

            // 埃氏筛
            int[] arr = new int[n];
            Arrays.fill(arr, 1);
            int res = 0;
            for (int i = 2; i < n; i++) {
                if (arr[i] == 1) {
                    res++;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            System.out.println("j=" + j + ",n=" + n);
                            arr[j] = 0;
                        }
                    }
                }
            }
            return res;
        }
    }

    /**
     * 2867. 统计树中的合法路径数目
     * 困难
     * 相关标签
     * 相关企业
     * 提示
     * 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
     * <p>
     * 请你返回树中的 合法路径数目 。
     * <p>
     * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
     * <p>
     * 注意：
     * <p>
     * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
     * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
     */
    static class Solution_2867 {
        int N = 100001;
        boolean[] prime = new boolean[N];

        {
            Arrays.fill(prime, true);
            prime[1] = false;
            for (int i = 2; i * i < N; i++) {
                if (prime[i]) {
                    for (int j = i * i; j < N; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }

        public long countPaths(int n, int[][] edges) {


            // 分析每个节点的第一层通路,存储在G中
            List<Integer>[] G = new ArrayList[n + 1];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                int i = edge[0];
                int j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }


            long result = 0;
            int cur = 0;
            List<Integer> seachArr = new ArrayList<>();
            // 存储计算的每个子树的节点数量
            int[] count = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!prime[i]) {
                    continue;
                }

                cur = 0;
                // 开始遍历这个素数的每个子树
                for (Integer j : G[i]) {
                    if (prime[j]) {
                        continue;
                    }

                    if (count[j] == 0) {
                        seachArr.clear();
                        deepFirstSearch(G, j, seachArr, i);
                        for (Integer node : seachArr) {
                            count[node] = seachArr.size();
                        }
                    }
                    result += count[j] * cur;
                    cur += count[j];
                }

                result += cur;
            }
            return result;
        }

        private void deepFirstSearch(List<Integer>[] G, Integer i, List<Integer> seachArr, int pre) {
            seachArr.add(i);
            for (Integer j : G[i]) {
                if (j != pre && !prime[j]) {
                    deepFirstSearch(G, j, seachArr, i);
                }
            }
        }

        public boolean isPrime(int value) {
            if (value <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(value); i++) {
                if (value % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 2. 两数相加
     * 中等
     * 相关标签
     * 相关企业
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    class Solution_2 {

        public class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(0);
            ListNode p = result;
            ListNode p1 = l1;
            ListNode p2 = l2;
            while (p1 != null || p2 != null) {
                int p1Value = p1 == null ? 0 : p1.val;
                int p2Value = p2 == null ? 0 : p2.val;
                int addValue = p.val + p1Value + p2Value;
                p.val = addValue % 10;
                int nextLevelNum = addValue / 10;
                p1 = p1 == null ? null : p1.next;
                p2 = p2 == null ? null : p2.next;
                if (nextLevelNum > 0 || p1 != null || p2 != null) {
                    p.next = new ListNode(nextLevelNum);
                }
                p = p.next;
            }

            return result;
        }
    }

    /**
     * 938. 二叉搜索树的范围和
     * 简单
     * 相关标签
     * 相关企业
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     */
    class Solution_938 {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public int rangeSumBST(TreeNode root, int low, int high) {
            return rangeSumBST_Recursive(root, low, high);
        }

        public int rangeSumBST_Recursive(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }
            if (root.val < low) {
                return rangeSumBST_Recursive(root.right, low, high);
            } else if (root.val <= high) {
                int left = rangeSumBST_Recursive(root.left, low, high);
                int right = rangeSumBST_Recursive(root.right, low, high);
                return root.val + left + right;
            } else {
                return rangeSumBST_Recursive(root.left, low, high);
            }
        }
    }

    /**
     * 2583. 二叉树中的第 K 大层和
     * 中等
     * 相关标签
     * 相关企业
     * 提示
     * 给你一棵二叉树的根节点 root 和一个正整数 k 。
     * <p>
     * 树中的 层和 是指 同一层 上节点值的总和。
     * <p>
     * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
     * <p>
     * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
     */
    class kthLargestLevelSum_2583 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public long kthLargestLevelSum(TreeNode root, int k) {
            HashMap<Integer, Long> result = new HashMap<>();

            kthLargestLevelSum_Recursive(root, 1, result);
            if (result.get(k - 1) == null) {
                return -1;
            }

            // 插入排序
            long[] sortArr = new long[k];
            for (Long value : result.values()) {
                if (value == null) {
                    continue;
                }
                int index = k - 1;
                while (index >= 0 && value > sortArr[index]) {
                    if (index < k - 1) {
                        //do swap
                        sortArr[index + 1] = sortArr[index];
                    }
                    index--;
                }
                index++;
                if (index < k) {
                    sortArr[index] = value;
                }
            }
            return sortArr[k - 1];
        }

        public void kthLargestLevelSum_Recursive(TreeNode root, int curFloor, HashMap<Integer, Long> result) {
            if (root == null) {
                return;
            }
            int curIndex = curFloor - 1;
            Long integer = result.get(curIndex);
            long preValue = integer == null ? 0 : integer;
            result.put(curIndex, preValue + root.val);
            kthLargestLevelSum_Recursive(root.left, curFloor + 1, result);
            kthLargestLevelSum_Recursive(root.right, curFloor + 1, result);
        }
    }

    /**
     * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
     * <p>
     * 如果存在多个答案，您可以返回其中 任何 一个。
     */
    class BuildTree_899 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            HashMap<Integer, Integer> valueMapIndexOfPostorder = new HashMap<>();
            for (int i = 0; i < postorder.length; i++) {
                valueMapIndexOfPostorder.put(postorder[i], i);
            }
            return constructFromPrePostRecursive(valueMapIndexOfPostorder, preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
        }

        private TreeNode constructFromPrePostRecursive(HashMap<Integer, Integer> valueMapIndexOfPostorder, int[] preorder, int[] postorder, int preorder_left, int preorder_right, int postorder_left, int postorder_right) {
            if (preorder_left > preorder_right || postorder_left > postorder_right) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preorder_left]);
            // 还有左树
            if (preorder_left + 1 <= preorder_right) {
                int leftTreeLength = valueMapIndexOfPostorder.get(preorder[preorder_left + 1]) - postorder_left + 1;
                root.left = constructFromPrePostRecursive(valueMapIndexOfPostorder, preorder, postorder, preorder_left + 1, preorder_left + leftTreeLength, postorder_left, postorder_left + leftTreeLength - 1);

                // 还有右树
                if (preorder_left + leftTreeLength + 1 <= preorder_right) {
                    root.right = constructFromPrePostRecursive(valueMapIndexOfPostorder, preorder, postorder, preorder_left + leftTreeLength + 1, preorder_right, postorder_left + leftTreeLength, postorder_right - 1);
                }
            }

            return root;
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     */
    class BuildTree_106 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public TreeNode buildTree_106(int[] inorder, int[] postorder) {
            if (inorder == null || inorder.length == 0) {
                return null;
            }
            HashMap<Integer, Integer> valueIndexInInorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                valueIndexInInorderMap.put(inorder[i], i);
            }
            return buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        }

        public TreeNode buildTreeNodeRecursive(HashMap<Integer, Integer> valueIndexInInorderMap, int[] inorder, int[] postorder, int inorderLeft, int inorderRight, int postorderLeft, int postorderRight) {
            if (inorderLeft > inorderRight || postorderLeft > postorderRight) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorderRight]);
            int indexOfRootInInorder = valueIndexInInorderMap.get(root.val);
            int leftTreeLength = indexOfRootInInorder - inorderLeft;
            root.left = buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, inorderLeft, indexOfRootInInorder - 1, postorderLeft, postorderLeft + leftTreeLength - 1);
            root.right = buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, indexOfRootInInorder + 1, inorderRight, postorderLeft + leftTreeLength, postorderRight - 1);
            return root;
        }
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     */
    class BuildTree_105 {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        public TreeNode buildTree_105(int[] preorder, int[] inorder) {
            HashMap<Integer, Integer> inOrderValueIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inOrderValueIndexMap.put(inorder[i], i);
            }
            return buildTreeRecursive(inOrderValueIndexMap, preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode buildTreeRecursive(HashMap<Integer, Integer> inOrderValueIndexMap, int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            TreeNode root = new TreeNode(preorder[preorder_left]);
            int indexOfRootInInOrderArr = inOrderValueIndexMap.get(root.val);
            int leftTreeLength = indexOfRootInInOrderArr - inorder_left;
            if (leftTreeLength > 0) {
                root.left = buildTreeRecursive(inOrderValueIndexMap, preorder, inorder, preorder_left + 1, preorder_left + 1 + leftTreeLength - 1, inorder_left, indexOfRootInInOrderArr - 1);
            }
            int rightTreeLength = inorder_right - indexOfRootInInOrderArr;
            if (rightTreeLength > 0) {
                root.right = buildTreeRecursive(inOrderValueIndexMap, preorder, inorder, preorder_left + 1 + leftTreeLength - 1 + 1, preorder_right, indexOfRootInInOrderArr + 1, inorder_right);
            }
            return root;
        }

        public TreeNode buildTree_105_planB(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length <= 0) {
                return null;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode result = new TreeNode(preorder[0]);
            stack.push(result);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int newValue = preorder[i];
                TreeNode peek = stack.peek();
                if (peek.val != inorder[inorderIndex]) {
                    TreeNode cur = new TreeNode(newValue);
                    peek.left = cur;
                    stack.push(cur);
                    continue;
                }

                TreeNode lastPop = null;
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    lastPop = stack.pop();
                    inorderIndex++;
                }
                TreeNode newValueNode = new TreeNode(newValue);
                lastPop.right = newValueNode;
                stack.push(newValueNode);
            }
            return result;
        }
    }

    /**
     * 9. 回文数
     * 简单
     * 相关标签
     * 相关企业
     * 提示
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 例如，121 是回文，而 123 不是。
     *
     * @param x
     * @return
     */
    public boolean isPalindrome_9(int x) {
        String s = x + "";
        int distance = s.length() / 2;
        for (int i = 0; i < distance; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome_9_planB(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }

        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }

        return x == reverseNum || x == reverseNum / 10;
    }

    /**
     * 589. N 叉树的前序遍历
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     */
    class BinaryTreePreOrderTraversal_589 {
        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        public List<Integer> preorder2(Node root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            HashMap<Node, Integer> record = new HashMap<>();
            Stack<Node> stack = new Stack<>();
            Node cur = root;
            while (true) {
                stack.add(cur);
                result.add(cur.val);
                if (cur.children != null && cur.children.size() > 0) {
                    cur = cur.children.get(0);
                    record.put(cur, 0);
                } else {
                    while (true) {
                        if (stack.size() == 1) {
                            return result;
                        }
                        Node pop = stack.pop();
                        Node peek = stack.peek();
                        Integer recordIndex = record.get(pop);
                        recordIndex = recordIndex == null ? -1 : recordIndex;
                        record.remove(pop);
                        int nextIndex = recordIndex + 1;
                        if (peek.children != null && peek.children.size() > nextIndex) {
                            cur = peek.children.get(nextIndex);
                            record.put(cur, nextIndex);
                            break;
                        }
                    }
                }
            }
        }

        public List<Integer> preorder(Node root) {
            List<Integer> result = new ArrayList<>();
            preOrderRecursive(root, result);
            return result;
        }

        public void preOrderRecursive(Node node, List<Integer> result) {
            if (node == null) {
                return;
            }
            result.add(node.val);
            if (node.children != null && node.children.size() > 0) {
                for (int i = 0; i < node.children.size(); i++) {
                    preOrderRecursive(node.children.get(i), result);
                }
            }
        }
    }

    /**
     * 590. N 叉树的后序遍历
     * 简单
     * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     */
    class BinaryTreePostOrderTraversal_590 {

        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        class Solution {
            // 方案1
            public List<Integer> postorder(Node root) {
                ArrayList<Integer> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }
                Stack<Node> stack = new Stack<>();
                Node cur = root;
                while (true) {
                    stack.add(cur);
                    if (cur.children != null && cur.children.size() > 0) {
                        cur = cur.children.get(0);
                    } else {
                        while (true) {
                            result.add(cur.val);
                            if (stack.size() == 1) {
                                return result;
                            }
                            Node pop = stack.pop();
                            Node peek = stack.peek();
                            int i = peek.children.indexOf(pop);
                            if (i < peek.children.size() - 1) {
                                cur = peek.children.get(i + 1);
                                break;
                            }
                            cur = peek;
                        }
                    }
                }
            }


            // 方案二
            public List<Integer> postorder2(Node root) {
                ArrayList<Integer> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }
                postOrderRecursive(root, result);
                return result;
            }

            public void postOrderRecursive(Node root, ArrayList<Integer> result) {
                if (root.children != null && root.children.size() > 0) {
                    for (int i = 0; i < root.children.size(); i++) {
                        postOrderRecursive(root.children.get(i), result);
                    }
                }
                result.add(root.val);
            }
        }
    }
}
