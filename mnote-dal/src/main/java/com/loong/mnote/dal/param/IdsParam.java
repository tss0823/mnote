package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

@ApiModel("ids包装类")
public class IdsParam extends BaseDomain{

    @NotBlank(message = "Ids不能为空")
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
