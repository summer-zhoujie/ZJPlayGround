package com.example.playground.leetcode;

import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Solution_1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(target - nums[i])) {
                return new int[]{(int) hash.get(target - nums[i]), i};
            }
            hash.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        //强引用
        String object = new String("str");
        //创建引用队列
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        WeakReference<String> weakRef = new WeakReference<>(object, queue);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Reference<? extends String> remove = queue.remove();
                    System.out.println( "run: remove = " + remove);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println( "run: e = " + e);
                }

            }
        }).start();
//        object = null;
        System.out.println("After new byte[]:Soft Get= " + weakRef.get());
        System.gc();
        System.out.println("After new byte[]:Soft Get= " + weakRef.get() + ", object = " + object);
    }
}
