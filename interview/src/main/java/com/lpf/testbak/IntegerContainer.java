package com.lpf.testbak;

import java.util.PriorityQueue;

public class IntegerContainer {
    private PriorityQueue<Integer> maxHeap; // 最大堆，存储较小的一半数据
    private PriorityQueue<Integer> minHeap; // 最小堆，存储较大的一半数据
    private int maxValue = Integer.MIN_VALUE;
    private int minValue = Integer.MAX_VALUE;

    public IntegerContainer() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // 最大堆
        minHeap = new PriorityQueue<>(); // 最小堆
    }

    // 添加一个整数到容器中
    public void add(int num) {
        // 更新最大值和最小值
        maxValue = Math.max(maxValue, num);
        minValue = Math.min(minValue, num);

        // 添加到最大堆
        maxHeap.add(num);

        // 将最大堆的最大值移动到最小堆
        minHeap.add(maxHeap.poll());

        // 保持两个堆的平衡
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // 获取最大值
    public int getMax() {
        return maxValue;
    }

    // 获取最小值
    public int getMin() {
        return minValue;
    }

    // 获取中间值
    public int getMedian() {
        // 中间值始终是最大堆的堆顶
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        IntegerContainer container = new IntegerContainer();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);

        System.out.println("Max: " + container.getMax()); // 200
        System.out.println("Min: " + container.getMin()); // 1
        System.out.println("Median: " + container.getMedian()); // 3

        container.add(50);
        System.out.println("Max: " + container.getMax()); // 200
        System.out.println("Min: " + container.getMin()); // 1
        System.out.println("Median: " + container.getMedian()); // 3
    }
}