package hust.ioic.oa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;



public class Device implements Comparable,Serializable,Cloneable{
	
@Expose private Integer id;
@Expose private String name;
@Expose private String addr;
@Expose private String deviceNo;
@Expose private String userNo;
@Expose private Integer uplinkType;
@Expose private Timestamp readTime;
@Expose private BigDecimal MaxNum;
@Expose private BigDecimal CeValue;
@Expose private BigDecimal OldValue;
@Expose private BigDecimal imultiple;
@Expose private BigDecimal ShowValue;
@Expose private Integer runStatue;
@Expose private Integer needAlarm;
@Expose private Integer alarmGetType;
@Expose private BigDecimal alarmMax;
@Expose private BigDecimal alarmMin;
@Expose private Integer nowStatue;
@Expose private Integer commStatue;
@Expose private Integer deviceStatue;
@Expose private Integer strobeStatue;
@Expose private Timestamp AlarmDate;
@Expose private Device parent;
@Expose private Set<Device> children=new HashSet<>();
@Expose private Integer status;
@Expose private String CardID;
@Expose private Integer ceType;
@Expose private Timestamp createDate;
@Expose private Timestamp setupDate;
@Expose private Integer StopUse;
@Expose private Integer ShowX;
@Expose private Integer ShowY;
@Expose private Integer ShowType;
@Expose private String PageGoNo;
@Expose private String PicFileName;
@Expose private Timestamp StrobeDate;
@Expose private Integer StrobeExecute;
@Expose private Integer LogicNo;
@Expose private Integer allowOper;
@Expose private Integer manualOpen;
@Expose private Integer testMonth;
@Expose private Integer testState;
@Expose private BigDecimal PrShowValue;
@Expose private Timestamp PrCeDate;
@Expose private BigDecimal MonthCount;
@Expose private Integer StopType;
@Expose private BigDecimal ErrData;
@Expose private Timestamp ErrDate;
@Expose private Integer GetMoneyType;
@Expose private BigDecimal PrSaveValue;
@Expose private Timestamp PrSaveDate;
@Expose public Integer UserCountType;
@Expose public Integer BigDeviceFlag;
@Expose private Integer isMemBer;
@Expose private DeviceRelation deviceRelation;
@Expose private DeviceType deviceType;
@Expose private String enprNo;
@Expose private String userAddr;
@Expose private String userName;
@Expose private Integer areaId;
	public Integer getAreaId() {
	return areaId;
}
public void setAreaId(Integer areaId) {
	this.areaId = areaId;
}
	public Integer getBigDeviceFlag() {
		return BigDeviceFlag;
	}
	public void setBigDeviceFlag(Integer bigDeviceFlag) {
		BigDeviceFlag = bigDeviceFlag;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public Timestamp getReadTime() {
		return readTime;
	}
	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

	
	public BigDecimal getImultiple() {
		return imultiple;
	}
	public void setImultiple(BigDecimal imultiple) {
		this.imultiple = imultiple;
	}

	public BigDecimal getMaxNum() {
		return MaxNum;
	}
	public void setMaxNum(BigDecimal maxNum) {
		MaxNum = maxNum;
	}
	public BigDecimal getCeValue() {
		return CeValue;
	}
	public void setCeValue(BigDecimal ceValue) {
		CeValue = ceValue;
	}
	public BigDecimal getOldValue() {
		return OldValue;
	}
	public void setOldValue(BigDecimal oldValue) {
		OldValue = oldValue;
	}
	public BigDecimal getShowValue() {
		return ShowValue;
	}
	public void setShowValue(BigDecimal showValue) {
		ShowValue = showValue;
	}
	public Integer getRunStatue() {
		return runStatue;
	}
	public void setRunStatue(Integer runStatue) {
		this.runStatue = runStatue;
	}
	public Integer getNeedAlarm() {
		return needAlarm;
	}
	public void setNeedAlarm(Integer needAlarm) {
		this.needAlarm = needAlarm;
	}
	public Integer getAlarmGetType() {
		return alarmGetType;
	}
	public void setAlarmGetType(Integer alarmGetType) {
		this.alarmGetType = alarmGetType;
	}
	public BigDecimal getAlarmMax() {
		return alarmMax;
	}
	public void setAlarmMax(BigDecimal alarmMax) {
		this.alarmMax = alarmMax;
	}
	public BigDecimal getAlarmMin() {
		return alarmMin;
	}
	public void setAlarmMin(BigDecimal alarmMin) {
		this.alarmMin = alarmMin;
	}
	public Integer getNowStatue() {
		return nowStatue;
	}
	public void setNowStatue(Integer nowStatue) {
		this.nowStatue = nowStatue;
	}
	public Integer getCommStatue() {
		return commStatue;
	}
	public void setCommStatue(Integer commStatue) {
		this.commStatue = commStatue;
	}
	public Integer getDeviceStatue() {
		return deviceStatue;
	}
	public void setDeviceStatue(Integer deviceStatue) {
		this.deviceStatue = deviceStatue;
	}

	public Timestamp getAlarmDate() {
		return AlarmDate;
	}
	public void setAlarmDate(Timestamp alarmDate) {
		AlarmDate = alarmDate;
	}
	public Device getParent() {
		return parent;
	}
	public void setParent(Device parent) {
		this.parent = parent;
	}
	public Set<Device> getChildren() {
		return children;
	}
	public void setChildren(Set<Device> children) {
		this.children = children;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCardID() {
		return CardID;
	}
	public void setCardID(String cardID) {
		CardID = cardID;
	}
	public Integer getCeType() {
		return ceType;
	}
	public void setCeType(Integer ceType) {
		this.ceType = ceType;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getSetupDate() {
		return setupDate;
	}
	public void setSetupDate(Timestamp setupDate) {
		this.setupDate = setupDate;
	}
	public Integer getStopUse() {
		return StopUse;
	}
	public void setStopUse(Integer stopUse) {
		StopUse = stopUse;
	}
	public Integer getShowX() {
		return ShowX;
	}
	public void setShowX(Integer showX) {
		ShowX = showX;
	}
	public Integer getShowY() {
		return ShowY;
	}
	public void setShowY(Integer showY) {
		ShowY = showY;
	}
	public Integer getShowType() {
		return ShowType;
	}
	public void setShowType(Integer showType) {
		ShowType = showType;
	}
	public String getPageGoNo() {
		return PageGoNo;
	}
	public void setPageGoNo(String pageGoNo) {
		PageGoNo = pageGoNo;
	}
	public String getPicFileName() {
		return PicFileName;
	}
	public void setPicFileName(String picFileName) {
		PicFileName = picFileName;
	}
	public Timestamp getStrobeDate() {
		return StrobeDate;
	}
	public void setStrobeDate(Timestamp strobeDate) {
		StrobeDate = strobeDate;
	}


	public Integer getStrobeStatue() {
		return strobeStatue;
	}
	public void setStrobeStatue(Integer strobeStatue) {
		this.strobeStatue = strobeStatue;
	}
	public Integer getStrobeExecute() {
		return StrobeExecute;
	}
	public void setStrobeExecute(Integer strobeExecute) {
		StrobeExecute = strobeExecute;
	}
	public Integer getLogicNo() {
		return LogicNo;
	}
	public void setLogicNo(Integer logicNo) {
		LogicNo = logicNo;
	}
	public Integer getAllowOper() {
		return allowOper;
	}
	public void setAllowOper(Integer allowOper) {
		this.allowOper = allowOper;
	}
	public Integer getManualOpen() {
		return manualOpen;
	}
	public void setManualOpen(Integer manualOpen) {
		this.manualOpen = manualOpen;
	}
	public Integer getTestMonth() {
		return testMonth;
	}
	public void setTestMonth(Integer testMonth) {
		this.testMonth = testMonth;
	}
	public Integer getTestState() {
		return testState;
	}
	public void setTestState(Integer testState) {
		this.testState = testState;
	}
	public BigDecimal getPrShowValue() {
		return PrShowValue;
	}
	public void setPrShowValue(BigDecimal prShowValue) {
		PrShowValue = prShowValue;
	}
	public Timestamp getPrCeDate() {
		return PrCeDate;
	}
	public void setPrCeDate(Timestamp prCeDate) {
		PrCeDate = prCeDate;
	}
	public BigDecimal getMonthCount() {
		return MonthCount;
	}
	public void setMonthCount(BigDecimal monthCount) {
		MonthCount = monthCount;
	}
	public Integer getStopType() {
		return StopType;
	}
	public void setStopType(Integer stopType) {
		StopType = stopType;
	}
	public BigDecimal getErrData() {
		return ErrData;
	}
	public void setErrData(BigDecimal errData) {
		ErrData = errData;
	}
	public Timestamp getErrDate() {
		return ErrDate;
	}
	public void setErrDate(Timestamp errDate) {
		ErrDate = errDate;
	}
	public Integer getGetMoneyType() {
		return GetMoneyType;
	}
	public void setGetMoneyType(Integer getMoneyType) {
		GetMoneyType = getMoneyType;
	}
	public BigDecimal getPrSaveValue() {
		return PrSaveValue;
	}
	public void setPrSaveValue(BigDecimal prSaveValue) {
		PrSaveValue = prSaveValue;
	}
	public Timestamp getPrSaveDate() {
		return PrSaveDate;
	}
	public void setPrSaveDate(Timestamp prSaveDate) {
		PrSaveDate = prSaveDate;
	}
	public Integer getUserCountType() {
		return UserCountType;
	}
	public void setUserCountType(Integer userCountType) {
		UserCountType = userCountType;
	}

	public DeviceRelation getDeviceRelation() {
		return deviceRelation;
	}
	public void setDeviceRelation(DeviceRelation deviceRelation) {
		this.deviceRelation = deviceRelation;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public Integer getUplinkType() {
		return uplinkType;
	}
	public void setUplinkType(Integer uplinkType) {
		this.uplinkType = uplinkType;
	}
	
	public Integer getIsMemBer() {
		return isMemBer;
	}
	public void setIsMemBer(Integer isMemBer) {
		this.isMemBer = isMemBer;
	}
	
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//--------------------------------------------------
	public int compareTo(Object o) {  
	    return this.id - ((Device) o).getId();  
	}
	/**
	 * 实现device对象的浅复制
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	} 
}
