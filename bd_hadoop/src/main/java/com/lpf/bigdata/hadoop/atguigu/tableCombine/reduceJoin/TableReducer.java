package com.lpf.bigdata.hadoop.atguigu.tableCombine.reduceJoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lipengfei
 * @create 2018-11-21 19:32
 **/
public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {


        List<TableBean> orderList = new ArrayList<>(); //订单表集合
        TableBean pBean = new TableBean();// 商品单条数据

        // *************************
        // 使用Iterable此迭代器，一定记得新建对象，将迭代出来的value赋值给新建对象。
        // 因为在循环中迭代出来的value值，对象地址一直不变。
        // *************************
        TableBean tmpBean;
        for (TableBean value : values) {
            try {
                if (StringUtils.equals(value.getFlag(), "0")) {
                    // 订单表
                    tmpBean = new TableBean();
                    BeanUtils.copyProperties(tmpBean, value);
                    orderList.add(tmpBean);
                } else {
                    // 商品表
                    BeanUtils.copyProperties(pBean, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (TableBean order : orderList) {
            order.setPname(pBean.getPname());
            context.getCounter("TableReducer", "reduce").increment(1);
            context.write(order, NullWritable.get());
        }

    }
}
