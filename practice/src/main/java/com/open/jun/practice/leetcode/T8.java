package com.open.jun.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王俊
 * @Desctription: leetcode第八题
 * @Date: Created in 2024/5/5 上午12:37
 * @Version: 1.0
 */
public class T8 {

    public static void main(String[] args) {
        new T8().myAtoi("-91283472332");
    }

    /**
     * 实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     *
     * @param str
     * @return 致敬大佬 ， 官方题解太棒了，抄一波
     */
    public int myAtoi(String str) {
        if(str == null || str.length() == 0 ) {
            return 0;
        }
        AtoiHelper atoiHelper = new AtoiHelper();
        for (int i = 0; i < str.length(); i++) {
            atoiHelper.update(str.charAt(i));
        }
        return atoiHelper.getAns();
    }

    private class AtoiHelper {

        private final long _MAX = Integer.MAX_VALUE;
        private final long _MIN = Integer.MIN_VALUE;

        private String _START = "start";
        private String _SIGNED = "signed";
        private String _NUM = "num";
        private String _END = "end";

        private int flag = 1;
        private long ans = 0;
        private String step = _START;

        private final Map<String, String[]> status = new HashMap<>() {{
            put(_START, new String[]{_START, _SIGNED, _NUM, _END});
            put(_SIGNED, new String[]{_END, _END, _NUM, _END});
            put(_NUM, new String[]{_END, _END, _NUM, _END});

            put(_END, new String[]{_END, _END, _END, _END});
        }};

        private void numMethod(char ch) {
            if(ans == _MAX || ans == -_MIN) {
                return;
            }
            ans = ans * 10 + ch - '0';
            ans = flag == 1 ? Math.min(ans, _MAX) : Math.min(ans, -_MIN);

        }

        private void singedMethod(char ch) {
            flag = ch == '+' ? 1 : -1;
        }

        private int getNextStep(char ch) {
            if (ch == ' ') {
                return 0;
            }
            if (ch == '-' || ch == '+') {
                return 1;
            }
            if (ch <= '9' && ch >= '0') {
                return 2;
            }
            return 3;
        }

        public void update(char ch) {
            step = status.get(step)[getNextStep(ch)];
            if (_NUM.equals(step)) {
                numMethod(ch);
            } else if (_SIGNED.equals(step)) {
                singedMethod(ch);
            }
        }

        public int getAns() {
            return flag * (int) ans;
        }

    }

}
