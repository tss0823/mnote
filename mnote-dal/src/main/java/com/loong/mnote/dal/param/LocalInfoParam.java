package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel
public class LocalInfoParam extends BaseDomain {

    @ApiModelProperty(notes = "类型（0 提示消息，1 app label, 2 other ）")
    private Integer type;

    @ApiModelProperty(notes = "关键字")
    private String key;

    @ApiModelProperty(notes = "应用类型")
    private Integer appType;


    private List<LocalInfoDetailParam> itemList = new ArrayList<>();

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

    public List<LocalInfoDetailParam> getItemList() {
        return itemList;
    }

    public void setItemList(List<LocalInfoDetailParam> itemList) {
        this.itemList = itemList;
    }
}
