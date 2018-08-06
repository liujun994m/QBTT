package hust.ioic.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class Area {
	private Integer id;
	private String name;

	private String remark;
	
	private Area parent;
	private String enprNo;
	private Set<Operator> operators=new HashSet<>();
	private Set<Area> children=new HashSet<>();
	private Set<Center> centers=new HashSet<>();
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Area getParent() {
		return parent;
	}
	public void setParent(Area parent) {
		this.parent = parent;
	}
	public Set<Operator> getOperators() {
		return operators;
	}
	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}
	public Set<Area> getChildren() {
		return children;
	}
	public void setChildren(Set<Area> children) {
		this.children = children;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	public Set<Center> getCenters() {
		return centers;
	}
	public void setCenters(Set<Center> centers) {
		this.centers = centers;
	}

	
}
