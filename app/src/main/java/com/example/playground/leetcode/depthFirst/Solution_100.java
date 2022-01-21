package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_100 {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (q == null && p == null) {
            return true;
        }
        if (q == null && p != null) {
            return false;
        }
        if (q != null && p == null) {
            return false;
        }
        if(q.val!=p.val){
            return false;
        }
        return isSameTree(q.left, p.left) && isSameTree(q.right, p.right);
    }

    public static void main(String[] args) {

    }




}
