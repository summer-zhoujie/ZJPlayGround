package com.example.playground.leetcode.depthFirst;

import java.util.Deque;
import java.util.LinkedList;

public class Solution_129 {
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

    public int sumNumbers(TreeNode root) {
       return sum(root,0);
    }

    private int sum(TreeNode node, int preSum){
        if(node==null)
            return 0;
        int sum = preSum*10+node.val;
        if (node.left == null && node.right == null) {
            return sum;
        }else {
            return sum(node.left, sum) + sum(node.right, sum);
        }
    }



    public static void main(String[] args) {

    }


}
