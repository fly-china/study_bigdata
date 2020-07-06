package com.lpf.bd.rocketmq.mq01;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用SQL过滤的消息生产者
 *
 * @author lipengfei
 * @create 2020-05-28 18:42
 **/
public class ProducerSQLFilter {

    public static void main(String[] args) throws Exception {
        String nameSrv = "192.168.206.201:9876";
        String producerGroup = "sqlProducer";
        String topic = "topic01";
        String tag01 = "tagFilter";

        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameSrv);
        producer.start();
        System.out.println("SQLFilter-MQ生产者启动成功...");

        List<Message> lists = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String msg = "女孩岁数：" + i;
            String msgKey = "SQLFilter-msgKey" + i;
            Message message = new Message(topic, tag01, msgKey, msg.getBytes(StandardCharsets.UTF_8));
            // 设置属性，以便使用sql filter功能
            message.putUserProperty("age", i + "");
            message.putUserProperty("height", 120 + i + "");
            lists.add(message);
            SendResult sendResult = producer.send(message);
            System.out.println("消息发送结果为：" + JSONObject.toJSONString(sendResult));
        }

        Thread.sleep(2000);
        producer.shutdown();
        System.out.println("已经停机");

    }
}
