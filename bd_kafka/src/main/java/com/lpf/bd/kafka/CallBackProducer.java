package com.lpf.bd.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CallBackProducer {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号（配置一个和多个均可，即使只配置一个，一会从单个broker中查找到其他的broker信息）
        props.put("bootstrap.servers", "hadoop01:9092,hadoop02:9092,hadoop03:9092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop01:9092,hadoop02:9092");// 和上一句效果相同
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 增加服务端请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小(RecordAccumulator累加器)
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());// 和上一句效果相同
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 自定义分区
//		props.put("partitioner.class", "com.atguigu.kafka.CustomPartitioner");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        for (int i = 0; i < 50; i++) {
            Thread.sleep(500);
            // 未指定key和partition，随机分区
//			kafkaProducer.send(new ProducerRecord<String, String>("first", "hh" + i), new Callback() {
//				@Override
//				public void onCompletion(RecordMetadata metadata, Exception exception) {
//
//					if (metadata != null) {
//
//						System.out.println(metadata.partition() + "---" + metadata.offset());
//					}
//				}
//			});

            // 指定key，不指定partition，按照key的hash，会发送至固定的某一分区
//			kafkaProducer.send(new ProducerRecord<String, String>("test1", "aa" ,"hh" + i), new Callback() {
//				@Override
//				public void onCompletion(RecordMetadata metadata, Exception exception) {
//
//					if (metadata != null) {
//
//						System.out.println(metadata.partition() + "---" + metadata.offset());
//					}
//				}
//			});

            // 指定partition，key不会再生效，会发送到指定的part分区
            Future<RecordMetadata> send = kafkaProducer.send(new ProducerRecord<String, String>("first2", 1, "aaa", "hh" + i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {

                            if (metadata != null) {

                                System.out.println(metadata.partition() + "---" + metadata.offset());
                            }
                        }
                    });

            // 下面可有可无，get方法用来阻塞等待KafKa的相应，直到消息发送成功，或者发送异常。
            // 但一般还是使用上述send方法中指定的callback函数，做回调
            try {
                RecordMetadata recordMetadata = send.get(); // recordMetadata中包含了消息的一些元素，如：主题、分区号、分区中的偏移量、时间戳等。
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        kafkaProducer.close();

    }

}
