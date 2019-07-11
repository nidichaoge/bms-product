package com.mouse.bms.product.api.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductEditRequest
 * @date : 2019/3/20 21:29
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductEditRequest implements Serializable {

    private static final long serialVersionUID = -2744106235251945058L;

    private Long productId;

    private Long businessId;

    private String title;

    private String image;

    private Long brandId;

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
