package com.jihu.mall.tiny.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutCancelTask {

    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    @Scheduled(cron = "* * * * * *")
    private void cancelTimeOutOrder(){
        System.out.println("取消订单，并根据sku编号释放锁定库存");
        LOGGER.info("取消订单，并根据sku编号释放锁定库存");
    }
}
