package com.lpf.bigdata.hadoop.atguigu.phoneData.sort;

import com.lpf.bigdata.hadoop.atguigu.phoneData.FlowBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 按手机号（当前手机号为value值）分区规则,默认按照HashPartitioner进行分区
 *
 * @author lipengfei
 * @create 2018-11-12 18:30
 **/
public class PhoneNumSortPartition extends Partitioner< FlowBeanComparable, Text> {


    @Override
    public int getPartition(FlowBeanComparable flowBean, Text value, int numReduceTasks) {

        int partition = 0;

        // 获取电话号码前3位
        String phoneNum = value.toString();
        String perfix = phoneNum.substring(0, 3);

        // 按照电话号码前三位分区
        if(StringUtils.equals(perfix, "135")){
            partition = 1;
        }else if(StringUtils.equals(perfix, "136")){
            partition = 2;
        }else if(StringUtils.equals(perfix, "137")){
            partition = 3;
        }else if(StringUtils.equals(perfix, "138")){
            partition = 4;
        }

        return partition;
    }
}
