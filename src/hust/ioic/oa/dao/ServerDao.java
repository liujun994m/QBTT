package hust.ioic.oa.dao;


import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.domain.Server;

public interface ServerDao extends BaseDaoPS<Server>{
Server getByName(String name);
	
}