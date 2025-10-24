package com.lpf.testbak;

/**
 * @author lipengfei
 * @create 2025-02-27 18:32
 **/
public class CompineListNode {

    public static void main(String[] args) {

    }

    private static ListNode combine(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }

        ListNode dummy = new ListNode(-1, null);
        ListNode newNode = dummy;
        while (node1 != null || node2 != null) {
            if (node1 == null) {
                newNode.setNext(node2);
                break;
            }
            if (node2 == null) {
                newNode.setNext(node1);
                break;
            }

            if (node1.getValue() > node2.getValue()) {
                newNode.setNext(node2);
                node2 = node2.getNext();
            } else {
                newNode.setNext(node1);
                node1 = node1.getNext();
            }
            newNode = newNode.getNext();
        }

        return dummy.getNext();
    }
}

class ListNode {
    private int value;
    private ListNode next;

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
