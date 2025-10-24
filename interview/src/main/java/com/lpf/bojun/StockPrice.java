package com.lpf.bojun;

import java.util.HashMap;
import java.util.TreeMap;

class StockPrice {
    private HashMap<Integer, Integer> timeToPrice;  // 时间戳到价格的映射
    private TreeMap<Integer, Integer> priceCounts;  // 价格到出现次数的映射（用于快速获取最值）
    private int currentTimestamp;                   // 当前最新时间戳

    public StockPrice() {
        timeToPrice = new HashMap<>();
        priceCounts = new TreeMap<>();
        currentTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        // 如果时间戳已存在，先移除旧价格计数
        if (timeToPrice.containsKey(timestamp)) {
            int oldPrice = timeToPrice.get(timestamp);
            priceCounts.put(oldPrice, priceCounts.get(oldPrice) - 1);
            if (priceCounts.get(oldPrice) == 0) {
                priceCounts.remove(oldPrice);
            }
        }
        
        // 更新新价格和时间戳
        timeToPrice.put(timestamp, price);
        priceCounts.put(price, priceCounts.getOrDefault(price, 0) + 1);
        
        // 维护最新时间戳
        currentTimestamp = Math.max(currentTimestamp, timestamp);
    }

    public int current() {
        return timeToPrice.get(currentTimestamp);
    }

    public int maximum() {
        return priceCounts.lastKey();
    }

    public int minimum() {
        return priceCounts.firstKey();
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);    // 时间戳1价格10
        stockPrice.update(2, 5);     // 时间戳2价格5
        System.out.println(stockPrice.current());        // 返回5
        System.out.println(stockPrice.maximum());        // 返回10
        stockPrice.update(1, 3);     // 时间戳1价格更新为3
        System.out.println(stockPrice.maximum());        // 返回5（时间戳2的价格）
        stockPrice.update(4, 2);     // 时间戳4价格2
        System.out.println(stockPrice.minimum());        // 返回2
        System.out.println(stockPrice.current());        // 返回2
    }
}


/**
 * 示例用法：
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */