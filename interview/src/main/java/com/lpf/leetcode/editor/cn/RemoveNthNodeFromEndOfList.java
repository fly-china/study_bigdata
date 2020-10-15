//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 1024 👎 0


package com.lpf.leetcode.editor.cn;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(solution.removeNthFromEnd(node1, 2));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head != null && n > 0) {
                ListNode fast = head;
                ListNode slow = head; // 目标删除节点

                ListNode slowLast = new ListNode(-1);// 目标删除节点的上一个节点。
                slowLast.next = slow;
                ListNode tempHead = slowLast;

                while (n > 1) {
                    fast = fast.next;
                    n--;
                }

                while (fast.next != null) {
                    fast = fast.next;
                    slow = slow.next;
                    slowLast = slowLast.next;
                }

                //  删除目标节点：slow节点
                slowLast.next = slow.next;
                slow = null;
                head = tempHead.next;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static class ListNode {
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
    }
}