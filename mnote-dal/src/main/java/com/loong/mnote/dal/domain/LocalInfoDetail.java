package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

@ApiModel("国际化信息详情")
@Entity(name = "local_info_detail")
public class LocalInfoDetail extends BaseEntity{

    @ApiModelProperty(notes = "父类ID")
    @Column(length = 20)
    private Long infoId;

    @ApiModelProperty(notes = "语言")
    @Column(length = 50)
    private String language;

    @ApiModelProperty(notes = "内容")
    @Column(length = 1000)
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
