package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_94 {
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

    // 94
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        return list;
    }

    private void inorder(TreeNode node,List<Integer> list){
        if(node == null)
            return;
        inorder(node.left,list);
        list.add(node.val);
        inorder(node.right,list);
    }

    // no recursive
    public List<Integer> inorderTraversal2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        while(root!=null||!deque.isEmpty()){
            while(root!=null){
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            result.add(root.val);
            root=root.right;
        }
        return result;
    }

    public static void main(String[] args) {
    }


}
