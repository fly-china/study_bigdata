package com.lpf.test;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

class RangeModule {
    private TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if (start != null && intervals.get(start) >= left) {
            left = start;
        }
        if (end != null && intervals.get(end) > right) {
            right = intervals.get(end);
        }

        intervals.put(left, right);

        Map<Integer, Integer> subMap = intervals.subMap(left, false, right, true);
        subMap.clear();
    }

    public boolean queryRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        return start != null && intervals.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if (end != null && intervals.get(end) > right) {
            intervals.put(right, intervals.get(end));
        }
        if (start != null && intervals.get(start) > left) {
            intervals.put(start, left);
        }

        Map<Integer, Integer> subMap = intervals.subMap(left, true, right, false);
        subMap.clear();
    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        System.out.println(rangeModule.queryRange(10, 14));
        System.out.println(rangeModule.queryRange(13, 15));
        System.out.println(rangeModule.queryRange(16, 17));

        TreeMap<Integer, Integer> treeMap  = new TreeMap<>();
        treeMap.put(10, 20);
        treeMap.put(30, 40);

        System.out.println(treeMap.get(10));
        System.out.println(treeMap.get(20));
        System.out.println(treeMap.get(25));
        System.out.println(treeMap.floorKey(25));
        System.out.println(treeMap.lowerEntry(25));

    }
}    