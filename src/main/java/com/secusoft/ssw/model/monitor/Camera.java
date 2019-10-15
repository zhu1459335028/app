package com.secusoft.ssw.model.monitor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * camera 实体类
 * @author yaojiacheng
 * 2018年6月6日
 */
public class Camera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int camerano;
	private int deviceno;
	private int chanindex;
	private String cameraname;
	private int pos_x;
	private int pos_y;
	private int pos_fontsize;
	private int pos_fonttype;
	private int pos_fontcolor;
	private int pos_fontbold;
	private int useprofile;
	private int mainprofile;
	private int subprofile;
	private int enabled;
	private int checksd;
	private int checklowbattery;
	private String authority;
	private int suspend;
	private int cameratype;
	private int slicingTime;
	private int enableSlicing;
	private int outletid;

	public int getOutletid() {
		return outletid;
	}

	public void setOutletid(int outletid) {
		this.outletid = outletid;
	}

	public int getEnableSlicing() {
		return enableSlicing;
	}

	public void setEnableSlicing(int enableSlicing) {
		this.enableSlicing = enableSlicing;
	}

	public int getSlicingTime() {
		return slicingTime;
	}

	public void setSlicingTime(int slicingTime) {
		this.slicingTime = slicingTime;
	}

	public int getCameratype() {
		return cameratype;
	}
	
	public void setCameratype(int cameratype) {
		this.cameratype = cameratype;
	}
	
	public void setCamerano(int camerano){
	this.camerano=camerano;
	}
	public int getCamerano(){
		return camerano;
	}
	public void setDeviceno(int deviceno){
	this.deviceno=deviceno;
	}
	public int getDeviceno(){
		return deviceno;
	}
	public void setChanindex(int chanindex){
	this.chanindex=chanindex;
	}
	public int getChanindex(){
		return chanindex;
	}
	public void setCameraname(String cameraname){
	this.cameraname=cameraname;
	}
	public String getCameraname(){
		return cameraname;
	}
	public void setPos_x(int pos_x){
	this.pos_x=pos_x;
	}
	public int getPos_x(){
		return pos_x;
	}
	public void setPos_y(int pos_y){
	this.pos_y=pos_y;
	}
	public int getPos_y(){
		return pos_y;
	}
	public void setPos_fontsize(int pos_fontsize){
	this.pos_fontsize=pos_fontsize;
	}
	public int getPos_fontsize(){
		return pos_fontsize;
	}
	public void setPos_fonttype(int pos_fonttype){
	this.pos_fonttype=pos_fonttype;
	}
	public int getPos_fonttype(){
		return pos_fonttype;
	}
	public void setPos_fontcolor(int pos_fontcolor){
	this.pos_fontcolor=pos_fontcolor;
	}
	public int getPos_fontcolor(){
		return pos_fontcolor;
	}
	public void setPos_fontbold(int pos_fontbold){
	this.pos_fontbold=pos_fontbold;
	}
	public int getPos_fontbold(){
		return pos_fontbold;
	}
	public void setUseprofile(int useprofile){
	this.useprofile=useprofile;
	}
	public int getUseprofile(){
		return useprofile;
	}
	public void setMainprofile(int mainprofile){
	this.mainprofile=mainprofile;
	}
	public int getMainprofile(){
		return mainprofile;
	}
	public void setSubprofile(int subprofile){
	this.subprofile=subprofile;
	}
	public int getSubprofile(){
		return subprofile;
	}
	public void setEnabled(int enabled){
	this.enabled=enabled;
	}
	public int getEnabled(){
		return enabled;
	}
	public void setChecksd(int checksd){
	this.checksd=checksd;
	}
	public int getChecksd(){
		return checksd;
	}
	public void setChecklowbattery(int checklowbattery){
	this.checklowbattery=checklowbattery;
	}
	public int getChecklowbattery(){
		return checklowbattery;
	}
	public void setAuthority(String authority){
	this.authority=authority;
	}
	public String getAuthority(){
		return authority;
	}
	public void setSuspend(int suspend){
	this.suspend=suspend;
	}
	public int getSuspend(){
		return suspend;
	}


	   @Override
	   public boolean equals(Object o) {
		   if (o == this) return true;
		   if (!(o instanceof Camera)) {
			   return false;
		   }
		   Camera camera = (Camera) o;
		   return camera.cameraname.equals(cameraname) &&
				   camera.camerano == camerano &&
				   camera.deviceno == deviceno;
	   }

	   @Override
	   public int hashCode() {
		   int result = 17;
		   result = 31 * result + cameraname.hashCode();
		   result = 31 * result + deviceno;
		   result = 31 * result + camerano;
		   return result;
	   }
   }

