package com.mouse.bms.product.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductDO
 * @date : 2019/3/20 21:04
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductDO {

    private Long id;

    private Long businessId;

    private String title;

    private String image;

    private Long brandId;

    private Long secondCategoryId;

    private Short status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt ;

    private LocalDateTime deletedAt ;

    private String description;

}
