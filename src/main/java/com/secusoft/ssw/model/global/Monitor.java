package com.secusoft.ssw.model.global;

/**
 * @ClassName Monitor
 * @Author jcyao
 * @Description monitor 实体类
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class Monitor{
	private int monitorid;
	private String showname;
	private String dbname;
	private String dbuser;
	private String dbpswd;
	private String dbip;
	private int dbport;
	private String loginuser;
	private String loginpswd;
	private String templateids;
	private int style;
	
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public String getTemplateids() {
		return templateids;
	}
	public void setTemplateids(String templateids) {
		this.templateids = templateids;
	}
	;
	public void setMonitorid(int monitorid){
	this.monitorid=monitorid;
	}
	public int getMonitorid(){
		return monitorid;
	}
	public void setShowname(String showname){
	this.showname=showname;
	}
	public String getShowname(){
		return showname;
	}
	public void setDbname(String dbname){
	this.dbname=dbname;
	}
	public String getDbname(){
		return dbname;
	}
	public void setDbuser(String dbuser){
	this.dbuser=dbuser;
	}
	public String getDbuser(){
		return dbuser;
	}
	public void setDbpswd(String dbpswd){
	this.dbpswd=dbpswd;
	}
	public String getDbpswd(){
		return dbpswd;
	}
	public void setDbip(String dbip){
	this.dbip=dbip;
	}
	public String getDbip(){
		return dbip;
	}
	public void setDbport(int dbport){
	this.dbport=dbport;
	}
	public int getDbport(){
		return dbport;
	}
	public void setLoginuser(String loginuser){
	this.loginuser=loginuser;
	}
	public String getLoginuser(){
		return loginuser;
	}
	public void setLoginpswd(String loginpswd){
	this.loginpswd=loginpswd;
	}
	public String getLoginpswd(){
		return loginpswd;
	}
	@Override
	public String toString() {
		return "Monitor [monitorid=" + monitorid + ", showname=" + showname + ", dbname=" + dbname + ", dbuser="
				+ dbuser + ", dbport=" + dbport + "]";
	}
	
}

