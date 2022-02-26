package com.ziznah.rabbit.five;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.ziznah.rabbit.utils.RabbitMqUtil;

import java.util.UUID;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.five
 * @Author: sugarzhang
 * @CreateTime: 2022-02-27 00:04
 * @Description: 不同确认发布的方式
 *
 * 发布确认：
 * 1.设置要求队列必须持久化
 * channel.queueDeclare 声明队列的时候 设置持久化
 * 2.设置要求队列中的消息必须持久化
 * channel.basicPublish 发布消息的时候设置消息持久化 MessageProperties.PERSISTENT_TEXT_PLAIN
 * 3.发布确认
 * channel.confirmSelect() 获取队列之后就可设置信道发布确认
 *
 * 1、单个发布确认
 * 2、批量发布确认
 * 3、异步发布确认
 */
public class ConfirmMessage {

    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception{
        ConfirmMessage.publishMessageSingle();

    }

    /**
     * 单个确认发布
     * @throws Exception
     */
    public static void publishMessageSingle() throws Exception{

        //生命队列名称
        String queue_name = UUID.randomUUID().toString();
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(queue_name, true, false, false, null);
        //发布确认
        channel.confirmSelect();
        //计算单个发布确认的耗时
        //计时开始
        Long begin = System.currentTimeMillis();

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String  message = i + "";
            channel.basicPublish("", queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));

            //发布确认
            //服务端返回false 或者超时内未返回，生产者可以消息重发
            boolean flag = channel.waitForConfirms();
            //判断是否成功
            if(flag){
                System.out.println("消息发送成功");
            }

        }
        //计时结束
        Long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个消息单独确认，耗时：" + (end - begin) + "ms");
    }

    /**
     * 批量确认发布
     * 声明批量的大小，根据声明的大小判断指定大小的消息发布就进行一次确认
     * @throws Exception
     */
    public static void publishMessageBulk() throws Exception{

    }
}
