package com.secusoft.ssw.model.viewobject.Response;

public class DangerpointVO {


    /**
     * 点位名称
     */
    private String pointname;
    /**
     * 点位类型
     */
    private String pointtype;
    /**
     * 起始间距--里程
     */
    private Float startspacing;
    /**
     * 危险深度
     */
    private String dangerdepth;

    /**
     *  标准深度
     */
    private String standarddepth;

    /**
     * 图纸总长
     */
    private String drawlength;

    public String getDrawlength() {
        return drawlength;
    }

    public void setDrawlength(String drawlength) {
        this.drawlength = drawlength;
    }

    public String getStandarddepth() {
        return standarddepth;
    }

    public void setStandarddepth(String standarddepth) {
        this.standarddepth = standarddepth;
    }

    public String getPointname() {
        return pointname;
    }

    public void setPointname(String pointname) {
        this.pointname = pointname;
    }

    public String getPointtype() {
        return pointtype;
    }

    public void setPointtype(String pointtype) {
        this.pointtype = pointtype;
    }

    public Float getStartspacing() {
        return startspacing;
    }

    public void setStartspacing(Float startspacing) {
        this.startspacing = startspacing;
    }

    public String getDangerdepth() {
        return dangerdepth;
    }

    public void setDangerdepth(String dangerdepth) {
        this.dangerdepth = dangerdepth;
    }
}
