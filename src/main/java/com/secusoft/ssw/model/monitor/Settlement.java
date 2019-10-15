package com.secusoft.ssw.model.monitor;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.channels.FileLock;
import java.util.Date;

   /**
    * settlement 实体类
    * Sun Dec 30 14:01:14 CST 2018 ChenYongHeng
    */ 


public class Settlement{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String location;
	private String location_no;
	@Column(name="last_val")
	private Float last_val;
	@Column(name="current_val")
	private Float current_val;
	@Column(name="accumulate_val")
	private Float accumulate_val;
	@Column(name="rate")
	private Float rate;
	private String remark;
	private Date dt;
	private String equipment;
	private String equipment_no;
	@Column(name="outletid")
	private Integer outletid;

	/**深度*/
	private Float depth;
	/**最大报警*/
	private Float maxalarm;
	/**最小报警*/
	private Float minalarm;
	/**前测试点的类型id*/
	private Integer settleunitmonid;
	/**水位埋深*/
	private Float waterdepth;

	//累计变量
	private Float lastaccumulate_val;

	//本次变化量
	private Float  thecurrent_var;

	   public Float getLastaccumulate_val() {
		   return lastaccumulate_val;
	   }

	   public void setLastaccumulate_val(Float lastaccumulate_val) {
		   this.lastaccumulate_val = lastaccumulate_val;
	   }

	   public Float getThecurrent_var() {
		   return thecurrent_var;
	   }

	   public void setThecurrent_var(Float thecurrent_var) {
		   this.thecurrent_var = thecurrent_var;
	   }

	   public Float getWaterdepth() {
		   return waterdepth;
	   }

	   public void setWaterdepth(Float waterdepth) {
		   this.waterdepth = waterdepth;
	   }

	   public Integer getSettleunitmonid() {
		   return settleunitmonid;
	   }

	   public void setSettleunitmonid(Integer settleunitmonid) {
		   this.settleunitmonid = settleunitmonid;
	   }

	   public Float getDepth() {
		   return depth;
	   }

	   public void setDepth(Float depth) {
		   this.depth = depth;
	   }

	   public Float getMaxalarm() {
		   return maxalarm;
	   }

	   public void setMaxalarm(Float maxalarm) {
		   this.maxalarm = maxalarm;
	   }

	   public Float getMinalarm() {
		   return minalarm;
	   }

	   public void setMinalarm(Float minalarm) {
		   this.minalarm = minalarm;
	   }

	   /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the location_no
	 */
	public String getLocation_no() {
		return location_no;
	}

	/**
	 * @param location_no the location_no to set
	 */
	public void setLocation_no(String location_no) {
		this.location_no = location_no;
	}

	/**
	 * @return the last_val
	 */
	public Float getLast_val() {
		return last_val;
	}

	/**
	 * @param last_val the last_val to set
	 */
	public void setLast_val(Float last_val) {
		this.last_val = last_val;
	}

	/**
	 * @return the current_val
	 */
	public Float getCurrent_val() {
		return current_val;
	}

	/**
	 * @param current_val the current_val to set
	 */
	public void setCurrent_val(Float current_val) {
		this.current_val = current_val;
	}

	/**
	 * @return the accumulate_val
	 */
	public Float getAccumulate_val() {
		return accumulate_val;
	}

	/**
	 * @param accumulate_val the accumulate_val to set
	 */
	public void setAccumulate_val(Float accumulate_val) {
		this.accumulate_val = accumulate_val;
	}

	/**
	 * @return the rate
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the dt
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getDt() {
		return dt;
	}

	/**
	 * @param dt the dt to set
	 */
	public void setDt(Date dt) {
		this.dt = dt;
	}

	/**
	 * @return the equipment
	 */
	public String getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the equipment_no
	 */
	public String getEquipment_no() {
		return equipment_no;
	}

	/**
	 * @param equipment_no the equipment_no to set
	 */
	public void setEquipment_no(String equipment_no) {
		this.equipment_no = equipment_no;
	}

	/**
	 * @return the outletid
	 */
	public Integer getOutletid() {
		return outletid;
	}

	/**
	 * @param outletid the outletid to set
	 */
	public void setOutletid(Integer outletid) {
		this.outletid = outletid;
	}
	

}

