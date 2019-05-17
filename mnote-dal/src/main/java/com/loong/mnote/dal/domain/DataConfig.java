package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author: sam
 * @date: 2019-01-09 09:54
 */
@Entity(name="data_config")
@ApiModel
public class DataConfig   extends BaseEntity {

    @ApiModelProperty(notes = "编码")
    @Column(length = 100,nullable = false)
    private java.lang.String code;

    @ApiModelProperty(notes = "值")
    @Column(length = 1000,nullable = false)
    private java.lang.String value;

    @ApiModelProperty(notes = "是否启用")
    @Column(length = 1,nullable = false,insertable = false)
    @ColumnDefault("1")
    private java.lang.Boolean enable;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
