package com.lpf.bigdata.youtobe.etl;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * ETL之Mapper
 *
 * @author lipengfei
 * @create 2019-01-28 11:21
 **/
public class VideoETLMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

    Text text;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        String etlString = ETLUtil.getETLString(line);
        if (StringUtils.isBlank(etlString))
            return;

        text = new Text();
        text.set(etlString);
        context.write(NullWritable.get(), text);
    }
}
