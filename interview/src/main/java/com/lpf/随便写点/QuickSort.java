package com.lpf.随便写点;

/**
 * 快速排序
 *
 * @author lipengfei
 * @create 2020-07-23 22:36
 **/
public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {4, 0,0, 5, 7, 7, 9, 3, 0, 6};
        int[] arr = {110, 100, 0};
        quickSort20241223(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    private static void quickSort20241223(int[] nums, int a, int b) {
        if (nums == null || nums.length <= 1 || a >= b) return;

        int start = a;
        int end = b;
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] > pivot) {
                end--;
            }
            nums[start] = nums[end];

            while (start < end && nums[start] <= pivot) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = pivot;

        quickSort20241223(nums, a, start - 1);
        quickSort20241223(nums, start + 1, b);
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


    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
