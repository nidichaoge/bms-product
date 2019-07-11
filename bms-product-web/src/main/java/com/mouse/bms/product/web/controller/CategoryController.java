package com.mouse.bms.product.web.controller;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.product.api.request.Category2CreateRequest;
import com.mouse.bms.product.api.request.Category2EditRequest;
import com.mouse.bms.product.api.request.Category2QueryRequest;
import com.mouse.bms.product.api.request.CategoryCreateRequest;
import com.mouse.bms.product.api.request.CategoryEditRequest;
import com.mouse.bms.product.api.request.CategoryQueryRequest;
import com.mouse.bms.product.api.response.Category2QueryResp;
import com.mouse.bms.product.api.response.CategoryQueryResp;
import com.mouse.bms.product.api.service.CategoryService;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CategoryController
 * @date : 2019/3/21 16:45
 * @description :
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "创建分组")
    @PostMapping("/create")
    public PlainResult<Long> addCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        LOGGER.info("CategoryController | addCategory, categoryCreateRequest:{}.", categoryCreateRequest);
        return categoryService.addCategory(categoryCreateRequest);
    }

    @ApiOperation(value = "创建二级分组")
    @PostMapping("/createSecond")
    public PlainResult<Long> addSecondCategory(@RequestBody Category2CreateRequest category2CreateRequest) {
        LOGGER.info("CategoryController | addSecondCategory, category2CreateRequest:{}.", category2CreateRequest);
        return categoryService.addSecondCategory(category2CreateRequest);
    }

    @ApiOperation(value = "编辑分组")
    @PutMapping("/edit")
    public PlainResult<Boolean> editCategory(@RequestBody CategoryEditRequest categoryEditRequest) {
        LOGGER.info("CategoryController | editCategory, categoryEditRequest:{}.", categoryEditRequest);
        return categoryService.editCategory(categoryEditRequest);
    }

    @ApiOperation(value = "编辑二级分组")
    @PutMapping("/editSecond")
    public PlainResult<Boolean> editSecondCategory(@RequestBody Category2EditRequest category2EditRequest) {
        LOGGER.info("CategoryController | editSecondCategory, category2EditRequest:{}.", category2EditRequest);
        return categoryService.editSecondCategory(category2EditRequest);
    }

    @ApiOperation(value = "删除分组")
    @DeleteMapping("/delete")
    public PlainResult<Boolean> deleteCategory(@RequestParam Long businessId, @RequestParam Long categoryId,
                                               @RequestParam Boolean isHeader) {
        LOGGER.info("CategoryController | deleteCategory, businessId:{},categoryId:{}, isHeader:{}.", businessId,
                    categoryId, isHeader);
        return categoryService.deleteCategory(businessId, categoryId, isHeader);
    }

    @ApiOperation(value = "分组列表")
    @GetMapping("/list")
    public ListResult<CategoryQueryResp> listCategory(@RequestParam String query) {
        CategoryQueryRequest categoryQueryRequest = JSON.parseObject(query, CategoryQueryRequest.class);
        LOGGER.info("CategoryController | listCategory, categoryQueryRequest:{}.", categoryQueryRequest);
        return categoryService.listCategory(categoryQueryRequest);
    }

    @ApiOperation(value = "二级分组列表")
    @GetMapping("/listSecond")
    public ListResult<Category2QueryResp> listSecondCategory(@RequestParam String query) {
        Category2QueryRequest category2QueryRequest = JSON.parseObject(query, Category2QueryRequest.class);
        LOGGER.info("CategoryController | listSecondCategory, category2QueryRequest:{}.", category2QueryRequest);
        return categoryService.listSecondCategory(category2QueryRequest);
    }

    @ApiOperation(value = "分组数量")
    @GetMapping("/count")
    public PlainResult<Long> countCategory(@RequestParam Long businessId, @RequestParam Boolean isHeader) {
        LOGGER.info("CategoryController | countCategory, businessId:{}, isHeader:{}.", businessId, isHeader);
        return categoryService.countCategory(businessId, isHeader);
    }

}
