package hust.ioic.oa.domain;

import java.sql.Timestamp;

/**
 * 采集服务器log
 * 
 * @author lecky
 * 
 */
public class CollectionLog {
	private Integer id;
	private String command;
	private Integer LogType;
	private Integer commandType;
	private String CenterNo;
	private Timestamp CommandGernrateTime;
	private Timestamp LogGenerateTime;
	private String detail;
	private String enprNo;
	public Integer getLogType() {
		return LogType;
	}

	public void setLogType(Integer logType) {
		LogType = logType;
	}

	public Integer getCommandType() {
		return commandType;
	}

	public void setCommandType(Integer commandType) {
		this.commandType = commandType;
	}

	public String getCenterNo() {
		return CenterNo;
	}

	public void setCenterNo(String centerNo) {
		CenterNo = centerNo;
	}

	public Timestamp getCommandGernrateTime() {
		return CommandGernrateTime;
	}

	public void setCommandGernrateTime(Timestamp commandGernrateTime) {
		CommandGernrateTime = commandGernrateTime;
	}

	public Timestamp getLogGenerateTime() {
		return LogGenerateTime;
	}

	public void setLogGenerateTime(Timestamp logGenerateTime) {
		LogGenerateTime = logGenerateTime;
	}

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEnprNo() {
		return enprNo;
	}

	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}

}
