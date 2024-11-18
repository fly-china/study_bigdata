//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
//
// Related Topics 栈 递归 链表 双指针 👍 1976 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.List;

/**
 * [234]-回文链表
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();
        ListNode head = ListNode.convertArrayToList(new int[]{1, 2, 3, 2, 1});
        boolean palindrome = solution.isPalindrome(head);
        System.out.println(palindrome);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 一种方式是遍历链表，将内容填充至数组，在使用双指针判断
        // 下面就用链表的特性，不引入数组进行实现
        public boolean isPalindrome(ListNode head) {
            if (head == null) return false;
            if (head.next == null) return true;


            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode pre = null;
            slow = slow.next;
            // 将 slow 之后的链表进行反转
            while (slow != null) {
                ListNode tmp = slow;
                slow = slow.next;
                tmp.next = pre;
                pre = tmp;
            }

            ListNode newHead = pre;
            while (pre != null) {
                if (pre.val != head.val) {
                    return false;
                }
                pre = pre.next;
                head = head.next;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}