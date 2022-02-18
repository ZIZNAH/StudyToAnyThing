package com.ziznah.rabbit.two;

import com.rabbitmq.client.Channel;
import com.ziznah.rabbit.utils.RabbitMqUtil;

import java.util.Scanner;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.two
 * @Author: sugarzhang
 * @CreateTime: 2022-02-18 12:38
 * @Description: ${Description}
 */
public class Task {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //获取通道
        Channel channel = RabbitMqUtil.getChannel();
        //生成队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //从控制台中获取发送得信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完成：" + message);
        }
    }
}
