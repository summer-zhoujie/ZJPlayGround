package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_114 {
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

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(pre!=null){
                pre.right = cur;
                pre.left = null;
            }
            pre = cur;
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }
    }

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode next = null;
        TreeNode pre = null;
        while (cur!=null){
            next = cur.left;
            if (next != null) {
                pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }



    public static void main(String[] args) {

    }


}
