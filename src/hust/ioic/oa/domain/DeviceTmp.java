package hust.ioic.oa.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;
@Resource
public class DeviceTmp {
	
private String iAddr;
private Integer readDate;
private Integer centerID;
private BigDecimal showValue;
private BigDecimal fshowValue;
private Integer meterState;
private Integer commState;
private Integer isUse;
private Timestamp readTime;
private String enprNo;
public String getiAddr() {
	return iAddr;
}
public void setiAddr(String iAddr) {
	this.iAddr = iAddr;
}
public Integer getReadDate() {
	return readDate;
}
public void setReadDate(Integer readDate) {
	this.readDate = readDate;
}

public Integer getCenterID() {
	return centerID;
}
public void setCenterID(Integer centerID) {
	this.centerID = centerID;
}

public BigDecimal getShowValue() {
	return showValue;
}
public void setShowValue(BigDecimal showValue) {
	this.showValue = showValue;
}
public BigDecimal getFshowValue() {
	return fshowValue;
}
public void setFshowValue(BigDecimal fshowValue) {
	this.fshowValue = fshowValue;
}
public Integer getMeterState() {
	return meterState;
}
public void setMeterState(Integer meterState) {
	this.meterState = meterState;
}
public Integer getCommState() {
	return commState;
}
public void setCommState(Integer commState) {
	this.commState = commState;
}
public Integer getIsUse() {
	return isUse;
}
public void setIsUse(Integer isUse) {
	this.isUse = isUse;
}
public Timestamp getReadTime() {
	return readTime;
}
public void setReadTime(Timestamp readTime) {
	this.readTime = readTime;
}
public String getEnprNo() {
	return enprNo;
}
public void setEnprNo(String enprNo) {
	this.enprNo = enprNo;
}

}
