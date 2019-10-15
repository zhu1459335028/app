package com.secusoft.ssw.model.viewobject.Request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class DeviceManage {
    private Integer id;
    private String devicename;
    private String devicetype;
    private Integer devicetypeid;
    private String devicemodel;
    private Integer iscertificateofinspection;
    private String examineid;
    private String examinetime;
    private Integer isputonrecord;
    private String drivername;
    private String telephone;
    private String operate;
    private String qualifiedimage;
    private String operateimage;
    private String fileurl;
    private Integer outletid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp inserttime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updatetime;

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getQualifiedimage() {
        return qualifiedimage;
    }

    public void setQualifiedimage(String qualifiedimage) {
        this.qualifiedimage = qualifiedimage;
    }

    public String getOperateimage() {
        return operateimage;
    }

    public void setOperateimage(String operateimage) {
        this.operateimage = operateimage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public Integer getDevicetypeid() {
        return devicetypeid;
    }

    public void setDevicetypeid(Integer devicetypeid) {
        this.devicetypeid = devicetypeid;
    }

    public String getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public Integer getIscertificateofinspection() {
        return iscertificateofinspection;
    }

    public void setIscertificateofinspection(Integer iscertificateofinspection) {
        this.iscertificateofinspection = iscertificateofinspection;
    }

    public String getExamineid() {
        return examineid;
    }

    public void setExamineid(String examineid) {
        this.examineid = examineid;
    }

    public String getExaminetime() {
        return examinetime;
    }

    public void setExaminetime(String examinetime) {
        this.examinetime = examinetime;
    }

    public Integer getIsputonrecord() {
        return isputonrecord;
    }

    public void setIsputonrecord(Integer isputonrecord) {
        this.isputonrecord = isputonrecord;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Integer getOutletid() {
        return outletid;
    }

    public void setOutletid(Integer outletid) {
        this.outletid = outletid;
    }

    public Timestamp getInserttime() {
        return inserttime;
    }

    public void setInserttime(Timestamp inserttime) {
        this.inserttime = inserttime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
}
