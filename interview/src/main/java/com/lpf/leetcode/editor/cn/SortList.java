//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表


package com.lpf.leetcode.editor.cn;

/**
 * [148]-排序链表
 */
public class SortList {

    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode head = n1;
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
        System.out.println("---------------");
        ListNode listNode = solution.sortList(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    // leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;

            // 将链表一分为二,分别排序
            ListNode midNode = findMiddleNode(head);
            ListNode secondHead = midNode.next;
            midNode.next = null;

            ListNode sortHead = sortList(head);
            ListNode sortHead2 = sortList(secondHead);

            // 将两个排序好的链表，归并到一起
            ListNode newHead = mergeAndSort(sortHead, sortHead2);

            return newHead;
        }

        // 合并两个有序链表
        private ListNode mergeAndSort(ListNode sortHead, ListNode sortHead2) {
            ListNode newHead = new ListNode(-1);
            ListNode temp = newHead;
            while (sortHead != null && sortHead2 != null) {
                if (sortHead.val <= sortHead2.val) {
                    temp.next = sortHead;
                    temp = sortHead;
                    sortHead = sortHead.next;
                } else {
                    temp.next = sortHead2;
                    temp = sortHead2;
                    sortHead2 = sortHead2.next;
                }
            }
            if (sortHead == null) {
                temp.next = sortHead2;
            } else {
                temp.next = sortHead;
            }

            return newHead.next;
        }

        // 查询链表的中间节点
        public ListNode findMiddleNode(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode slow = head;
            ListNode fast = head.next;

            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast.next != null) {
                // 奇数节点情况
                return slow.next;
            } else {
                // 偶数节点情况
                return slow;
            }
        }

    }
    // leetcode submit region end(Prohibit modification and deletion)

    /**
     * 这个太慢了
     */
    class Solution_A {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode newHead = null;
            ListNode temp = head;
            while (head != null) {
                // 处理首个节点
                if (newHead == null) {
                    newHead = head;
                    head = head.next;
                    newHead.next = null;
                    continue;
                }

                // 非首个节点
                if (head.val <= newHead.val) {
                    // 后续节点的值 小于等于 头节点的值，后续节点作为新头节点
                    temp = head.next;
                    head.next = newHead;
                    newHead = head;
                    head = temp;
                } else {
                    ListNode compareNode = newHead;
                    while (compareNode.next != null && head.val > compareNode.next.val) {
                        compareNode = compareNode.next;
                    }
                    temp = head.next;
                    head.next = compareNode.next;
                    compareNode.next = head;
                    head = temp;
                }
            }
            return newHead;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}