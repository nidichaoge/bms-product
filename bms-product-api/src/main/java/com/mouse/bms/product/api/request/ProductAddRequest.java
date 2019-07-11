package com.mouse.bms.product.api.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductAddRequest
 * @date : 2019/3/3 13:32
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductAddRequest implements Serializable {

    private static final long serialVersionUID = 8867854392170129840L;

    private Long businessId;

    private String title;

    private MultipartFile image;

    private Long brandId;

    private Long categoryId;

    private Long secondCategoryId;

    private Short status;

    private String description;

    private List<Short> service;

    private Long weight;

    private Integer color;

    private Long inventory;

    private Long sale;

    private String infoDescription;

    private Long costPrice;

    private Long marketPrice;

    private Long sellPrice;

    private Long postage;

    private Long profits;

    private String priceDescription;

}
