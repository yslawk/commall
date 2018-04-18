package com.mall.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 活动回顾表：active
 */
@ApiModel(value = "Active")
public class Active {

    @ApiModelProperty(value = "ID")
    private Integer activeId;

    @ApiModelProperty(value = "名称")
    private String activeName;

    @ApiModelProperty(value = "活动内容")
    private String activeContent;


    public Active() {
        super();
    }

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName == null ? null : activeName.trim();
    }

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent == null ? null : activeContent.trim();
    }

}