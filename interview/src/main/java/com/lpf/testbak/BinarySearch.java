package com.lpf.testbak;

import java.util.Arrays;

/**
 * @author lipengfei
 * @create 2025-02-25 11:08
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int targetNum = 9;

        int left = 0;
        int right = arr.length - 1;

        Arrays.sort(arr);
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == targetNum) {
                System.out.println("find target num. idx = " + mid);
                return;
            } else if (arr[mid] > targetNum) {
                right--;
            } else {
                left++;
            }
        }
    }
}
