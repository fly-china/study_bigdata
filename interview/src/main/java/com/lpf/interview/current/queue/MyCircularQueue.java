package com.lpf.interview.current.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环队列
 *
 * @author lipengfei
 * @create 2019-02-18 11:16
 **/
public class MyCircularQueue {

    private List<Integer> data;

    private int p_start  = -1;
    private int p_end = -1;
    private int capacity = 0;

    public MyCircularQueue(){}

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.data = new ArrayList<Integer>(k);
        this.capacity = k;
//        p_start = -1;
//        p_end = -1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull())
            return false;
        if(isEmpty())
            p_start =0;
        if(++p_end == capacity)
            p_end = 0;
        if(data.size() >= capacity){
            data.set(p_end, value);
        }else{
            data.add(p_end, value);
        }
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty())
            return false;

        if(p_start == p_end){
            p_start = -1;
            p_end = -1;
        }else{
//            if(++p_start == capacity)
//                p_start = 0;
            p_start = (p_start + 1) % capacity;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty())
            return -1;
        return data.get(p_start);
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty())
            return -1;
        return data.get(p_end);
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (p_start == -1 || p_end == -1);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if((p_start + capacity - p_end) % capacity ==1)
            return true;
        return false;
    }

    public static void main(String[] args) {
//        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//
//        System.out.println(circularQueue.enQueue(1));  // 返回 true
//
//       System.out.println(circularQueue.enQueue(2));  // 返回 true
//
//       System.out.println(circularQueue.enQueue(3));  // 返回 true
//
//       System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满
//
//       System.out.println(circularQueue.Rear());  // 返回 3
//
//       System.out.println(circularQueue.isFull());  // 返回 true
//
//       System.out.println(circularQueue.deQueue());  // 返回 true
//
//       System.out.println(circularQueue.enQueue(4));  // 返回 true
//
//       System.out.println(circularQueue.Rear());  // 返回 4


        MyCircularQueue queue = new MyCircularQueue(30);
        // 30个数
        int[] example = {71,32,1,32,8,6,8,3,56,41,14,6,25,44,84,59,4,40,11,94,72,19,20,58,54,65,59,26,10,14};

        for (int i = 0 ; i <30; i++){
            queue.enQueue(example[i]);
        }

        for (int i = 0 ; i < 7; i++){
          boolean flag =   queue.deQueue();
          if(!flag){
              System.out.println("出队列失败");
          }
        }


        System.out.println("1、当前队尾元素：" + queue.Rear());
        System.out.println("1、当前队首元素：" + queue.Front());

        queue.enQueue(2);
        queue.deQueue();
        queue.enQueue(37);

        System.out.println("2、当前队尾元素：" + queue.Rear());
        System.out.println("2、当前队首元素：" + queue.Front());
    }


//    @Test
//    public void testCircularQueue(){
//        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//        circularQueue.enQueue(1);
//
////        Assert.assertTrue("非法入队列",  circularQueue.enQueue(1));
//    }
}
