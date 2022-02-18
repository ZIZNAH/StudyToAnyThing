package com.ziznah.rabbit.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.utils
 * @Author: sugarzhang
 * @CreateTime: 2022-02-18 12:25
 * @Description:  抽取rabbitmq工具类
 */
public class RabbitMqUtil {

    public static Channel getChannel() throws Exception{
        //创建一个工厂
        ConnectionFactory factory = new ConnectionFactory();
        //连接
        factory.setHost("119.29.105.6");
        factory.setUsername("admin");
        factory.setPassword("123");
        //创建连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        return channel;
    }
}
