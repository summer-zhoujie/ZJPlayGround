package com.example.playground.leetcode.depthFirst;

import java.util.HashMap;
import java.util.List;

public class Solution_5 {

    int subCount = 0;
    public String longestPalindrome(String s) {
        if(s==null||s.isEmpty())
            return s;
        subCount++;
        if(subCount>=s.length())
            return;
    }

    public static void main(String[] args) {

    }


}
