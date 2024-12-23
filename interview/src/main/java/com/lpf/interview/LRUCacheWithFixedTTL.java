package com.lpf.interview;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

/**
 * [Leetcode 146.LRU缓存](https://leetcode.cn/problems/lru-cache/description/)
 * <p>
 * [Leetcode 460.LFU缓存](https://leetcode.cn/problems/lfu-cache/description/)
 * <p>
 * [CSDN LRU 缓存博客(包含过期时间详解)](https://blog.csdn.net/saxon_li/article/details/123974696)
 * <p>
 * 下面是一个带有固定过期时间的 LRU
 * 固定过期时间：类似于 GuavaCache 中的 expireAfterAccess
 * 由于每次写入 or 访问时都会重新调整过期时间，所以在过期淘汰时和普通 LRU 无区别，因为最久未访问的节点或过期最久的节点都在链表尾部
 */
class LRUCacheWithFixedTTL {

    class LRUCache {
        class Node {
            int key, val;
            Node prev, next;
            long expireTime;

            public Node(int key, int val, long expireTime) {
                this.key = key;
                this.val = val;
                this.expireTime = expireTime;
            }
        }

        Map<Integer, Node> objMap;
        Node dummyNode;
        int capacity;
        long ttl = 5000L; // 5 秒过期

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.dummyNode = initDummyNode();
            objMap = new HashMap<>(capacity);
        }

        public int get(int key) {
            Node node = getNode(key);
            return node == null ? -1 : node.val;
        }

        public Node getNode(int key) {
            Node node = objMap.get(key);

            if (node == null) return null; // 不存在
            if (currentTimeMillis() > node.expireTime) {
                // 过期节点，惰性删除
                removeCurNode(node);
                objMap.remove(key);
                return null;
            }

            // 从双向链表中，移除当前节点，并插入至头部,同时更改过期时间
            node.expireTime = currentTimeMillis() + ttl;
            removeCurNode(node);
            insertToHead(node);

            return node;
        }

        public void put(int key, int value) {
            Node node = this.getNode(key);
            if (node != null) {
                node.val = value; // 上述 getNode 已经对节点做了处理
                return;
            }

            if (objMap.size() >= this.capacity) {
                // 最久未访问节点 == 最早要过期的节点，淘汰它就完了
                Node longestNode = dummyNode.prev;
                removeCurNode(longestNode);
                objMap.remove(longestNode.key);
            }
            node = new Node(key, value, currentTimeMillis() + ttl);
            insertToHead(node);
            objMap.put(key, node);
        }

        private void removeCurNode(Node curNode) {
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
        }

        private void insertToHead(Node curNode) {
            curNode.prev = dummyNode;
            curNode.next = dummyNode.next;

            dummyNode.next.prev = curNode;
            dummyNode.next = curNode;
        }

        private Node initDummyNode() {
            Node dummy = new Node(-1, -1, -1L);
            dummy.next = dummy;
            dummy.prev = dummy;
            return dummy;
        }
    }

    public static void main(String[] args) {

    }
}