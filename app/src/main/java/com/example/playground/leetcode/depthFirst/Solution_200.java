package com.example.playground.leetcode.depthFirst;

public class Solution_200 {

    int result = 0;
    int w = 0;
    int h = 0;

    public int numIslands(char[][] grid) {
        w = grid.length;
        if (w <= 0)
            return result;
        h = grid[0].length;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    handleALand(i, j, grid);
                }
            }
        }
        return result;
    }

    private void handleALand(int x, int y, char[][] grid) {
        if (x < 0 || x >= w || y < 0 || y >= h || grid[x][y] != '1') {
            return;
        }
        grid[x][y] = '2';
        handleALand(x - 1, y, grid);
        handleALand(x + 1, y, grid);
        handleALand(x, y - 1, grid);
        handleALand(x, y + 1, grid);
    }

    public static void main(String[] args) {

    }


}
