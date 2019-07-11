package com.mouse.bms.product.biz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mouse.bms.product.api.request.Category2CreateRequest;
import com.mouse.bms.product.api.request.Category2EditRequest;
import com.mouse.bms.product.api.request.Category2QueryRequest;
import com.mouse.bms.product.api.request.CategoryCreateRequest;
import com.mouse.bms.product.api.request.CategoryEditRequest;
import com.mouse.bms.product.api.request.CategoryQueryRequest;
import com.mouse.bms.product.api.response.Category2QueryResp;
import com.mouse.bms.product.api.response.CategoryQueryResp;
import com.mouse.bms.product.api.service.CategoryService;
import com.mouse.bms.product.common.enums.CommonResultEnum;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;
import com.mouse.bms.product.common.util.ListResults;
import com.mouse.bms.product.common.util.PlainResults;
import com.mouse.bms.product.dal.dao.CategoryDAO;
import com.mouse.bms.product.dal.dao.SecondCategoryDAO;
import com.mouse.bms.product.dal.dataobject.CategoryDO;
import com.mouse.bms.product.dal.dataobject.SecondCategoryDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CategoryServiceImpl
 * @date : 2019/3/18 15:02
 * @description :
 */
@Service("categoryService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private static final String ORDER = "desc";
    private static final String ORDER_BY = "created_at";

    @Resource
    private CategoryDAO categoryDAO;
    @Resource
    private SecondCategoryDAO secondCategoryDAO;

    @Override
    public PlainResult<Long> addCategory(CategoryCreateRequest categoryCreateRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(categoryCreateRequest));
            Long businessId = categoryCreateRequest.getBusinessId();
            String name = categoryCreateRequest.getName();
            Boolean isCreateSecond = categoryCreateRequest.getIsCreateSecond();
            Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name));
            CategoryDO categoryDO = buildCategoryDO(businessId, name, categoryCreateRequest.getDescription());
            long insert = categoryDAO.insert(categoryDO);
            if (0 < insert) {
                if (isCreateSecond) {
                    PlainResult<Long>
                        longPlainResult =
                        addSecondCategory(categoryCreateRequest.getCategory2CreateRequest().setCategoryId(categoryDO.getId()));
                    if (!longPlainResult.isSuccess() || 0 >= longPlainResult.getData()) {
                        return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
                    }
                }
                return PlainResults.success(insert);
            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CategoryService | addCategory, params is illegal, productAddRequest:{}.",
                        categoryCreateRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CategoryService | addCategory, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> addSecondCategory(Category2CreateRequest category2CreateRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(category2CreateRequest));
            Long businessId = category2CreateRequest.getBusinessId();
            String name = category2CreateRequest.getName();
            Long categoryId = category2CreateRequest.getCategoryId();
            Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name) && 0L < categoryId);
            long
                insert =
                secondCategoryDAO.insert(
                    buildSecondCategoryDO(businessId, name, categoryId, category2CreateRequest.getDescription()));
            if (0 < insert) {
                categoryDAO.plusSecondCategoryNum(businessId, categoryId, 1L);
                return PlainResults.success(insert);
            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CategoryService | addSecondCategory, params is illegal, category2CreateRequest:{}.",
                        category2CreateRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CategoryService | addSecondCategory, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> editCategory(CategoryEditRequest categoryEditRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(categoryEditRequest));
            Long businessId = categoryEditRequest.getBusinessId();
            Long categoryId = categoryEditRequest.getCategoryId();
            String name = categoryEditRequest.getName();
            Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name) && 0L < categoryId);

            CategoryDO exists = categoryDAO.get(businessId, categoryId);
            if (Objects.isNull(exists)) {
                return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
            }
            boolean update = categoryDAO.update(businessId, categoryId, name, categoryEditRequest.getDescription());
            return PlainResults.success(update);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CategoryService | editCategory, params is illegal, categoryEditRequest:{}.",
                        categoryEditRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CategoryService | editCategory, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> editSecondCategory(Category2EditRequest category2EditRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(category2EditRequest));
            Long businessId = category2EditRequest.getBusinessId();
            Long secondCategoryId = category2EditRequest.getSecondCategoryId();
            Long categoryId = category2EditRequest.getCategoryId();
            String name = category2EditRequest.getName();
            CategoryDO categoryDO = categoryDAO.get(businessId, categoryId);
            SecondCategoryDO exists = secondCategoryDAO.get(businessId, secondCategoryId);
            if (Objects.isNull(exists) || Objects.isNull(categoryDO)) {
                return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
            }
            boolean update = secondCategoryDAO.update(businessId, secondCategoryId, name, categoryId,
                                                      category2EditRequest.getDescription());
            return PlainResults.success(update);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("CategoryService | editCategory, params is illegal, categoryEditRequest:{}.",
                      category2EditRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CategoryService | editCategory, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> deleteCategory(Long businessId, Long categoryId, Boolean isHeader) {
        try {
            if (isHeader) {
                CategoryDO categoryDO = categoryDAO.get(businessId, categoryId);
                if (!Objects.isNull(categoryDO)) {
                    if (0 < categoryDO.getProductNum() || 0 < categoryDO.getSecondCategoryNum()) {
                        return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
                    }
                    boolean delete = categoryDAO.delete(businessId, categoryId);
                    return PlainResults.success(delete);
                }
            }
            SecondCategoryDO secondCategoryDO = secondCategoryDAO.get(businessId, categoryId);
            if (!Objects.isNull(secondCategoryDO)) {
                if (0 < secondCategoryDO.getProductNum()) {
                    return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
                }
                boolean delete = secondCategoryDAO.delete(businessId, categoryId);
                categoryDAO.subtractSecondCategoryNum(businessId, secondCategoryDO.getCategoryId(), 1L);
                return PlainResults.success(delete);
            }
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("CategoryService | deleteCategory, params is illegal, businessId:{}, categoryId:{}.", businessId,
                      categoryId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CategoryService | deleteCategory, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public ListResult<CategoryQueryResp> listCategory(CategoryQueryRequest categoryQueryRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(categoryQueryRequest));
            Long businessId = categoryQueryRequest.getBusinessId();
            String order = categoryQueryRequest.getOrder();
            String orderBy = categoryQueryRequest.getOrderBy();
            Integer page = categoryQueryRequest.getPage();
            Integer pageSize = categoryQueryRequest.getPageSize();
            Preconditions.checkArgument(0 <= page && 0 < pageSize);
            if (StringUtils.isEmpty(order)) {
                order = ORDER;
            }
            if (StringUtils.isEmpty(orderBy)) {
                orderBy = ORDER_BY;
            }
            List<CategoryDO> list = categoryDAO.list(businessId, null, order, orderBy, page, pageSize);
            if (CollectionUtils.isEmpty(list)) {
                return ListResults.success(Lists.newArrayList());
            }
            return ListResults.success(
                list.stream().map(this::buildCategoryQueryResp).filter(Objects::nonNull).collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("CategoryService | listCategory, params is illegal, categoryQueryRequest:{}.",
                      categoryQueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CategoryService | listCategory, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public ListResult<Category2QueryResp> listSecondCategory(Category2QueryRequest category2QueryRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(category2QueryRequest));
            Long businessId = category2QueryRequest.getBusinessId();
            Long categoryId = category2QueryRequest.getCategoryId();
            String order = category2QueryRequest.getOrder();
            String orderBy = category2QueryRequest.getOrderBy();
            Integer page = category2QueryRequest.getPage();
            Integer pageSize = category2QueryRequest.getPageSize();
            Preconditions.checkArgument(0L < businessId && 0L < categoryId && 0 <= page && 0 < pageSize);
            if (StringUtils.isEmpty(order)) {
                order = ORDER;
            }
            if (StringUtils.isEmpty(orderBy)) {
                orderBy = ORDER_BY;
            }
            List<SecondCategoryDO> list = secondCategoryDAO.list(businessId, categoryId, null, order, orderBy,
                                                                 page, pageSize);
            if (CollectionUtils.isEmpty(list)) {
                return ListResults.success(Lists.newArrayList());
            }
            return ListResults.success(
                list.stream().map(this::buildCategory2QueryResp).filter(Objects::nonNull).collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("CategoryService | listSecondCategory, params is illegal, categoryQueryRequest:{}.",
                      category2QueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CategoryService | listSecondCategory, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countCategory(Long businessId, Boolean isHeader) {
        long count;
        try {
            Preconditions.checkArgument(0L < businessId);
            if (isHeader) {
                count = categoryDAO.count(businessId, (short) 0);
            } else {
                count = secondCategoryDAO.count(businessId, (short) 0);
            }
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("CategoryService | countCategory, params is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CategoryService | countCategory, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    private Category2QueryResp buildCategory2QueryResp(SecondCategoryDO secondCategoryDO) {
        return new Category2QueryResp()
            .setCategory2Id(secondCategoryDO.getId())
            .setName(secondCategoryDO.getName())
            .setCreatedAt(secondCategoryDO.getCreatedAt())
            .setProductNum(secondCategoryDO.getProductNum())
            .setDescription(secondCategoryDO.getDescription());
    }

    private CategoryQueryResp buildCategoryQueryResp(CategoryDO categoryDO) {
        return new CategoryQueryResp()
            .setCategoryId(categoryDO.getId())
            .setName(categoryDO.getName())
            .setProductNum(categoryDO.getProductNum())
            .setSecondCategoryNum(categoryDO.getSecondCategoryNum())
            .setCreatedAt(categoryDO.getCreatedAt())
            .setDescription(categoryDO.getDescription());
    }

    private SecondCategoryDO buildSecondCategoryDO(Long businessId, String name, Long categoryId, String description) {
        return new SecondCategoryDO()
            .setBusinessId(businessId)
            .setName(name)
            .setCategoryId(categoryId)
            .setDescription(description)
            .setCreatedAt(LocalDateTime.now());
    }

    private CategoryDO buildCategoryDO(Long businessId, String name, String description) {
        return new CategoryDO()
            .setBusinessId(businessId)
            .setName(name)
            .setDescription(description)
            .setCreatedAt(LocalDateTime.now());
    }

}
