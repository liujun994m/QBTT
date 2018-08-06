package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.domain.Port;

public interface PortDao extends BaseDaoPS<Port>{

	List<Port> findById(Integer serverId);
	Port getByName(String name);
	
}