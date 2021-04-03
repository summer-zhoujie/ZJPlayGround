package com.example.playground.leetcode;

class Solution_190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int t_1 = 0x55555555;
        int t_2 = 0x33333333;
        int t_4 = 0x0f0f0f0f;
        int t_8 = 0x00ff00ff;
        int t_16 = 0x0000ffff;
        n = (n >>> 1 & t_1) | (n & t_1) << 1;
        n = (n >>> 2 & t_2) | (n & t_2) << 2;
        n = (n >>> 4 & t_4) | (n & t_4) << 4;
        n = (n >>> 8 & t_8) | (n & t_8) << 8;
        n = (n >>> 16 & t_16) | (n & t_16) << 16;
        return n;
    }

    public int reverseBits2(int n) {
        int result = 0;
        for (int i = 1; i <= 32; i++) {
            result |= (n & 1) << (32 - i);
            n = n >>> 1;
        }
        return result;
    }
}
