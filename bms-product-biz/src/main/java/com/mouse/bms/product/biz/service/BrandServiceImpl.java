package com.mouse.bms.product.biz.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Preconditions;
import com.mouse.bms.product.api.request.BrandAddRequest;
import com.mouse.bms.product.api.request.BrandEditRequst;
import com.mouse.bms.product.api.request.BrandQueryRequest;
import com.mouse.bms.product.api.response.BrandQueryResp;
import com.mouse.bms.product.api.service.BrandService;
import com.mouse.bms.product.api.service.ProductService;
import com.mouse.bms.product.common.enums.CommonResultEnum;
import com.mouse.bms.product.common.response.ListResult;
import com.mouse.bms.product.common.response.PlainResult;
import com.mouse.bms.product.common.util.PlainResults;
import com.mouse.bms.product.dal.dao.BrandDAO;
import com.mouse.bms.product.dal.dataobject.BrandDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BrandServiceImpl
 * @date : 2019/3/18 15:07
 * @description :
 */
@Service("brandService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = BrandService.class)
public class BrandServiceImpl implements BrandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Resource
    private BrandDAO brandDAO;

    @Override
    public PlainResult<Long> addBrand(BrandAddRequest brandAddRequest) {
        try {
//            Preconditions.checkArgument(!Objects.isNull(brandAddRequest));
//            Long businessId = brandAddRequest.getBusinessId();
//            String telephone = brandAddRequest.getTelephone();
//            String name = brandAddRequest.getName();
//            Preconditions
//                .checkArgument(null!=businessId&&0L < businessId && StringUtils.isNotEmpty(telephone) && StringUtils.isNotEmpty(name));
//            BrandDO brandDO = brandRepository.save(buildBrandDO(brandAddRequest));
//            if (!Objects.isNull(brandDO)) {
//                return PlainResults.success(brandDO.getId());
//            }
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("BrandService | addBrand, params is ille, brandAddRequest:{}.", brandAddRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("BrandService | addBrand, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
//        return PlainResults.success(3L);
    }

    private BrandDO buildBrandDO(BrandAddRequest brandAddRequest) {
        return new BrandDO()
            .setBusinessId(brandAddRequest.getBusinessId())
            .setName(brandAddRequest.getName())
            .setTelephone(brandAddRequest.getTelephone())
            .setWeb(brandAddRequest.getWeb())
            .setDescription(brandAddRequest.getDescription())
            .setLogo(brandAddRequest.getLogo())
            .setStatus(brandAddRequest.getStatus());
    }

    @Override
    public PlainResult<Boolean> editBrand(BrandEditRequst brandEditRequst) {
        LOGGER.info("BrandService | editBrand");
        return null;
    }

    @Override
    public PlainResult<Boolean> deleteBrand(Long businessId, Long brandId) {
        LOGGER.info("BrandService | deleteBrand");
        return null;
    }

    @Override
    public PlainResult<Boolean> deleteBrandBatch(Long businessId, List<Long> brandId) {
        LOGGER.info("BrandService | deleteBrandBatch");
        return null;
    }

    @Override
    public ListResult<BrandQueryResp> listBrands(BrandQueryRequest brandQueryRequest) {
        LOGGER.info("BrandService | listBrands");
        return null;
    }

    @Override
    public PlainResult<Long> countBrand(Long businessId) {
        LOGGER.info("BrandService | countBrand");
        return null;
    }
}
