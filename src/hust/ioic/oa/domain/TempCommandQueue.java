package hust.ioic.oa.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class TempCommandQueue {
	  
	
	
	private Integer id;
	private String command;
	private String Operator;
	private Timestamp GenerateTime;
	// 开始执行时间
	private Timestamp startExeTime;
	// 结束执行时间
	private Timestamp endExeTime;
	// 重复间隔时间
	private Integer retryIntervalTime;
		// 重复执行次数
	private Integer retryTimes;
	private Integer ExecuteTime;
	private Integer protocalTYpe;
	private String ContentValue1;
	private String ContentValue2;
	private String ContentValue3;
	private String ContentValue4;
	private String ContentValue5;
	private String ContentValue6;
	private String ContentValue7;
	private String ContentValue8;
	private String ContentValue9;
	private String ContentValue10;
	private Integer State=0;
	private String enprNo;
	private String ip;
	private Integer isLinkedLastCmd;
	private Integer hashCode;
	
	public Integer getHashCode() {
		return hashCode;
	}
	public void setHashCode(Integer hashCode) {
		this.hashCode = hashCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIsLinkedLastCmd() {
		return isLinkedLastCmd;
	}

	public void setIsLinkedLastCmd(Integer isLinkedLastCmd) {
		this.isLinkedLastCmd = isLinkedLastCmd;
	}

	/**
	 * 格式：202.114.21.26:5002
	 */
	private String port;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public Timestamp getGenerateTime() {
		return GenerateTime;
	}

	public void setGenerateTime(Timestamp generateTime) {
		GenerateTime = generateTime;
	}

	public Timestamp getStartExeTime() {
		return startExeTime;
	}

	public void setStartExeTime(Timestamp startExeTime) {
		this.startExeTime = startExeTime;
	}

	public Timestamp getEndExeTime() {
		return endExeTime;
	}

	public void setEndExeTime(Timestamp endExeTime) {
		this.endExeTime = endExeTime;
	}

	public Integer getRetryIntervalTime() {
		return retryIntervalTime;
	}

	public void setRetryIntervalTime(Integer retryIntervalTime) {
		this.retryIntervalTime = retryIntervalTime;
	}

	public Integer getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}



	public Integer getExecuteTime() {
		return ExecuteTime;
	}

	public void setExecuteTime(Integer executeTime) {
		ExecuteTime = executeTime;
	}

	public Integer getProtocalTYpe() {
		return protocalTYpe;
	}

	public void setProtocalTYpe(Integer protocalTYpe) {
		this.protocalTYpe = protocalTYpe;
	}

	public String getContentValue1() {
		return ContentValue1;
	}

	public void setContentValue1(String contentValue1) {
		ContentValue1 = contentValue1;
	}

	public String getContentValue2() {
		return ContentValue2;
	}

	public void setContentValue2(String contentValue2) {
		ContentValue2 = contentValue2;
	}

	public String getContentValue3() {
		return ContentValue3;
	}

	public void setContentValue3(String contentValue3) {
		ContentValue3 = contentValue3;
	}

	public String getContentValue4() {
		return ContentValue4;
	}

	public void setContentValue4(String contentValue4) {
		ContentValue4 = contentValue4;
	}

	public String getContentValue5() {
		return ContentValue5;
	}

	public void setContentValue5(String contentValue5) {
		ContentValue5 = contentValue5;
	}

	public String getContentValue6() {
		return ContentValue6;
	}

	public void setContentValue6(String contentValue6) {
		ContentValue6 = contentValue6;
	}

	public String getContentValue7() {
		return ContentValue7;
	}

	public void setContentValue7(String contentValue7) {
		ContentValue7 = contentValue7;
	}

	public String getContentValue8() {
		return ContentValue8;
	}

	public void setContentValue8(String contentValue8) {
		ContentValue8 = contentValue8;
	}

	public String getContentValue9() {
		return ContentValue9;
	}

	public void setContentValue9(String contentValue9) {
		ContentValue9 = contentValue9;
	}

	public String getContentValue10() {
		return ContentValue10;
	}

	public void setContentValue10(String contentValue10) {
		ContentValue10 = contentValue10;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}




	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public TempCommandQueue() {
		super();
	}
	/**
	 * 根据子类生成父类对象
	 * @param uncompeletCommandQueue
	 */
	public TempCommandQueue(TempCommandQueue tempCommandQueue) {
		this.setCommand(tempCommandQueue.getCommand());
		this.setContentValue1(tempCommandQueue.getContentValue1());
		this.setContentValue10(tempCommandQueue.getContentValue10());
		this.setContentValue2(tempCommandQueue.getContentValue2());
		this.setContentValue3(tempCommandQueue.getContentValue3());
		this.setContentValue4(tempCommandQueue.getContentValue4());
		this.setContentValue5(tempCommandQueue.getContentValue5());
		this.setContentValue6(tempCommandQueue.getContentValue6());
		this.setContentValue7(tempCommandQueue.getContentValue7());
		this.setContentValue8(tempCommandQueue.getContentValue8());
		this.setContentValue9(tempCommandQueue.getContentValue9());
		this.setEndExeTime(tempCommandQueue.getEndExeTime());
		this.setEnprNo(tempCommandQueue.getEnprNo());
		this.setExecuteTime(tempCommandQueue.getExecuteTime());
		this.setGenerateTime(tempCommandQueue.getGenerateTime());
		this.setHashCode(tempCommandQueue.getHashCode());
		this.setId(tempCommandQueue.getId());
		this.setIp(tempCommandQueue.getIp());
		this.setIsLinkedLastCmd(tempCommandQueue.getIsLinkedLastCmd());
		this.setOperator(tempCommandQueue.getOperator());
		this.setPort(tempCommandQueue.getPort());
		this.setProtocalTYpe(tempCommandQueue.getProtocalTYpe());
		this.setRetryIntervalTime(tempCommandQueue.getRetryIntervalTime());
		this.setRetryTimes(tempCommandQueue.getRetryTimes());
		this.setStartExeTime(tempCommandQueue.getStartExeTime());
		this.setState(tempCommandQueue.getState());
	}
	
}
