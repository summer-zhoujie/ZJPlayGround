package com.example.playground.leetcode.depthFirst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution_130 {


    public void solve(char[][] board) {
        if (board.length <= 0)
            return;

        int l = 0;
        int t = 0;
        int r = board.length;
        int b = board[0].length;

        for (int i = l; i < r; i++) {

            // l && r
            if (i == l || i == r - 1) {
                for (int j = t; j < b; j++) {
                    handleNode(i, j, board);
                }
            }

            // b
            handleNode(i, b - 1, board);

            // t
            handleNode(i, t, board);
        }

        for (int w = l ; w < r ; w++) {
            for (int h = t ; h < b ; h++) {
                if (board[w][h] == 'A') {
                    board[w][h] = 'O';
                } else if (board[w][h] == 'O') {
                    board[w][h] = 'X';
                }
            }
        }
    }

    private void handleNode(int i, int j, char[][] board) {
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'A';
        handleNode(i - 1, j, board);
        handleNode(i + 1, j, board);
        handleNode(i, j - 1, board);
        handleNode(i, j + 1, board);
    }


    public static void main(String[] args) {

    }


}
