package com.lpf.bigdata.hadoop.atguigu.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author lipengfei
 * @create 2018-11-16 15:14
 **/
public class OrderSortGroupingComparator extends WritableComparator {

    public OrderSortGroupingComparator() {
        super(OrderBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        int result;
        if (Integer.valueOf(aBean.getOrderId().trim()) > Integer.valueOf(bBean.getOrderId().trim())) {
            result = 1;
        } else if (Integer.valueOf(aBean.getOrderId().trim()) < Integer.valueOf(bBean.getOrderId().trim())) {
            result = -1;
        } else {
            result = 0;
        }

        return result;
    }

}
