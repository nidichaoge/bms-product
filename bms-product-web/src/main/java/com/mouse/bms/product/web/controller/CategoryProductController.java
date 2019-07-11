package com.mouse.bms.product.web.controller;

import com.mouse.bms.product.api.service.CategoryProductService;
import com.mouse.bms.product.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CategoryProductController
 * @date : 2019/3/24 17:22
 * @description :
 */
@RestController
@RequestMapping("/category-product")
public class CategoryProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryProductController.class);

    @Resource
    private CategoryProductService categoryProductService;

    @ApiOperation(value = "添加商品到指定分组")
    @PutMapping("/add")
    public PlainResult<Boolean> addProductToCategory(Long businessId, Long categoryId, Long productId) {
        LOGGER.info("CategoryProductController | addProductToCategory, businessId:{}, categoryId:{}, productId:{}.",
                    businessId, categoryId, productId);
        return categoryProductService.addProductToGroup(businessId, categoryId, productId);
    }

    @ApiOperation(value = "移除分组下商品")
    @DeleteMapping("/remove")
    public PlainResult<Boolean> removeProductFromCategory(Long businessId, Long categoryId, Long productId) {
        LOGGER
            .info("CategoryProductController | removeProductFromCategory, businessId:{}, categoryId:{}, productId:{}.",
                  businessId, categoryId, productId);
        return categoryProductService.removeProductFromGroup(businessId, categoryId, productId);
    }

    @ApiOperation(value = "批量添加商品到指定分组")
    @PutMapping("/add-batch")
    public PlainResult<Boolean> addProductBatch(Long businessId, Long categoryId, List<Long> productIds) {
        LOGGER.info("CategoryProductController | addProductToGroup, businessId:{}, categoryId:{}, productIds:{}.",
                    businessId, categoryId, productIds);
        return categoryProductService.addProductBatch(businessId, categoryId, productIds);
    }

    @ApiOperation(value = "批量删除分组下商品")
    @DeleteMapping("/remove-batch")
    public PlainResult<Boolean> removeProductBatch(Long businessId, Long categoryId, List<Long> productIds) {
        LOGGER.info("CategoryProductController | addProductToGroup, businessId:{}, categoryId:{}, productIds:{}.",
                    businessId, categoryId, productIds);
        return categoryProductService.removeProductBatch(businessId, categoryId, productIds);
    }

}
