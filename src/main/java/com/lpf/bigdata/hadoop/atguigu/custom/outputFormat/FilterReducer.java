package com.lpf.bigdata.hadoop.atguigu.custom.outputFormat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-21 17:24
 **/
public class FilterReducer extends Reducer<Text, NullWritable,Text, NullWritable> {


    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        String k = key.toString();
        k = k + "\r\n";

        context.write(new Text(k), NullWritable.get());
    }
}
