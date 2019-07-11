package com.mouse.bms.product.api.response;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GroupQueryResp
 * @date : 2019/3/4 20:41
 * @description :
 */
@Data
@Accessors(chain = true)
public class CategoryQueryResp implements Serializable {

    private static final long serialVersionUID = -8918817454885479625L;

    private Long categoryId;

    private String name;

    private Long productNum;

    private Integer secondCategoryNum;

    private LocalDateTime createdAt;

    private String description;

//    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        Instant now1 = Instant.now();
//        System.out.println(now);
//        System.out.println(now1);
//    }
}
