package com.lpf.bigdata.hadoop.atguigu.indexOrdering;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lipengfei
 * @create 2018-11-22 17:16
 **/
public class OneIndexReducer extends Reducer<Text, Text, Text, IntWritable> {

    Map<String, Integer> countMap = null;

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        countMap = new HashMap<>();

        // key = atguigu
        for (Text value : values) {
            // value = "a.txt---1"
            String[] fields = value.toString().split("---");

            if (countMap.get(fields[0]) == null) {
                countMap.put(fields[0], 1);
            } else {
                Integer oldCount = countMap.get(fields[0]);
                countMap.put(fields[0], oldCount + 1);
            }
        }

        for (Map.Entry<String, Integer> mapEntry : countMap.entrySet()) {
            context.write(new Text(key + "---" + mapEntry.getKey()), new IntWritable(mapEntry.getValue()));
        }
    }
}
