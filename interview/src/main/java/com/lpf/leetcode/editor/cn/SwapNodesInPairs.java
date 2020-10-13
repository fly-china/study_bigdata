//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表 
// 👍 679 👎 0


package com.lpf.leetcode.editor.cn;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null) return null;
            ListNode tempHead = new ListNode(-1);
            ListNode tailLast = tempHead;
            ListNode tail = head;
            tempHead.next = tail;
            head = head.next;
            tail.next = null;

            int index = 2;
            while (head != null) {
                if (index % 2 == 0) {
                    // 偶数节点
                    tailLast.next = head;
                    head = head.next;
                    tailLast.next.next = tail;
                    tailLast = tailLast.next;
                } else {
                    tail.next = head;
                    tail = head;
                    head = head.next;
                    tail.next = null;
                    tailLast = tailLast.next;
                }
                index++;
            }

            return tempHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

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
    }
}