package com.ziznah.rabbit.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.ziznah.rabbit.utils.RabbitMqUtil;
import com.ziznah.rabbit.utils.SleepUtil;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.three
 * @Author: sugarzhang
 * @CreateTime: 2022-02-19 18:46
 * @Description: 消费者 手动应答 处理时间较短
 */
public class Work03 {

    //队列名称
    public static final String ACK_QUEUE_NAME = "ack_name";

    public static void main(String[] args) throws Exception {

        //获取信道
        Channel channel = RabbitMqUtil.getChannel();

        System.out.println("C1处理时间较短");

        DeliverCallback deliverCallback = ((consumerTag, message) -> {
            String messages = new String(message.getBody());
            //利用休眠 模拟处理消息时间的长短
            //休眠一秒
            SleepUtil.sleep(1);
            System.out.println("消息已处理： " + messages);
            /**
             * 将消息标记
             * 1.消息标记 ： 消息的标记
             * 2.是否批量应答消息
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        });

        /**
         * 采用手动应答方式消费消息
         */
        //定义手动应答
        boolean autoAck = false;
        channel.basicConsume(ACK_QUEUE_NAME, autoAck, deliverCallback, (consumerTag, sig) -> {
            System.out.println("消费者取消消费接口或者被中断");
        });

    }
}
