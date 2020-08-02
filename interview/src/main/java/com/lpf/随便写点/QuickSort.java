package com.lpf.随便写点;

/**
 * 快速排序
 *
 * @author lipengfei
 * @create 2020-07-23 22:36
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {4, 2, 5, 7, 1, 9, 3, 0, 6};
        quickSort1(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    private static void quickSort1(int[] arr, int begin, int end) {
        if (arr == null || arr.length <= 1 || begin < 0 || end > arr.length || begin >= end)
            return;

        int privot = arr[begin];
        int endFlag = end;
        int beginFlag = begin;
        while (begin < end) {
            while (begin < end && arr[end] > privot) {
                end--;
            }
            if (begin < end) {
                arr[begin] = arr[end];
                begin++;
            }
            while (begin < end && arr[begin] <= privot) {
                begin++;
            }
            if (begin < end) {
                arr[end] = arr[begin];
                end--;
            }
        }
        arr[begin] = privot;
        quickSort1(arr, beginFlag, begin - 1);
        quickSort1(arr, begin + 1, endFlag);
    }

    private static void quickSort2(int[] arr, int low, int high) {
        if (arr == null || arr.length <= 1 || low > high)
            return;

        int key = arr[low];
        int m = low;
        int n = high;
        // {4, 2, 5, 7, 1, 9, 3, 0, 6}
        int temp;
        while (m < n) {
            while (m < n && arr[n] > key) {
                n--;
            }
            while (m < n && arr[m] <= key) {
                m++;
            }
            if (m < n) {
                temp = arr[m];
                arr[m] = arr[n];
                arr[n] = temp;
            }

        }

        temp = arr[m];
        arr[m] = arr[low];
        arr[low] = temp;

        quickSort2(arr, low, m - 1);
        quickSort2(arr, m + 1, high);
    }


}
