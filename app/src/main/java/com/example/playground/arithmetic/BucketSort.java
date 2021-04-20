package com.example.playground.arithmetic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序
 */
class BucketSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int max = 0;
        int min = 0;
        for (int i : arr) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        // 定义桶数量
        int BUCKET_SIZE = 10;
        int bucketCount = (max - min) / BUCKET_SIZE + 1;

        // 定义桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // 初始化桶
        for (int i : arr) {
            int index = (i - min) / BUCKET_SIZE;
            buckets.get(index).add(i);
        }

        // 对各个桶数据进行排序
        for (ArrayList<Integer> bucket : buckets) {
            // 这里的排序算法决定着桶的算法复杂度
            Collections.sort(bucket);
        }

        // 桶拼接
        int p = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer integer : bucket) {
                arr[p++] = integer;
            }
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
