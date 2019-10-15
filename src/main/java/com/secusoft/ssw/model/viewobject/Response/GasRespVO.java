package com.secusoft.ssw.model.viewobject.Response;

public class GasRespVO {

    private Integer id;

    private String name;

    private String value;

    private String hzavlue;

    public String getHzavlue() {
        return hzavlue;
    }

    public void setHzavlue(String hzavlue) {
        this.hzavlue = hzavlue;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
