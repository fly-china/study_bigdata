package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lipengfei
 * @create 2024-11-18 11:05
 **/
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode convertArrayToList(int[] array) {
        if (array == null) return null;

        ListNode pre = new ListNode();
        ListNode head = pre;
        for (int i : array) {
            ListNode curNode = new ListNode(i);
            pre.next = curNode;
            pre = curNode;
        }

        return head.next;
    }

    public static int[] convertNodeToArray(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
