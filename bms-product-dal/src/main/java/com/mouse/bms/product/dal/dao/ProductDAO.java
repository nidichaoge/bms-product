package com.mouse.bms.product.dal.dao;

import com.mouse.bms.product.dal.dataobject.ProductDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductDAO
 * @date : 2019/3/25 14:41
 * @description :
 */
public interface ProductDAO {

    long insert(ProductDO productDO);

    boolean update(@Param("businessId") Long businessId,
                   @Param("productId") Long productId,
                   @Param("title") String title,
                   @Param("image") String image,
                   @Param("brandId") Long brandId,
                   @Param("secondCategoryId") Long secondCategoryId,
                   @Param("description") String description);

    ProductDO get(@Param("businessId") Long businessId,
                  @Param("productId") Long productId);

    boolean modifyStatus(@Param("businessId") Long businessId,
                         @Param("productId") Long productId,
                         @Param("status") Short status);

    List<ProductDO> list(@Param("businessId") Long businessId,
                         @Param("keyword") String keyword,
                         @Param("order") String order,
                         @Param("orderBy") String orderBy,
                         @Param("page") Integer page,
                         @Param("pageSize") Integer pageSize);

    long count(@Param("businessId") Long businessId,
               @Param("status") Short status,
               @Param("includeDeleted") Short includeDeleted);

    boolean updateImage(@Param("businessId") Long businessId,
                        @Param("productId") Long productId,
                        @Param("image") String image);
}
