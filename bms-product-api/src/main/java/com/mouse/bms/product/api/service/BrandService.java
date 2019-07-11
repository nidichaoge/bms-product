package com.mouse.bms.product.api.service;

import com.mouse.bms.product.api.request.BrandAddRequest;
import com.mouse.bms.product.api.request.BrandEditRequst;
import com.mouse.bms.product.api.request.BrandQueryRequest;
import com.mouse.bms.product.api.response.BrandQueryResp;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BrandService
 * @date : 2019/3/4 20:46
 * @description : 品牌服务
 */
public interface BrandService {

    /**
     * 添加品牌
     * @param brandAddRequest
     * @return
     */
    PlainResult<Long> addBrand(BrandAddRequest brandAddRequest);

    /**
     * 编辑品牌
     * @param brandEditRequst
     * @return
     */
    PlainResult<Boolean> editBrand(BrandEditRequst brandEditRequst);

    /**
     * 删除品牌
     * @param businessId
     * @param brandId
     * @return
     */
    PlainResult<Boolean> deleteBrand(Long businessId, Long brandId);

    /**
     * 批量删除品牌
     * @param businessId
     * @param brandId
     * @return
     */
    PlainResult<Boolean> deleteBrandBatch(Long businessId, List<Long> brandId);

    /**
     * 品牌列表
     * @param brandQueryRequest
     * @return
     */
    ListResult<BrandQueryResp> listBrands(BrandQueryRequest brandQueryRequest);

    /**
     * 品牌数量
     * @param businessId
     * @return
     */
    PlainResult<Long> countBrand(Long businessId);
    
}
