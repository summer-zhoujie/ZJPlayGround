package com.example.playground.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        Integer[] arrs = buildKmpNext1("leeto");
//        System.out.println(Arrays.asList(arrs));
//        int i = strStr("aabaaabaaac", "aabaaac");
//        System.out.println(i);
        boolean abaababaab = isMonotonic(new int[]{6,5,4,4});
        System.out.println(abaababaab);
    }

    public static boolean isMonotonic(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        Boolean r = null;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }

            if (r == null) {
                r = nums[i] > nums[i - 1];
            } else if (r != nums[i] > nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean canMakeArithmeticProgression(int[] arr) {
        //sort
        int value,j;
        for (int i = 1; i < arr.length; i++) {
            value = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (value < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }

        int interval = -1;
        for (int i = 1; i < arr.length; i++) {
            if (interval < 0) {
                interval = arr[i] - arr[i - 1];
            } else if (interval != (arr[i] - arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    // 1822
    public int arraySign(int[] nums) {
        int r = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                r = -r;
            }
        }
        return r;
    }

    // 66
    public int[] plusOne(int[] digits) {
        int extra = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
                if (i == 0) {
                    extra = 1;
                }
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if (extra == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            System.arraycopy(digits, 0, newDigits, 1, digits.length);
            return newDigits;
        } else {
            return digits;
        }
    }

    // 283
    public void moveZeroes(int[] nums) {
        int length = nums.length, i = 0, j = 0;
        while (j < length) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                if (i != j) {
                    nums[j] = 0;
                }
                i++;
            }
            j++;
        }

    }

    //459
    public static boolean repeatedSubstringPattern(String s) {

        boolean isMatch;
        int length = s.length();
        if (length <= 1) {
            return false;
        }
        for (int i = 0, stepLength = 1; stepLength < length && i < length; i++) {
            if (length % stepLength == 0) {
                isMatch = true;
                for (int j = 0; j < length; j++) {
                    if (s.charAt(j % stepLength) != s.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    return true;
                }
            }
            stepLength++;
        }
        return false;
    }

    // 242
    public boolean isAnagram(String s, String t) {
        int[] counts = new int[26];
        Arrays.fill(counts, 0);
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            counts[index]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            counts[index]--;

        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] buildKmpNext1(String needle) {
//        int length = needle.length();
//        Integer[] next = new Integer[length + 1];
//        Arrays.fill(next, 0);
//        for (int i = 2, j = 0; i < next.length; i++) {
//            while (j > 0 && needle.charAt(i - 1) != needle.charAt(next[j])) {
//                j = next[j];
//            }
//            if (needle.charAt(i - 1) == needle.charAt(j)) {
//                j++;
//            }
//            next[i] = j;
//        }
        Integer[] next = new Integer[needle.length() + 1];
        next[0] = next[1] = 0;
        for (int i = 2, j = 0; i < next.length; i++) {
            while (j > 0 && needle.charAt(i - 1) != needle.charAt(j)) {
                j = next[j];
            }
            if (needle.charAt(i - 1) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    // 389. 找不同
    public char findTheDifference(String s, String t) {
        int[] arr = new int[26];
        Arrays.fill(arr, 0);
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            index = c - 'a';
            arr[index]++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            index = c - 'a';
            arr[index]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                return (char) (i + 'a');
            }
        }
        return ' ';
    }

    // 28.
    public static int strStr(String haystack, String needle) {

        // planA
//        int lengthOfNeedle = needle.length();
//        boolean result = false;
//        for (int i = 0; i <= haystack.length() - lengthOfNeedle; i++) {
//            result = true;
//            for (int j = 0; j < lengthOfNeedle; j++) {
//                if (needle.charAt(j) != haystack.charAt(i + j)) {
//                    result = false;
//                    break;
//                }
//            }
//            if (result) {
//                return i;
//            }
//        }
//        return -1;

        // planB KMP
        int[] next = new int[needle.length() + 1];
        next[0] = next[1] = 0;
        for (int i = 2, j = 0; i < next.length; i++) {
            while (j > 0 && needle.charAt(i - 1) != needle.charAt(j)) {
                j = next[j];
            }
            if (needle.charAt(i - 1) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        int j = 0;
        for (int i = 0; i < haystack.length(); ) {
            while (i < haystack.length() && j < needle.length() && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == 0) {
                i++;
                continue;
            }
            if (j == needle.length()) {
                return i - j;
            }
            j = next[j];
        }

        return -1;
    }

}
