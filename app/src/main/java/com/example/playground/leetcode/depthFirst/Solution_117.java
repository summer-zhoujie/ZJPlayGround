package com.example.playground.leetcode.depthFirst;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_117 {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Node temp = root;
        Deque<Node> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            Node pre = null;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.offerLast(cur.left);
                }
                if (cur.right != null) {
                    deque.offerLast(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return temp;
    }

    Node pre = null;
    Node nextLeftMost = null;
    public Node connect2(Node root) {
        if(root==null)
            return root;
        Node leftMost = root;
        while (leftMost != null) {
            Node cur = leftMost;
            pre = null;
            nextLeftMost = null;
            do {
                handle(cur.left);
                handle(cur.right);
                cur = cur.next;
            } while (cur != null);
            leftMost = nextLeftMost;
        }
        return root;
    }

    private void handle(Node node){
        if(node==null){
            return;
        }
        if(pre!=null){
            pre.next = node;
        }
        pre = node;
        if (nextLeftMost == null) {
            nextLeftMost = pre;
        }
    }

}
