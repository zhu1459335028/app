package com.secusoft.ssw.model.monitor;

/**
 * @ClassName Job
 * @Author jcyao
 * @Description 职位信息表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class Job {

    private String id;

    private String jobid;

    private int outletid;

    private String name;

    //是否关键岗位：1.是、0.不是
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
