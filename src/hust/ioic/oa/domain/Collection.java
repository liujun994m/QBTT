package hust.ioic.oa.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Collection {
	private Integer id;
	private String name;
	private String address;
	private Timestamp readTime;
	private Integer readCount;
	private Integer readType;
	private Integer runStatue;
	private Integer isUse;
	private String remark;
	private Integer uplinkType;
	private Center center;
	private String enprNo;
	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
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


	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getReadTime() {
		return readTime;
	}

	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getReadType() {
		return readType;
	}

	public void setReadType(Integer readType) {
		this.readType = readType;
	}

	public Integer getRunStatue() {
		return runStatue;
	}

	public void setRunStatue(Integer runStatue) {
		this.runStatue = runStatue;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public Integer getUplinkType() {
		return uplinkType;
	}

	public void setUplinkType(Integer uplinkType) {
		this.uplinkType = uplinkType;
	}

	public String getEnprNo() {
		return enprNo;
	}

	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}

	

}
