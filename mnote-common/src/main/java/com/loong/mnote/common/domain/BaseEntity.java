package com.loong.mnote.common.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Java Bean抽象实体 封装一些公用字段并且实现序列化
 */
@MappedSuperclass
public class BaseEntity extends BaseDomain {

    @ApiModelProperty(notes = "ID")
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "创建时间")
    @Column(name="gmtCreate", updatable=false,nullable = false,length = 0)
    @CreationTimestamp
    private Date gmtCreate;

    @ApiModelProperty(notes = "最后修改时间")
    @Column(name="gmtModified",nullable = false,length = 0)
    @UpdateTimestamp
    private Date gmtModified;

    @ApiModelProperty(notes = "数据状态（0 不存在，1 存在）")
    @Column(name = "delState",length = 1,nullable = false,insertable = false)
    @ColumnDefault("0")
    private Boolean delState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getDelState() {
        return delState;
    }

    public void setDelState(Boolean delState) {
        this.delState = delState;
    }
}
