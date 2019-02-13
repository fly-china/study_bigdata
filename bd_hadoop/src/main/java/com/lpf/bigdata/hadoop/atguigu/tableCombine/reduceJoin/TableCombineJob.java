package com.lpf.bigdata.hadoop.atguigu.tableCombine.reduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Mapper端的JOIN
 *
 * @author lipengfei
 * @create 2018-11-21 22:54
 **/
public class TableCombineJob {

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();

        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/tableCombine";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/tableCombine/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TableCombineJob.class);
        job.setMapperClass(TableMapper.class);
        job.setReducerClass(TableReducer.class);


        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TableBean.class);

        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);

        boolean flag = job.waitForCompletion(true);
        Long end = System.currentTimeMillis();
        System.out.println("---------------耗时：" + (end - start));
        System.exit(flag ? 0 : -1);


        ReentrantLock reLock = new ReentrantLock(true);

    }
}
