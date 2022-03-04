package com.example.playground;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Map;

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
        return s.substring(l, r + 1);
    }

    @Test
    public void testlongestPalindrome() {
        String babad = longestPalindrome("babad");
        assertEquals("bab", babad);
        assertEquals("aba", babad);
    }

    /**
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        final int[][] rows = new int[9][9];
        final int[][] columns = new int[9][9];
        final int[][][] grids = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int target = board[i][j] - '0';
                final int a = rows[i][target]++;
                final int b = columns[j][target]++;
                final int c = grids[i / 3][j / 3][target]++;
                if (a > 1 || b > 1 || c > 1) {
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String item = strs[i];

            //find list
            String validKey = null;
            for (String key : map.keySet()) {
                validKey = key;
                for (int j = 0; j < item.length(); j++) {
                    if (!key.contains(item.charAt(j) + "")) {
                        validKey = null;
                        break;
                    }
                }

                if (validKey != null) {
                    break;
                }
            }

            List<String> listFinded = null;
            if (validKey == null) {
                listFinded = new ArrayList<>();
                result.add(0, listFinded);
                map.put(item, listFinded);
            } else {
                listFinded = map.get(validKey);
            }

            listFinded.add(item);
        }
        return result;
    }


    /**
     * 6. Z 字形变换
     */
    public String convert(String s, int numRows) {
        final StringBuilder result = new StringBuilder();
        int length = s.length();
        int cycLen = 2 * numRows - 2;
        // i 第几行
        for (int i = 0; i < numRows; i++) {

            for (int j = 0; j + i < length; j += cycLen) {
                result.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycLen - i < length) {
                    result.append(s.charAt(j + cycLen - i));
                }
            }
        }
        return result.toString();
    }

    @Test
    public void testconvert() {
        final String paypalishiring = convert("PAYPALISHIRING", 3);
    }

    /**
     * 8. 字符串转换整数 (atoi)
     */
    public int myAtoi(String s) {
        int curDigit = 1;
        long result = 0;
        int end = 0;
        int symbol = 1;
        boolean hasDigit = false;
        int length = s.length();

        // find + -
        for (int i = 0; i < length; i++) {
            final char c = s.charAt(i);
            if (c == '-') {
                symbol = -1;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
                int value = c - '0';
                result = result * 10 + value * symbol;
                if (result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else if (c == '.' && hasDigit) {
                return (int) result;
            } else if (c != '+' && c != ' ' && !hasDigit) {
                return 0;
            }
        }

        return (int) result;
    }

    @Test
    public void testmyAtoi() {
        final int i = myAtoi(" -42");
        System.out.println(i);
    }

    /**
     * 22.括号生成
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        generateAll(sb, list, 0, 0, n);
        return list;
    }

    private void generateAll(StringBuilder sb, List<String> list, int left, int right, int max) {
        if (sb.length() == max * 2) {
            list.add(sb.toString());
            return;
        }

        if (left < max) {
            sb.append('(');
            generateAll(sb, list, left + 1, right, max);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (left > right) {
            sb.append(")");
            generateAll(sb, list, left, right + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * 12. 整数转罗马数字
     * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
     * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
     * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
     * 给你一个整数，将其转为罗马数字。
     * <p>
     * <p>
     * <p>
     * 示例1:
     * <p>
     * 输入:num = 3
     * 输出: "III"
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        int curNum = num;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {

            Integer key = entry.getKey();
            int multiple = curNum / key;
            for (int i = 1; i <= multiple; i++) {
                sb.append(entry.getValue());
            }
            curNum = curNum % key;

        }
        return sb.toString();
    }

    @Test
    public void testIntToRoman() {
        String s = intToRoman(3);
        assertEquals("III", s);
    }

    /**
     * 13. 罗马数字转整数
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     * <p>
     * 输入: s = "III"
     * 输出: 3
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        int result = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int value = map.get(s.charAt(i));
            if (i + 1 < length && value < map.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }

        }
        return result;
    }

    @Test
    public void testRomanToInt() {
        int mcmxciv = romanToInt("MCMXCIV");
        assertEquals(1994, mcmxciv);
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        String[] map = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        if (digits == null || digits == "") {
            return result;
        }

        for (int i = 0; i < digits.length(); i++) {
            int l = digits.charAt(i) - '2';

            if (result.isEmpty()) {
                for (int j = 0; j < map[l].length(); j++) {
                    char c = map[l].charAt(j);
                    result.add(c + "");
                }
                continue;
            }

            int size = result.size();
            for (int k = 0; k < size; k++) {
                String second = result.get(k);
                for (int j = 0; j < map[l].length() - 1; j++) {
                    char c = map[l].charAt(j);
                    result.add(second + c);
                }
                result.set(k, second + map[l].charAt(map[l].length() - 1));
            }


        }

        return result;
    }

    @Test
    public void testletterCombinations() {
        List<String> strings = letterCombinations("2");
        System.out.println(strings);
    }


    /**
     * 45.跳跃游戏
     *
     * @param nums
     * @return
     */

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int length = nums.length;
        int step = 0;
        int nextEnd = 0;
        int maxPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == nextEnd) {
                nextEnd = maxPosition;
                step++;
            }
        }
        return step;
    }

    private void doJump(int[] nums, int curStep, int pos, int length) {
    }

    @Test
    public void testJump() {
        int jump = jump(new int[]{2, 3, 1, 1, 4});
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }

        int maxJumpLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxJumpLength) {
                maxJumpLength = Math.max(maxJumpLength, nums[i] + i);
                if (maxJumpLength >= nums.length - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    @Test
    public void testcanJump() {
        boolean b = canJump(new int[]{2, 3, 1, 1, 4});
        System.out.println(b);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 102. 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> nodes = new LinkedList<>();
        if (root != null) {
            nodes.offerLast(root);
        }

        while (!nodes.isEmpty()) {
            int size = nodes.size();
            ArrayList<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pop = nodes.pollFirst();
                item.add(pop.val);
                if (pop.left != null) {
                    nodes.offerLast(pop.left);
                }
                if (pop.right != null) {
                    nodes.offerLast(pop.right);
                }

            }
            result.add(item);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> nodes = new LinkedList<>();
        if (root != null) {
            nodes.offerLast(root);
        }

        boolean dir = false;
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            ArrayList<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pop = nodes.pollFirst();
                if (dir) {
                    item.add(pop.val);
                } else {
                    item.add(0, pop.val);
                }
                if (pop.left != null) {
                    nodes.offerLast(pop.left);
                }
                if (pop.right != null) {
                    nodes.offerLast(pop.right);
                }
            }
            dir = !dir;
            result.add(item);
        }
        return result;
    }

    /**
     * 18.四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int swapValue = nums[i];
            int curPos = i;
            while (curPos > 0 && swapValue < nums[curPos - 1]) {
                nums[curPos] = nums[curPos - 1];
                curPos--;
            }
            nums[curPos] = swapValue;
        }
        System.out.println(Arrays.toString(nums));

        for (int a = 0; a < length; a++) {
            if (a != 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b < length; b++) {
                if (b != a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }


                int curTarget = target - nums[a] - nums[b];
                int d = length - 1;

                for (int c = b + 1; c < length; c++) {
                    if (c != b + 1 && nums[c] == nums[c - 1]) {
                        continue;
                    }

                    while (c < d) {
                        int diff = nums[c] + nums[d] - curTarget;
                        if (diff == 0) {
                            List<Integer> item = new ArrayList<>();
                            item.add(nums[a]);
                            item.add(nums[b]);
                            item.add(nums[c]);
                            item.add(nums[d]);
                            result.add(item);
                            break;
                        } else if (diff < 0) {
                            break;
                        } else {
                            d--;
                        }
                    }
                }
            }
        }

        return result;
    }


    /**
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */
    @Test
    public void testfourSum() {
        List<List<Integer>> lists = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println(lists);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode preN = null;
        ListNode curN = head;
        for (int i = 0; i < n - 1; i++) {
            curN = curN.next;
        }

        while (true) {
            curN = curN.next;
            if (curN == null) {
                if (preN == null) {
                    return head.next;
                } else {
                    preN.next = preN.next.next;
                }
                break;
            }

            if (preN == null) {
                preN = head;
            } else {
                preN = preN.next;
            }
        }
        return head;
    }


    /**
     * 26. 删除有序数组中的重复项
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * <p>
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     * <p>
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     * <p>
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 判题标准:
     * <p>
     * 系统会用下面的代码来测试你的题解:
     * <p>
     * int[] nums = [...]; // 输入数组
     * int[] expectedNums = [...]; // 长度正确的期望答案
     * <p>
     * int k = removeDuplicates(nums); // 调用
     * <p>
     * assert k == expectedNums.length;
     * for (int i = 0; i < k; i++) {
     * assert nums[i] == expectedNums[i];
     * }
     * 如果所有断言都通过，那么您的题解将被 通过。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2,_]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 1;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * [0,0,1,1,1,2,2,3,3,4]
     * 输出：
     * [0,1,1,1,2]
     * 预期结果：
     * [0,1,2,3,4]
     */
    @Test
    public void testremoveDuplicates() {
        int i = removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    }
}