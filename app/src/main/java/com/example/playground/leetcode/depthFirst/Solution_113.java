package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_113 {
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

    Deque<Integer> deque = new LinkedList<Integer>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return result;
        deque.offerLast(root.val);
        if (root.left == null && root.right == null && targetSum == root.val) {
            List<Integer> integers = new ArrayList<>();
            integers.addAll(deque);
            result.add(integers);
        }
        pathSum(root.left, targetSum - root.val);
        pathSum(root.right, targetSum - root.val);
        deque.pollLast();
        return result;
    }

    public static void main(String[] args) {

    }


}
