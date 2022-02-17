package com.example.playground;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
        System.out.println("neg = " + neg + ", sum=" + sum + ", target = " + target);
        int[] dp = new int[neg + 1];
        // init
        dp[0] = 1;
        // cul
        for (int i = 0; i < nums.length; i++) {
            for (int j = dp.length - 1; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
                System.out.println(dp[j] + ", i=" + i);
            }
            System.out.println("===");
        }
        return dp[neg];
    }

    @Test
    public void testFindTargetSumWays() {
        int targetSumWays = findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        Assert.assertEquals(5, targetSumWays);
        targetSumWays = findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);
        Assert.assertEquals(256, targetSumWays);
    }

    /**
     * 11. 盛最多水的容器
     * <p>
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     * <p>
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * <p>
     * 返回容器可以储存的最大水量。
     * <p>
     * 说明：你不能倾斜容器。
     * <p>
     * 最大储水量
     * i j
     * (j-i)*Math.min(height[i],height[j])
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int result = 0;
        int i = 0, j = height.length - 1;
        result = (j - i) * Math.min(height[i], height[j]);
        while (i < j) {
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
            int newValue = (j - i) * Math.min(height[i], height[j]);
            result = Math.max(result, newValue);
        }
        return result;
    }

    @Test
    public void testMaxArea() {
        int i = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        Assert.assertEquals(49, i);
    }

    /**
     * 15. 三数之和
     * <p>
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        // sort
        for (int i = 0; i < nums.length - 1; i++) {
            int curP = i + 1;
            int curPValue = nums[curP];
            while (curP > 0 && curPValue < nums[curP - 1]) {
                nums[curP] = nums[curP - 1];
                curP--;
            }
            nums[curP] = curPValue;
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            int third = nums.length - 1;
            for (int second = first + 1; second < nums.length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }

                if (second == third) {
                    break;
                }

                if (nums[second] + nums[third] == target) {
                    ArrayList<Integer> resultItem = new ArrayList<>();
                    resultItem.add(nums[first]);
                    resultItem.add(nums[second]);
                    resultItem.add(nums[third]);
                    result.add(resultItem);
                }

            }
        }
        return result;
    }


    @Test
    public void testThreeSum() {
        List<List<Integer>> lists = threeSum(new int[]{1, -1, -1, 0});
        System.out.println(lists);
    }


    /**
     * 16. 最接近的三数之和
     * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
     * <p>
     * 返回这三个数的和。
     * <p>
     * 假定每组输入只存在恰好一个解。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            int curP = i + 1;
            int curPValue = nums[curP];
            while (curP > 0 && curPValue < nums[curP - 1]) {
                nums[curP] = nums[curP - 1];
                curP--;
            }
            nums[curP] = curPValue;
        }

        int result = 10000000;
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int second = first + 1;
            int third = nums.length - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                int diff = sum - target;
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (diff > 0) {
                    third--;
                } else if (diff == 0) {
                    return sum;
                } else {
                    second++;
                }

                if (second >= third) {
                    break;
                }


            }
        }
        return result;
    }

    @Test
    public void testThreeSumClosest() {
        int i = threeSumClosest(new int[]{0, 2, 1, -3}, 1);
        assertEquals(0, i);
    }

    /**
     * 3. 无重复字符的最长子串
     * <p>
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int result = 0;
        HashSet<Character> characters = new HashSet<>();
        int l = 0, r = 0;
        while (l <= r && r < s.length()) {
            if (!characters.contains(s.charAt(r))) {
                characters.add(s.charAt(r));
                result = Math.max(result, characters.size());
                r++;
            } else {
                char needRemove = s.charAt(l);
                characters.remove(needRemove);
                l++;
            }
        }
        result = Math.max(result, characters.size());
        return result;
    }

    @Test
    public void testLengthOfLongestSubstring() {
        int abcabcbb = lengthOfLongestSubstring("pwwkew");
        Assert.assertEquals(3, abcabcbb);
    }

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * <p>
     * 状态转移方程
     * dp[i][j]表示i~j是回文字串
     * dp[i][j] = (dp[i+1][j-1] == true && s[i-1] = s[j+1])
     * <p>
     * 边界条件
     * dp[i][i] = true
     * dp[i][i+1] = s[i]==s[i+1]
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int length = s.length();
        if (length == 1) {
            return s;
        }

        int l = 0, r = 0;
        boolean[] dp = new boolean[length];
        List<String> strings = new ArrayList<>();
        //init
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j >= i; j--) {
                if (i == j) {
                    dp[j] = true;
                } else if (j - i == 1) {
                    dp[j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[j] = dp[j - 1] && (s.charAt(i) == s.charAt(j));
                }

                if (dp[j] && (j - i > r - l)) {
                    l = i;
                    r = j;
                }

            }
            strings.add(Arrays.toString(dp));

        }
        Collections.reverse(strings);
        for (String string : strings) {
            System.out.println(string);
        }
        return s.substring(l, r+1);
    }

    @Test
    public void testlongestPalindrome() {
        String babad = longestPalindrome("babad");
        assertEquals("bab",babad);
        assertEquals("aba",babad);
    }
}