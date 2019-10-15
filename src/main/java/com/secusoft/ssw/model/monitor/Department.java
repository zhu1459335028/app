package com.secusoft.ssw.model.monitor;

/**
 * @ClassName Department
 * @Author jcyao
 * @Description 部门信息表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class Department {

    private String id;

    private int outletid;

    private String name;

    private String parentid;

    private String path;

    //部门班组：0.无班组，1.施工班
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
