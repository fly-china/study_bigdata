package com.lpf.interview.current.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 测试优先级队列
 * 若完全二叉树的数组从0开始存储，那么：
 * * 设当前节点的 index = x,
 * * 那么 parent index = (x-1)/2,
 * * 左孩子 left  child index = 2 * x + 1,
 * * 右孩子 right child index = 2 * x + 2.
 *
 * @author lipengfei
 * @create 2020-07-27 12:06
 **/
public class TestPriorityQueue {

    public static void main(String[] args) throws Exception {

        Integer[] arr = {6, 8, 3, 2, 4, 7, 5, 10, 1, 9,11};
        PriorityQueue<Integer> queue2 = new PriorityQueue<>(Arrays.asList(arr));


        for (int i = 0; i < arr.length; i++) {
            System.out.println("小顶堆第" + i + "个元素为：" + queue2.poll());
        }

        System.out.println("------------下面是自己写的堆排序-------------");
        int[] arr2 = {6, 8, 3, 2, 4, 7, 5, 10, 1, 9};
        MaxHeap heap = new MaxHeap();
        int[] sortArr = heap.sort(arr2);
        String sortStr = "堆排序后的数组元素为：";
        for (int i = 0; i < sortArr.length; i++) {
            sortStr = sortStr + sortArr[i] + ", ";
        }
        System.out.println(sortStr.substring(0, sortStr.length() - 2));
    }

}
