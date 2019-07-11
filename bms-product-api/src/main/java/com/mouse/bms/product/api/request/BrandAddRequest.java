package com.mouse.bms.product.api.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BrandCreateRequest
 * @date : 2019/3/20 21:33
 * @description :
 */
@Data
@Accessors(chain = true)
public class BrandAddRequest implements Serializable {

    private static final long serialVersionUID = 395109361750163075L;

    private Long businessId;

    private String name;

    private String web;

    private String telephone;

    private String logo;

    private Short status;

    private String description;

}
