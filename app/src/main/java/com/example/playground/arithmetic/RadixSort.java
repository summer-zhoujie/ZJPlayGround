package com.example.playground.arithmetic;

import java.util.ArrayList;

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
                if (isAllZero && num == 0) {
                    isAllZero = false;
                }
                buckets.get(num).add(arr[i]);
            }

            // 回填Arr


            if (isAllZero) {
                break;
            }

            // 开始下一个位数的比较
            radix *= 10;
        }
    }
}
