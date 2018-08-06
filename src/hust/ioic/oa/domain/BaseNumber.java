package hust.ioic.oa.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BaseNumber {
private Integer id;
private String deviceAddr;
private String collectionAddr;
private String centerAddr;
private BigDecimal deviceData;
private Timestamp writeDate;
private Integer moneyMonth;
private Integer isMoney;
private Integer deviceId;
private String enprNo;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getDeviceAddr() {
	return deviceAddr;
}
public void setDeviceAddr(String deviceAddr) {
	this.deviceAddr = deviceAddr;
}
public String getCollectionAddr() {
	return collectionAddr;
}
public void setCollectionAddr(String collectionAddr) {
	this.collectionAddr = collectionAddr;
}
public String getCenterAddr() {
	return centerAddr;
}
public void setCenterAddr(String centerAddr) {
	this.centerAddr = centerAddr;
}
public BigDecimal getDeviceData() {
	return deviceData;
}
public void setDeviceData(BigDecimal deviceData) {
	this.deviceData = deviceData;
}
public Timestamp getWriteDate() {
	return writeDate;
}
public void setWriteDate(Timestamp writeDate) {
	this.writeDate = writeDate;
}
public Integer getMoneyMonth() {
	return moneyMonth;
}
public void setMoneyMonth(Integer moneyMonth) {
	this.moneyMonth = moneyMonth;
}
public Integer getIsMoney() {
	return isMoney;
}
public void setIsMoney(Integer isMoney) {
	this.isMoney = isMoney;
}
public Integer getDeviceId() {
	return deviceId;
}
public void setDeviceId(Integer deviceId) {
	this.deviceId = deviceId;
}
public String getEnprNo() {
	return enprNo;
}
public void setEnprNo(String enprNo) {
	this.enprNo = enprNo;
}


}
