// 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
// 并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
// 输出：7 -> 0 -> 8
// 原因：342 + 465 = 807
// 
// Related Topics 链表 数学

package com.lpf.leetcode.editor.cn;

/**
 * 要考虑进位问题
 * 输入1： 9 -> 9 -> 9
 * 输入2： 2 -> 3
 * 输出 ： 1 -> 3 -> 0 -> 1
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        ListNode l1 = new AddTwoNumbers().new ListNode(9);
        ListNode l12 = new AddTwoNumbers().new ListNode(9);
        ListNode l13 = new AddTwoNumbers().new ListNode(9);
        l12.next = l13;
        l1.next = l12;

        ListNode l2 = new AddTwoNumbers().new ListNode(2);
        ListNode l22 = new AddTwoNumbers().new ListNode(3);
//        ListNode l23 = new AddTwoNumbers().new ListNode(4, null);
//        l22.next = l23;
        l2.next = l22;

        ListNode listNode = solution.addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }


//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return null;
            }

            ListNode currentNode = new ListNode(0);
            currentNode.next = currentNode;
            int highBitNum = 0;
            while (l1 != null || l2 != null || highBitNum == 1) {
                int num1 = l1 == null ? 0 : l1.val;
                int num2 = l2 == null ? 0 : l2.val;

                int num = num1 + num2 + highBitNum;
                if (num >= 10) {
                    num -= 10;
                    highBitNum = 1;
                } else {
                    highBitNum = 0;
                }

                ListNode newNode = new ListNode(num);
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                currentNode = newNode;

                l1 = l1 != null ? l1.next : null;
                l2 = l2 != null ? l2.next : null;
            }

            ListNode resultNode = currentNode.next.next;
            currentNode.next = null;

            return resultNode;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

}