package com.secusoft.ssw.model.monitor;

/**
 * videodevice 实体类
 * @author yaojiacheng
 * 2018年6月6日
 */
public class Videodevice {
	private int deviceno;
	private int devicetype;
	private String modelname;
	private String devicename;
	private int enabledomain;
	private String domainname;
	private String ipaddress;
	private int tcpport;
	private String username;
	private String passwd;
	private int channum;
	private int timeout;
	private int defaultlivevideo;
	private int defaultplaybackstream;
	private int isrecord;
	private int contactid;
	private int webport;
	private String clientid;
	private String authority;
	private int transcodeport;
	private int suspend;
	public void setDeviceno(int deviceno){
	this.deviceno=deviceno;
	}
	public int getDeviceno(){
		return deviceno;
	}
	public void setDevicetype(int devicetype){
	this.devicetype=devicetype;
	}
	public int getDevicetype(){
		return devicetype;
	}
	public void setModelname(String modelname){
	this.modelname=modelname;
	}
	public String getModelname(){
		return modelname;
	}
	public void setDevicename(String devicename){
	this.devicename=devicename;
	}
	public String getDevicename(){
		return devicename;
	}
	public void setEnabledomain(int enabledomain){
	this.enabledomain=enabledomain;
	}
	public int getEnabledomain(){
		return enabledomain;
	}
	public void setDomainname(String domainname){
	this.domainname=domainname;
	}
	public String getDomainname(){
		return domainname;
	}
	public void setIpaddress(String ipaddress){
	this.ipaddress=ipaddress;
	}
	public String getIpaddress(){
		return ipaddress;
	}
	public void setTcpport(int tcpport){
	this.tcpport=tcpport;
	}
	public int getTcpport(){
		return tcpport;
	}
	public void setUsername(String username){
	this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPasswd(String passwd){
	this.passwd=passwd;
	}
	public String getPasswd(){
		return passwd;
	}
	public void setChannum(int channum){
	this.channum=channum;
	}
	public int getChannum(){
		return channum;
	}
	public void setTimeout(int timeout){
	this.timeout=timeout;
	}
	public int getTimeout(){
		return timeout;
	}
	public void setDefaultlivevideo(int defaultlivevideo){
	this.defaultlivevideo=defaultlivevideo;
	}
	public int getDefaultlivevideo(){
		return defaultlivevideo;
	}
	public void setDefaultplaybackstream(int defaultplaybackstream){
	this.defaultplaybackstream=defaultplaybackstream;
	}
	public int getDefaultplaybackstream(){
		return defaultplaybackstream;
	}
	public void setIsrecord(int isrecord){
	this.isrecord=isrecord;
	}
	public int getIsrecord(){
		return isrecord;
	}
	public void setContactid(int contactid){
	this.contactid=contactid;
	}
	public int getContactid(){
		return contactid;
	}
	public void setWebport(int webport){
	this.webport=webport;
	}
	public int getWebport(){
		return webport;
	}
	public void setClientid(String clientid){
	this.clientid=clientid;
	}
	public String getClientid(){
		return clientid;
	}
	public void setAuthority(String authority){
	this.authority=authority;
	}
	public String getAuthority(){
		return authority;
	}
	public void setTranscodeport(int transcodeport){
	this.transcodeport=transcodeport;
	}
	public int getTranscodeport(){
		return transcodeport;
	}
	public void setSuspend(int suspend){
	this.suspend=suspend;
	}
	public int getSuspend(){
		return suspend;
	}
}

