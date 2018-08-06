package hust.ioic.oa.domain;

import java.sql.Timestamp;

public class CommandState {
	
	private Integer id;
	private String ip;
	private String command;
	private String Operator;
	private Timestamp GenerateTime;
	private Timestamp ExecuteTime;
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
	private Integer State;
	private String enprNo;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public Timestamp getExecuteTime() {
		return ExecuteTime;
	}
	public void setExecuteTime(Timestamp executeTime) {
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



	

}
