package hust.ioic.oa.domain;

import java.util.HashSet;
import java.util.Set;


public class Role {
	private Integer id;
	private String name;
	private String description;
	private Set<Function> functions=new HashSet<>();
	private Set<Operator> operators=new HashSet<>();
	private String enprNo;


	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}
	public Set<Operator> getOperators() {
		return operators;
	}
	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	
}
