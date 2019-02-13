package com.lpf.bigdata.hadoop.atguigu.commonFriend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 找出共同好友第二步的mapper
 *
 * @author lipengfei
 * @create 2018-11-23 11:16
 **/
public class TwoShareReducer extends Reducer<Text, Text, Text, Text> {


    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // <A-B,E>、<A-B, C>

        StringBuffer sb = new StringBuffer();
        for (Text value : values) {
            sb.append(value).append(" ");
        }

        context.write(key, new Text(sb.toString()));
    }
}
