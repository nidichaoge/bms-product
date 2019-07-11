package com.mouse.bms.product.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CategoryDO
 * @date : 2019/3/20 20:58
 * @description :
 */
@Data
@Accessors(chain = true)
public class CategoryDO {

    private Long id;

    private Long businessId;

    private String name;

    private Integer secondCategoryNum;

    private Long productNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String description;

}
