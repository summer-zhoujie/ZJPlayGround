package com.example.playground.leetcode.depthFirst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution_133 {


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class Solution {
        HashMap<Integer,Node> map =  new HashMap<Integer,Node>();
        public Node cloneGraph(Node node) {
            if(node==null)
                return null;
            Node cloneNode = map.get(node.val);
            if(cloneNode==null){
                cloneNode = new Node(node.val);
                map.put(node.val, cloneNode);
                if(node.neighbors!=null){
                    for (Node neighbor : node.neighbors) {
                        cloneNode.neighbors.add(cloneGraph(neighbor));
                    }
                }
            }

            return cloneNode;
        }
    }


    public static void main(String[] args) {

    }


}
