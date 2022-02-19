package com.ziznah.rabbit.three;

import com.rabbitmq.client.Channel;
import com.ziznah.rabbit.utils.RabbitMqUtil;

import java.util.Scanner;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.three
 * @Author: sugarzhang
 * @CreateTime: 2022-02-19 18:50
 * @Description: 生产者 发布消息
 */
public class Task02 {

    //队列名称
    public static final String ACK_QUEUE_NAME = "ack_name";

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(ACK_QUEUE_NAME, false, false, false, null);

        /**
         * 从控制台获取消息 发布
         */
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("", ACK_QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("发布者发布消息：" + message);
        }

    }

}
