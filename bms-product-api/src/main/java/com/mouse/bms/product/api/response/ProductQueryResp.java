package com.mouse.bms.product.api.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductQueryResp
 * @date : 2019/3/3 11:49
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductQueryResp implements Serializable {

    private static final long serialVersionUID = 6582766462798480708L;

    private Long productId;

    private String image;

    private String name;

    private Integer price;

    private Integer count;

    private LocalDateTime createdAt;

    private String description;

}
