package com.mouse.bms.product.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Category2QueryRequest
 * @date : 2019/3/25 16:07
 * @description :
 */
@Data
@Accessors(chain = true)
public class Category2QueryRequest implements Serializable {

    private static final long serialVersionUID = 208962521727705416L;

    private Long businessId;

    private Long categoryId;

    private String order;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

}
