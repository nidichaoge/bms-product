package com.mouse.bms.product.web.controller;

import com.mouse.bms.product.api.request.BrandAddRequest;
import com.mouse.bms.product.api.service.BrandService;
import com.mouse.bms.product.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BrandController
 * @date : 2019/3/21 09:44
 * @description :
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Resource
    private BrandService brandService;

    @ApiOperation(value = "添加品牌")
    @PostMapping("/add")
    public PlainResult<Long> addBrand(@RequestBody BrandAddRequest brandAddRequest){
        LOGGER.info("BrandController | addBrand, brandAddRequest:{}.",brandAddRequest);
        return brandService.addBrand(brandAddRequest);
    }
}
