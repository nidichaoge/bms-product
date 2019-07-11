package com.mouse.bms.product.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductCreateDTO
 * @date : 2019/3/28 20:53
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductCreateDTO implements Serializable {

    private static final long serialVersionUID = 2885442982836416854L;

    private Long businessId;

    private Long productId;

    private String title;

    private Long categoryId;

    private Long secondCategoryId;

}
