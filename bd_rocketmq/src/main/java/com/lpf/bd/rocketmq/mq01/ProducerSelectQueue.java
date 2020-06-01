package com.lpf.bd.rocketmq.mq01;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 生产者-选择队列
 *
 * @author lipengfei
 * @create 2020-05-30 15:39
 **/
public class ProducerSelectQueue {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static int counter = 0;

    public static void main(String[] args) throws Exception {
        String nameSrv = "192.168.206.201:9876";
        String producerGroup = "selectorProducer";
        String topic = "topic001";
        String tag01 = "tagSelector";

        TransactionMQProducer producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr(nameSrv);
        producer.start();
        System.out.println("生产者启动成功...");


        // 1、定义队列选择器。默认实现有：SelectMessageQueueByHash、SelectMessageQueueByRandom等
        MessageQueueSelector selector = new MessageQueueSelector() {
            // 可以根据消息msg和自定义参数arg，定义选择队列的依据
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                // 假设arg定义为用户userId，我们根据userId选择使用队列
                int userId = (int) arg;
                int value = userId % mqs.size();
                return mqs.get(value);
            }
        };
        // 发送消息时，指定队列选择器，并传入参数
        for (int i = 0; i < 20; i++) {
            int userId = i;
            Message message = new Message(topic, tag01, "msgKey-selector", ("*****我是用户：" + userId).getBytes(StandardCharsets.UTF_8));
            producer.send(message, selector, userId);
        }


        System.out.println("=======================");
        Thread.sleep(2000);
        producer.shutdown();

    }
}
