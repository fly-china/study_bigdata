package com.lpf.bd.rocketmq.mq01;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * MQ生产者
 *
 * @author lipengfei
 * @create 2020-05-28 15:20
 **/
public class Producer01 {

    public static void main(String[] args) throws Exception {
        String nameSrv = "192.168.206.201:9876";
        String producerGroup = "myProducer";
        String topic = "topic01";
        String tag01 = "tag01";
        String tag02 = "tag02";


        DefaultMQProducer mqProducer = new DefaultMQProducer(producerGroup);
        mqProducer.setNamesrvAddr(nameSrv);
        mqProducer.start();
        System.out.println("MQ生产者启动成功...");


        Message message01 = new Message(topic, tag01, "msgKey01", ("说你爱我01" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        Message message02 = new Message(topic, tag02, "msgKey02", ("说你爱我02" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        List<Message> messageList = Lists.newArrayList(message01, message02);
        // 一、可靠同步发送：直至发送mq到broker成功。内部有失败重试机制，默认两次
        SendResult sendResult01 = mqProducer.send(messageList);
        System.out.println("同步消息-发送成功，发送结果为：" + JSONObject.toJSONString(sendResult01));
        Thread.sleep(500);


        Message message03 = new Message(topic, tag01, "msgKey01-async", ("async-说你爱我01" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        // 二、可靠异步发送：直至发送mq到broker成功。内部有失败重试机制，默认两次
        mqProducer.send(message03, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步发送MQ消息成功，响应结果为：" + JSONObject.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("异步发送MQ消息失败，原因：" + e.getMessage());
            }
        });
        System.out.println("异步消息已发送，等待响应结果...........");
        Thread.sleep(1000);

        Message message04 = new Message(topic, tag01, "msgKey-oneweay", ("oneweay-说你爱我02" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        // 三、单向发送：只发送请求不等待应答。吞吐量最高，但是可能丢消息，适合可靠性不高的场景，如：日志采集
        mqProducer.sendOneway(message04);
        System.out.println("单向消息-发送成功，此方法无返回值");


        Thread.sleep(2000);
        mqProducer.shutdown();
        System.out.println("已经停机");
    }


}
