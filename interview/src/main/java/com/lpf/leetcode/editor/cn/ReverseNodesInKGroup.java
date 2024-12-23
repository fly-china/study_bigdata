//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
//
// Related Topics 递归 链表 👍 2457 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * [25]-K 个一组翻转链表
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
        ListNode listNode = ListNode.convertArrayToList(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode newHead = solution.reverse(listNode);
        System.out.println("正常反转" + JSONObject.toJSON(ListNode.convertNodeToArray(newHead)));

        listNode = ListNode.convertArrayToList(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode kGroupHead = solution.reverseKGroup(listNode, 3);
        System.out.println("K个一组正常反转" + JSONObject.toJSON(ListNode.convertNodeToArray(kGroupHead)));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // [1,2,3,4,5,6,7] -> [3,2,1, 6,5,4, 7]
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode();
            dummy.next = head;

            ListNode pre = dummy;
            while (head != null) {
                ListNode newPre = pre; // 本组处理完后新的 pre 节点

                boolean lessKGroup = false;
                for (int i = 0; i < k; i++) {
                    newPre = newPre.next;
                    if (newPre == null) { // 不满足 k 个，直接返回
                        lessKGroup = true;
                        break;
                    }
                }

                if (lessKGroup) { // 无需再特殊处理
                    break;
                }

                head = newPre.next;
                newPre.next = null; // 先解除掉连接，才能做翻转

                newPre = pre.next;
                ListNode reverseHead = reverse(pre.next);

                pre.next.next = head; // 重新和后面的节点连接
                pre.next = reverseHead; // pre 节点和 （k 组节点的头节点） 进行连接
                pre = newPre;
            }


            return dummy.next;
        }

        public ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode pre = null;
            while (head != null) {
                ListNode cur = head;
                head = head.next;
                cur.next = pre;
                pre = cur;
            }

            return pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}