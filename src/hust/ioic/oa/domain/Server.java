package hust.ioic.oa.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class Server {
	private Integer id;
	private String name;
	private Timestamp lastLoginTime;
	private Integer runStatus;
	private Integer overtime;
	private String localIp;
	private Set<Port> ports= new HashSet<>();
	private Set<Enterprise>enterprises=new HashSet<>();
	
	public Set<Enterprise> getEnterprises() {
		return enterprises;
	}
	public void setEnterprises(Set<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}
	public String getLocalIp() {
		return localIp;
	}
	public void setLocalIp(String localIp) {
		this.localIp = localIp;
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
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}
	public Integer getOvertime() {
		return overtime;
	}
	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}

	
	

	public Set<Port> getPorts() {
		return ports;
	}
	public void setPorts(Set<Port> ports) {
		this.ports = ports;
	}
/*	public boolean hasPortByName(String name){

		for(Port port:getPorts()){
			if(port.getName().equals(name)){
				return true;
			}
		}
		return false;
		
	}*/


}
