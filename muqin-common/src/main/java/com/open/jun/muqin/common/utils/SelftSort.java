package com.open.jun.muqin.common.utils;

import java.util.*;

public final class SelftSort {

    private SelftSort(){}


    public static void bubbleSort(int[] souce) {

        if(Objects.isNull(souce) || souce.length < 2) {
            return;
        }

        for(int i = 0 ; i < souce.length -1 ; i ++) {
            boolean flag = true;
            for(int j = 0 ; j < souce.length - i - 1; j++) {
                if(souce[j] > souce[j+1]) {
                    souce[j] += souce[j+1];
                    souce[j+1] = souce[j]-souce[j+1];
                    souce[j] = souce[j]-souce[j+1];
                    flag = false;
                }
            }
            if(flag) break;
        }

    }

    public static void insertSort(int[] source) {
        for (int i = 1; i < source.length; i++) {
            for (int j = i ; j > 0 ; j --) {
                if(source[j] < source[j-1]) {
                    source[j] = source[j] + source[j-1];
                    source[j-1] = source[j] - source[j-1];
                    source[j] = source[j] - source[j-1];
                }else{
                    break;
                }
            }
        }
    }

    public static void selectSort(int[] source) {
        for (int i = 0; i < source.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < source.length; j++) {
                if(source[j] < source[minIndex]) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                source[i] += source[minIndex];
                source[minIndex] = source[i] - source[minIndex];
                source[i] -= source[minIndex];
            }
        }
    }

    public static void quickSort(int[] source, int start , int end) {

        if (start < end) {
            int aim = partitionSingle(source,start,end);
            quickSort(source,start,aim-1);
            quickSort(source,aim+1,end);
        }

    }

    private static int partition(int[] source, int start , int end) {

        int target = source[start];
        int left = start,right = end;

        while (left < right) {
            while (left < right && target <= source[right]) {
                right -- ;
            }
            if ( left < right) { // 如果左指针和右指针没有相遇，将右指针所指的元素赋值给左指针所指的位置
                source[left] = source[right];
                left ++;
            }
            while (left < right && target >= source[left]) {
                left ++ ;
            }
            if(left < right) {
               source[right] = source[left];
               right -- ;
            }
        }

        source[left] = target;

        return left;

    }

    private static int partitionSingle(int[] source , int left , int right) {

        int target = source[right];
        int i = left;
       for(int k = left ; k < right ; k ++) {
            if(source[k] < target) {
                if(i != k) {
                    source[k] = source[k] + source[i];
                    source[i] = source[k] - source[i];
                    source[k] = source[k] - source[i];
                }
                i ++ ;
            }
       }
       if(i != right) {
           source[i] = source[i] + source[right];
           source[right] = source[i] - source[right];
           source[i] = source[i] - source[right];
       }

       return i;
    }

    public static void shellSort(int[] source ) {
        int gap = source.length / 2;
        int temp;
        int j;
       while (gap > 0) {
        for(int i = gap ; i < source.length ; i++) {
            temp = source[i];
            j = i - gap;
            while (j >= 0 && temp < source[j]) {
                source[j+gap] = source[j];
                j -= gap;
            }
            source[j+gap] = temp;
        }
        gap /= 2;
       }
    }

    public static void mergeSort(int[] source,int left , int right) {

        if(left >= right) {
            return;
        }

        int mid = left + (right-left)/2;
        mergeSort(source,left,mid);
        mergeSort(source,mid+1,right);

        merge(source,left,mid,right);

    }

    private static void merge(int[] source, int left, int mid, int right) {

        int[] temp = new int[right-left+1];
        int i = left,j=mid+1,k = 0;

        while (i <= mid && j <= right) {
            if(source[j] < source[i] ) {
                temp[k ++] = source[j ++];
            }else{
                temp[k ++] = source[i ++];
            }
        }

        while (i <= mid) {
            temp[k ++] = source[i ++];
        }

        while (j <= right) {
            temp[k ++] = source[j ++];
        }

        for (int p = 0; p < temp.length; p++) {
            source[p+left] = temp[p];
        }

    }

    public static void bucketSort(int[] source, Integer bucketSize) {
        int _MAX = source[0];
        int _MIN = source[0];
        for (int i = 1; i < source.length; i++) {
           _MIN = Math.min(_MIN,source[i]);
           _MAX = Math.max(_MAX,source[i]);
        }
        if(Objects.isNull(bucketSize) || bucketSize <= 0) {
            bucketSize = source.length / 10;
        }

        int bucketCount = (_MAX - _MIN ) / bucketSize + 1;

        List<Integer>[] bucketList = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            bucketList[i] = new ArrayList<>();
        }

        //入桶
        for (int i : source) {
            bucketList[(i-_MIN)/bucketSize].add(i);
        }

        int k = 0;
        for (List<Integer> integers : bucketList) {
            if(!integers.isEmpty()) {
                int[] nums = new int[integers.size()];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = integers.get(i);
                }
                shellSort(nums);
                for (int num : nums) {
                    source[k ++] = num;
                }
            }
        }

    }

    public static void radixSort(int[] source) {

        int _MAX = source[0];
        for (int i = 1; i < source.length; i++) {
            _MAX = Math.max(_MAX,source[i]);
        }

        int maxLen = (_MAX+"").length();

        List<Integer>[] bucketList = new List[10];
        for (int i = 0; i < 10; i++) {
            bucketList[i] = new ArrayList<>();
        }

        int n = 1;
        for (int i = 0; i < maxLen; i++) {
            //入桶
            for (int ele : source) {
                bucketList[(ele/n)%10].add(ele);
            }
            //将桶内数据放回数组，并清空桶
            int index = 0 ;
            for (int i1 = 0; i1 < bucketList.length; i1++) {
                if(!bucketList[i1].isEmpty()) {
                    for (Integer ele : bucketList[i1]) {
                        source[index ++] = ele;
                    }
                    bucketList[i1].clear();
                }
            }
            n *= 10;
        }

    }

    public static void countSort(int[] source) {

        int _MAX = source[0];
        int _MIN = source[0];

        for (int i = 1; i < source.length; i++) {
            _MAX = Math.max(_MAX,source[i]);
            _MIN = Math.min(_MIN,source[i]);
        }

        int[] temp = new int[_MAX - _MIN + 1];
        Arrays.fill(temp,-1);
        for (int i : source) {
            temp[i-_MIN] ++;
        }

        int index = 0 ;
        for (int i = _MIN; i <= _MAX ; i++) {
            while (temp[i-_MIN] >= 0) {
                source[index ++] = i;
                temp[i-_MIN] -- ;
            }
        }

        Stack<Character> stack = new Stack<>();

    }



    public static void main(String[] args) {
        int[] test = new int[]{10,1,110,21,0,90,87,18,111};
        //bubbleSort(test);
        //insertSort(test);
        //selectSort(test);
        //quickSort(test,0,test.length-1);
        //shellSort(test);
        //mergeSort(test,0,test.length-1);
        //bucketSort(test,3);
        //radixSort(test);
        countSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }

}
