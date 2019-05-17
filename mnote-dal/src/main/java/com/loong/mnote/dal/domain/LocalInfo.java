package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.*;

@ApiModel("国际化信息")
@Entity(name = "local_info")
public class LocalInfo extends BaseEntity {

    @ApiModelProperty(notes = "类型（0 提示消息，1 app label, 2 other ）")
    @Column(length = 4,nullable = false)
    private Integer type;

    @ApiModelProperty(notes = "关键字")
    @Column(name="[key]",length = 100,nullable = false)
    private String key;

    @ApiModelProperty(notes = "应用类型")
    @Column(length = 4,nullable = false)
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
