package com.mouse.bms.product.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductPriceDO
 * @date : 2019/3/20 21:05
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductPriceDO {

    private Long id;

    private Long businessId;

    private Long productId;

    private Long costPrice;

    private Long marketPrice;

    private Long sellPrice;

    private Long postage;

    private Long profits;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String description;

}
