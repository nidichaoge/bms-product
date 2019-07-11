package com.mouse.bms.product.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BrandDO
 * @date : 2019/3/20 20:42
 * @description :
 */
@Data
@Accessors(chain = true)
public class BrandDO {

    private Long id;

    private Long businessId;

    private String name;

    private String web;

    private String telephone;

    private String logo;

    private Short status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String description;

}
