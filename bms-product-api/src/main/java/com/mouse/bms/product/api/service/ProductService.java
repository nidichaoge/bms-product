package com.mouse.bms.product.api.service;

import com.mouse.bms.product.api.request.ProductAddRequest;
import com.mouse.bms.product.api.request.ProductEditRequest;
import com.mouse.bms.product.api.request.ProductPurchaseRequest;
import com.mouse.bms.product.api.request.ProductQueryRequest;
import com.mouse.bms.product.api.response.ProductInfoResp;
import com.mouse.bms.product.api.response.ProductQueryResp;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductManagerService
 * @date : 2019/3/3 11:42
 * @description : 商品管理服务，商品列表页面使用
 */
public interface ProductService {

    /**
     * 添加商品
     * @param
     * @return
     */
    PlainResult<Long> addProduct(ProductAddRequest productAddRequest);

    /**
     * 编辑商品
     * @param productEditRequest
     * @return
     */
    PlainResult<Boolean> editProduct(ProductEditRequest productEditRequest);

    /**
     * 修改商品状态
     * @param businessId
     * @param productId
     * @param status
     * @return
     */
    PlainResult<Boolean> updateProductStatus(Long businessId, Long productId, Short status);

    /**
     * 批量修改商品状态
     * @param businessId
     * @param productIds
     * @param status
     * @return
     */
    PlainResult<Boolean> updateProductBatch(Long businessId, List<Long> productIds,Short status);

    /**
     * 查询商品
     * @param productQueryRequest
     * @return
     */
    ListResult<ProductQueryResp> listProducts(ProductQueryRequest productQueryRequest);

    /**
     * 商品数量
     * @param businessId
     * @param status
     * @return
     */
    PlainResult<Long> countProduct(Long businessId, Short status);

    /**
     * 购买商品
     * @param productPurchaseRequest
     * @return
     */
    PlainResult<Boolean> generateOrder(ProductPurchaseRequest productPurchaseRequest);

    /**
     * 查询商品信息
     * @param businessId
     * @param productId
     * @return
     */
    PlainResult<ProductInfoResp> getProduct(Long businessId, Long productId);
}
