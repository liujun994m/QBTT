package hust.ioic.oa.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.annotations.Expose;

public class Center implements Comparable,Serializable {
	@Expose private Integer id;
	@Expose private String name;
	@Expose private Integer protocolType;
	@Expose private String gprsNum;
	@Expose private Timestamp readTime;
	@Expose private Integer readPeriod;
	@Expose private Integer readType;
	@Expose private Integer runStatue=0;
	@Expose private Integer isUse;
	@Expose private String config;
	@Expose private String enprNo;
    private Port port;
	@Expose private String remark;
	@Expose private String modem;
	@Expose private Integer uplinkType;
	private Area area;
	private Set<Collection>collections = new HashSet<>();
	/**
	 * 集中器对应的方案id
	 */
	@Expose private Integer readSchemeId;
	/**
	 * 标示集中器是否自动采集
	 */
	@Expose private Integer isAutoCollection;
	
	
	/**
	 * 安装地址
	 */
	@Expose private String installAddr;
	
	public Integer getIsAutoCollection() {
		return isAutoCollection;
	}
	public void setIsAutoCollection(Integer isAutoCollection) {
		this.isAutoCollection = isAutoCollection;
	}
	public Set<Collection> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	
	


	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	public Integer getReadSchemeId() {
		return readSchemeId;
	}
	public void setReadSchemeId(Integer readSchemeId) {
		this.readSchemeId = readSchemeId;
	}
	


	public Port getPort() {
		return port;
	}
	public void setPort(Port port) {
		this.port = port;
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

	public Integer getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(Integer protocolType) {
		this.protocolType = protocolType;
	}

	public String getGprsNum() {
		return gprsNum;
	}
	public void setGprsNum(String gprsNum) {
		this.gprsNum = gprsNum;
	}
	public Timestamp getReadTime() {
		return readTime;
	}
	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

	public Integer getReadPeriod() {
		return readPeriod;
	}

	public void setReadPeriod(Integer readPeriod) {
		this.readPeriod = readPeriod;
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
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	
public String getModem() {
		return modem;
	}
	public void setModem(String modem) {
		this.modem = modem;
	}

	public Integer getUplinkType() {
		return uplinkType;
	}
	public void setUplinkType(Integer uplinkType) {
		this.uplinkType = uplinkType;
	}
	/*	public boolean hasCollectionByName(String name){

		for(DeviceRelation deviceRelation:getDeviceRelations()){
			
			if(deviceRelation.getCollection().getName().equals(name)){
				return true;
			}
		}
		return false;
	}*/
	public int compareTo(Object o) {  
	    return this.id - ((Center) o).getId();  
	}
	public String getInstallAddr() {
		return installAddr;
	}
	public void setInstallAddr(String installAddr) {
		this.installAddr = installAddr;
	} 
}

