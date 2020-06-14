//反转一个单链表。
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表


package com.lpf.leetcode.editor.cn;

/**
 * [206]-反转链表
 * 
 */
public class ReverseLinkedList{
  public static void main(String[] args) {
	   Solution solution = new ReverseLinkedList().new Solution();
	   
  }
  
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) return head;
        ListNode reverse = null;
        ListNode temp = null;

        while (head.next != null) {
           temp = head.next;
           head.next = reverse;
           reverse = head;
           head = temp;
        }
        head.next = reverse;

        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // 递归
    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return head;
    }
}