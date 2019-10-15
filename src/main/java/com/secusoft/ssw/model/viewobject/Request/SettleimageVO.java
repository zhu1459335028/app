package com.secusoft.ssw.model.viewobject.Request;

/**
 * 保存图片相关信息
 */
public class SettleimageVO {
    private String imgurl;
    /**
     * code
     */
    private String code;
    /**
     * 中文标识
     */
    private String name;


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
