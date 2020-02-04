package com.lpf.jdk8;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author lipengfei
 * @create 2019-04-12 15:27
 **/
public class Demo {


    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(222.1);
        BigDecimal b = new BigDecimal(222.10);
        Double d = 222.1d;

        System.out.println(Objects.equals(a, b));
        System.out.println(Objects.equals(a, new BigDecimal(d)));
        System.out.println("-------------------------");

        String s1 = "abc";
        String s2 = "a" + "bc";
        String s3 = new String("abc");
        String as = "a";
        String bcs = "bc";
        String s4 = as + bcs;

        System.out.println("s1 == s2结果为：" + (s1 == s2));
        System.out.println("s1 == s3结果为：" + (s1 == s3));
        System.out.println("s1 == s4结果为：" + (s1 == s4));
        System.out.println("工具类s1 == s2结果为：" + Objects.equals(s1, s2));
        System.out.println("工具类s1 == s3结果为：" + Objects.equals(s1, s3));
        System.out.println("工具类s1 == s4结果为：" + Objects.equals(s1, s4));
        System.out.println("-------------------------");


    }


}
