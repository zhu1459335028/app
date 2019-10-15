package com.secusoft.ssw.model.viewobject.Response;

import java.util.Date;

public class ThresholdRespVO {

    private Integer id;

    private String type;

    private String max_val;

    private String min_val;

    private String identical_val;

    /**
     *子类型
     */
    private Integer typesub;

    private String  starttimestr;

    private String endtimestr;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMax_val() {
        return max_val;
    }

    public void setMax_val(String max_val) {
        this.max_val = max_val;
    }

    public String getMin_val() {
        return min_val;
    }

    public void setMin_val(String min_val) {
        this.min_val = min_val;
    }

    public String getIdentical_val() {
        return identical_val;
    }

    public void setIdentical_val(String identical_val) {
        this.identical_val = identical_val;
    }

    public Integer getTypesub() {
        return typesub;
    }

    public void setTypesub(Integer typesub) {
        this.typesub = typesub;
    }

    public String getStarttimestr() {
        return starttimestr;
    }

    public void setStarttimestr(String starttimestr) {
        this.starttimestr = starttimestr;
    }

    public String getEndtimestr() {
        return endtimestr;
    }

    public void setEndtimestr(String endtimestr) {
        this.endtimestr = endtimestr;
    }
}
