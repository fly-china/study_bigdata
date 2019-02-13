package com.lpf.bigdata.hadoop.atguigu.indexOrdering;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author lipengfei
 * @create 2018-11-22 17:32
 **/
public class OneIndexDriver {

    public static void main(String[] args) throws Exception {


        Long start = System.currentTimeMillis();

        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/indexOrdering/";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/indexOrdering/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(OneIndexDriver.class);
        job.setMapperClass(OneIndexMapper.class);
        job.setReducerClass(OneIndexReducer.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        boolean flag = job.waitForCompletion(true);
        Long end = System.currentTimeMillis();
        System.out.println("---------------耗时：" + (end - start));
        System.exit(flag ? 0 : -1);


    }
}
