package com.mouse.bms.product.dal.dao;

import com.mouse.bms.product.dal.dataobject.ProductInfoDO;

import org.apache.ibatis.annotations.Param;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductInfoDAO
 * @date : 2019/3/25 14:42
 * @description :
 */
public interface ProductInfoDAO {

    long insert(ProductInfoDO productInfoDO);

    boolean update(@Param("businessId") Long businessId,
                   @Param("productId") Long productId,
                   @Param("service") String service,
                   @Param("weight") Long weight,
                   @Param("color") Integer color,
                   @Param("inventory") Long inventory,
                   @Param("sale") Long sale,
                   @Param("description") String description);

    ProductInfoDO get(@Param("businessId") Long businessId,
                      @Param("productId") Long productId);

    boolean plusInventory(@Param("businessId") Long businessId,
                          @Param("productId") Long productId,
                          @Param("num") Integer num);

    boolean subtractInventory(@Param("businessId") Long businessId,
                              @Param("productId") Long productId,
                              @Param("num") Integer num);
}
