package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 工地开始日期表
 * </p>
 *
 * @author wdz
 * @since 2019-04-20
 */
public class Startdatetab implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 开始日期
     */
	private Date startdate;
    /**
     * 工地id
     */
	private Integer outletid;
    /**
     * 插入时间
     */
	private Date inserttime;
    /**
     * 更新时间
     */
	private Date updatetime;


	public Integer getId() {
		return id;
	}

	public Startdatetab setId(Integer id) {
		this.id = id;
		return this;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Startdatetab setStartdate(Date startdate) {
		this.startdate = startdate;
		return this;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public Startdatetab setOutletid(Integer outletid) {
		this.outletid = outletid;
		return this;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public Startdatetab setInserttime(Date inserttime) {
		this.inserttime = inserttime;
		return this;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Startdatetab setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
		return this;
	}

}
