package com.ziznah.rabbit.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者：发送消息
 */
public class Producer {


    //队列名称
    public static final String QUEUE_NAME = "hello";

    /**
     * 生产者
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {

        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //连接工厂
        //主机地址
        factory.setHost("119.29.105.6");
        //账户
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        //创建一个连接
        Connection connection = factory.newConnection();

        //创建一个信道
        Channel channel = connection.createChannel();
        //生成一个队列
        //1.队列名称
        //2.队列里面的消息是否持久化 默认消息存储在内存中 默认 false
        //3.该队列是否只提供一个消费者进行消费 是否共享 true 可以多个消费者消费 默认false
        //4.是否自动删除 最后一个消费者断开连接以后 该队列是否自动删除 true 自动删除
        //5.其他参数
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息
        String message = "hello world";
        /**
         * 发送消息
         * 1.发送到哪个交换机
         * 2.路由的key是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("发送完成");
    }

}
