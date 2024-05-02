package com.open.jun.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王俊
 * @Desctription: leetcode第三题
 * @Date: Created in 2024/5/3 上午12:23
 * @Version: 1.0
 */
public class T3 {

    //给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度
    public static void main(String[] args) {

    }

    private int lengthOfLastSubstring(String s) {

        int maxLength = Integer.MIN_VALUE;

        if (s == null || s.length() == 0) {
            return 0;
        }

        int fromIndex = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                fromIndex = Math.max(fromIndex, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i);
            maxLength = Math.max(maxLength, i - fromIndex + 1);
        }

        return maxLength;
    }

}
