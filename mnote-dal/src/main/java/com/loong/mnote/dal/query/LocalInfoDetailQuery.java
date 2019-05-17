package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel("国际化信息详情")
public class LocalInfoDetailQuery extends BaseQuery {

    @ApiParam(value = "父类ID")
    private Long infoId;

    @ApiParam(value = "语言")
    private String language;

    @ApiParam(value = "内容")
    private String content;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
