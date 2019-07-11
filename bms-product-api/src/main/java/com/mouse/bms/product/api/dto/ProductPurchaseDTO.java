package com.mouse.bms.product.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductPurchaseDTO
 * @date : 2019/3/29 11:12
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductPurchaseDTO implements Serializable {

    private static final long serialVersionUID = -5624465942220085885L;

    private Long businessId;

    private Long productId;

    private String telephone;

    private Integer number;

    private Long unitPrice;

    private Long productPrice;

    private Long postage;

    private Long realPay;

    private String remark;

}
