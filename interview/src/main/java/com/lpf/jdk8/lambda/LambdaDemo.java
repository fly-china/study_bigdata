package com.lpf.jdk8.lambda;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一段带有输入参数的可执行语句块
 * <p>
 * 标准结构：
 * (Type1 param1, Type2 param2, ..., TypeN paramN) -> {
 * statment1;
 * statment2;
 * //.............
 * return statmentM;
 * }
 * <p>
 * 1. 参数类型省略–绝大多数情况，编译器都可以从上下文环境中推断出lambda表达式的参数类型。这样lambda表达式就变成了：
 * (param1, param2,..., paramN) -> {
 * statment1;
 * stament2;
 * // .........
 * return statmentM;
 * }
 * <p>
 * 2.当lambda表达式的参数个数只有一个，可以省略小括号。lambda表达式简写为：
 * param ->  {
 * statment1;
 * stament2;
 * // .........
 * return statmentM;
 * }
 * <p>
 * 3.当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号。lambda表达式简化为：
 * param ->  statmentM;
 * <p>
 * 4. 使用Method Reference(具体语法后面介绍)
 *
 * @author lipengfei
 * @create 2019-04-04 18:28
 **/
public class LambdaDemo {


    @Test
    public void testGuavaTypeMaps() {
        /**
         * 双括号”{{}}”,用来初始化，使代码简洁易读。
         * 第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class);
         * 第二层括弧实际上是一个实例初始化块 (instance initializer block)，这个块在内部匿名类构造时被执行;
         *
         * 缺点：
         *      此种方式是匿名内部类的声明方式，所以引用中持有着外部类的引用。所以当时串行化这个集合时外部类也会被不知不觉的串行化，当外部类没有实现serialize接口时，就会报错
         *      使用new HashMap(map)，这样就可以正常初始化了。
         */
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("List", "Lists.newArrayList()");
                put("Map", "Maps.newHashMap()");
                put("Set", "Sets.newHashSet()");
            }
        };
        System.out.println(JSONObject.toJSONString(map));
        Map<String, String> newMap = new HashMap<>(map);
        System.out.println(JSONObject.toJSONString(newMap));

        System.out.println("---------------------");

        List<String> lists = new ArrayList<String>(){
            {
                add("a");
                add("b");
                add("c");
            }
        };
        System.out.println(JSONObject.toJSONString(lists));

    }

    List<String> names = new ArrayList<>();

    @Before
    public void before() throws Exception {
        names = new ArrayList<>();
        names.add("TaoBao");
        names.add("ZhiFuBao");
        names.add("Weixin");
        names.add("Alibaba");
    }

    @Test
    public void testBaseDemo1() {

        // 传统方式，对list进行排序
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("传统排序结果：" + names.toString());

        Collections.sort(names, ((o1, o2) -> o1.compareTo(o2)));
        System.out.println("lambda排序结果：" + names.toString());


        List<String> lowercaseNames = FluentIterable.from(names).transform(new Function<String, String>() {
            @Override
            public String apply(String name) {
                return name.toLowerCase();
            }
        }).toList();

        System.out.println(lowercaseNames);
    }


    /**
     *
     */
    @Test
    public void testToLowercase() {
        // 借助guava实现
        List<String> lowercaseNames = FluentIterable.from(names).transform(new Function<String, String>() {
            @Override
            public String apply(String name) {
                return name.toLowerCase();
            }
        }).toList();

        System.out.println("借助guava实现的转换结果：" + lowercaseNames);

        List<String> lowerList =
                names.stream()
//                        .map(String::toUpperCase) // 使用Method Reference用法
                        .map(name -> name.toUpperCase())    // 等同于上一句
                        .collect(Collectors.toList());
        System.out.println("借助lambda实现的转换结果：" + lowerList);


    }

    @Test
    public void testM2() {
        String[] array = {"a", "b", "c"};

        for (Integer i : Lists.newArrayList(1, 2, 3)) {
            Stream.of(array).map(item -> Strings.padEnd(item, i, '@')).forEach(System.out::println);
        }

        /**
         * j不用显示使用final修饰，编译器隐式当成final来处理。
         */
//        for ( int j = 1; j<4; j++) {
//            Stream.of(array).map(item -> Strings.padEnd(item, j, '@')).forEach(System.out::println);
//        }
    }

    @Test
    public void methodReference() {

        Set<String> knownNames = Sets.newHashSet("a", "b", "c");
        Predicate<String> isKnown = knownNames::contains;

        System.out.println("是否包含b？，result=" + isKnown.test("b"));
        System.out.println("是否包含d？，result=" + isKnown.test("d"));

    }


}
