package hust.ioic.oa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Function implements Comparable{
	private Integer id;
	private String url;
	private String name;
	private Function parent;
	private Set<Function> children=new HashSet<Function>();
	private Set<Role> roles=new HashSet<Role>();
	
	
	
	public Function(String name, String url, Function parent) {
		super();
		this.name = name;
		this.url = url;
		this.parent = parent;
	}


	public Function() {
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Function getParent() {
		return parent;
	}


	public void setParent(Function parent) {
		this.parent = parent;
	}


	public Set<Function> getChildren() {
		return children;
	}


	public void setChildren(Set<Function> children) {
		this.children = children;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public int compareTo(Object o) {  
	    return this.id - ((Function) o).getId();  
	} 
	

}
