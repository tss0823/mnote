package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @author: sam
 * @date: 2019-01-10 14:52
 */
@ApiModel
public class AuthResQuery extends BaseQuery {

    @ApiParam(name = "状态")
    private Integer status;

    @ApiParam(name = "应用类型")
    private String appType;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
