package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

@ApiModel("id包装类")
public class IdParam extends BaseDomain{

    @NotNull(message = "Id不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
