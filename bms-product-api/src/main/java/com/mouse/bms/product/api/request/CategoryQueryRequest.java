package com.mouse.bms.product.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupQueryRequest
 * @date : 2019/3/4 20:41
 * @description :
 */
@Data
@Accessors(chain = true)
public class CategoryQueryRequest implements Serializable {

    private static final long serialVersionUID = -2398710770486019058L;

    private Long businessId;

    private String order;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

}
