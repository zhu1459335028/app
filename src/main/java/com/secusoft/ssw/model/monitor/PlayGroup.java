package com.secusoft.ssw.model.monitor;

/**
 * @ClassName PlayGroup
 * @Author jcyao
 * @Description 视频轮播信息表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class PlayGroup {

    private int groupid;

    private int userid;

    private int outletid;

    //执行方式：1.单次执行，0.循环执行
    private int grouptype;

    /**
     * 轮播信息
     * “1:20;3:25”，表示camerano为1的相机，播放10秒后，camerano为3的相机播放25秒
     */
    private String groupinfo;

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }

    public int getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(int grouptype) {
        this.grouptype = grouptype;
    }

    public String getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(String groupinfo) {
        this.groupinfo = groupinfo;
    }
}
