package com.lpf.bigdata.hadoop.atguigu.order;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author lipengfei
 * @create 2018-11-16 14:55
 **/
public class OrderGroupJob {

    public static void main(String[] args) throws Exception {
        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/order/orderRecord";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/order/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(OrderGroupJob.class);
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));


        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);


        //  设置Partition分区,按订单号分区
        job.setPartitionerClass(OrderPartition.class);
        job.setNumReduceTasks(3);

        // 设置GroupingComparator
        job.setGroupingComparatorClass(OrderSortGroupingComparator.class);

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);
    }
}
