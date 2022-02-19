package com.ziznah.rabbit.utils;

/**
 * @BelongsProject: rabbitmq
 * @BelongsPackage: com.ziznah.rabbit.utils
 * @Author: sugarzhang
 * @CreateTime: 2022-02-19 18:56
 * @Description: 休眠工具类 按秒计时
 */
public class SleepUtil {

    public static void sleep(int second) {
        try {
            Thread.sleep(1000*second);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
