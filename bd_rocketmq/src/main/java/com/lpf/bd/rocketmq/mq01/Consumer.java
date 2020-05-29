package com.lpf.bd.rocketmq.mq01;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * mq的消费者
 *
 * @author lipengfei
 * @create 2020-05-27 22:15
 **/
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        String nameSrv = "192.168.206.201:9876";
        String topic = "topic01";
        String consumerGroup = "myConsumer";

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameSrv);
        // 设置消费者组
        consumer.setConsumerGroup(consumerGroup);
        // 订阅topic，设置subExpression（*通配符代表订阅所有Tag）
        // consumer.subscribe(topic, "*");
        /*
         * subExpression="tag01 || tag02",代表仅接受tag01和tag02两个tag的消息。
         * 此处过滤为消费端过滤，相当于消息被消费掉，但是其余tag的消息被过滤掉了
         */
        consumer.subscribe(topic, "tag01 || tag02");

        // 设置消费模式（CLUSTERING：集群消费；BROADCASTING：广播消费）
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 设置监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("---- 消费者接收到消息 ----");
                for (MessageExt msgExt : msgs) {
                    String msg = new String(msgExt.getBody(), StandardCharsets.UTF_8);
                    System.out.println("接收到消息为：" + msgExt.toString());
//                    String brokerName = msgExt.getBrokerName();
//                    String msgId = msgExt.getMsgId();
//                    int queueId = msgExt.getQueueId();
//                    long queueOffset = msgExt.getQueueOffset();
//                    String msgExtTopic = msgExt.getTopic();
//                    String tags = msgExt.getTags();

                }
                // ack成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });


        consumer.start();
        System.out.println("MQ消费者启动成功...");
    }
}
