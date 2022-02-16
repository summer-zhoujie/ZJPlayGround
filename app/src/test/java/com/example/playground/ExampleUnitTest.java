package com.example.playground;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * <p>
     * Sum是奇数, false
     * maxNum大于Sum/2, false
     * <p>
     * nums 一堆数
     * dp[i][j] 取数组中0~i中任意数量的数, 存在一种组合满足 sum=j
     * <p>
     * 状态转移方程:
     * dp[i][j] = dp[i-1]dp[j] (nums[i] 不参与)
     * dp[i][j] = dp[i-1][j-nums[i]] (nums[i] 参与)
     * 状态转移方程(一阶):
     * dp[j] = dp[j] (nums[i] 不参与)
     * dp[j] = dp[j-nums[curIndex]](nums[i] 参与)
     */
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int target, sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        boolean[] result = new boolean[target + 1];
        //init array
        for (int i = 0; i < result.length; i++) {
            result[i] = nums[0] == i;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = target; j >= 0; j--) {
                result[j] = result[j] || (j - nums[i] >= 0 && result[j - nums[i]]);
                if (j == target && result[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void testCanPartition() {
        boolean b = canPartition(new int[]{1, 5, 11, 5});
        assertTrue(b);
        b = canPartition(new int[]{1, 2, 3, 5});
        assertFalse(b);
    }

    /**
     * 494. 目标和
     * <p>
     * 给你一个整数数组 nums 和一个整数 target 。
     * <p>
     * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * <p>
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * 数组和值: sum
     * 负数和值: neg
     * 正数和纸: sum - neg
     * target = (sum-neg) - neg
     * neg = (sum-target)/2
     * <p>
     * 条件
     * sum-target<0, 0;
     * sum-target%2!=0, 0;
     * 定义
     * j: neg
     * dp[i][j] 选取0~i, 中间存在n种可能, 使得值为 j
     * <p>
     * 状态转移方程
     * dp[i][j] =  dp[i-1][j](j<nums[i])
     * dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j](j>=nums[i])
     * <p>
     * 状态转移方程(一阶)
     * dp[j] = dp[j](j<nums[i])
     * dp[j] = dp[j-nums[i]]+dp[j](j>=nums[i])
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0, neg;
        for (int num : nums) {
            sum += num;
        }
        if (sum - target < 0 || (sum - target) % 2 != 0) {
            return 0;
        }
        neg = (sum - target) / 2;
        System.out.println("neg = "+neg+", sum="+sum+", target = "+target);
        int[] dp = new int[neg + 1];
        // init
        dp[0] = 1;
        // cul
        for (int i = 0; i < nums.length; i++) {
            for (int j = dp.length - 1; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
                System.out.println(dp[j]+", i="+i);
            }
            System.out.println("===");
        }
        return dp[neg];
    }

    @Test
    public void testFindTargetSumWays() {
        int targetSumWays = findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        Assert.assertEquals(5,targetSumWays);
        targetSumWays = findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1);
        Assert.assertEquals(256,targetSumWays);
    }
}