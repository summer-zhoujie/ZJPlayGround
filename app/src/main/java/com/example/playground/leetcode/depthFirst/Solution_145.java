package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_145 {
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root,list);
        return list;
    }

    private void postorder(TreeNode root,List<Integer> list){
        if(root ==null)
            return;
        postorder(root.left,list);
        postorder(root.right,list);
        list.add(root.val);

    }

    // no recursive
    public List<Integer> postorderTraversal2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        TreeNode pre = null;
        while(root!=null||!deque.isEmpty()){
            while(root!=null){
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            if(root.right==null||root.right == pre){
                result.add(root.val);
                pre = root;
                root = null;
            }else{
                deque.push(root);
                root = root.right;
            }
        }
        return result;

    }

    public static void main(String[] args) {
    }
}
