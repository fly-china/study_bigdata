package com.lpf.bigdata.hadoop.atguigu.commonFriend;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * 找出共同好友第二步的mapper
 *
 * @author lipengfei
 * @create 2018-11-23 11:16
 **/
public class TwoShareMapper extends Mapper<LongWritable, Text, Text, Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // A I,K,C,B,G,F,H,O,D,     A是"I,K,C,B,G,F,H,O,D"的好友
        // B A,F,J,E,               B是"A,F,J,E"的好友

        String line = value.toString();
        if (StringUtils.isNotBlank(line)) {
            String[] splits = line.split("\t");

            String friend = splits[0];
            String[] persons = splits[1].split(",");
            Arrays.sort(persons);

            for (int i = 0; i < persons.length - 1; i++) {
                for (int j = i + 1; j < persons.length; j++) {
//                    String person1 = persons[i];
//                    String person2 = persons[j];
//
//                    Text k = null;
//                    if (person1.compareTo(person2) > 0) {
//                        k = new Text(person2 + "-" + person1);
//                    } else {
//                        k = new Text(person1 + "-" + person2);
//                    }
//
//                    // 发出 <人-人，好友> ，这样，相同的“人-人”对的所有好友就会到同 1 个 reduce 中去
//                    context.write(k, new Text(friend));

                    context.write(new Text(persons[i] + "-" + persons[j]), new Text(friend));
                }

            }


        } else {
            return;
        }


    }
}
