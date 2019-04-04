package com.lpf.interview.map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ListMultimap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * guava的双向map
 *
 * @author lipengfei
 * @create 2019-04-03 14:56
 **/
public class GuavaBiMapdemo {


    /**
     * guava中的BIMap(键值对的双向映射)
     * 根据唯一key，定位唯一value；根据唯一value，定位唯一key
     *
     */
    @Test
    public void testBiMap() {

        HashBiMap<String, String> hashBiMap = HashBiMap.create(16);// default initial capacity (16)


        hashBiMap.put("1", "a.log");
        hashBiMap.put("1", "aa.log"); // key相同，value不同时，旧value覆盖新value
        hashBiMap.put("2", "c.log");
//        hashBiMap.put("3", "c.log"); // 由于value已存在，报异常value already present: c.log
        hashBiMap.forcePut("3", "c.log"); // 强制设置value已存在的值时，会删除已有entry。上述<2, c.log>会被删除

        System.out.println(hashBiMap.toString());

        String value1 = hashBiMap.get("1");
        System.out.println(value1);

        BiMap<String, String> inverseMap = hashBiMap.inverse(); // <key， value>的map翻转为<value， key>的map
        System.out.println(inverseMap.get("c.log"));
    }


    /**
     * 一对多的map，一个key中存在多个value
     * commons.collections的实现
     */
    @Test
    public void testCommonMultiValueMap() {

        MultiMap giftMap = new MultiValueMap();
        giftMap.put("1", "鲜花");
        giftMap.put("1", "戒指");
        giftMap.put("1", "伞");
        giftMap.put("2", "巧克力");
        giftMap.put("2", "平安果");
        List list = (List) giftMap.get("1");
        List list2 = (List) giftMap.get("2");

        System.out.println(list.toString());
        System.out.println(list2.toString());
    }

    /**
     * 一对多的map，一个key中存在多个value
     * google.guava的实现
     */
    @Test
    public void testGuavaMultiValueMap() {

        ListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("1", "鲜花");
        multimap.put("1", "戒指");
        multimap.put("1", "伞");
        multimap.put("2", "巧克力");
        multimap.put("2", "平安果");
        multimap.putAll("3", Arrays.asList("戒指", "项链"));
        multimap.put("3", "耳钉");

        List list = multimap.get("1");
        List list2 = multimap.get("2");
        List list3 = multimap.get("3");
        Map<String, Collection<String>> mapView = multimap.asMap();
        Collection<String> strings = mapView.get("1"); // asMap()返回的Map支持remove操作，并且会反映到底层的Multimap，但它不支持put或putAll操作

        System.out.println(list.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
        System.out.println(strings.toString());

    }


}
