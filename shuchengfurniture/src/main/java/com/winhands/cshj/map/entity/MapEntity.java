package com.winhands.cshj.map.entity;

import java.util.Date;

/**
 * @author guojun
 */
public class MapEntity {
    private String id;
    private String portName;//站点名称
    private String portAddress;//站点地址
    private String lon;//经度
    private String lat;//纬度
    private String lable;//标签
    private String createUserId;
    private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getPortAddress() {
		return portAddress;
	}
	public void setPortAddress(String portAddress) {
		this.portAddress = portAddress;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    
  
}
