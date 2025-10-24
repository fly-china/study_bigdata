package com.lpf.interview.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 您是一名会议室管理员，掌握了今天所有预定的会议记录。
 * 比如：[6,10), [8,11), [9,17], [19, 20)
 * 求出最小需要几间会议室,可以满足会议预定的需求
 * 输入：时间段数组   输出：会议室个数
 *
 * @author lipengfei
 * @create 2025-02-24 14:54
 **/
public class DemoRight {

    //  [1,      10]    A：1-10
    //     [8,       11]    B:11
    //        [9,                17]  C:17
    //                      [15,        20]
    public static void main(String[] args) {
        int[] meet1 = {8, 11};
        int[] meet2 = {15, 20};
        int[] meet3 = {9, 17};
        int[] meet4 = {1, 10};
        int[] meet5 = {22, 24};
//        int[][] meetings = {meet1, meet2, meet3, meet4, meet5};
        int[][] meetings = {{1, 10}, {2, 15}, {20, 30}, {22, 40}, {25, 50}};
        int minNum = getMinNum(meetings);
        System.out.println("需要的最小会议室为：" + minNum);
    }

    public static int getMinNum(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        // 将会议室的开始时间，从小到大排序
        Arrays.sort(meetings, Comparator.comparingInt(e -> e[0]));

        // 使用优先队列（最小堆）来跟踪每个会议室的结束时间。
        // 堆顶存放最小值。含义为：最早结束会议的结束时间。
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        // 第一个会议需要一个会议室
        minHeap.add(meetings[0][1]);

        for (int i = 1; i < meetings.length; i++) {
            // 如果当前会议的开始时间大于或等于优先队列中的最小结束时间
            if (meetings[i][0] >= minHeap.peek()) {
                // 可以复用这个会议室，更新该会议室的结束时间
                minHeap.poll();
            }
            // 将当前会议的结束时间加入优先队列
            minHeap.add(meetings[i][1]);
        }

        // 优先队列的大小即为所需的最小会议室数量
        return minHeap.size();
    }

    public static int getMinNum2(int[][] meetings) {
        if (meetings == null) return 0;
        if (meetings.length <= 1) return meetings.length;

        // 将会议室的开始时间，从小到大排序
        Arrays.sort(meetings, Comparator.comparingInt(e -> e[0]));

        int left = meetings[0][0];
        int rightMaxTime = meetings[0][1];
        int maxNum = 1;

        int currNum = 0;
        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] > rightMaxTime) {
                // 未出现重叠时间的会议室。
                currNum++;

            }

        }

        return 0;
    }

    static class User {
        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
