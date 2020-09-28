//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 398 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.List;

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head != null) {
                int pre = head.val;
                ListNode compareNode = head;

                while (compareNode.next != null) {
                    if (compareNode.next.val != pre) {
                        compareNode = compareNode.next;
                        pre = compareNode.val;
                    } else {
                        compareNode.next = compareNode.next.next;
                    }
                }
            }
            return head;
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