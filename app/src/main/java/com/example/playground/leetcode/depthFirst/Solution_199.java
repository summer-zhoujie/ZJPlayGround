package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_199 {
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            result.add(deque.peekLast().val);
            for(int i = 0; i< size; i++){
                TreeNode node = deque.pollFirst();
                if(node.left!=null)
                    deque.offerLast(node.left);
                if(node.right!=null)
                    deque.offerLast(node.right);
            }
        }
        return result;
    }


    public static void main(String[] args) {

    }


}
