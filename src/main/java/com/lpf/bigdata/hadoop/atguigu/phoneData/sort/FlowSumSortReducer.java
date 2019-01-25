package com.lpf.bigdata.hadoop.atguigu.phoneData.sort;

import com.lpf.bigdata.hadoop.atguigu.phoneData.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 流量计算mapper
 *
 * @author lipengfei
 * @create 2018-11-03 16:32
 **/
public class FlowSumSortReducer extends Reducer<FlowBeanComparable, Text, Text, FlowBeanComparable> {

    FlowBean v = null;

    @Override
    protected void reduce(FlowBeanComparable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        // mapper的输出(K,V)为（FlowBeanComparable,Text）
        // reducer需要转换输出的(K,V)为（Text,FlowBeanComparable）---（手机号、流量）
        context.write(values.iterator().next(), key);
    }
}
