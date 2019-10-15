package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 测给测量图片表
 * </p>
 *
 * @author wdz
 * @since 2019-04-25
 */
public class Settleimage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
	private String imgurl;
    /**
     * code
     */
	private String code;
    /**
     * 中文标识
     */
	private String name;
    /**
     * 插入时间
     */
	private Date inserttime;
    /**
     * 最近更新时间
     */
	private Date updatetime;
    /**
     * 工地id
     */
	private Integer outletid;


	public Integer getId() {
		return id;
	}

	public Settleimage setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getImgurl() {
		return imgurl;
	}

	public Settleimage setImgurl(String imgurl) {
		this.imgurl = imgurl;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Settleimage setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public Settleimage setName(String name) {
		this.name = name;
		return this;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public Settleimage setInserttime(Date inserttime) {
		this.inserttime = inserttime;
		return this;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Settleimage setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
		return this;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public Settleimage setOutletid(Integer outletid) {
		this.outletid = outletid;
		return this;
	}

}
