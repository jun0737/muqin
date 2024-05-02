package com.open.jun.practice.leetcode;

/**
 * @Author: 王俊
 * @Desctription: Leetcode第四题
 * @Date: Created in 2024/5/3 上午1:08
 * @Version: 1.0
 */
public class T4 {


    //给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
    //
    //算法的时间复杂度应该为 O(log (m+n)) 。

    public static void main(String[] args) {

    }

    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int mid = len/2;
        int index1 = 0,index2 = 0;
        int first = 0;
        int second = 0;
        while (mid -- > 0) {
            second = first;
            if(index1 < len1 && index2 < len2) {
                if(nums1[index1] <= nums2[index2]) {
                    first = nums1[index1];
                    index1 ++ ;
                }else{
                    first = nums2[index2];
                    index2 ++;
                }
            }else if(index1 < len1){
                first = nums1[index1];
                index1 ++;
            }else{
                first = nums2[index2];
                index2 ++;
            }
        }
        double ans = first;
        if(len % 2 == 0) {
            ans = (first + second)/2.0;
        }
        return ans;
    }

}
