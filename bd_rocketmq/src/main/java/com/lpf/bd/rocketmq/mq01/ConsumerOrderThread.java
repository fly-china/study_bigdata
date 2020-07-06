package com.lpf.bd.rocketmq.mq01;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lipengfei
 * @create 2020-05-30 16:38
 **/
public class ConsumerOrderThread {

    public static void main(String[] args) throws Exception {
        String nameSrv = "192.168.206.201:9876";
        String topic = "topic001";
        String consumerGroup = "OrderThreadConsumer";

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameSrv);
        consumer.subscribe(topic, "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);


        /**
         * MessageListenerOrderly：有序消费的消息。一个队列,一个线程（多个队列可以是相同线程，也可是不同线程）。队列内有序
         * MessageListenerConcurrently：并发消费消息模式
         */
        // 设置最小、最大消费线程数。默认均为：20
        consumer.setConsumeThreadMin(1);
        consumer.setConsumeThreadMax(1);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msgExt : msgs) {
                    String msg = new String(msgExt.getBody(), StandardCharsets.UTF_8);
                    System.out.println("线程：" + Thread.currentThread().getName() + "---接收到消息为：" + msg);
                }
                // ack成功
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        });

//        consumer.setConsumeThreadMin(10);
//        consumer.setConsumeThreadMax(10);
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msgExt : msgs) {
//                    String msg = new String(msgExt.getBody(), StandardCharsets.UTF_8);
//                    System.out.println("线程：" + Thread.currentThread().getName() + "---接收到消息为：" + msg);
//                }
//                // ack成功
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//            }
//        });

        consumer.start();
        System.out.println("MQ消费者启动成功...");
    }
}
