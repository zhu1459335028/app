package com.secusoft.ssw.model.monitor;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
    * threshold 实体类
    * Sun Dec 30 14:01:14 CST 2018 ChenYongHeng
    */ 


public class Threshold{
	private Integer id;
	private String type;
	private String max_val;
	private String min_val;
	private String identical_val;
	private int outletid;

	 private Integer typesub;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	 private Date starttime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	 private Date endtime;

	 private String  starttimestr;

	private String endtimestr;



	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getStarttimestr() {
		   return starttimestr;
	   }

	   public void setStarttimestr(String starttimestr) {
		   this.starttimestr = starttimestr;
	   }

	   public String getEndtimestr() {
		   return endtimestr;
	   }

	   public void setEndtimestr(String endtimestr) {
		   this.endtimestr = endtimestr;
	   }

	   public Integer getTypesub() {
		   return typesub;
	   }

	   public void setTypesub(Integer typesub) {
		   this.typesub = typesub;
	   }

	   public Integer getId() {
		   return id;
	   }

	   public void setId(Integer id) {
		   this.id = id;
	   }

	   public String getType() {
		   return type;
	   }

	   public void setType(String type) {
		   this.type = type;
	   }

	   public String getMax_val() {
		   return max_val;
	   }

	   public void setMax_val(String max_val) {
		   this.max_val = max_val;
	   }

	   public String getMin_val() {
		   return min_val;
	   }

	   public void setMin_val(String min_val) {
		   this.min_val = min_val;
	   }

	   public String getIdentical_val() {
		   return identical_val;
	   }

	   public void setIdentical_val(String identical_val) {
		   this.identical_val = identical_val;
	   }

	   public int getOutletid() {
		   return outletid;
	   }

	   public void setOutletid(int outletid) {
		   this.outletid = outletid;
	   }
   }

