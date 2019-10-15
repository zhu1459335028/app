package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdz
 * @since 2019-04-11
 */
public class Shieldimage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 盾构id
     */
	private Integer shieldid;
    /**
     * 盾构图片地址
     */
	private String imageurl;
    /**
     * 插入时间
     */
	private Date inserttime;
    /**
     * 更新时间
     */
	private Date updatetime;
	/**
	 * 工地id
	 */
	private Integer outletid;

	public Integer getOutletid() {
		return outletid;
	}

	public void setOutletid(Integer outletid) {
		this.outletid = outletid;
	}

	public Integer getId() {
		return id;
	}

	public Shieldimage setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getShieldid() {
		return shieldid;
	}

	public Shieldimage setShieldid(Integer shieldid) {
		this.shieldid = shieldid;
		return this;
	}

	public String getImageurl() {
		return imageurl;
	}

	public Shieldimage setImageurl(String imageurl) {
		this.imageurl = imageurl;
		return this;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public Shieldimage setInserttime(Date inserttime) {
		this.inserttime = inserttime;
		return this;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Shieldimage setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
		return this;
	}

}
