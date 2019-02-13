package com.lpf.bigdata.hadoop.atguigu.phoneData;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 流量计算mapper
 *
 * @author lipengfei
 * @create 2018-11-03 16:32
 **/
public class FlowSumReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    FlowBean v = null;

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long sum_upFlow = 0;
        long sum_downFlow = 0;

        for (FlowBean value : values) {
            sum_upFlow += value.getUpFlow();
            sum_downFlow += value.getDownFlow();
        }
        v = new FlowBean(sum_upFlow, sum_downFlow);

        context.write(key , v);
    }
}
