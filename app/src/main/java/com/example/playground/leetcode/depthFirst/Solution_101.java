package com.example.playground.leetcode.depthFirst;

import java.util.LinkedList;

public class Solution_101 {
    public static class TreeNode {
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

    public boolean isSymmetric(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        return inorder(left, right);
    }

    private boolean inorder(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }

        boolean isLeftOk = inorder(left.left, right.right);
        if (left.val != right.val) {
            return false;
        }
        boolean isRightOK = inorder(left.right,right.left);
        return isLeftOk && isRightOK;
    }

    public static void main(String[] args) {

    }




}
