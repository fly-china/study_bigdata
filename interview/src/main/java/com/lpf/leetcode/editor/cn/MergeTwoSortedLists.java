//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


package com.lpf.leetcode.editor.cn;

/**
 * [21]-合并两个有序链表
 *
 */
public class MergeTwoSortedLists{
  public static void main(String[] args) {
	   Solution solution = new MergeTwoSortedLists().new Solution();

  }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * // 输入：1->2->4, 1->3->4
 * //输出：1->1->2->3->4->4
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        // 原来这tm的叫哑头(dummy header)链表算法,因为把头节点当成一个空节点
        ListNode l0 = new ListNode();
        ListNode firstNode = l0;
        while (l1 != null && l2 != null) {
            if(l1.val < l2.val){
                l0.next = l1;
                l1 = l1.next;
            }else {
                l0.next = l2;
                l2 = l2.next;
            }
            l0 = l0.next;
        }

        if(l1 != null){
            l0.next = l1;
        }
        if(l2 != null){
            l0.next = l2;
        }

        return firstNode.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}