package com.secusoft.ssw.model.monitor;

public class DeviceIpPort {
    private String domainName;//域名

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(String tcpPort) {
        this.tcpPort = tcpPort;
    }

    private String tcpPort;//端口



}
