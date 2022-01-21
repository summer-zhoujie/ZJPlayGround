package com.example.playground.leetcode.depthFirst;

public class Solution_110 {
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


    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(max(root.left) - max(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int max(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(max(root.left), max(root.right)) + 1;
    }


    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }
    private int height(TreeNode root){
        if(root==null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if(Math.abs(leftHeight - rightHeight)>1||leftHeight<0||rightHeight<0){
            return -1;
        }
        return Math.max(leftHeight,rightHeight)+1;
    }

    public static void main(String[] args) {
        Solution_110 solution_110 = new Solution_110();
        TreeNode treeNode = new TreeNode(1);

        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = null;

        treeNode.left.left.left = new TreeNode(8);


        solution_110.isBalanced(treeNode);
    }


}
