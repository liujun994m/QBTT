package hust.ioic.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 主数据库的企业信息表
 * @author lecky
 *
 */

public class Enterprise {
	private Integer id;
	private String enprNo;
	private String name;
	private Integer userCount;
	private String telNum;
	private String phoneNum;
	private String address;
	private String linkman;
	private Integer enprType;
	private String logoFile;
	private String databaseName;
	private String url;
	private String userName;
	private String passWord;
	private Set<Port> ports=new HashSet<Port>();
    private Integer fileLoadStatus;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public Integer getEnprType() {
		return enprType;
	}
	public void setEnprType(Integer enprType) {
		this.enprType = enprType;
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Set<Port> getPorts() {
		return ports;
	}
	public void setPorts(Set<Port> ports) {
		this.ports = ports;
	}
	public Integer getFileLoadStatus() {
		return fileLoadStatus;
	}
	public void setFileLoadStatus(Integer fileLoadStatus) {
		this.fileLoadStatus = fileLoadStatus;
	}

	
}
