package com.lpf.bigdata.current;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lipengfei
 * @create 2018-11-06 11:21
 **/
public class LambdaDemo {


    public static void main(String[] args) {

        List<User> list = getLoopList();
        Map<Integer, Integer> map = getLoopMap();

        list.forEach((User user )-> {
            System.out.println(user.getUserName());
        });

        System.out.println("-------------------");

        map.forEach((k,v) -> {
            System.out.println("k=" + k + "----v=" + v);
        });

        System.out.println("-------------------");

        // jdk8的Stream流
        System.out.println("对集合进行过滤：user name 包含 1 的用户");
        List<User> filtered = list.stream().filter(user -> user.getUserName().contains("1")).collect(Collectors.toList());
        filtered.forEach(user -> System.out.println(user.toString()));
        System.out.println("-------------------");

        // jdk8的Stream流
        testNumber();


    }


    /**
     * 测试用法，稍复杂的串行操作。
     * 过滤空值；去重；排序；循环输出。
     */
    private static void testNumber() {
        List<Integer> integers = Arrays.asList(100, 3, null, 8, 7, 8, 13, 10);
        integers.stream().filter(Objects::nonNull).distinct().sorted().forEach(System.out::println);
    }

    /**
     * 获得一个list集合
     */
    private static List<User> getLoopList() {
        List<User> all = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            all.add(new User("lxk_" + i, "pwd_" + i));
        }
        return all;
    }

    /**
     * 获得一个map集合
     */
    private static Map<Integer, Integer> getLoopMap() {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        return map;
    }

    public static class User {
        private String userName;
        private String pwd;

        public User() {
        }

        public User(String userName, String pwd) {
            this.userName = userName;
            this.pwd = pwd;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }
    }
}
