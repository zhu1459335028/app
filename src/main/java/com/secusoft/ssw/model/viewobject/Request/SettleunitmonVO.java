package com.secusoft.ssw.model.viewobject.Request;

public class SettleunitmonVO {

    /**
     * id
     */
    private Integer id;
    /**
     * 中文名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;

    private String unit;

    /**
     * 监测点类型
     */
    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
