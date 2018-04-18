package com.mall.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 极限场地表：place
 */
@ApiModel(value = "Place")
public class Place {
    @ApiModelProperty(value = "ID")
    private Integer placeId;

    @ApiModelProperty(value = "场地名称")
    private String placeName;

    @ApiModelProperty(value = "场地地址")
    private String placeAddress;

    @ApiModelProperty(value = "场地介绍")
    private String placeIntroduction;

    @ApiModelProperty(value = "开放时间")
    private Date placeTime;

    public Place(Integer placeId, String placeName, String placeAddress, String placeIntroduction, Date placeTime) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeIntroduction = placeIntroduction;
        this.placeTime = placeTime;
    }

    public Place() {
        super();
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress == null ? null : placeAddress.trim();
    }

    public String getPlaceIntroduction() {
        return placeIntroduction;
    }

    public void setPlaceIntroduction(String placeIntroduction) {
        this.placeIntroduction = placeIntroduction == null ? null : placeIntroduction.trim();
    }

    public Date getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Date placeTime) {
        this.placeTime = placeTime;
    }
}