//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 2938 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * [23]-合并 K 个升序链表
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(value -> (int) value).reversed());
        queue.offer(5);
        queue.offer(1);
        queue.offer(3);
        queue.offer(8);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length < 1) return null;


            //  1、将数组中的每个链表的头元素放入优先级队列
            PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
            for (ListNode node : lists) {
                if (node != null)
                    queue.offer(node);
            }

            ListNode dummy = new ListNode();
            ListNode pre = dummy;
            while (!queue.isEmpty()) {
                // 2、从队列中获取到数值最小的节点
                ListNode minNode = queue.poll();
                if (minNode.next != null) {
                    // 2.1 如果该节点还有后继结点，将后继结点依然放入优先级队列
                    queue.offer(minNode.next);
                }

                // 3、将最小节点，拼接到合并链表最后的位置
                pre.next = minNode;
                pre = pre.next;
            }

            return dummy.next;
        }

        public ListNode mergeKLists2(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            ListNode first = lists[0];
            for (int i = 1; i < lists.length; i++) {
                ListNode second = lists[i];
                first = mergeTwoList(first, second);
            }
            return first;
        }

        private ListNode mergeTwoList(ListNode first, ListNode second) {
            ListNode dummy = new ListNode(-1);

            ListNode pre = dummy;
            while (first != null && second != null) {
                if (first.val <= second.val) {
                    pre.next = first;
                    first = first.next;
                } else {
                    pre.next = second;
                    second = second.next;
                }
                pre = pre.next;
            }

//            if (first == null) {
//                pre.next = second;
//            } else {
//                pre.next = first;
//            }
            pre.next = first == null ? second : first;

            return dummy.next;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


}