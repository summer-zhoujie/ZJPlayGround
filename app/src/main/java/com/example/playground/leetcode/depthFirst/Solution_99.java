package com.example.playground.leetcode.depthFirst;

import java.util.Deque;
import java.util.LinkedList;

public class Solution_99 {
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

    public static void recoverTree(TreeNode root) {
        Deque<TreeNode> stack =  new LinkedList<>();
        TreeNode pre = null;
        TreeNode first = null;
        TreeNode second = null;
        while(!stack.isEmpty() || root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root = stack.pop();
            if(pre!=null&&root.val<pre.val){
                if(first==null){
                    first = pre;
                    second = root;
                }else{
                    second = root;
                }
            }
            pre = root;
            root = root.right;
        }

        int temp = second.val;
        second.val = first.val;
        first.val = temp;
    }



    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(2);
        recoverTree(treeNode);
    }




}
