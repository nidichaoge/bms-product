package com.mouse.bms.product.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductInfoDO
 * @date : 2019/3/20 21:05
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductInfoDO {

    private Long id;

    private Long businessId;

    private Long productId;

    private String service;

    private Long weight;

    private Integer color;

    private Long inventory;

    private Long sale;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String description;

}
