package hust.ioic.oa.service;

import hust.ioic.oa.base.BaseServicePS;
import hust.ioic.oa.domain.Server;



public interface ServerService extends BaseServicePS<Server>{

	Server getByName(String name);
}