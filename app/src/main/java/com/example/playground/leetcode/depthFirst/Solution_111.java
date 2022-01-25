package com.example.playground.leetcode.depthFirst;

public class Solution_111 {
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


    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left==null&&root.right==null){
            return 1;
        }
        int min_deep=Integer.MAX_VALUE;
        if(root.left!=null)
            min_deep = Math.min(min_deep,minDepth(root.left));
        if(root.right!=null)
            min_deep = Math.min(min_deep,minDepth(root.right));
        return min_deep+1;
    }

    public static void main(String[] args) {

    }


}
