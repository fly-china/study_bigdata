package com.lpf.jdk8.lambda.SortDemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author lipengfei
 * @date 2019-07-12 16:49
 **/
public class SortFunctionDemo {

    public static int getMin(OrderVO orderVO) {
        return orderVO.getOrderNum().getMin();
    }


    private static List<OrderNum> orderList;
    private static List<OrderVO> orderList2;


    public static void main(String[] args) {

        // 先按照最小值有序重排List
        orderList.stream().sorted(Comparator.comparing(OrderNum::getMin));


        // 方式一：外层对象OrderVO中，定义非静态方法获取OrderNum的Min值
        orderList2.stream().sorted(Comparator.comparing(OrderVO :: getOrderMin))
                .forEach(p -> System.out.println(p.getOrderNum().getMin()));

         // 方式二：任意类中，定义静态方法：根据OrderVO获取OrderNum的Min值
        orderList2.stream()
                .sorted(Comparator.comparing(SortFunctionDemo:: getMin))
                .forEach(p -> System.out.println(p.getOrderNum().getMin()));

        // 方式三：idea会提示，自动优化到第四种方式
        orderList2.stream()
                .sorted((OrderVO o1,OrderVO  o2) -> o1.getOrderNum().getMin()- o2.getOrderNum().getMin())
                .forEach(p -> System.out.println(p.getOrderNum().getMin()));

        // 方式四：
        orderList2.stream().sorted(Comparator.comparing(ite -> ite.getOrderNum().getMin()))
                .forEach(p -> System.out.println(p.getOrderNum().getMin()));


        // 从第二个开始遍历，需要第二个和第一个作比较
//        for (int i = 1; i < orderList.size(); i++) {
//            OrderNum orderNum = orderList.get(i);
//            OrderNum lastOrderNum = orderList.get(i - 1);
//
//            // 只有本次兑现的min值 大于 上个对象的max值，才不会重叠
//            // 当本个对象的min值 小于等于 上个对象的max值，则定义为出现重叠
//            if (orderNum.getMin() <= lastOrderNum.getMax()) {
//                System.out.println("出现重叠了");
//                return;
//            }
//        }
//
//        System.out.println("完美通过，无重叠");
    }


    static {
        orderList = new ArrayList<>();
        orderList2 = new ArrayList<>();
        OrderNum order1 = new OrderNum();
        order1.setMin(1);
        order1.setMax(3);

        OrderNum order2 = new OrderNum();
        order2.setMin(20);
        order2.setMax(29);

        OrderNum order3 = new OrderNum();
        order3.setMin(11);
        order3.setMax(13);

        OrderNum order4 = new OrderNum();
        order4.setMin(3);
        order4.setMax(6);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);

        orderList2.add(new OrderVO(order1));
        orderList2.add(new OrderVO(order2));
        orderList2.add(new OrderVO(order3));
        orderList2.add(new OrderVO(order4));
    }

}
