package hust.ioic.oa.domain;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

/**
 * log日志Bean
 * @author lecky
 *
 */
public class Log {
	private Integer id;
	private Timestamp creatTime;
	private String logger;
	private String enprNo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	} 
	
}
