package com.mouse.bms.product.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Category2EditRequest
 * @date : 2019/3/25 15:40
 * @description :
 */
@Data
@Accessors(chain = true)
public class Category2EditRequest implements Serializable {

    private static final long serialVersionUID = -674781857463898192L;

    private Long businessId;

    private Long secondCategoryId;

    private String name;

    private Long categoryId;

    private String description;

}
