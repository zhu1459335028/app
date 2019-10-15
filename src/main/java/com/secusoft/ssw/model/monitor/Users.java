package com.secusoft.ssw.model.monitor;

/**
 * users 实体类
 * @author yaojiacheng
 * 2018年6月6日
 */
public class Users {
	private int userid;
	private String username;
	private String passwd;
	private int authlevel;
	private String default_deviceauthority;
	private String default_cameraauthority;
	private String safeCode;
	private int bind;
	private int online;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getAuthlevel() {
		return authlevel;
	}

	public void setAuthlevel(int authlevel) {
		this.authlevel = authlevel;
	}

	public String getDefault_deviceauthority() {
		return default_deviceauthority;
	}

	public void setDefault_deviceauthority(String default_deviceauthority) {
		this.default_deviceauthority = default_deviceauthority;
	}

	public String getDefault_cameraauthority() {
		return default_cameraauthority;
	}

	public void setDefault_cameraauthority(String default_cameraauthority) {
		this.default_cameraauthority = default_cameraauthority;
	}

	public String getSafeCode() {
		return safeCode;
	}

	public void setSafeCode(String safeCode) {
		this.safeCode = safeCode;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}
}

