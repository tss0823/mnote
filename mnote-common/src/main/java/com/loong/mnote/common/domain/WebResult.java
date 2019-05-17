package com.loong.mnote.common.domain;

import com.loong.mnote.common.constants.SystemConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

//@Entity
@ApiModel(value = "返回结果")
public class WebResult extends BaseDomain {

    @ApiModelProperty(notes = "返回状态 true:成功,false:失败")
    private boolean success = true;

    @ApiModelProperty(notes = "编码(00:正常，01:未登录，02:未授权)")
    private String code = SystemConstant.ResponseCode.NORMAL;

    @ApiModelProperty(notes = "系统消息")
    private String message;

    @ApiModelProperty(notes = "业务数据")
    private Object data;

    public WebResult(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public WebResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> put(String key, Object value) {
        if (this.data == null || !(this.data instanceof Map)) {
            this.data = new HashMap();
        }

        Map<String, Object> map = (Map)this.data;
        map.put(key, value);
        return map;
    }
}
