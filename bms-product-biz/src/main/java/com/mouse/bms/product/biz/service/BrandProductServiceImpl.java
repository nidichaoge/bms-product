package com.mouse.bms.product.biz.service;

import com.mouse.bms.product.api.service.BrandProductService;
import com.mouse.bms.product.api.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BrandProductServiceImpl
 * @date : 2019/3/18 15:09
 * @description :
 */
@Service("brandProductService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = BrandProductService.class)
public class BrandProductServiceImpl implements BrandProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandProductServiceImpl.class);

}
