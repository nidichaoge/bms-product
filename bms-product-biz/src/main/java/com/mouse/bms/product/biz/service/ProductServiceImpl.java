package com.mouse.bms.product.biz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mouse.bms.product.api.request.ProductAddRequest;
import com.mouse.bms.product.api.request.ProductEditRequest;
import com.mouse.bms.product.api.request.ProductPurchaseRequest;
import com.mouse.bms.product.api.request.ProductQueryRequest;
import com.mouse.bms.product.api.response.ProductInfoResp;
import com.mouse.bms.product.api.response.ProductQueryResp;
import com.mouse.bms.product.api.service.ProductService;
import com.mouse.bms.product.api.dto.ProductCreateDTO;
import com.mouse.bms.product.api.dto.ProductPurchaseDTO;
import com.mouse.bms.product.biz.kafka.Provider;
import com.mouse.bms.product.biz.kafka.TopicCommon;
import com.mouse.bms.product.biz.repository.QiniuUploadFileConfig;
import com.mouse.bms.product.common.enums.CommonResultEnum;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;
import com.mouse.bms.product.common.util.ListResults;
import com.mouse.bms.product.common.util.PlainResults;
import com.mouse.bms.product.dal.dao.ProductDAO;
import com.mouse.bms.product.dal.dao.ProductInfoDAO;
import com.mouse.bms.product.dal.dao.ProductPriceDAO;
import com.mouse.bms.product.dal.dataobject.ProductDO;
import com.mouse.bms.product.dal.dataobject.ProductInfoDO;
import com.mouse.bms.product.dal.dataobject.ProductPriceDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
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
 * @fileName : ProductServiceImpl
 * @date : 2019/3/18 15:00
 * @description :
 */
