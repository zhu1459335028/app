package com.secusoft.ssw.model.viewobject.Response;

import java.util.Date;

/**
 * 进出记录详情Vo
 */
public class VehiclerecordDetailoRespVO {

    private String url;

    private String source;//图片来源

    private int type;//进出态

    private String imagetype;//图片类型

    private String imagetime;//抓拍时间

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

    public String getImagetime() {
        return imagetime;
    }

    public void setImagetime(String imagetime) {
        this.imagetime = imagetime;
    }
}
