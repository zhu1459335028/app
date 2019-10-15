package com.secusoft.ssw.model.monitor;

/**
 * <p>
 * 阈值条目名称
 * </p>
 *
 * @author wdz
 * @since 2019-04-23
 */
public class ThresholdName {

    /**
     * id
     */
    private Integer id;
    /**
     * 中文名称
     */
    private String name;

    /**
     * 工地id
     */
    private Integer outletid;

    /**
     * 1预警，2报警，3严重
     */
    private Integer type;

    /**
     * 框的个数
     */
    private Integer number;

    /**
     * ,tv.max_val
     */
    private String max_val;

    private String value;

    private Integer thresholeid;

    public Integer getThresholeid() {
        return thresholeid;
    }

    public void setThresholeid(Integer thresholeid) {
        this.thresholeid = thresholeid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMax_val() {
        return max_val;
    }

    public void setMax_val(String max_val) {
        this.max_val = max_val;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
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
}
