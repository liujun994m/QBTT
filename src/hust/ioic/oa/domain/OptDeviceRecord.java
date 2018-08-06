package hust.ioic.oa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;



/**
 * 换表记录表Bean对象
 * @author lecky
 *
 */
public class OptDeviceRecord implements Serializable{
@Expose private Integer id;
@Expose private Integer centerId;
@Expose private Integer collectionId;
@Expose private Integer deviceId;
@Expose private String deviceNo;
@Expose private String deviceName;
@Expose private Timestamp operateDate;
@Expose private String operateMan;
@Expose private BigDecimal oldReadData;
@Expose private BigDecimal newReadData;
@Expose private String oldDeviceNo;
@Expose private String newDeviceNo;
@Expose private String oldDevID;
@Expose private String newDevID;
@Expose private Integer userMoney;
@Expose private BigDecimal userCount;
@Expose private BigDecimal sumMoney;
@Expose private String remark;
@Expose private Integer flag;
@Expose private String  userNo;
@Expose private String  enprNo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Timestamp getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Timestamp operateDate) {
		this.operateDate = operateDate;
	}
	public String getOperateMan() {
		return operateMan;
	}
	public void setOperateMan(String operateMan) {
		this.operateMan = operateMan;
	}
	public BigDecimal getOldReadData() {
		return oldReadData;
	}
	public void setOldReadData(BigDecimal oldReadData) {
		this.oldReadData = oldReadData;
	}
	public BigDecimal getNewReadData() {
		return newReadData;
	}
	public void setNewReadData(BigDecimal newReadData) {
		this.newReadData = newReadData;
	}
	public String getOldDeviceNo() {
		return oldDeviceNo;
	}
	public void setOldDeviceNo(String oldDeviceNo) {
		this.oldDeviceNo = oldDeviceNo;
	}
	public String getNewDeviceNo() {
		return newDeviceNo;
	}
	public void setNewDeviceNo(String newDeviceNo) {
		this.newDeviceNo = newDeviceNo;
	}
	public String getOldDevID() {
		return oldDevID;
	}
	public void setOldDevID(String oldDevID) {
		this.oldDevID = oldDevID;
	}
	public String getNewDevID() {
		return newDevID;
	}
	public void setNewDevID(String newDevID) {
		this.newDevID = newDevID;
	}
	public Integer getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(Integer userMoney) {
		this.userMoney = userMoney;
	}
	public BigDecimal getUserCount() {
		return userCount;
	}
	public void setUserCount(BigDecimal userCount) {
		this.userCount = userCount;
	}

	public BigDecimal getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}


}
