package com.example.playground.arithmetic;

import java.util.ArrayList;

/**
 * 基数排序
 */
class RadixSort {
    public static void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        // 初始化比较需要的`桶`空间
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }

        int radix = 1;
        // 循环比较各个位数
        while (true) {

            boolean isAllZero = true;

            // 按照位数放置到相应的桶位置
            for (int i = 0; i < arr.length; i++) {
                // 取出整数的个,十, 百, 千...
                int num = arr[i] / radix % 10;
                if (isAllZero && num != 0) {
                    isAllZero = false;
                }
                buckets.get(num).add(arr[i]);
            }

            if (isAllZero) {
                break;
            }

            // 回填Arr && 清空桶(buckets)中数据
            int index = 0;
            for (ArrayList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    Integer remove = bucket.remove(0);
                    arr[index++] = remove;
                }
            }

            // 开始下一个位数的比较
            radix *= 10;
        }
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
