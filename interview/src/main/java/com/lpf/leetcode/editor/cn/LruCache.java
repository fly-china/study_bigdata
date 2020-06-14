//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。 
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计


package com.lpf.leetcode.editor.cn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * [146]-LRU缓存机制
 */
public class LruCache {
    public static void main(String[] args) {
//	   Solution solution = new LruCache().new Solution();
        Map<Integer, Integer> map = new LinkedHashMap<>(3);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        map.get(2);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 暂时利用LinkedHashMap实现。即，重写LinkedHashMap的removeEldestEntry方法。
     * removeEldestEntry方法，只有在afterNodeInsertion插入新节点时调用
     * removeEldestEntry默认永远为false，即：不删除老节点。
     * 重写removeEldestEntry，当map中元素个数大于capacity了，就要删除老节点了。
     *
     * accessOrder = true，节点会按照插入时排序
     * accessOrder = false，节点会按照访问时排序
     */
    class LRUCache {
        Map<Integer, Integer> map = null;
        int max;

        public LRUCache(int capacity) {
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;  // 容量大于capacity 时就删除
                }
            };
            max = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            } else {
//                int val = map.get(key);
//                map.remove(key); // 从链表中删除
//                map.put(key, val); // 添加到链尾
                return map.get(key);
            }
        }

        public void put(int key, int value) {
//            if (map.containsKey(key)) {
//                map.remove(key); // 已经存在，链表中删除
//            }
//
//            if (max == map.size()) {
//                // map 已满,删除链表头
//                Set<Integer> keySet = map.keySet();
//                Iterator<Integer> iterator = keySet.iterator();
//                map.remove(iterator.next());
//            }
            map.put(key, value);// 添加到链尾
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)


}