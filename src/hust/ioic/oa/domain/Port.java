package hust.ioic.oa.domain;

import java.util.HashSet;
import java.util.Set;


public class Port {
	private Integer id;
	private String name;
	private Integer type;
	private String collectionPortConfig;
	private String managePortConfig;

	private String initCmd;

	private Integer overtime;
	private Integer protocolType;
	private String remark;
	
	private Server server;
	private Integer portNum;
	private Set<Enterprise> enterprises=new HashSet<>();
	private Set<Center> centers=new HashSet<>();
	public Set<Enterprise> getEnterprises() {
		return enterprises;
	}
	public void setEnterprises(Set<Enterprise> enterprises) {
		this.enterprises = enterprises;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCollectionPortConfig() {
		return collectionPortConfig;
	}
	public void setCollectionPortConfig(String collectionPortConfig) {
		this.collectionPortConfig = collectionPortConfig;
	}
	public String getManagePortConfig() {
		return managePortConfig;
	}
	public void setManagePortConfig(String managePortConfig) {
		this.managePortConfig = managePortConfig;
	}

	public String getInitCmd() {
		return initCmd;
	}
	public void setInitCmd(String initCmd) {
		this.initCmd = initCmd;
	}

	public Integer getOvertime() {
		return overtime;
	}
	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}
	public Integer getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(Integer protocolType) {
		this.protocolType = protocolType;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPortNum() {
		return portNum;
	}
	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}
	public Set<Center> getCenters() {
		return centers;
	}
	public void setCenters(Set<Center> centers) {
		this.centers = centers;
	}
	

}
