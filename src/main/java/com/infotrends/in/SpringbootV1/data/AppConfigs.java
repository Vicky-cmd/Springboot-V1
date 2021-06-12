package com.infotrends.in.SpringbootV1.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AppConfigs")
public class AppConfigs {

	@Id
	private String app_id;
	
	@Column(name = "strValue")
	private String strValue;
	
	@Column(name = "intValue")
	private int intValue;
	
	@Column(name = "accessTimestamp")
	private Date accessTimestamp = new Date();

	
	public AppConfigs() {
	}

	public AppConfigs(String app_id, String strValue, int intValue) {
		this.app_id = app_id;
		this.strValue = strValue;
		this.intValue = intValue;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public Date getAccessTimestamp() {
		return accessTimestamp;
	}

	public void setAccessTimestamp(Date accessTimestamp) {
		this.accessTimestamp = accessTimestamp;
	}
	

}