@Service("productService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = ProductService.class)
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final String ORDER = "desc";
    private static final String ORDER_BY = "created_at";
    private static final String SLASH = "/";

    @Resource
    private ProductDAO productDAO;
    @Resource
    private ProductInfoDAO productInfoDAO;
    @Resource
    private ProductPriceDAO productPriceDAO;
    @Resource
    private QiniuUploadFileConfig qiniuUploadFileConfig;
    @Resource
    private Provider provider;

    //todo brandId = 0 检查参数
    @Override
    public PlainResult<Long> addProduct(ProductAddRequest productAddRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(productAddRequest));
            Long businessId = productAddRequest.getBusinessId();
            Long categoryId = productAddRequest.getCategoryId();
            Long secondCategoryId = productAddRequest.getSecondCategoryId();
            Long costPrice = productAddRequest.getCostPrice();
            MultipartFile image = productAddRequest.getImage();
            Long sellPrice = productAddRequest.getSellPrice();
            Long insert = productDAO.insert(buildProductDO(productAddRequest));
            if (0 < insert) {
                productInfoDAO.insert(buildProductInfoDO(productAddRequest, insert));
                productPriceDAO.insert(buildProductPriceDO(productAddRequest, insert));
//                String name = qiniuUploadFileConfig.uploadImg((FileInputStream) file.getInputStream(),
//                                                              String
//                                                                  .join("_", businessId.toString(), insert.toString()));
                productDAO.updateImage(businessId, insert, "");
                provider.send(TopicCommon.PRODUCT_CREATE_TOPIC,
                              buildMsg(businessId, insert, productAddRequest.getTitle(), categoryId, secondCategoryId));
                return PlainResults.success(insert);
            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | addProduct, params is illegal, productAddRequest:{}.", productAddRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | addProduct, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> editProduct(ProductEditRequest productEditRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(productEditRequest));
            Long productId = productEditRequest.getProductId();
            Long businessId = productEditRequest.getBusinessId();
            String title = productEditRequest.getTitle();
            String image = productEditRequest.getImage();
            Long brandId = productEditRequest.getBrandId();
            Long secondCategoryId = productEditRequest.getSecondCategoryId();
            List<Short> service = productEditRequest.getService();
            Integer color = productEditRequest.getColor();
            Long weight = productEditRequest.getWeight();
            Long inventory = productEditRequest.getInventory();
            Long sale = productEditRequest.getSale();
            Long costPrice = productEditRequest.getCostPrice();
            Long sellPrice = productEditRequest.getSellPrice();
            Long marketPrice = productEditRequest.getMarketPrice();
            Long profits = productEditRequest.getProfits();
            Long postage = productEditRequest.getPostage();
            ProductDO productDO = productDAO.get(businessId, productId);
            ProductInfoDO productInfoDO = productInfoDAO.get(businessId, productId);
            ProductPriceDO productPriceDO = productPriceDAO.get(businessId, productId);
            if (Objects.isNull(productDO) || Objects.isNull(productInfoDO) || Objects.isNull(productPriceDO)) {
                return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
            }
            productDAO.update(businessId, productId, title, image, brandId, secondCategoryId,
                              productEditRequest.getDescription());
            productInfoDAO.update(businessId, productId, JSON.toJSONString(service), weight, color, inventory, sale,
                                  productEditRequest.getInfoDescription());
            productPriceDAO.update(businessId, productId, costPrice, marketPrice, sellPrice, postage, profits,
                                   productEditRequest.getPriceDescription());
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | editProduct, params is illegal, productAddRequest:{}.", productEditRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | editProduct, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> updateProductStatus(Long businessId, Long productId, Short status) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < productId);
            ProductDO productDO = productDAO.get(businessId, productId);
            if (Objects.isNull(productDO)) {
                return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
            }
            boolean modifyStatus = productDAO.modifyStatus(businessId, productId, status);
            return PlainResults.success(modifyStatus);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | updateProductStatus, params is illegal, businessId:{}, productId:{}.",
                        businessId, productId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | updateProductStatus, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> updateProductBatch(Long businessId, List<Long> productIds, Short status) {
        try {
            Preconditions.checkArgument(0L < businessId && CollectionUtils.isNotEmpty(productIds));
            productIds.stream().filter(Objects::nonNull)
                .forEach(productId -> updateProductStatus(businessId, productId, status));
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | updateProductBatch, params is illegal, businessId:{}, productIds:{}.",
                        businessId, productIds);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | updateProductBatch, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    //todo
    @Override
    public ListResult<ProductQueryResp> listProducts(ProductQueryRequest productQueryRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(productQueryRequest));
            Long businessId = productQueryRequest.getBusinessId();
            String order = productQueryRequest.getOrder();
            String orderBy = productQueryRequest.getOrderBy();
            Integer page = productQueryRequest.getPage();
            Integer pageSize = productQueryRequest.getPageSize();
            String keyword = productQueryRequest.getKeyword();
            if (StringUtils.isEmpty(order)) {
                order = ORDER;
            }
            if (StringUtils.isEmpty(orderBy)) {
                orderBy = ORDER_BY;
            }
            if (StringUtils.isEmpty(keyword)) {
                keyword = null;
            }
            List<ProductDO> list = productDAO.list(businessId, keyword, order, orderBy, page, pageSize);
            if (CollectionUtils.isEmpty(list)) {
                return ListResults.success(Lists.newArrayList());
            }
            return ListResults.success(list.stream().filter(Objects::nonNull).map(this::buildProductQueryResp).collect(
                Collectors.toList()));
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | listProducts, params is illegal, productAddRequest:{}.", productQueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | listProducts, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countProduct(Long businessId, Short status) {
        try {
            Preconditions.checkArgument(0L < businessId);
            long count = productDAO.count(businessId, status, (short) 0);
            return PlainResults.success(count);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | countProduct, params is illegal, businessId:{}, status:{}.", businessId,
                        status);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | countProduct, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> generateOrder(ProductPurchaseRequest productPurchaseRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(productPurchaseRequest));
            Long businessId = productPurchaseRequest.getBusinessId();
            Long productId = productPurchaseRequest.getProductId();
            String telephone = productPurchaseRequest.getTelephone();
            Integer number = productPurchaseRequest.getNumber();
            Long realPay = productPurchaseRequest.getRealPay();
            productInfoDAO.subtractInventory(businessId, productId, number);
            provider.send(TopicCommon.PRODUCT_PURCHASE_TOPIC, buildMsg(productPurchaseRequest));
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | purchaseProduct, params is illegal, productPurchaseRequest:{}.",
                        productPurchaseRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | purchaseProduct, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<ProductInfoResp> getProduct(Long businessId, Long productId) {
        try {
            Preconditions.checkArgument(
                !Objects.isNull(businessId) && 0L < businessId && !Objects.isNull(productId) && 0L < productId);
            ProductDO productDO = productDAO.get(businessId, productId);
            if (Objects.isNull(productDO)) {
                return PlainResults
                    .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
            }
            ProductInfoDO productInfoDO = productInfoDAO.get(businessId, productId);
            ProductPriceDO productPriceDO = productPriceDAO.get(businessId, productId);
            return PlainResults.success(buildProductInfoResp(productDO, productInfoDO, productPriceDO));
        } catch (IllegalArgumentException e) {
            LOGGER.warn("ProductService | purchaseProduct, params is illegal, businessId:{}, productId:{}.",
                        businessId, productId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("ProductService | purchaseProduct, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    private ProductInfoResp buildProductInfoResp(ProductDO productDO, ProductInfoDO productInfoDO,
                                                 ProductPriceDO productPriceDO) {
        return new ProductInfoResp()
            .setProductId(productDO.getId())
            .setTitle(productDO.getTitle())
            .setImage(productDO.getImage())
            .setBrandId(productDO.getBrandId())
            .setSecondCategoryId(productDO.getSecondCategoryId())
            .setStatus(productDO.getStatus())
            .setDescription(productDO.getDescription())
            .setService(productInfoDO.getService())
            .setWeight(productInfoDO.getWeight())
            .setColor(productInfoDO.getColor())
            .setInventory(productInfoDO.getInventory())
            .setSale(productInfoDO.getSale())
            .setInfoDescription(productInfoDO.getDescription())
            .setCostPrice(productPriceDO.getCostPrice())
            .setMarketPrice(productPriceDO.getMarketPrice())
            .setSellPrice(productPriceDO.getSellPrice())
            .setPostage(productPriceDO.getPostage())
            .setProfits(productPriceDO.getProfits())
            .setPriceDescription(productPriceDO.getDescription());
    }

    private String buildMsg(ProductPurchaseRequest productPurchaseRequest) {
        ProductPurchaseDTO productPurchaseDTO = new ProductPurchaseDTO();
        BeanUtils.copyProperties(productPurchaseRequest, productPurchaseDTO);
        return JSON.toJSONString(productPurchaseDTO);
    }

    //todo price
    private ProductQueryResp buildProductQueryResp(ProductDO productDO) {
        return new ProductQueryResp()
            .setPrice(10)
            .setProductId(productDO.getId())
            .setImage(productDO.getImage())
            .setName(productDO.getTitle())
            .setCount(100)
            .setCreatedAt(productDO.getCreatedAt())
            .setDescription(productDO.getDescription());
    }

    private ProductPriceDO buildProductPriceDO(ProductAddRequest productAddRequest, Long id) {
        return new ProductPriceDO()
            .setBusinessId(productAddRequest.getBusinessId())
            .setCostPrice(productAddRequest.getCostPrice())
            .setCreatedAt(LocalDateTime.now())
            .setSellPrice(productAddRequest.getSellPrice())
            .setProfits(productAddRequest.getProfits())
            .setPostage(productAddRequest.getPostage())
            .setMarketPrice(productAddRequest.getMarketPrice())
            .setProductId(id)
            .setDescription(productAddRequest.getPriceDescription());
    }

    private ProductInfoDO buildProductInfoDO(ProductAddRequest productAddRequest, Long id) {
        return new ProductInfoDO()
            .setBusinessId(productAddRequest.getBusinessId())
            .setProductId(id)
            .setColor(productAddRequest.getColor())
            .setCreatedAt(LocalDateTime.now())
            .setInventory(productAddRequest.getInventory())
            .setWeight(productAddRequest.getWeight())
            .setService(JSON.toJSONString(productAddRequest.getService()))
            .setSale(productAddRequest.getSale())
            .setDescription(productAddRequest.getInfoDescription());
    }

    //todo
    private ProductDO buildProductDO(ProductAddRequest productAddRequest) {
        return new ProductDO()
            .setBrandId(productAddRequest.getBrandId())
            .setBusinessId(productAddRequest.getBusinessId())
            .setSecondCategoryId(productAddRequest.getSecondCategoryId())
            .setCreatedAt(LocalDateTime.now())
            .setDescription(productAddRequest.getDescription())
            .setImage(qiniuUploadFileConfig.getPath() + SLASH)
            .setTitle(productAddRequest.getTitle())
            .setStatus(productAddRequest.getStatus());
    }

    private String buildMsg(Long businessId, Long productId, String title, Long categoryId,
                            Long secondCategoryId) {
        return JSON.toJSONString(
            new ProductCreateDTO().setBusinessId(businessId).setProductId(productId).setTitle(title)
                .setCategoryId(categoryId).setSecondCategoryId(secondCategoryId));
    }

//    private ProductPriceDO buildProductPriceDO(ProductEditRequest productEditRequest, ProductPriceDO productPriceDO) {
//        return productPriceDO
//            .setId(productPriceDO.getId())
//            .setBusinessId(productEditRequest.getBusinessId())
//            .setCostPrice(productEditRequest.getCostPrice())
//            .setUpdatedAt(new Date())
//            .setDescription(productEditRequest.getDescription())
//            .setSellPrice(productEditRequest.getSellPrice())
//            .setProfits(productEditRequest.getProfits())
//            .setPostage(productEditRequest.getPostage())
//            .setMarketPrice(productEditRequest.getMarketPrice())
//            .setProductId(productEditRequest.getProductId())
//            .setDescription(productEditRequest.getPriceDescription());
//    }
//
//    private ProductInfoDO buildProductInfoDO(ProductEditRequest productEditRequest, ProductInfoDO productInfoDO) {
//        return productInfoDO
//            .setId(productInfoDO.getId())
//            .setBusinessId(productEditRequest.getBusinessId())
//            .setProductId(productEditRequest.getProductId())
//            .setColor(productEditRequest.getColor())
//            .setUpdatedAt(new Date())
//            .setInventory(productEditRequest.getInventory())
//            .setWeight(productEditRequest.getWeight())
//            .setDescription(productEditRequest.getDescription())
//            .setService(JSON.toJSONString(productEditRequest.getStatus()))
//            .setSale(productEditRequest.getSale())
//            .setDescription(productEditRequest.getInfoDescription());
//    }
//
//    private ProductDO buildProductDO(ProductEditRequest productEditRequest, ProductDO productDO) {
//        return productDO
//            .setId(productEditRequest.getProductId())
//            .setBrandId(productEditRequest.getBrandId())
//            .setBusinessId(productEditRequest.getBusinessId())
//            .setSecondCategoryId(productEditRequest.getSecondCategoryId())
//            .setUpdatedAt(new Date())
//            .setDescription(productEditRequest.getDescription())
//            .setImage(productEditRequest.getImage())
//            .setTitle(productEditRequest.getTitle())
//            .setStatus(productEditRequest.getStatus());
//    }

}
