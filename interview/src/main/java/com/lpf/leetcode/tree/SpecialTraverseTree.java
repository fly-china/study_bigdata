package com.lpf.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 按照指定顺序遍历二叉树
 * <p>
 *            A
 *       B          C
 *   D     E    F     G
 * 1  2   3 4  5 6  7   8
 * <p>
 * 遍历输出顺序：A  CB  DEFG 87654321
 * 规则：奇数层-从左向右遍历；偶数层-从右向左遍历
 */
public class SpecialTraverseTree {
    public static void main(String[] args) {

//        String[] strArr = {"A", "B", "C", "D", "E", "F", "G", "1", "2", "3", "4", "5", "6", "7", "8"};
        String[] strArr = {"A", "B", "C", "D", "E", "F", "G", "1", "2", "3", "4", "5"};
        int length = strArr.length;
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        List<String> printList = new ArrayList<>(strArr.length);

        stackB.push(0);
//        printList.add(strArr[0]);

        int loopNum = 1;
        while (!stackA.empty() || !stackB.empty()) {
            if (loopNum % 2 == 0) {
                // 偶数行,处理stackB
                if (!stackA.empty()) {
                    while (!stackA.empty()) {
                        int index = stackA.pop();
                        printList.add(strArr[index]);
                        int left = 2 * index + 1;
                        int right = 2 * index + 2;
                        if (left >= length) {
                            // 左孩子超出数组最大长度
                            continue;
                        } else if (right < length) {
                            // 右孩子在数组范围内
                            stackB.push(right);
                            stackB.push(left);
                        } else {
                            stackB.push(left);
                        }
                    }
                }

            } else {
                // 奇数行，处理stackA,进栈前打印
                if (!stackB.empty()) {
                    while (!stackB.empty()) {
                        int index = stackB.pop();
                        printList.add(strArr[index]);
                        int left = 2 * index + 1;
                        int right = 2 * index + 2;
                        if (left >= length) {
                            // 左孩子超出数组最大长度
                            continue;
                        } else if (right < length) {
                            // 右孩子在数组范围内
                            stackA.push(left);
                            stackA.push(right);
                        } else {
                            stackA.push(left);
                        }
                    }
                }
            }
            loopNum++;
        }

        printList.forEach(ite -> System.out.print(ite + ", "));

    }

}