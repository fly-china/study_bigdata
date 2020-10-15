//给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下： 
//
// struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 示例： 
//
// 
//
// 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"ri
//ght":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right
//":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left"
//:null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":nu
//ll,"next":null,"right":null,"val":7},"val":3},"val":1}
//
//输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4
//","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next"
//:null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":
//null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":
//"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"va
//l":1}
//
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
// 
//
// 
//
// 提示： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
// Related Topics 树 深度优先搜索 
// 👍 291 👎 0


package com.lpf.leetcode.editor.cn;

public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        /**
         * 1、此节点为父节点的左孩子，next指向其父节点的右孩子
         * 2.1、此节点为父节点的右孩子，且父节点为【左】孩子节点，那么next指向其父节点右兄弟节点的左孩子
         * 2.1、此节点为父节点的右孩子，且父节点为【右】孩子节点，那么next指向null
         */
        public Node connect(Node root) {
            if (root != null) {
                // 二叉树某一层最左侧的节点
                Node tempHead = root;
                while (tempHead.left != null) {
                    Node connectNode = tempHead;
                    // 每次进行连接时，连接的是tempHead节点下一层的节点
                    while (connectNode != null) {
                        // 连接一：处理左孩子节点的next指针
                        connectNode.left.next = connectNode.right;

                        // 连接二：处理右孩子节点的next指针
                        if (connectNode.next != null) {
                            connectNode.right.next = connectNode.next.left;
                        }

                        // 处理本层的下一个节点
                        connectNode = connectNode.next;
                    }

                    tempHead = tempHead.left;
                }
            }

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    class Solution_RECUR {
        /**
         * 1、此节点为父节点的左孩子，next指向其父节点的右孩子
         * 2.1、此节点为父节点的右孩子，且父节点为【左】孩子节点，那么next指向其父节点右兄弟节点的左孩子
         * 2.1、此节点为父节点的右孩子，且父节点为【右】孩子节点，那么next指向null
         */
        public Node connect(Node root) {
            if (root != null) {
                helperRecur(root.left, true, root);
                helperRecur(root.right, false, root);
            }

            return root;
        }

        private void helperRecur(Node currNode, boolean currIsLeftNode, Node parentNode) {
            if (currNode == null) return;

            if (currIsLeftNode) {
                // 当前节点为左节点，next指向其父节点的右孩子
                currNode.next = parentNode.right;
            } else {
                // 借助父节点的next指针已经做好了连接
                // 当前节点为右节点，next指向其父节点的next节点（如果有）的左孩子
                if (parentNode.next != null) {
                    currNode.next = parentNode.next.left;
                }
            }

            helperRecur(currNode.left, true, currNode);
            helperRecur(currNode.right, false, currNode);
        }
    }

    class Solution_RECUR_EASY {
        public Node connect(Node root) {
            dfs(root, null);
            return root;
        }

        private void dfs(Node currNode, Node nextNode) {
            if (currNode == null) return;
            currNode.next = nextNode;
            dfs(currNode.left, currNode.right);
            dfs(currNode.right, currNode.next == null ? null : currNode.next.left);
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;
}