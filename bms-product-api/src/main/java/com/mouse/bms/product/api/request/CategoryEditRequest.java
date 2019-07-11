package com.mouse.bms.product.api.request;

import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupEditRequest
 * @date : 2019/3/20 21:39
 * @description :
 */
@Data
@Accessors(chain = true)
public class CategoryEditRequest implements Serializable {

    private static final long serialVersionUID = -2960216235705919366L;

    private Long businessId;

    private Long categoryId;

    private String name;

    private String description;

}
