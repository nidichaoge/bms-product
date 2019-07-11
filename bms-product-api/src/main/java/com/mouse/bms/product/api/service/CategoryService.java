package com.mouse.bms.product.api.service;

import com.mouse.bms.product.api.request.Category2CreateRequest;
import com.mouse.bms.product.api.request.Category2EditRequest;
import com.mouse.bms.product.api.request.Category2QueryRequest;
import com.mouse.bms.product.api.response.Category2QueryResp;
import com.mouse.bms.product.api.request.CategoryCreateRequest;
import com.mouse.bms.product.api.request.CategoryEditRequest;
import com.mouse.bms.product.api.request.CategoryQueryRequest;
import com.mouse.bms.product.api.response.CategoryQueryResp;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupService
 * @date : 2019/3/4 20:36
 * @description : 分组服务
 */
public interface CategoryService {

    /**
     * 创建分组
     * @param categoryCreateRequest
     * @return
     */
    PlainResult<Long> addCategory(CategoryCreateRequest categoryCreateRequest);

    /**
     * 创建二级分组
     * @param category2CreateRequest
     * @return
     */
    PlainResult<Long> addSecondCategory(Category2CreateRequest category2CreateRequest);

    /**
     * 编辑分组
     * @param categoryEditRequest
     * @return
     */
    PlainResult<Boolean> editCategory(CategoryEditRequest categoryEditRequest);

    /**
     * 编辑二级分组
     * @param category2EditRequest
     * @return
     */
    PlainResult<Boolean> editSecondCategory(Category2EditRequest category2EditRequest);

    /**
     * 删除分组
     * @param businessId
     * @param categoryId
     * @param isHeader
     * @return
     */
    PlainResult<Boolean> deleteCategory(Long businessId, Long categoryId, Boolean isHeader);

    /**
     * 分组列表
     * @param categoryQueryRequest
     * @return
     */
    ListResult<CategoryQueryResp> listCategory(CategoryQueryRequest categoryQueryRequest);

    /**
     * 二级分组列表
     * @param category2QueryRequest
     * @return
     */
    ListResult<Category2QueryResp> listSecondCategory(Category2QueryRequest category2QueryRequest);

    /**
     * 分组数量
     * @param businessId
     * @param isHeader
     * @return
     */
    PlainResult<Long> countCategory(Long businessId, Boolean isHeader);

}
