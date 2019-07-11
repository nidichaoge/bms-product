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
 * @fileName : Category2QueryRequest
 * @date : 2019/3/25 16:04
 * @description :
 */
@Data
@Accessors(chain = true)
public class Category2QueryResp implements Serializable {

    private static final long serialVersionUID = -1079619317116801212L;

    private Long category2Id;

    private String name;

    private Long productNum;

    private LocalDateTime createdAt;

    private String description;

}
