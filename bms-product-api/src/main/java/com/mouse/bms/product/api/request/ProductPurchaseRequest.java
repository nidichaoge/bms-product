package com.mouse.bms.product.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductPurchaseRequest
 * @date : 2019/3/29 10:58
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductPurchaseRequest implements Serializable {

    private static final long serialVersionUID = 9169281931371356669L;

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
