package com.lpf.interview.current.queue;

import java.util.Arrays;

/**
 * 大顶堆
 */
class MaxHeap {

    public int[] sort(int[] sourceArray) throws Exception {
        if (sourceArray == null || sourceArray.length <= 1)
            return sourceArray;

        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int len = arr.length;

        // 将数组构建为大顶堆
        initMaxHeap(arr, len);

        // 因为只有堆顶为最大的元素，每次循环会将堆顶元素提取出来，并重新将剩余元素构建堆
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            siftDown(arr, 0, len);
        }

        return arr;
    }

    /**
     * 根据传入的数组，构建大顶堆
     */
    public void initMaxHeap(int[] arr, int len) {
        // 叶子节点，无需处理。
        // 从最后一个非叶子节点开始
        for (int i = (len >>> 1) - 1; i >= 0; i--) {
            siftDown(arr, i, len);
        }
    }

    /**
     * 对长度为len的数组arr中，下标从i，进行堆化
     */
    private void siftDown(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 将堆顶元素和左右孩子比较，若左右孩子更大，则选择一个进行交换。将被交换的孩子 自子树进行堆化。
        int maxIndex = i;
        if (left < len && arr[left] > arr[i]) {
            maxIndex = left;
        }

        if (right < len && arr[right] > arr[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != i) {
            swap(arr, i, maxIndex);
            // 继续堆化 孩子树
            siftDown(arr, maxIndex, len);
        }
    }

    /**
     * 向堆中添加元素
     */
    private int[] addEle(int[] sourceArr, int val) {
        // 扩容一个元素拷贝到新数组，并将新插入的值放入最后一个index
        int[] arr = new int[sourceArr.length + 1];
        System.arraycopy(sourceArr, 0, arr, 0, sourceArr.length);
        arr[arr.length - 1] = val;

        siftUp(arr, arr.length - 1);
        return arr;
    }

    /**
     * 对长度为len的数组arr中，下标从i，进行堆化
     */
    private void siftUp(int[] arr, int insertIndex) {
        int parentIndex = (insertIndex - 1) / 2;
        if (insertIndex > 0 && arr[parentIndex] < arr[insertIndex]) {
            swap(arr, parentIndex, insertIndex);

            siftUp(arr, parentIndex);
        }
    }

    /**
     * 交换数组中i和j的元素
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws Exception {

//        Integer[] arr = {6, 8, 3, 2, 4, 7, 5, 10, 1, 9};
//        PriorityQueue<Integer> queue2 = new PriorityQueue<>(Arrays.asList(arr));
//
//
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("小顶堆第" + i + "个元素为：" + queue2.poll());
//        }
//
//        System.out.println("------------下面是自己写的堆排序-------------");
        int[] arr2 = {6, 8, 3, 2, 4, 7, 5, 10, 1, 9};
        MaxHeap heap = new MaxHeap();
        heap.initMaxHeap(arr2, arr2.length);
        arr2 = heap.addEle(arr2, 11);
        arr2 = heap.addEle(arr2, 12);
        arr2 = heap.addEle(arr2, 10);
        arr2 = heap.addEle(arr2, 13);
        arr2 = heap.addEle(arr2, 13);

        int[] sortArr = heap.sort(arr2);
        String sortStr = "堆排序后的数组元素为：";
        for (int i = 0; i < sortArr.length; i++) {
            sortStr = sortStr + sortArr[i] + ", ";
        }
        System.out.println(sortStr.substring(0, sortStr.length() - 2));
    }
}