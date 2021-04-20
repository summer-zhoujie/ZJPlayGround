package com.example.playground.arithmetic;

import java.security.Key;

/**
 * 计数排序
 */
class CountSort {

    public static int[] sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // 找出最大值和最小值
        int max = 0;
        int min = 0;
        for (int i : arr) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        int k = max - min + 1;
        int[] c = new int[k];

        // 计数
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            c[arr[i] - min] = c[arr[i] - min] + 1;
        }

        // 求和
        for (int i = 1; i < k; i++) {
            c[i] = c[i] + c[i - 1];
        }

        // 回填
        int[] b = new int[length];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int index = c[num - min] - 1;
            c[num - min]--;
            b[index] = num;
        }

        return b;
    }

    public static void main(String[] args) {
        int[] array = {111, 52, 77, 98, 36, 12, 13, 48};
        array = sort(array);
        System.out.println(arrayToString(array));
    }

    private static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int t : array) {
            builder.append(t + " ");
        }
        return builder.toString();
    }
}
