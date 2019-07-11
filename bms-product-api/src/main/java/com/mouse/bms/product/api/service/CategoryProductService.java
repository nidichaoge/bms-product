package com.mouse.bms.product.api.service;

import com.mouse.bms.product.common.response.PlainResult;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupProductService
 * @date : 2019/3/4 20:52
 * @description : 分组和商品服务
 */
public interface CategoryProductService {

    /**
     * 给分组添加商品
     * @param businessId
     * @param groupId
     * @param productId
     * @return
     */
    PlainResult<Boolean> addProductToGroup(Long businessId, Long groupId, Long productId);

    /**
     * 移除分组下商品
     * @param businessId
     * @param groupId
     * @param productId
     * @return
     */
    PlainResult<Boolean> removeProductFromGroup(Long businessId, Long groupId, Long productId);

    /**
     * 批量添加商品到一个分组
     * @param businessId
     * @param groupId
     * @param productId
     * @return
     */
    PlainResult<Boolean> addProductBatch(Long businessId, Long groupId, List<Long> productId);

    /**
     * 批量移除分组下商品
     * @param businessId
     * @param groupId
     * @param productId
     * @return
     */
    PlainResult<Boolean> removeProductBatch(Long businessId, Long groupId, List<Long> productId);

}
