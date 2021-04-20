package com.example.playground.arithmetic;

/**
 * 堆排序
 */
class HeapSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int length = arr.length;
        for (int i = getParentIndex(length - 1); i >= 0; i--) {
            ajustHeap(arr, i, length - 1);
        }

        int i = length - 1;
        while (i > 0) {
            swap(arr, 0, i);
            i--;
            ajustHeap(arr, 0, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void ajustHeap(int[] arr, int start, int end) {
        int left = getLeftChildIndex(start);
        while (left <= end) {
            int right = left + 1;
            int target = left;

            if (right <= end && arr[left] < arr[right]) {
                target = right;
            }

            if (arr[start] < arr[target]) {
                swap(arr, start, target);
                start = target;
                left = getLeftChildIndex(start);
            } else {
                break;
            }
        }
    }

    private static int getParentIndex(int child) {
        return (child - 1) / 2;
    }

    private static int getLeftChildIndex(int parent) {
        return 2 * parent + 1;
    }

    public static void main(String[] args) {
        int[] array = {111, 52, 77, 98, 36, 12, 13, 48, 79, 10, 6, 500};
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
