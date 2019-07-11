package com.mouse.bms.product.config.config;

import com.mouse.bms.product.biz.repository.QiniuUploadFileConfig;
import com.mouse.bms.product.biz.repository.UpLoadInterface;
import com.qiniu.common.QiniuException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Resource;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : UpLoadController
 * @date : 2019/3/27 18:19
 * @description :
 */
@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @Resource
    private UpLoadInterface upLoadInterface;
    @Resource
    private QiniuUploadFileConfig qiniuUploadFileConfig;


    /**
     * 上传文件到七牛云存储
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("/qi")
    public String uploadImgQiniu(@RequestBody HeLLO hello) throws IOException {
        MultipartFile multipartFile = hello.getMultipartFile();
        String name = hello.getKey();
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        return qiniuUploadFileConfig.uploadImg(inputStream, name);
    }

    @PostMapping("/qiniu")
    public String index(@RequestParam File inputStream) throws QiniuException {
        upLoadInterface.uploadFile(inputStream);
        return "Success!";
    }

    @Data
    @Accessors(chain = true)
    class HeLLO implements Serializable {

        private static final long serialVersionUID = 6103464451498013406L;

        private MultipartFile multipartFile;
        private String key;
    }

}