package com.example.playground.arithmetic;

import java.lang.reflect.Array;

class MergeSort {

    private static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int[] tmp = new int[array.length];
        int length = array.length;
        doSort(array, tmp, 0, length - 1);
    }

    private static void doSort(int[] arr, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (end + start) / 2;
            doSort(arr, tmp, start, mid);
            doSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, mid, end);
        }
    }

    private static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            }

            if (arr[i] > arr[j]) {
                tmp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            tmp[t++] = arr[i++];
        }

        while (j <= end) {
            tmp[t++] = arr[j++];
        }

        System.arraycopy(tmp, 0, arr, start, end - start + 1);
    }

    public static void main(String[] args) {
        int[] array = {111, 52, 77, 98, 36, 12, 12, 48};
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
