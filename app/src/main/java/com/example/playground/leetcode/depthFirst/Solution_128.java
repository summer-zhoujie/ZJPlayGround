package com.example.playground.leetcode.depthFirst;

import java.util.HashSet;

public class Solution_128 {

    public int longestConsecutive(int[] nums) {
        if(nums.length<=0){
            return 0;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for(int num: nums){
            set.add(num);
        }
        int maxLength = 1;
        for(int num:nums){
            if(set.contains(num-1))
                continue;
            int curLength = 1;
            while(set.contains(num+1)){
                curLength++;
                num++;
            }
            maxLength = Math.max(curLength,maxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {

    }


}
