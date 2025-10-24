package com.lpf.bojun;

import java.util.TreeMap;

class RangeModule {
    private TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        // 找到左边界的插入点
        Integer floorKey = map.floorKey(left);
        // 找到右边界的插入点
        Integer ceilingKey = map.ceilingKey(left);
        if (floorKey != null && map.get(floorKey) >= left) {
            left = floorKey;
        }
        while (ceilingKey != null && ceilingKey < right && map.get(ceilingKey) <= right) {
            right = Math.max(right, map.get(ceilingKey));
            map.remove(ceilingKey);
            ceilingKey = map.ceilingKey(right);
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer floorKey = map.floorKey(left);
        return floorKey != null && map.get(floorKey) >= right;
    }

    public void removeRange(int left, int right) {
        Integer floorKey = map.floorKey(left);
        Integer ceilingKey = map.ceilingKey(left);
        if (floorKey != null && map.get(floorKey) > left) {
            int start = floorKey;
            int end = map.get(floorKey);
            map.remove(floorKey);
            if (end > right) {
                map.put(right, end);
            }
            if (start < left) {
                map.put(start, left);
            }
        }
        while (ceilingKey != null && ceilingKey < right && map.get(ceilingKey) <= right) {
            map.remove(ceilingKey);
            ceilingKey = map.ceilingKey(right);
        }
        if (ceilingKey != null && map.get(ceilingKey) > right) {
            map.put(right, map.get(ceilingKey));
        }
    }

}