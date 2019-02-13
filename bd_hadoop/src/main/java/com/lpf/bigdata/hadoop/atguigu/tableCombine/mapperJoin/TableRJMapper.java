package com.lpf.bigdata.hadoop.atguigu.tableCombine.mapperJoin;

import com.lpf.bigdata.hadoop.atguigu.tableCombine.reduceJoin.TableBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lipengfei
 * @create 2018-11-21 19:24
 **/
public class TableRJMapper extends Mapper<LongWritable, Text, TableBean, NullWritable> {


    Map<String, String> pNameMap = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        // pid.txt不在需要全路径，因为在job中已通过DistributeCache设置到缓存中
        // 直接根据文件名获取即可
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream("pid.txt"), "UTF-8"))) {
            String line;
            while (StringUtils.isNotBlank(line = bufferedReader.readLine())) {
                String[] fields = line.split("\t");
                pNameMap.put(fields[0], fields[1]);
            }
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {


        String line = value.toString();
        if (StringUtils.isNotBlank(line)) {

            String[] fields = line.split("\t");

            FileSplit splitInput = (FileSplit) context.getInputSplit();
            String tableName = splitInput.getPath().getName();
            System.out.println(tableName + "*********************" + line);

            TableBean tableBean = new TableBean();

            // 订单表--flag=0
            tableBean.setOrder_id(fields[0]);
            tableBean.setAmount(Integer.valueOf(fields[2]));
            tableBean.setP_id(fields[1]);
            tableBean.setFlag("0");
            tableBean.setPname(pNameMap.get(tableBean.getP_id()));


            context.getCounter("TableTJMapper", "map").increment(1);
            context.write(tableBean, NullWritable.get());
        }
    }
}
