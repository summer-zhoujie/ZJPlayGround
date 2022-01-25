package com.example.playground.leetcode.depthFirst;

public class Solution_112 {
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


    public boolean hasPathSum(TreeNode root, int targetSum) {
        int preSum = 0;
        return hasPathSum(root, targetSum, preSum);
    }

    private boolean hasPathSum(TreeNode root,int targetSum, int preSum){
        if(root == null){
            return false;
        }
        preSum += root.val;
        if (root.left == null && root.right == null && preSum ==targetSum) {
            return true;
        }

        return hasPathSum(root.left, targetSum, preSum) || hasPathSum(root.right, targetSum, preSum);
    }

    public static void main(String[] args) {

    }


}
