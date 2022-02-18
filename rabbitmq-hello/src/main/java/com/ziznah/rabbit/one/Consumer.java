package com.ziznah.rabbit.one;

import com.rabbitmq.client.*;
/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.one
 * @Author: sugarzhang
 * @CreateTime: 2022-02-17 18:27
 * @Description: 消费者 消费消息
 */
public class Consumer {

    //创建队列名称
    public static final String QUEUE_NAME = "hello";

    //消费消息
    public static void main(String[] args)  throws Exception{

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //连接
        factory.setHost("119.29.105.6");
        factory.setUsername("admin");
        factory.setPassword("123");

        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();


        /**
         * 成功消费的回调
         */
        DeliverCallback deliverCallback = (consumerTag,delivery) ->{
            String message = new String(delivery.getBody());
            System.out.println(message);
        };

        /**
         * 取消消费的一个回调
         */
        CancelCallback cancelCallback = consumerTag ->{
            System.out.println("消费消息被中断");
        };

        //通过信道消费消息
        /**
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 代表手动应答
         * 3.消费者成功消费的回调
         * 4.消费者中断消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
