package com.lpf.interview.stack;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * @author lipengfei
 * @create 2019-02-20 16:57
 **/
public class MinStack {
    private int stackMax = 500;

    private int[] stack;
    private int top = -1;

    private int[] minStack;// 栈顶为stack栈的最小元素
    private int minStackTop = -1;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new int[stackMax];
        minStack = new int[stackMax];
    }

    public void push(int x) {
        // 扩容
        if(top == (stackMax-1)){
            stack = new int[stackMax * 2];
            minStack = new int[stackMax * 2];
            stackMax *= 2;
        }
        stack[++top] = x;

        // 比较最小栈的元素
        if(minStackTop == -1){
            ++minStackTop;
            minStack[minStackTop] = x;
        }else{
            if(x <= minStack[minStackTop]){// 小于栈顶元素，新元素入栈
                ++minStackTop;
                minStack[minStackTop] = x;
            }
        }

    }

    public void pop() {
        int peek = top();
        int minStackpeek =  minStack[minStackTop];
        --top;

        if(peek == minStackpeek)
            --minStackTop;
    }

    public int top() {
        if (top == -1)
            return -999;
        return stack[top];
    }

    public int getMin() {
        if(minStackTop == -1)
            return -999;
        return minStack[minStackTop];
    }
    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//
//        Integer pek1 = stack.peek();
//        System.out.println(pek1);
//
//        Integer pop = stack.pop();
//        System.out.println(pop);
//
//        Integer pek2 = stack.peek();
//        System.out.println(pek2);
        MinStack minStack  = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);

        int min = minStack.getMin();
        System.out.println(min);

        minStack.pop();

        int min2 = minStack.getMin();
        System.out.println(min2);
    }
}
