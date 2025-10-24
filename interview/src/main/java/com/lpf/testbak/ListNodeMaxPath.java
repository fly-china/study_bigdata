package com.lpf.testbak;

/**
 * @author lipengfei
 * @create 2025-03-04 15:50
 **/
public class ListNodeMaxPath {

    private static int maxSum = Integer.MIN_VALUE;
    private static ListNodeDemo listNoe;

    public static void main(String[] args) {
        TreeNode node8 = new TreeNode(8);
        TreeNode node3 = new TreeNode(3);
        TreeNode node10 = new TreeNode(10);
        TreeNode node1 = new TreeNode(1);
        TreeNode node14 = new TreeNode(14);
        TreeNode node13 = new TreeNode(13);
        node8.left = node3;
        node3.left = node1;
        node8.right = node10;
        node10.right = node14;
        node14.left = node13;

        int maxLongPath = getMaxLongPath(node8);
        System.out.println("maxPath = " + maxLongPath);

    }

    private static int getMaxLongPath(TreeNode root) {
        if (root == null)
            return 0;

        int maxLeft = getMaxLongPath(root.left);
        int maxRight = getMaxLongPath(root.right);

        int currentSum = root.val + Math.max(maxLeft, maxRight);
        maxSum = Math.max(currentSum, maxSum);


        return currentSum;
    }

}

class ListNodeDemo {
    int val;
    ListNode next;

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


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}