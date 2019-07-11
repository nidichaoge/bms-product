package com.mouse.bms.product.dal.dao;

import com.mouse.bms.product.dal.dataobject.SecondCategoryDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : SecondCategortDAO
 * @date : 2019/3/25 14:41
 * @description :
 */
public interface SecondCategoryDAO {

    long insert(SecondCategoryDO secondCategoryDO);

    boolean update(@Param("businessId") Long businessId,
                   @Param("secondCategoryId") Long secondCategoryId,
                   @Param("name") String name,
                   @Param("categoryId") Long categoryId,
                   @Param("description") String description);

    SecondCategoryDO get(@Param("businessId") Long businessId,
                         @Param("secondCategoryId") Long secondCategoryId);

    boolean delete(@Param("businessId") Long businessId,
                   @Param("secondCategoryId") Long secondCategoryId);

    List<SecondCategoryDO> list(@Param("businessId") Long businessId,
                                @Param("categoryId") Long categoryId,
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


}
