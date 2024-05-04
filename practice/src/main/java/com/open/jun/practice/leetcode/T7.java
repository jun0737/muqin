package com.open.jun.practice.leetcode;

/**
 * @Author: 王俊
 * @Desctription: leetcode第七题
 * @Date: Created in 2024/5/5 上午12:19
 * @Version: 1.0
 */
public class T7 {

    public static void main(String[] args) {
        new T7().reverse(1534236469);
    }

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0
     *
     * @param x
     * @return
     */
    public int reverse(int x) {

        int min = (int) -Math.pow(2, 31) / 10;
        int max = (int) (Math.pow(2, 31) - 1) / 10;

        int sum = 0;
        while (x != 0) {
            if (sum > max || sum < min) {
                sum = 0;
                break;
            }
            int modle = x % 10;
            sum = sum * 10 + modle;
            x /= 10;
        }
        return sum;
    }
}
