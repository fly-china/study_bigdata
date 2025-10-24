package com.lpf.bojun;

import java.util.*;

/**
 * TweetCounts类用于记录推文时间并按不同频率统计时间块内的推文数量
 */
public class TweetCounts {
    // 使用TreeMap存储每个推文名称对应的推文时间及其出现次数，保证时间有序
    private final Map<String, TreeMap<Integer, Integer>> tweetMap;

    public TweetCounts() {
        tweetMap = new HashMap<>();
    }

    /**
     * 记录推文时间
     * @param tweetName 推文名称
     * @param time 推文时间（秒）
     */
    public void recordTweet(String tweetName, int time) {
        TreeMap<Integer, Integer> times = tweetMap.computeIfAbsent(tweetName, k -> new TreeMap<>());
        times.put(time, times.getOrDefault(time, 0) + 1);
    }

    /**
     * 获取指定时间范围内按频率统计的推文数量
     * @param frequency 统计频率（minute/hour/day）
     * @param tweetName 推文名称
     * @param startTime 起始时间（秒）
     * @param endTime 结束时间（秒）
     * @return 各时间块的推文数量列表
     */
    public List<Integer> getTweetCountsPerFrequency(String frequency, String tweetName, int startTime, int endTime) {
        int freq = parseFrequency(frequency);
        List<Integer> result = new ArrayList<>();
        TreeMap<Integer, Integer> times = tweetMap.getOrDefault(tweetName, new TreeMap<>());

        int currentStart = startTime;
        while (currentStart <= endTime) {
            int currentEnd = currentStart + freq;
            int effectiveEnd = Math.min(currentEnd, endTime + 1); // 处理结束边界
            
            // 获取时间区间内的所有推文（左闭右闭）
            NavigableMap<Integer, Integer> subMap = times.subMap(currentStart, true, effectiveEnd - 1, true);
            
            // 计算总推文次数
            int count = subMap.values().stream().mapToInt(Integer::intValue).sum();
            result.add(count);
            
            currentStart = currentEnd; // 处理下一个时间块
        }
        return result;
    }

    /**
     * 将频率字符串转换为秒数
     */
    private int parseFrequency(String frequency) {
        switch (frequency) {
            case "minute": return 60;
            case "hour": return 3600;
            case "day": return 86400;
            default: throw new IllegalArgumentException("Invalid frequency: " + frequency);
        }
    }

    // ====================== 测试用例 ======================
    public static void main(String[] args) {
//        testCase1();
        testCase2();
    }

    /**
     * 测试用例1：验证基础功能
     */
    private static void testCase1() {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);

        // 验证分钟频率统计
        List<Integer> result1 = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59);
        System.out.println("Test Case 1-1: " + result1 + " (预期: [2])");

        List<Integer> result2 = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60);
        System.out.println("Test Case 1-2: " + result2 + " (预期: [2, 1])");
    }

    /**
     * 测试用例2：验证小时频率和扩展记录
     */
    private static void testCase2() {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        tweetCounts.recordTweet("tweet3", 120); // 新增记录

        // 验证小时频率统计
        List<Integer> result = tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);
        System.out.println("Test Case 2: " + result + " (预期: [4])");
    }
}