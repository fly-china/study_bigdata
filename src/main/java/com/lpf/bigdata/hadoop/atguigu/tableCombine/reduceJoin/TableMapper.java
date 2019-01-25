package com.lpf.bigdata.hadoop.atguigu.tableCombine.reduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 *
 * @author lipengfei
 * @create 2018-11-21 19:24
 **/
public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {

    TableBean tableBean;
    Text k;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {


        String line = value.toString();
        System.out.println("***************" + line);
        String[] fields = line.split("\t");

        FileSplit splitInput = (FileSplit) context.getInputSplit();
        String tableName = splitInput.getPath().getName();

        tableBean = new TableBean();
        k = new Text();

        if (tableName.startsWith("order")) {
            // 订单表--flag=0
            tableBean.setOrder_id(fields[0]);
            tableBean.setAmount(Integer.valueOf(fields[2]));
            tableBean.setP_id(fields[1]);
            tableBean.setFlag("0");
            tableBean.setPname("");

            k.set(fields[1]);
            context.getCounter("TableTJMapper", "orderCounter").increment(1);
        } else {
            // 商品表--flag=1
            tableBean.setP_id(fields[0]);
            tableBean.setPname(fields[1]);
            tableBean.setFlag("1");
            tableBean.setAmount(0);
            tableBean.setOrder_id("");

            k.set(fields[0]);
            context.getCounter("TableMapper", "pidCounter").increment(1);
        }

        context.write(k, tableBean);
    }
}
