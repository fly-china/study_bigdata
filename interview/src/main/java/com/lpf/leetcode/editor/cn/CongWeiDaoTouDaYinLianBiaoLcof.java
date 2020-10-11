//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 链表 
// 👍 62 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Stack;

/**
 * [剑指 Offer 06]-从尾到头打印链表
 */
public class CongWeiDaoTouDaYinLianBiaoLcof {
    public static void main(String[] args) {
        Solution solution = new CongWeiDaoTouDaYinLianBiaoLcof().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int[] reversePrint(ListNode head) {
            // 先计算链表的长度
            int size = 0;
            ListNode temp = head;
            while (temp != null) {
                size++;
                temp = temp.next;
            }
            // 再将链表倒序放入数组。
            int[] nums = new int[size];
            int i = size - 1;
            while (head != null) {
                nums[i--] = head.val;
                head = head.next;
            }

            return nums;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution_A {
        public int[] reversePrint(ListNode head) {
            if (head == null) return new int[0];
            Stack<Integer> stack = new Stack<>();
            while (head != null) {
                stack.push(head.val);
                head = head.next;
            }
            int[] nums = new int[stack.size()];
            int i = 0;
            while (!stack.isEmpty()) {
                nums[i++] = stack.pop();
            }

            return nums;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}