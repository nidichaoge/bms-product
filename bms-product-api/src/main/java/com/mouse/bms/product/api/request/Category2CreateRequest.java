package com.mouse.bms.product.api.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Category2CreateRequest
 * @date : 2019/3/21 16:03
 * @description :
 */
@Data
@Accessors(chain = true)
public class Category2CreateRequest implements Serializable {

    private static final long serialVersionUID = 7042689303702787372L;

    private Long businessId;

    private String name;

    private Long categoryId;

    private String description;
}
