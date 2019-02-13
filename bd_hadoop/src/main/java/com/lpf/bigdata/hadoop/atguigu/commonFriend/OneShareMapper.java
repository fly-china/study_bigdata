package com.lpf.bigdata.hadoop.atguigu.commonFriend;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 找出共同好友第一步的mapper
 *
 * @author lipengfei
 * @create 2018-11-23 11:16
 **/
public class OneShareMapper extends Mapper<LongWritable, Text, Text, Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // A:B,C,D,F,E,O
        // B:A,C,E,K

        String line = value.toString();
        if (StringUtils.isNotBlank(line)) {
            String[] splits = line.split(":");

            String person = splits[0];
            String[] friends = splits[1].split(",");

            Text k = new Text(person);
            for (String friend : friends) {
                // 输出<好友，人>
                context.write(new Text(friend), k);
            }

        } else {
            return;
        }


    }
}
