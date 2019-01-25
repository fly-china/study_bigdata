package com.lpf.bigdata.hadoop.atguigu.custom.outputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author lipengfei
 * @create 2018-11-21 17:25
 **/
public class FilterJob {

    public static void main(String[] args) throws Exception {
        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/cusOutput/";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/cusOutput/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FilterJob.class);
        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(FilterReducer.class);

        // 设置自定义输出的outputFormat
        job.setOutputFormatClass(FilterOutputFormat.class);

        // 虽然我们自定义了 outputformat，但是因为我们的 outputformat 继承自 fileoutputformat
        // 而 fileoutputformat 要输出一个_SUCCESS 文件，所以，在这还得指定一个输出目录
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        FileInputFormat.addInputPath(job, new Path(inputPath));


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);

    }
}
