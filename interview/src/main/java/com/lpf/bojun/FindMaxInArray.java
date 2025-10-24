package com.lpf.bojun;

public class FindMaxInArray {

    public static void main(String[] args) {
//        int[] arr = {1, 3, 3, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 9, 10, 10, 11, 11, 12, 23, 33, 33, 33, 10, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1};
        int[] arr = {2, 3, 4, 2, 2, 2, 1};
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int midIdx = left + (right - left) / 2;

            int midSameValueCnt = 1; // 自己本身也算一个；
            int low = midIdx - 1;
            while (arr[low] == arr[midIdx]) {
                low--;
                midSameValueCnt++;
            }
            int high = midIdx + 1;
            while (arr[high] == arr[midIdx]) {
                high++;
                midSameValueCnt++;
            }

            if (arr[low] < arr[midIdx] && arr[high] < arr[midIdx]) {
                // 输出最大数字
                System.out.println("最大数字: " + arr[midIdx]);
                System.out.println("个数: " + midSameValueCnt);
                return;
            } else if (arr[low] < arr[midIdx] && arr[high] > arr[midIdx]) {
                left = high;
            } else if (arr[low] > arr[midIdx] && arr[high] < arr[midIdx]) {
                right = low;
            }

        }
    }


    public static void main2(String[] args) {
//        int[] arr = {1, 3, 3, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 9, 10, 10, 11, 11, 12, 23, 33, 33,33, 10, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1};
        int[] arr = {2, 3, 4, 2, 2, 2, 1};
        int maxIndex = binarySearch(arr);
        // 通过索引获取最大值
        int maxValue = arr[maxIndex];
        // 调用优化后的统计方法统计最大值出现的次数
        int count = countOccurrences(arr, maxIndex, maxValue);
        // 输出最大数字
        System.out.println("最大数字: " + maxValue);
        // 输出最大数字出现的个数
        System.out.println("个数: " + count);
    }

    public static int binarySearch(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int midIdx = left + (right - left) / 2;

            int midSameValueCnt = 0;
            int low = midIdx - 1;
            while (arr[low] == arr[midIdx]) {
                low--;
                midSameValueCnt++;
            }
            int high = midIdx + 1;
            while (arr[high] == arr[midIdx]) {
                high++;
                midSameValueCnt++;
            }

            if (arr[low] < arr[midIdx] && arr[high] < arr[midIdx]) {
                System.out.println("----个数：" + midSameValueCnt);
                return midIdx;
            } else if (arr[low] < arr[midIdx] && arr[high] > arr[midIdx]) {
                left = high;
            } else if (arr[low] > arr[midIdx] && arr[high] < arr[midIdx]) {
                right = low;
            }

        }

        return -1;
    }


    // 二分查找函数，用于找到最大值的索引
//    public static int binarySearch(int[] arr, int left, int right) {
//        int maxIndex = left;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (arr[mid] > arr[maxIndex]) {
//                maxIndex = mid;
//            }
//            // 不能简单根据中间元素和其相邻元素比较来缩小区间，这里采用简单扩展方式
//            left++;
//            right--;
//        }
//        return maxIndex;
//    }


//    // 二分查找函数，用于找到最大值的索引
//    public static int binarySearch(int[] arr, int left, int right) {
//        // 初始化最大索引为左边界
//        int maxIndex = left;
//        // 当左边界小于等于右边界时进行二分查找
//        while (left <= right) {
//            // 计算中间索引，避免 (left + right) 可能的溢出
//            int mid = left + (right - left) / 2;
//            // 如果中间元素大于等于当前最大元素
//            if (arr[mid] >= arr[maxIndex]) {
//                // 更新最大索引为中间索引
//                maxIndex = mid;
//                // 缩小搜索范围到中间索引右侧
//                left = mid + 1;
//            } else {
//                // 缩小搜索范围到中间索引左侧
//                right = mid - 1;
//            }
//        }
//        // 返回最大元素的索引
//        return maxIndex;
//    }

    // 统计最大值出现的次数，利用最大值索引进行优化
    public static int countOccurrences(int[] arr, int maxIndex, int maxValue) {
        // 初始化计数器为 1，因为 maxIndex 对应的元素就是最大值
        int count = 1;
        // 从 maxIndex 向前查找相同值的元素
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (arr[i] == maxValue) {
                // 如果找到相同值，计数器加 1
                count++;
            } else {
                // 若遇到不同值，停止向前查找
                break;
            }
        }
        // 从 maxIndex 向后查找相同值的元素
        for (int i = maxIndex + 1; i < arr.length; i++) {
            if (arr[i] == maxValue) {
                // 如果找到相同值，计数器加 1
                count++;
            } else {
                // 若遇到不同值，停止向后查找
                break;
            }
        }
        // 返回最大值出现的总次数
        return count;
    }
}