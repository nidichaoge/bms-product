package com.mouse.bms.product.dal.dao;

import com.mouse.bms.product.dal.dataobject.CategoryDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CategoryDAO
 * @date : 2019/3/20 21:03
 * @description :
 */
public interface CategoryDAO {

    long insert(CategoryDO categoryDO);

    boolean update(@Param("businessId") Long businessId,
                   @Param("categoryId") Long categoryId,
                   @Param("name") String name,
                   @Param("description") String description);

    CategoryDO get(@Param("businessId") Long businessId,
                   @Param("categoryId") Long categoryId);

    boolean delete(@Param("businessId") Long businessId,
                   @Param("categoryId") Long categoryId);

    List<CategoryDO> list(@Param("businessId") Long businessId,
                          @Param("keyword") String keyword,
                          @Param("order") String order,
                          @Param("orderBy") String orderBy,
                          @Param("page") Integer page,
                          @Param("pageSize") Integer pageSize);

    long count(@Param("businessId") Long businessId,
               @Param("includeDeleted") Short includeDeleted);


    boolean plusProductNum(@Param("businessId") Long businessId,
                           @Param("categoryId") Long categoryId,
                           @Param("num") Long num);

    boolean subtractProductNum(@Param("businessId") Long businessId,
                               @Param("categoryId") Long categoryId,
                               @Param("num") Long num);

    boolean plusSecondCategoryNum(@Param("businessId") Long businessId,
                                  @Param("categoryId") Long categoryId,
                                  @Param("num") Long num);

    boolean subtractSecondCategoryNum(@Param("businessId") Long businessId,
                                      @Param("categoryId") Long categoryId,
                                      @Param("num") Long num);

}
