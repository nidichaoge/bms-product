package com.mouse.bms.product.dal.dao;

import com.mouse.bms.product.dal.dataobject.ProductDO;
import com.mouse.bms.product.dal.dataobject.ProductInfoDO;
import com.mouse.bms.product.dal.dataobject.ProductPriceDO;

import org.apache.ibatis.annotations.Param;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductPriceDAO
 * @date : 2019/3/25 14:41
 * @description :
 */
public interface ProductPriceDAO {

    long insert(ProductPriceDO productPriceDO);

    boolean update(@Param("businessId") Long businessId,
                   @Param("productId")Long productId,
                   @Param("costPrice")Long costPrice,
                   @Param("marketPrice")Long marketPrice,
                   @Param("sellPrice") Long sellPrice,
                   @Param("postage") Long postage,
                   @Param("profits")Long profits,
                   @Param("description")String description);

    ProductPriceDO get(@Param("businessId") Long businessId,
                           @Param("productId")Long productId);

}
