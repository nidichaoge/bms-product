package com.mouse.bms.product.biz.kafka;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.product.api.dto.ProductCreateDTO;
import com.mouse.bms.product.dal.dao.CategoryDAO;
import com.mouse.bms.product.dal.dao.ProductDAO;
import com.mouse.bms.product.dal.dao.SecondCategoryDAO;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductCreateMqConsumer
 * @date : 2019/3/28 20:41
 * @description :
 */
@Component
public class ProductCreateMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreateMqConsumer.class);

    @Resource
    private CategoryDAO categoryDAO;
    @Resource
    private SecondCategoryDAO secondCategoryDAO;
    @Resource
    private ProductDAO productDAO;

    @KafkaListener(topics = {TopicCommon.PRODUCT_CREATE_TOPIC})
    public void receive(ConsumerRecord record) {
        try {
            ProductCreateDTO productCreateDTO = JSON.parseObject(record.value().toString(), ProductCreateDTO.class);
            LOGGER.info("ProductCreateMqConsumer | receive,productCreateDTO:{}.", productCreateDTO);
            Long businessId = productCreateDTO.getBusinessId();
            Long productId = productCreateDTO.getProductId();

            categoryDAO.plusProductNum(businessId,productCreateDTO.getCategoryId(),1L);
            secondCategoryDAO.plusProductNum(businessId,productCreateDTO.getSecondCategoryId(),1L);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
