package com.mouse.bms.product.dal.dataobject;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : AbstractDO
 * @date : 2019/3/25 09:06
 * @description :
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


import lombok.Data;

@Data
public abstract class AbstractDO{

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deletedAt;

}
