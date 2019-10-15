package com.secusoft.ssw.model.monitor;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

   /**
    * gas 实体类
    * Sun Dec 30 13:57:23 CST 2018 ChenYongHeng
    */ 


public class Gas{
	private String id;
	private String tbm_range;
	private int dttte;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date dt;
	private String type;
	private String val;
	private String rummager;

	private String dtStr;

	private String device_id;

	   public String getDevice_id() {
		   return device_id;
	   }

	   public void setDevice_id(String device_id) {
		   this.device_id = device_id;
	   }

	   public String getDtStr() {
		   return dtStr;
	   }

	   public void setDtStr(String dtStr) {
		   this.dtStr = dtStr;
	   }

	   private int outletid;

	   public String getId() {
		   return id;
	   }

	   public void setId(String id) {
		   this.id = id;
	   }

	   public String getTbm_range() {
		   return tbm_range;
	   }

	   public void setTbm_range(String tbm_range) {
		   this.tbm_range = tbm_range;
	   }

	   public int getDttte() {
		   return dttte;
	   }

	   public void setDttte(int dttte) {
		   this.dttte = dttte;
	   }

	   public Date getDt() {
		   return dt;
	   }

	   public void setDt(Date dt) {
		   this.dt = dt;
	   }

	   public String getType() {
		   return type;
	   }

	   public void setType(String type) {
		   this.type = type;
	   }

	   public String getVal() {
		   return val;
	   }

	   public void setVal(String val) {
		   this.val = val;
	   }

	   public String getRummager() {
		   return rummager;
	   }

	   public void setRummager(String rummager) {
		   this.rummager = rummager;
	   }

	   public int getOutletid() {
		   return outletid;
	   }

	   public void setOutletid(int outletid) {
		   this.outletid = outletid;
	   }
   }

