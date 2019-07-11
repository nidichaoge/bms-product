package com.mouse.bms.product.biz.util;

import com.mouse.bms.product.api.request.ProductAddRequest;
import com.mouse.bms.product.api.request.ProductEditRequest;

import java.util.Objects;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : PreconditionsUtil
 * @date : 2019/3/25 09:11
 * @description :
 */
public class PreconditionsUtil {

    public static void checkArgument(ProductAddRequest productAddRequest){
        if (Objects.isNull(productAddRequest)){
            throw new IllegalArgumentException("argument must be null");
        }
    }

    public static void checkArgument(ProductEditRequest productEditRequest){
        if (Objects.isNull(productEditRequest)){
            throw new IllegalArgumentException("argument must be null");
        }
    }
}
