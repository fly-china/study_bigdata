//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 460 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [203]-移除链表元素
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode tempHead = new ListNode(-1);
            ListNode tempTail = tempHead;
            while (head != null) {
                if (head.val != val) {
                    tempTail.next = head;
                    tempTail = head;
                }
                head = head.next;
                tempTail.next = null;
            }

            return tempHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}