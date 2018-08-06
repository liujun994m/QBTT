package hust.ioic.oa.domain;

/**
 * 抄表方案管理类
 * 
 * @author nacl
 * 
 */
public class ReadScheme {
	private Integer id;
	/**
	 * 方案的编号
	 */
	private Integer schemeID;

	/**
	 * 方案的名称
	 */
	private String schemeName;
	
	
	/**
	 * 定时类型
	 */
	private Integer timerType;

	

	/**
	 * 开始日期，合法值1-31
	 */
	private  Integer beginDay;

	/**
	 * 抄表日之间间隔天数，为1时表示每天抄表
	 */
	private  Integer dayInterval;

	/**
	 * 每月内最大抄表天数,合法值：1-31
	 */
	private Integer readDayNum;

	/**
	 * 月末是否抄表,1：月末抄表 0：不一定，有可能抄表
	 */
	private Integer readInMonthEnd;

	/**
	 * 一天里首次抄表时间（小时）
	 */
	private Integer beginTime;

	/**
	 * 每天采集次数
	 */
	private Integer dayReadNum;

	/**
	 * 采集间隔（小时）
	 */
	private Integer hourInterval;

	/**
	 * 抄表是否指定时间
	 */
	private Integer isReadOnScheme;

	/**
	 * 重执行执行次数
	 */
	private Integer retryNum;

	/**
	 * 重复执行时的间隔时
	 */
	private Integer reTryInterval;

	/**
	 * 其它
	 */
	private Integer otherValue;

	/**
	 * 备注
	 */
	private String remark;
	private String enprNo;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getSchemeID() {
		return schemeID;
	}

	public void setSchemeID(Integer schemeID) {
		this.schemeID = schemeID;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Integer getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Integer beginDay) {
		this.beginDay = beginDay;
	}

	public Integer getDayInterval() {
		return dayInterval;
	}

	public void setDayInterval(Integer dayInterval) {
		this.dayInterval = dayInterval;
	}

	public Integer getReadDayNum() {
		return readDayNum;
	}

	public void setReadDayNum(Integer readDayNum) {
		this.readDayNum = readDayNum;
	}

	public Integer getReadInMonthEnd() {
		return readInMonthEnd;
	}

	public void setReadInMonthEnd(Integer readInMonthEnd) {
		this.readInMonthEnd = readInMonthEnd;
	}

	public Integer getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getDayReadNum() {
		return dayReadNum;
	}

	public void setDayReadNum(Integer dayReadNum) {
		this.dayReadNum = dayReadNum;
	}

	public Integer getHourInterval() {
		return hourInterval;
	}

	public void setHourInterval(Integer hourInterval) {
		this.hourInterval = hourInterval;
	}

	public Integer getIsReadOnScheme() {
		return isReadOnScheme;
	}

	public void setIsReadOnScheme(Integer isReadOnScheme) {
		this.isReadOnScheme = isReadOnScheme;
	}

	public Integer getRetryNum() {
		return retryNum;
	}

	public void setRetryNum(Integer retryNum) {
		this.retryNum = retryNum;
	}

	public Integer getReTryInterval() {
		return reTryInterval;
	}

	public void setReTryInterval(Integer reTryInterval) {
		this.reTryInterval = reTryInterval;
	}

	public Integer getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(Integer otherValue) {
		this.otherValue = otherValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getTimerType() {
		return timerType;
	}

	public void setTimerType(Integer timerType) {
		this.timerType = timerType;
	}

	public String getEnprNo() {
		return enprNo;
	}

	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	
}
