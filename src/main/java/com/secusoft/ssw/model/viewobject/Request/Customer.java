package com.secusoft.ssw.model.viewobject.Request;

public class Customer {
    private Integer id;
    private String username;
    private String userpswd;
    private String userdes;
    private Integer userright;
    private Integer reportright;
    private Integer deviceright;
    private Integer isfirst;
    private Integer isinspector;
    private Integer managerright;
    private String customercode;
    private String phone;
    private String bindsafecode;
    private String safecode;
    private String position;
    private Integer lastoutletid;

    public Integer getLastoutletid() {
        return lastoutletid;
    }

    public void setLastoutletid(Integer lastoutletid) {
        this.lastoutletid = lastoutletid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getUserdes() {
        return userdes;
    }

    public void setUserdes(String userdes) {
        this.userdes = userdes;
    }

    public Integer getUserright() {
        return userright;
    }

    public void setUserright(Integer userright) {
        this.userright = userright;
    }

    public Integer getReportright() {
        return reportright;
    }

    public void setReportright(Integer reportright) {
        this.reportright = reportright;
    }

    public Integer getDeviceright() {
        return deviceright;
    }

    public void setDeviceright(Integer deviceright) {
        this.deviceright = deviceright;
    }

    public Integer getIsfirst() {
        return isfirst;
    }

    public void setIsfirst(Integer isfirst) {
        this.isfirst = isfirst;
    }

    public Integer getIsinspector() {
        return isinspector;
    }

    public void setIsinspector(Integer isinspector) {
        this.isinspector = isinspector;
    }

    public Integer getManagerright() {
        return managerright;
    }

    public void setManagerright(Integer managerright) {
        this.managerright = managerright;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBindsafecode() {
        return bindsafecode;
    }

    public void setBindsafecode(String bindsafecode) {
        this.bindsafecode = bindsafecode;
    }

    public String getSafecode() {
        return safecode;
    }

    public void setSafecode(String safecode) {
        this.safecode = safecode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
