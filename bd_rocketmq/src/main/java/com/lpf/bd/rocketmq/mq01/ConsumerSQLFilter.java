package com.lpf.bd.rocketmq.mq01;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 使用SQL过滤的消息消费者
 *
 * @author lipengfei
 * @create 2020-05-28 18:43
 **/
public class ConsumerSQLFilter {

    public static void main(String[] args) throws Exception {

        String nameSrv = "192.168.206.201:9876";
        String topic = "topic01";
        String consumerGroup = "sqlConsumer";

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameSrv);
        consumer.setMessageModel(MessageModel.CLUSTERING);

        /**
         * 订阅topic，及通过sql过滤的方式，获取指定数据，达到数据清洗的目的
         * 构造MessageSelector的sql模式，并编写目标SQL。
         * 但前提是，目标清洗字段，生产者发送消息时，使用putUserProperty对消息指定属性字段
         */

        MessageSelector selector  = MessageSelector.bySql("age >= 18 and age <= 35");
//        MessageSelector selector = MessageSelector.bySql("age >= 88 or age < 10");
//        MessageSelector selector = MessageSelector.bySql("age is not null");
//        MessageSelector selector = MessageSelector.bySql("age = 18");
        consumer.subscribe(topic, selector);


        // 设置监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msgExt : msgs) {
                    String msg = new String(msgExt.getBody(), StandardCharsets.UTF_8);
                    System.out.println("接收到消息为：" + msgExt.toString());
                    System.out.println("接收到消息为：" + msg + "---age：" + JSONObject.toJSONString(msgExt.getUserProperty("age")));
                }
                // ack成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });


        consumer.start();
        System.out.println("MQ消费者启动成功...");
    }
}

/**
 * RocketMQ只定义了一些基本的语法来支持这个功能。 你也可以很容易地扩展它.
 *
 *     数字比较, 像 >, >=, <, <=, BETWEEN, =;
 *     字符比较, 像 =, <>, IN;
 *     IS NULL 或者 IS NOT NULL;
 *     逻辑运算AND, OR, NOT;
 *
 * 常量类型是:
 *
 *     数字, 像123, 3.1415;
 *     字符串, 像‘abc’,必须使用单引号;
 *     NULL, 特殊常数;
 *     布尔常量, TRUE 或FALSE;
 */
