package com.lpf.bigdata.hadoop.atguigu.tableCombine.mapperJoin;

import com.lpf.bigdata.hadoop.atguigu.tableCombine.reduceJoin.TableBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

/**
 * Reducer端的JOIN
 *
 * @author lipengfei
 * @create 2018-11-22 11:45
 **/
public class TableRJJob {

    public static void main(String[] args) throws Exception {

        Long start = System.currentTimeMillis();

        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/tableCombine/";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/tableCombineRJ/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TableRJJob.class);
        job.setMapperClass(TableRJMapper.class);


        FileInputFormat.addInputPath(job, new Path(inputPath + "order.txt"));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);

        // mapper端直接输出，如需启用redceTask
        job.setNumReduceTasks(0);

        // 设置分布式缓存（DistributedCache）
        job.addCacheFile(new URI("file:////" + inputPath + "pid.txt"));

        boolean flag = job.waitForCompletion(true);
        Long end = System.currentTimeMillis();
        System.out.println("---------------耗时：" + (end - start));
        System.exit(flag ? 0 : -1);

    }
}
