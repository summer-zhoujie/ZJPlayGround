package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_98 {
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

    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        int pre = Integer.MIN_VALUE;
        while(!deque.isEmpty() || root!=null){
            while(root!=null){
                deque.push(root.left);
                root = root.left;
            }
            root = deque.pop();
           if( root.val<=pre){
               return false;
           }
           pre = root.val;
           root = root.right;
        }
        return true;
    }

    int pre = Integer.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root==null)
            return true;
        boolean isLeftOk = isValidBST(root.left);
        if(root.val<=pre)
            return false;
        pre = root.val;
        boolean isRightOk = isValidBST(root.right);
        return isLeftOk && isRightOk;
    }

    public static void main(String[] args) {
    }




}
