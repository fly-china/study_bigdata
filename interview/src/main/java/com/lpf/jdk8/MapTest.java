package com.lpf.jdk8;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lipengfei
 * @create 2019-04-02 11:35
 **/
public class MapTest {

    private static final Integer sex_FEMALE = 0;
    private static final Integer sex_MALE = 1;
    List<UserVO> userVOList;

    @Before
    public void before() throws Exception {
        userVOList = new ArrayList<>();
        UserVO u1 = new UserVO();
        UserVO u2 = new UserVO();
        UserVO u3 = new UserVO();
        u1.setUserId("1");
        u1.setUserName("老大");
        u1.setUserSex(1);
        u1.setAge(11);
        u2.setUserId("2");
        u2.setUserName("老二");
        u2.setUserSex(0);
        u2.setAge(22);
        u3.setUserId("3");
        u3.setUserName("老三");
        u3.setUserSex(1);
        u3.setAge(33);
        userVOList.add(u1);
        userVOList.add(u2);
        userVOList.add(u3);
    }


    @Test
    public void testStream1() {
        System.out.println(JSONObject.toJSONString(userVOList));
        Map<String, UserVO> userMap = new HashMap<>();

        // 将List集合转换为，以集合中UserVO对象中Id为key，userVo对象为value的map集合
        if (CollectionUtils.isNotEmpty(userVOList)) {
            userMap = userVOList.stream().
                    collect(Collectors.toMap(UserVO::getUserId, userVO -> userVO));
        }

        System.out.println(JSONObject.toJSONString(userMap));

    }

    @Test
    public void testStream2() {

        List<UserBO> userBOS = userVOList.stream()
                .filter(userVO -> sex_FEMALE != userVO.getUserSex()) // 过滤出条件相等的,被过滤掉的不会再走后续中间操作
                .map(tempVO -> {
                    UserBO userBO = new UserBO();
                    userBO.setSex(tempVO.getUserSex());
                    userBO.setUserName(tempVO.getUserName());
                    userBO.setUserId(tempVO.getUserId());
                    return userBO;
                })
                .peek(tempVo -> System.out.println("----" + tempVo.toString()))
                .collect(Collectors.toList());

        System.out.println("userBOS内容为：" + userBOS.toString());
    }


    @Test
    public void testMethod3() {
        List<String> list = Lists.newArrayList(
                "bcd", "cde", "def", "abc", "ffff");
        List<String> result = list.stream()
                //.parallel()
                .filter(e -> e.length() >= 3)
                .map(e -> e.charAt(0))
//                .peek(System.out::println)
                //.sorted()
                //.peek(e -> System.out.println("++++" + e))
                .map(e -> String.valueOf(e))
                .collect(Collectors.toList());
        System.out.println("----------------------------");
        System.out.println(result);
        System.out.println("----------------------------");

        List<String> result2 =
                list.stream()
                        //.parallel()
                        .filter(e -> e.length() >= 4)
                        .peek(System.out::println)
                        .map(String::toUpperCase)
                        .peek(System.out::println)
                        .collect(Collectors.toList());
        System.out.println("----------------------------");
        System.out.println(result2);
    }


    @Test
    public void testMapToInt() {

        int sumAge = userVOList.stream()
                .mapToInt(UserVO::getAge).sum();
        System.out.println("所有用户的年龄和为：" + sumAge);

        OptionalDouble average = userVOList.stream()
                .mapToInt(UserVO::getAge).average();
        if (average.isPresent()) {
            System.out.println("所有用户的平均年龄为：" + average.toString());
        }

        List<String> collect = userVOList.stream()
                .map(UserVO::getUserName)
                .collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect2 = userVOList.stream()
                .map(UserVO::getAge)
                .collect(Collectors.toList());
        System.out.println(collect2);

/*        List<Integer> collect3 = userVOList.stream()
                .mapToInt(UserVO::getAge)
                .collect(Collectors.toList());
        System.out.println(collect3);*/
    }


    @Test
    public void testOptional() {

        Optional<UserVO> first = userVOList.stream()
                .filter(userVO -> userVO.getUserSex() == 1)
                .map(userVO -> {
                    userVO.setAge(100);
                    return userVO;
                }).findFirst()
                .filter(userVO -> userVO.getAge() >= 100) // 如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional。
                ;

        first.ifPresent(userVO -> userVO.setRemark("我是这条gai最靓的"));
        Optional<UserVO> userVO1 = first.filter(userVO -> userVO.getAge() != 100);


        if (first.isPresent()) {
            System.out.println("匹配数据存在,结果为：" + first.get().toString());

        } else {
            System.out.println("匹配数据不存在");
        }

        System.out.println("----------下面测试of()----------");
        UserVO userVO2 = null;
        Optional<UserVO> second = Optional.ofNullable(userVO2);
//        Optional<UserVO> second = Optional.of(userVO2); // 对象为null时，构建会抛npe
        System.out.println(second.isPresent());


        System.out.println("-----------下面测试orElse()----------");
        Optional<String> empty = Optional.ofNullable(null);
        Optional<String> name = Optional.of("lpf");

        System.out.println(empty.orElse("Default name"));
        System.out.println(name.orElse("Default name"));

        String defaultName = "Jack";
        UserVO tempUser = userVOList.get(0);

        // orElseGet和orElse的区别在于orElseGet方法可以接受Supplier接口的实现用来生成默认值
        System.out.println(empty.orElseGet(() -> tempUser.getUserName().toUpperCase()));
        System.out.println(name.orElseGet(() -> userVO1.get().getUserName()));
    }


    @Test
    public void testForeach() {

        System.out.println("遍历前：" + JSONObject.toJSONString(userVOList));
        userVOList.stream().forEach(userVO -> userVO.setAge(1000));
        System.out.println("遍历后：" + JSONObject.toJSONString(userVOList));
    }

    @Test
    public void testCount() {
        long num = userVOList.stream()
                .filter(userVO -> Objects.equals(userVO.getUserName(), "老大1"))
                .count();
        System.out.println("符合条件个数：" + num);
    }

}
