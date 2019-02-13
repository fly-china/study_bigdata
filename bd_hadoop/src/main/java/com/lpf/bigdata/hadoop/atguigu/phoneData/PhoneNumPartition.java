package com.lpf.bigdata.hadoop.atguigu.phoneData;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 按手机号分区规则,默认按照HashPartitioner进行分区
 *
 * @author lipengfei
 * @create 2018-11-12 18:30
 **/
public class PhoneNumPartition extends Partitioner<Text, FlowBean> {


    @Override
    public int getPartition(Text key, FlowBean flowBean, int numReduceTasks) {

        int partition = 0;

        // 获取电话号码前3位
        String phoneNum = key.toString();
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
