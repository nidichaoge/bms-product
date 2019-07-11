package com.mouse.bms.product.api.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupCreateRequest
 * @date : 2019/3/20 21:38
 * @description :
 */
@Data
@Accessors(chain = true)
public class CategoryCreateRequest implements Serializable {

    private static final long serialVersionUID = -6043522773570168834L;

    private Long businessId;

    private String name;

    private String description;

    private Boolean isCreateSecond;

    private Category2CreateRequest category2CreateRequest;
}
