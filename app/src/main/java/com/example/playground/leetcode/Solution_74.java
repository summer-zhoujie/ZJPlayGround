package com.example.playground.leetcode;

public class Solution_74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length <= 0) {
            return false;
        }

        int width = matrix[0].length;
        int height = matrix.length;

        int start = 0;
        int end = width * height - 1;

        while (start <= end) {
            int mid = (end - start + 1) / 2 + start;
            int x = mid / width;
            int y = mid % width;

            System.out.println("start="+start+",end="+end);
            if (target > matrix[x][y]) {
                start = mid + 1;
            } else if (target < matrix[x][y]) {
                end = mid - 1;
            } else {
                return true;
            }
        }



        return false;
    }

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1, 1}, {2, 2}}, 3));
    }
}
