package com.secusoft.ssw.model.viewobject.Request;

/**
 * @ClassName RecordVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/14 17:33
 * @Version 1.0
 */
public class RecordVO {

    private String cardId;
    private String deviceId;
    private String time;
    private String type;
    private String imageUrl;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "RecordVO{" +
                "cardId='" + cardId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
