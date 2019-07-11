package com.mouse.bms.product.biz.repository;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : UpLoadInterface
 * @date : 2019/3/27 18:08
 * @description :
 */
public interface UpLoadInterface {

    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;

}
