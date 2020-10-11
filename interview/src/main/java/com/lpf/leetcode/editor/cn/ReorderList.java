//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 321 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [143]-重排链表
 */
public class ReorderList {
    public static void main(String[] args) {
        Solution solution = new ReorderList().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        solution.reorderList(node1);
        System.out.println(node1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public void reorderList(ListNode head) {
            if (head == null) return;

            // 1、先找出链表中间节点（奇数节点）或中间位置前的节点（偶数节点）
            ListNode slow = head, fast = head;
            while (fast != null) {
                if (fast.next == null || fast.next.next == null) {
                    break;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tempHead = slow.next;
            slow.next = null;

            // 2、将后半部分链表倒序
            ListNode part2Head = new ListNode(-1);
            while (tempHead != null) {
                ListNode temp = tempHead;
                tempHead = tempHead.next;

                temp.next = part2Head.next;
                part2Head.next = temp;
            }
            part2Head = part2Head.next;

            // 3、充足为一个链表。
            tempHead = head;
            while (head != null || part2Head != null) {
                ListNode temp1 = head.next;

                if (part2Head != null) {
                    ListNode temp2 = part2Head.next;
                    part2Head.next = head.next;
                    head.next = part2Head;

                    head = temp1;
                    part2Head = temp2;
                } else {
                    head = head.next;
                }
            }

            head = tempHead;
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