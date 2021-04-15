package com.example.playground.arithmetic;

/**
 * 选择排序
 */
class SelectionSort {

    private static void sort(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }

        int n = array.length;
        int curMin;

        for (int i = 0; i < n - 1; i++) {
            curMin = i;
            for (int j = i + 1; j < n; j++) {
                curMin = array[curMin] > array[j] ? j : curMin;
            }
            doSwap(array, i, curMin);
        }
    }

    private static void doSwap(int[] array, int x, int y) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }

    public static void main(String[] args) {
        int[] array = {111, 522, 77, 98, 36, 12, 13, 48};
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
