package com.open.jun.muqin.common.utils;

public final class SelfSearch {

    private SelfSearch(){}

    public static int binarySearch(int[] source,int target ){

       int left = 0 ;
       int right = source.length - 1;
       while (left <= right) {
           int mid = left + (right-left)/2;
           if(source[mid] == target) {
               return mid;
           } else if (source[mid] < target) {
               left = mid + 1;
           }else{
               right = mid - 1;
           }
       }

       return -1;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,3,5,7,22,23,45,190,324};
        System.out.println(binarySearch(test,22));
        System.out.println(binarySearch(test,324));
        System.out.println(binarySearch(test,1));
        System.out.println(binarySearch(test,0));
        System.out.println(binarySearch(test,325));
    }

}
