package com.mouse.bms.product.biz.service;

import com.mouse.bms.product.api.service.CategoryProductService;
import com.mouse.bms.product.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupProductServiceImpl
 * @date : 2019/3/18 15:05
 * @description :
 */
@Service("groupProductService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = CategoryProductService.class)
public class CategoryProductServiceImpl implements CategoryProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryProductServiceImpl.class);

    @Override
    public PlainResult<Boolean> addProductToGroup(Long businessId, Long groupId, Long productId) {
        LOGGER.info("GroupProductService | addProductToGroup");
        return null;
    }

    @Override
    public PlainResult<Boolean> removeProductFromGroup(Long businessId, Long groupId, Long productId) {
        LOGGER.info("GroupProductService | removeProductFromGroup");
        return null;
    }

    @Override
    public PlainResult<Boolean> addProductBatch(Long businessId, Long groupId, List<Long> productId) {
        LOGGER.info("GroupProductService | addProductBatch");
        return null;
    }

    @Override
    public PlainResult<Boolean> removeProductBatch(Long businessId, Long groupId, List<Long> productId) {
        LOGGER.info("GroupProductService | removeProductBatch");
        return null;
    }
}
