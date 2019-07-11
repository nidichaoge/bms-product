package com.mouse.bms.product.biz.exception;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ProductException
 * @date : 2019/3/21 12:26
 * @description :
 */
public class ProductException extends Exception {

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductException(Throwable cause) {
        super(cause);
    }

    public ProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
