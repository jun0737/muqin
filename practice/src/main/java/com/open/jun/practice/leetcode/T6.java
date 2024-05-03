package com.open.jun.practice.leetcode;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 王俊
 * @Desctription: leetcode第6题
 * @Date: Created in 2024/5/4 上午12:52
 * @Version: 1.0
 */
public class T6 {

    public static void main(String[] args) {
        new T6().convert("AB", 3);
    }

    public String convert(String s, int numRows) {

        if (s == null || s.length() < 2 || numRows < 2) {
            return s;
        }

        boolean isDown = true;
        StringBuffer[] list = new StringBuffer[numRows];
        //不可以用fill方法填充，因为fill方法会使用同一个对象/值填充这个数组的每一项
        //所以这儿使用fill方法填充的结果是数组的每一项其实都是同一个buffer
        //Arrays.fill(list, new StringBuffer());
        int index = 0;
        while (index < s.length()) {
            if (isDown) {
                for (int i = 0; i < numRows && index < s.length(); i++) {
                    if(list[i] == null) {
                        list[i] = new StringBuffer();
                    }
                    list[i].append(s.charAt(index));
                    index++;
                }
                isDown = false;
            } else {
                for (int i = numRows - 2; i > 0 && index < s.length(); i--) {
                    list[i].append(s.charAt(index));
                    index++;
                }
                isDown = true;
            }
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < numRows; i++) {
            if(list[i] != null) {
                result.append(list[i]);
            }
        }
        return result.toString();
    }

}
