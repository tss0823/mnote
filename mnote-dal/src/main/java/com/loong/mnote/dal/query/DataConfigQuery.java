package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @author: sam
 * @date: 2019-01-09 09:55
 */
@ApiModel
public class DataConfigQuery extends BaseQuery {


    @ApiParam(value="是否启用",example = "true")
    private java.lang.Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
