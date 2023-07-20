package com.example.playground.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 * <p>
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * <p>
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * <p>
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/delete-nodes-and-return-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S_1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        if (to_delete == null) {
            result.add(root);
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur!=null ) {
                if (!isContainsInArray(to_delete, cur.val)) {
                    result.add(cur);
                }
                stack.push(cur.left);
                stack.push(cur.right);
            }
        }
        return null;
    }

    public boolean isContainsInArray(int[] to_delete,int target) {
        if (to_delete == null) {
            return false;
        }
        for (int i = 0; i < to_delete.length; i++) {
            if (to_delete[i] == target) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
//        ArrayList<Integer> result = new ArrayList<>();
//        if (root == null ) {
//            return result;
//        }
//        TreeNode cur = root;
//        Stack<TreeNode> stack = new Stack<>();
//        while (cur != null || !stack.isEmpty()) {
//            if (cur != null) {
//                result.add(cur.val);
//                stack.push(cur);
//                cur = cur.left;
//            } else {
//                cur = stack.pop();
//                cur = cur.right;
//            }
//        }
//        return result;
        ArrayList<Integer> result = new ArrayList<>();
        preorderTraversalInner(root,result);
        return result;
    }

    public static void preorderTraversalInner(TreeNode root, ArrayList<Integer> result) {
        if (root != null) {
            result.add(root.val);
            preorderTraversalInner(root.left, result);
            preorderTraversalInner(root.right, result);
        }
    }

    public static class TreeNode {
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
}
