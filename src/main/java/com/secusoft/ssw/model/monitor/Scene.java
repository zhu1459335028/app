package com.secusoft.ssw.model.monitor;

/**
 * @ClassName Scene
 * @Author jcyao
 * @Description 算法场景信息表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class Scene {
    private int id;
    private int brandid;
    private String scenename;
    private String modelname;
    private int typeid;
    private String setupfilename;
    private int sceneno;

    public int getSceneno() {
        return sceneno;
    }

    public void setSceneno(int sceneno) {
        this.sceneno = sceneno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getScenename() {
        return scenename;
    }

    public void setScenename(String scenename) {
        this.scenename = scenename;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getSetupfilename() {
        return setupfilename;
    }

    public void setSetupfilename(String setupfilename) {
        this.setupfilename = setupfilename;
    }
}
