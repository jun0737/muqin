package com.open.jun.practice.leetcode;

/**
 * @Author: 王俊
 * @Desctription: leetcode第五题
 * @Date: Created in 2024/5/3 下午11:57
 * @Version: 1.0
 */
public class T5 {

    public static void main(String[] args) {

    }


    /**
     * 给你一个字符串 s，找到 s 中最长的回文
     * 子串
     * <p>
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串
     */

    private String longestPalindrome(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }

        String ans = ""+s.charAt(0);
        for (int i = 0; i < s.length()-1; i++) {
            //以当前节点为中心扩散
            String subPre = expandAroundCenter(s, i, i);
            //以当前节点和下一节点为中心扩散
            String subDoublePre = expandAroundCenter(s, i, i+1);

            ans = ans.length() > subPre.length() ? ans : subPre;
            ans = ans.length() > subDoublePre.length() ? ans : subDoublePre;
        }

        return ans;
    }

    private String expandAroundCenter(String s, int left, int right) {

        int step = 0 ;
        while (left-step >= 0 && right+step < s.length() && s.charAt(left-step) == s.charAt(right+step)) {
            step ++ ;
        }
        return s.substring(left-step+1,right+step);
    }
}
