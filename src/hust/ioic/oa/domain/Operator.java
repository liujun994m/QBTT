package hust.ioic.oa.domain;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;


public class Operator {
	private Integer  id;
	private String username;
	private String gender;
	private String operatorNo;
	private String password;
 
	private String telNum;
	private String phoneNum;
	private String address;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Integer gropuID;
	private Integer status;

	private Role role;
	private Set<Area> areas=new HashSet<>();
	private String enprNo;

	public Integer getGropuID() {
		return gropuID;
	}
	public void setGropuID(Integer gropuID) {
		this.gropuID = gropuID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperatorNo() {
		return operatorNo;
	}
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Set<Area> getAreas() {
		return areas;
	}
	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}
	
	
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	public boolean hasFunctionByUrl(String privUrl) {
		if (isAdmin()) {
			return true;
		}

		// >> 去掉后面的参数
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// >> 去掉UI后缀
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}
		// 如果本URL不需要控制，则登录用户就可以使用
		Collection<String> allFunctionUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allFunctionUrls");

		if (!allFunctionUrls.contains(privUrl)) {
			return true;
		} else {
		
				for (Function priv : role.getFunctions()) {
					if (privUrl.equals(priv.getUrl())) {
						return true;
					}
				}
			
		return false;
		}
	}
	
	
	public boolean hasFunctionByName(String name) {
		if (isAdmin()) {
			return true;
		}
			for (Function priv : role.getFunctions()) {
				if (priv.getName().equals(name)) {
					return true;
				}
			}
		return false;
	}
	
	
	public boolean hasAreaByName(String name){
		if (isAdmin()) {
			return true;
		}
		
		
		for(Area area:getAreas()){
			if(area.getName().equals(name)){
				return true;
			}
		}
		return false;
		
	}

	// 判断用户是否为管理员
	public boolean isAdmin() {
		return "admin".equals(username);
	}
}
