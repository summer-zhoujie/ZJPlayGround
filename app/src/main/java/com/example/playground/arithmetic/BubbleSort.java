package com.example.playground.arithmetic;

/**
 * 冒泡排序
 */
public class BubbleSort {

    private static void sort(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        int length = array.length;


        for (int i = length - 1; i > 0; i--) {
            boolean isSwap = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    doSwap(array, j, j + 1);
                    isSwap = true;
                }
            }

            if (!isSwap) {
                return;
            }
        }
    }

    private static void doSwap(int[] array, int x, int y) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }

    public static void main(String[] args) {
        int[] array = {111, 52, 77, 98, 36, 12, 13, 48};
        sort(array);
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
