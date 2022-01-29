package com.example.playground.leetcode.depthFirst;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_01 {


    /**
     * i: 石头编号 j:背包大小
     * 不加i石头 dp[i][j]=dp[i-1][j]
     * 加i石头   dp[i][j]=dp[i-1][j-weight[i]] + value[i]
     * <p>
     * 最终 dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i])
     */
    public static void main1(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;

        int[][] dp = new int[value.length][bagWeight + 1];
        for (int i = bagWeight; i >= weight[0]; i--) {
            dp[0][i] = dp[0][i - weight[0]] + value[0];
        }

        for (int j = 1; j < dp[0].length; j++) {
            for (int i = 1; i < weight.length; i++) {
                int noI = dp[i - 1][j];
                int hasI = j - weight[i] < 0 ? 0 : dp[i - 1][j - weight[i]] + value[i];
                dp[i][j] = Math.max(noI, hasI);
            }
        }
        for (int[] ints : dp) {
            for (int i = 0; i < ints.length; i++) {
                System.out.println("" + ints[i]);
            }
            System.out.println("=====");
        }

    }


    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for(int i = 0;i<weight.length;i++){
            for(int j = bagWeight;j>=weight[i];j--){
                int temp = dp[j-weight[i]]+value[i];
                dp[j] = Math.max(dp[j], temp);
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println("" + dp[i]);
        }
    }

}
