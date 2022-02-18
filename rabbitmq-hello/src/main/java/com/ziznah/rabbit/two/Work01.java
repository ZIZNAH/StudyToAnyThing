package com.ziznah.rabbit.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.ziznah.rabbit.utils.RabbitMqUtil;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.two
 * @Author: sugarzhang
 * @CreateTime: 2022-02-18 12:29
 * @Description: 多消费者 消费信息
 */
public class Work01 {

    //队列名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args)  throws Exception{

        //获取信道
        Channel channel = RabbitMqUtil.getChannel();
        //成功消费回调
        DeliverCallback deliverCallback = (consumerTag, message) ->{
            String receivedMessage = new String(message.getBody());
            System.out.println("接收到消息：" + receivedMessage);
        };
        //消费者取消消费接口回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消费者取消消费接口回调");
        };

        //提示
        System.out.println("消费者2等待消息。。。");
        //消费信息
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);

    }
}
