package com.mouse.bms.product.common.response;

import java.util.Map;

import lombok.Data;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : MapResult
 * @date : 2019/3/2 13:49
 * @description : 接口返回的是map
 */
@Data
public class MapResult<K, V> extends BaseResult {

    private Map<K, V> data;
}
