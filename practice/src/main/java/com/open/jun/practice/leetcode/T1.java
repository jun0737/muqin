package com.open.jun.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王俊
 * @Desctription: leetCode第一题
 * @Date: Created in 2024/4/27 上午12:14
 * @Version: 1.0
 */
public class T1 {

    public static void main(String[] args) {

    }

    /**
     * 寻找数组中两个数的索引，使得它们的和等于一个特定目标数。
     *
     * @param nums 包含整数的数组。
     * @param target 目标和。
     * @return 包含两个整数索引的数组，它们的和等于目标数。如果找不到这样的两个数，则返回空数组。
     */
    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        Map<Integer,Integer> diffIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(diffIndex.containsKey(nums[i])) {
                answer[0] = diffIndex.get(nums[i]);
                answer[1] = i;
                break;
            }else {
                diffIndex.put(target-nums[i],i);
            }
        }
        return answer;
    }

}
