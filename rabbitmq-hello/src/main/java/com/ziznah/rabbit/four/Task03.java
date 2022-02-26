package com.ziznah.rabbit.four;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.ziznah.rabbit.utils.RabbitMqUtil;

import java.util.Scanner;

/**
 * 消息实现持久化
 * 1.队列声明时要声明此队列为持久化队列
 * 2.队列发布消息时，BasicProperties props 要设置为 MessageProperties.PERSISTENT_TEXT_PLAIN （没有真正写入到磁盘）
 *
 * 发布确认：
 * 1.设置要求队列必须持久化
 * channel.queueDeclare 声明队列的时候 设置持久化
 * 2.设置要求队列中的消息必须持久化
 * channel.basicPublish 发布消息的时候设置消息持久化 MessageProperties.PERSISTENT_TEXT_PLAIN
 * 3.发布确认
 * channel.confirmSelect() 获取队列之后就可设置信道发布确认
 */
public class Task03 {

    //设置队列名
    public static final String ACK_QUEUE_NAME = "ack_name";

    public static void main(String[] args) throws Exception {
        //获取队列
        Channel channel = RabbitMqUtil.getChannel();
        //发布确认
        channel.confirmSelect();

        //设置声明队列持久化
        boolean durable = true;
        channel.queueDeclare(ACK_QUEUE_NAME,durable,false,false,null);

        //控制台发布消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            //发布消息
            channel.basicPublish("",ACK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("发布者发布消息：" + message);
        }
    }
}
