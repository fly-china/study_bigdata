package com.lpf.bigdata.hadoop.atguigu.custom.inputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 自定义RecordReader
 *
 * @author lipengfei
 * @create 2018-11-20 17:08
 **/
public class WholeFileRecordReader extends RecordReader<NullWritable, BytesWritable> {

    FileSplit split;
    Configuration configuration;

    boolean processed = false;
    BytesWritable value = new BytesWritable();

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
        this.split = (FileSplit) inputSplit;
        this.configuration = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {


        if (!processed) {
            FSDataInputStream fis = null;
            try {
                // 1、定义缓存区
                byte[] buffer = new byte[(int) split.getLength()];

                // 2、获取文件系统
                Path path = split.getPath();
                FileSystem fileSystem = path.getFileSystem(configuration);

                // 3、读取数据
                fis = fileSystem.open(path);

                // 4、读取文件内容
                IOUtils.readFully(fis, buffer, 0, buffer.length);

                // 5、输出文件内容
                value.set(buffer, 0, buffer.length);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeStream(fis);
            }

            processed = true;
            return true;
        }


        return false;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return processed ? 1 : 0;
    }

    @Override
    public void close() throws IOException {

    }
}
