package com.mouse.bms.product.biz.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Provider
 * @date : 2019/3/28 20:34
 * @description :
 */
@Component
public class Provider {

    @Resource
    private KafkaTemplate kafkaTemplate;

    public void send(String topic, String msg) {
        try {
            kafkaTemplate.send(topic, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
