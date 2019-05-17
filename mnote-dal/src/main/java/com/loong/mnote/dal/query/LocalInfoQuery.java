package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel("国际化信息")
public class LocalInfoQuery extends BaseQuery {

    @ApiParam(value = "类型（0 提示消息，1 app label, 2 other ）")
    private Integer type;

    @ApiParam(value = "关键字")
    private String key;

    @ApiParam(value = "应用类型")
    private Integer appType;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
