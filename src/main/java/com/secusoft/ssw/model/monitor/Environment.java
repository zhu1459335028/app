package com.secusoft.ssw.model.monitor;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

   /**
    * environment 实体类
    * Fri Jan 04 09:27:19 CST 2019 ChenYongHeng
    */ 


public class Environment{
	private int id;
	private float pm25;
	private float pm10;
	private float noise;
	private float temperature;
	private float humidity;
	private float wind_velocity;
	private float wind_direction;
	private float water_ph;
	private float dust;
	private String solid_waste;
	private String remark;
	private String location;
	private String location_no;
	private String department;
	private String rummager;
	private Date dt;
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setPm25(float pm25){
	this.pm25=pm25;
	}
	public float getPm25(){
		return pm25;
	}
	public void setPm10(float pm10){
	this.pm10=pm10;
	}
	public float getPm10(){
		return pm10;
	}
	public void setNoise(float noise){
	this.noise=noise;
	}
	public float getNoise(){
		return noise;
	}
	public void setTemperature(float temperature){
	this.temperature=temperature;
	}
	public float getTemperature(){
		return temperature;
	}
	public void setHumidity(float humidity){
	this.humidity=humidity;
	}
	public float getHumidity(){
		return humidity;
	}
	public void setWind_velocity(float wind_velocity){
	this.wind_velocity=wind_velocity;
	}
	public float getWind_velocity(){
		return wind_velocity;
	}
	public void setWind_direction(float wind_direction){
	this.wind_direction=wind_direction;
	}
	public float getWind_direction(){
		return wind_direction;
	}
	public void setWater_ph(float water_ph){
	this.water_ph=water_ph;
	}
	public float getWater_ph(){
		return water_ph;
	}
	public void setDust(float dust){
	this.dust=dust;
	}
	public float getDust(){
		return dust;
	}
	public void setSolid_waste(String solid_waste){
	this.solid_waste=solid_waste;
	}
	public String getSolid_waste(){
		return solid_waste;
	}
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setLocation(String location){
	this.location=location;
	}
	public String getLocation(){
		return location;
	}
	public void setLocation_no(String location_no){
	this.location_no=location_no;
	}
	public String getLocation_no(){
		return location_no;
	}
	public void setDepartment(String department){
	this.department=department;
	}
	public String getDepartment(){
		return department;
	}
	public void setRummager(String rummager){
	this.rummager=rummager;
	}
	public String getRummager(){
		return rummager;
	}
	public void setDt(Date dt){
	this.dt=dt;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getDt(){
		return dt;
	}
}

