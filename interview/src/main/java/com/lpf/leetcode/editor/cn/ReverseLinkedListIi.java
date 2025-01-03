//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
//
// Related Topics 链表 👍 1901 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * [92]-反转链表 II
 */
public class ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi().new Solution();

        ListNode listNode = ListNode.convertArrayToList(new int[]{1, 2, 3, 4, 5});
        ListNode head = solution.reverseBetween(listNode, 2, 4);
        int[] nodeToArray = ListNode.convertNodeToArray(head);
        System.out.println(JSONObject.toJSON(nodeToArray));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode pre = dummy;

            ListNode reverseOldHeadPre = null; // 需要反转的前一个节点
            ListNode reverseOldHead = null; // 需要翻转的头节点。 即翻转后的尾节点
            ListNode reverseOldTail = null; // 需要翻转的尾节点。 即翻转后的头节点
            ListNode reverseOldTailNext = null; // 需要翻转的尾节点的后一个节点

            int count = 0;
            while (pre != null) {
                if (count == left - 1) {
                    reverseOldHeadPre = pre;
                    reverseOldHead = pre.next;
                }

                if (count == right) {
                    reverseOldTail = pre;
                    reverseOldTailNext = reverseOldTail.next;
                    break;
                }

                pre = pre.next;
                count++;
            }

            reverseOldHeadPre.next = null;
            reverseOldTail.next = null;


            ListNode newHead = reverse(reverseOldHead);

            reverseOldHeadPre.next = newHead;
            reverseOldHead.next = reverseOldTailNext;


            return dummy.next;
        }

        public ListNode reverse(ListNode head) {
            ListNode pre = null;
            while (head != null) {
                ListNode tmp = head;
                head = head.next;

                tmp.next = pre;
                pre = tmp;
            }
            return pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}