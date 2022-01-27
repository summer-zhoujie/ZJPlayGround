package com.example.playground.leetcode.depthFirst;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution_399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> map = new HashMap<>();
        int size = equations.size();
        UnionFind unionFind = new UnionFind(size * 2);
        int id = 0;
        // init
        for (int i = 0; i < size; i++) {
            String xKey = equations.get(i).get(0);
            Integer x = map.get(xKey);
            if (x == null) {
                map.put(xKey, id);
                x = id++;
            }

            String yKey = equations.get(i).get(1);
            Integer y = map.get(yKey);
            if (y == null) {
                map.put(yKey, id);
                y = id++;
            }

            unionFind.union(x, y, values[i]);
        }


        // find
        size = queries.size();
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            List<String> ss = queries.get(i);
            Integer x = map.get(ss.get(0));
            Integer y  = map.get(ss.get(1));
            if (x == null || y == null) {
                result[i] = -1.0d;
            }else {
                double connect = unionFind.isConnect(x, y);
                result[i] = connect;
            }
        }
        return result;
    }


    private class UnionFind {
        int[] parent;
        double[] weight;

        UnionFind(int n) {
            parent = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                int temp = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[temp];
            }
            return parent[x];
        }

        /**
         * @return weight
         */
        private void union(int x, int y, double value) {
            int rootx = find(x);
            int rooty = find(y);
            if (rooty == rootx)
                return;
            parent[rootx] = rooty;
            weight[rootx] = value * weight[y] / weight[x];
        }

        private double isConnect(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                return -1.0;
            } else {
                return weight[x]/weight[y];
            }
        }
    }

    public static void main(String[] args) {

    }


}
