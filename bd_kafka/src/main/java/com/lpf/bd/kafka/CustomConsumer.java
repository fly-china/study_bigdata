package com.lpf.bd.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class CustomConsumer {
    public static void main(String[] args) {

        Properties props = new Properties();
        // 定义kakfa 服务的地址，不需要将所有broker指定上
        props.put("bootstrap.servers", "hadoop01:9092");
        // 制定consumer group
        props.put("group.id", "g1");
        // 是否自动确认offset
        props.put("enable.auto.commit", "true");
        // 自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 定义consumer
        KafkaConsumer<String, String> consumer  = new KafkaConsumer<>(props);

        // 释放资源，虽然代码不在最好，但肯定在最后执行
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                if (consumer != null) {
                    consumer.close();
                }
            }
        }));

        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList("first"));

        while (true) {
            // 读取数据，读取超时时间为100ms
//            ConsumerRecords<String, String> records = consumer.poll(100);
            final Duration duration =  Duration.ofMillis(100);
			ConsumerRecords<String, String> records = consumer.poll(duration);

            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }


    }
}
