package com.lpf.bd.rocketmq.mq01;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生产者-发送事务消息
 *
 * @author lipengfei
 * @create 2020-05-29 15:39
 **/
public class ProducerTransaction {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static int counter = 0;

    public static void main(String[] args) throws Exception {
        String nameSrv = "192.168.206.201:9876";
        String producerGroup = "transProducer";
        String topic = "topic01";
        String tag01 = "tagTransaction";

        TransactionMQProducer producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr(nameSrv);

        // 设置事务相关监听器，以便broker回调执行事务。或检查半消息事务状态
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                // 在此处执行本地事务相关的方法，做业务处理。发送事务消息后：立即回调一次
                System.out.println("============= executeLocalTransaction 时间：" + sdf.format(new Date()));
                System.out.println("msg=" + msg);
                System.out.println("transactionId=" + msg.getTransactionId());

                try {
                    // 内部业务逻辑处理
                    // 包括：数据库级别的操作（也可以是数据库的事务操作）

                    // 若业务执行成功，确认消息，提交事务
                    // return LocalTransactionState.COMMIT_MESSAGE;
                }catch (Exception e){
                    e.printStackTrace();
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }

                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msgExt) {
                counter++;
                // broker回调此方法，查询该条半消息对应的本地事务的最新状态。默认：1分钟回调一次，连续回调15次
                // msgId每次会不同，TransactionId从始至终不变
                System.out.println("============= checkLocalTransaction 次数：" + counter + " --时间：" + sdf.format(new Date()));
                System.out.println("msg=" + new String(msgExt.getBody(), StandardCharsets.UTF_8));
                System.out.println("msgId=" + msgExt.getMsgId());
                System.out.println("transactionId=" + msgExt.getTransactionId());


                // 确认消息，提交事务
                // return LocalTransactionState.COMMIT_MESSAGE;
                // 回滚事务
                // return LocalTransactionState.ROLLBACK_MESSAGE;
                // 暂时不确定，broker稍后继续回调
                // return LocalTransactionState.UNKNOW;
                return LocalTransactionState.UNKNOW;
            }
        });

        producer.start();

        Message message = new Message(topic, tag01, "msgKey-transaction", ("事务消息哈哈哈，" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        TransactionSendResult result = producer.sendMessageInTransaction(message, null);
        System.out.println("事务消息发送结果为：" + JSONObject.toJSONString(result));
        System.out.println("=======================");


    }
}
