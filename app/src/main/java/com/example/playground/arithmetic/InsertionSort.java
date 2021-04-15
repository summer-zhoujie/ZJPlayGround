package com.example.playground.arithmetic;

class InsertionSort {

    private static void sort(int[] array) {

        if (array == null || array.length == 1) {
            return;
        }

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int waitToInsert = array[i + 1];
            int pos = i + 1;
            while (pos > 0 && waitToInsert < array[pos - 1]) {
                // swap
                array[pos] = array[pos - 1];
                pos--;
            }
            array[pos] = waitToInsert;
        }

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
