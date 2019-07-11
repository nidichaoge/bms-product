package com.mouse.bms.product.web.controller;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.product.api.request.ProductAddRequest;
import com.mouse.bms.product.api.request.ProductEditRequest;
import com.mouse.bms.product.api.request.ProductPurchaseRequest;
import com.mouse.bms.product.api.request.ProductQueryRequest;
import com.mouse.bms.product.api.response.ProductInfoResp;
import com.mouse.bms.product.api.response.ProductQueryResp;
import com.mouse.bms.product.api.service.ProductService;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductController
 * @date : 2019/3/21 16:46
 * @description :
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private ProductService productService;

//    @ApiOperation(value = "添加商品")
//    @PostMapping("/add")
//    public PlainResult<Long> addProduct(@RequestParam MultipartFile file, @RequestParam String query) {
//        ProductAddRequest productAddRequest = JSON.parseObject(query, ProductAddRequest.class);
//        LOGGER.info("ProductController | addProduct, productAddRequest:{}.", productAddRequest);
//        return productService.addProduct(file,productAddRequest);
//    }

    @ApiOperation(value = "添加商品")
    @PostMapping("/add")
    public PlainResult<Long> addProduct(@RequestBody ProductAddRequest productAddRequest) {
        LOGGER.info("ProductController | addProduct, productAddRequest:{}.", productAddRequest);
        return productService.addProduct(productAddRequest);
    }

    @ApiOperation(value = "编辑商品")
    @PutMapping("/edit")
    public PlainResult<Boolean> editProduct(@RequestBody ProductEditRequest productEditRequest) {
        LOGGER.info("ProductController | editProduct, productEditRequest:{}.", productEditRequest);
        return productService.editProduct(productEditRequest);
    }

    @ApiOperation(value = "修该商品状态")
    @PutMapping("/update")
    public PlainResult<Boolean> updateProductStatus(@RequestParam Long businessId, @RequestParam Long productId,
                                                    @RequestParam Short status) {
        LOGGER.info("ProductController | countProduct, businessId:{}, productId:{}, status:{}.", businessId, productId,
                    status);
        return productService.updateProductStatus(businessId, productId, status);
    }

    @ApiOperation(value = "批量修该商品状态")
    @PutMapping("/updatebatch")
    public PlainResult<Boolean> updateProductBatch(@RequestParam Long businessId, @RequestParam List<Long> productIds,
                                                   @RequestParam Short status) {
        LOGGER
            .info("ProductController | countProduct, businessId:{}, productIds:{}, status:{}.", businessId, productIds,
                  status);
        return productService.updateProductBatch(businessId, productIds, status);
    }

    @ApiOperation(value = "商品列表")
    @GetMapping("/list")
    public ListResult<ProductQueryResp> listProducts(@RequestParam String query) {
        ProductQueryRequest productQueryRequest = JSON.parseObject(query, ProductQueryRequest.class);
        LOGGER.info("ProductController | listProducts, productQueryRequest:{}.", productQueryRequest);
        return productService.listProducts(productQueryRequest);
    }

    @ApiOperation(value = "商品信息")
    @GetMapping("/get")
    public PlainResult<ProductInfoResp> getProduct(@RequestParam Long businessId, @RequestParam Long productId) {
        LOGGER.info("ProductController | getProduct, businessId:{}, productId:{}.", businessId, productId);
        return productService.getProduct(businessId, productId);
    }

    @ApiOperation(value = "商品数量")
    @GetMapping("/count")
    public PlainResult<Long> countProduct(@RequestParam Long businessId, @RequestParam Short status) {
        LOGGER.info("ProductController | countProduct, businessId:{}, status:{}.", businessId, status);
        return productService.countProduct(businessId, status);
    }

    @ApiOperation(value = "提交订单")
    @PostMapping("/order")
    public PlainResult<Boolean> generateOrder(@RequestBody ProductPurchaseRequest productPurchaseRequest){
        LOGGER.info("ProductController | purchaseProduct, productPurchaseRequest:{}.", productPurchaseRequest);
        return productService.generateOrder(productPurchaseRequest);
    }
}
