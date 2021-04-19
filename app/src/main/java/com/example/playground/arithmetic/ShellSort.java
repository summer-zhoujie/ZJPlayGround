package com.example.playground.arithmetic;

/**
 * 希尔排序
 */
class ShellSort {
    private static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;
        int step = length / 2;
        // step 为步长, 每次缩减为原来的 1/2
        for (; step > 0; step /= 2) {

            // 一共有step个序列
            for (int i = 0; i < step; i++) {

                // 对单个序列进行排序
                for (int j = i; j < length; j += step) {

                    int value = array[j];
                    int pre = j - step;
                    while (pre >= 0 && array[pre] > value) {
                        array[pre + step] = array[pre];
                        pre -= step;
                    }

                    array[pre + step] = value;

                }
            }
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
