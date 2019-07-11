package com.mouse.bms.product.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductQueryRequest
 * @date : 2019/3/3 11:45
 * @description :
 */
@Data
@Accessors(chain = true)
public class ProductQueryRequest implements Serializable {

    private static final long serialVersionUID = -133875308874691456L;

    /**
     * 店铺ID
     */
    private Long businessId;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页返回几条
     */
    private Integer pageSize;

    /**
     * 根据keyword对商品名字模糊查询
     */
    private String keyword;

    /**
     * 排序规则
     */
    private String order;

    /**
     * 排序
     */
    private String orderBy;

}
