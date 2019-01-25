package com.lpf.bigdata.hadoop.atguigu.commonFriend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 *
 * @author lipengfei
 * @create 2018-11-23 11:31
 **/
public class TwoShareJob {

    public static void main(String[] args) throws Exception {
        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/commonFriend/twoStep.txt";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/commonFriend/two/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TwoShareJob.class);
        job.setMapperClass(TwoShareMapper.class);
        job.setReducerClass(TwoShareReducer.class);


        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        FileInputFormat.addInputPath(job, new Path(inputPath));

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
