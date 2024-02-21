package arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class LeetCode {

    public static void main(String[] args) {

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
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
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
            return buildTreeNodeRecursive(valueIndexInInorderMap,inorder,postorder,0,inorder.length-1,0,postorder.length-1);
        }

        public TreeNode buildTreeNodeRecursive(HashMap<Integer, Integer> valueIndexInInorderMap, int[] inorder, int[] postorder, int inorderLeft, int inorderRight, int postorderLeft, int postorderRight) {
            if (inorderLeft > inorderRight || postorderLeft > postorderRight) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorderRight]);
            int indexOfRootInInorder = valueIndexInInorderMap.get(root.val);
            int leftTreeLength = indexOfRootInInorder - inorderLeft;
            root.left = buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, inorderLeft, indexOfRootInInorder - 1, postorderLeft, postorderLeft + leftTreeLength - 1);
            root.right = buildTreeNodeRecursive(valueIndexInInorderMap, inorder, postorder, indexOfRootInInorder+1, inorderRight, postorderLeft + leftTreeLength, postorderRight-1);
            return root;
        }

        public TreeNode buildTree_106_PlanB(int[] inorder, int[] postorder) {
            if (inorder == null || inorder.length == 0) {
                return null;
            }
            HashMap<Integer, Integer> valueIndexInInorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                valueIndexInInorderMap.put(inorder[i], i);
            }
            return buildTreeNodeRecursive(valueIndexInInorderMap,inorder,postorder,0,inorder.length-1,0,postorder.length-1);
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
