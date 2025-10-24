//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，po
//s 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 
// 你是否可以不用额外空间解决此题？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 链表 双指针 
// 👍 709 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [142]-环形链表 II
 */
public class LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    public class Solution {
        public ListNode detectCycle(ListNode head) {
            return detectCycle2025(head);
        }

        /**
         * 快慢指针，均从 head 开始。
         * 假设 slow 走过的距离为 s，fast 走过的距离为 f，f = 2s。 slow 和 fast 终会相遇在环上。
         * 假设 head 至环的起始处距离为 a，环的起点 -> 相遇点 距离为 b，相遇点再到环起点的距离为 c
         * s = a+b
         * f = a + n(b+c) +b = a + (n+1)b + nc  === 2s = 2(a+b)
         * a + (n+1)b + nc = 2(a+b)   ->  a = (n-1)b + nc = (n-1)(b+c) + c
         * 即：a 等于 n-1 个环的距离 + c 的距离。
         * 所以：从起点出出发 slow2 节点，从相遇处继续移动 slow 节点。 这两个节点相遇时，即为环的起点。  slow2 走了距离 a，slow 走了距离 c
         * <p>
         * 有另外一种解释：快指针走过的距离是两倍，慢指针是一倍。那么慢指针再走一倍就会回到相遇点。 假设从相遇点开始慢指针前进，同时有个新的慢指针从起点前进，
         * 那么它们还会在原先快慢指针的相遇点相遇。要注意这两个指针的速度都是1，所以实际上它们是从环入口点开始就相遇
         */
        public ListNode detectCycle2025(ListNode head) {
            if (head == null || head.next == head) return head;

            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    ListNode node1 = head;
                    ListNode node2 = slow;
                    while (node2 != node1) {
                        node1 = node1.next;
                        node2 = node2.next;
                    }
                    return node2;
                }
            }

            return null;
        }


        // 染色法(污染法)
        public ListNode detectCycle2020(ListNode head) {
            ListNode cycleHead = null;
            if (head != null) {
                while (head != null) {
                    if (head.val == Integer.MIN_VALUE) {
                        cycleHead = head;
                        break;
                    } else {
                        head.val = Integer.MIN_VALUE;
                        head = head.next;
                    }
                }
            }

            return cycleHead;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)


    public class Solution_A {
        public ListNode detectCycle(ListNode head) {
            ListNode cycleHead = null;
            if (head != null) {
                Set<ListNode> nodeSet = new HashSet<>();
                while (head != null) {
                    if (nodeSet.contains(head)) {
                        cycleHead = head;
                        break;
                    } else {
                        nodeSet.add(head);
                        head = head.next;
                    }
                }
            }

            return cycleHead;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}