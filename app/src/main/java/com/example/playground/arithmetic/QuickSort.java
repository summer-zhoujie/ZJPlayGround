package com.example.playground.arithmetic;

class QuickSort {

    private static void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        doSort(arr, start, end);
    }

    private static void doSort(int[] arr, int start, int end) {

        if (start >= end) {
            return;
        }

        int pivot = arr[start];
        int pivotIndex = start;
        int left = start;
        int right = end;

        while (left < right) {


            // 坑在左边,往右边找
            while (left == pivotIndex && left < right) {
                if (arr[right] > pivot) {
                    right--;
                } else {
                    arr[pivotIndex] = arr[right];
                    pivotIndex = right;
                    break;
                }
            }

            // 坑在右边,往左边找
            while (right == pivotIndex && left < right) {
                if (arr[left] < pivot) {
                    left++;
                } else {
                    arr[pivotIndex] = arr[left];
                    pivotIndex = left;
                    break;
                }
            }
        }

        arr[left] = pivot;

        doSort(arr, start, left);
        doSort(arr, left + 1, end);
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
