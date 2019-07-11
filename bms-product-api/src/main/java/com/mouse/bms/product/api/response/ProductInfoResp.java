package com.mouse.bms.product.api.response;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductInfoResp
 * @date : 2019/5/12 10:04
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductInfoResp implements Serializable {

    private static final long serialVersionUID = 1234693417873138391L;

    private Long productId;

    private String title;

    private String image;

    private Long brandId;

    private Long secondCategoryId;

    private Short status;

    private String description;

    private String service;

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
