package hust.ioic.oa.service;



import hust.ioic.oa.base.BaseServicePS;
import hust.ioic.oa.domain.Port;



public interface PortService extends BaseServicePS<Port> {
	Port getByName(String name);
}