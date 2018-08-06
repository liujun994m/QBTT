package hust.ioic.oa.service.impl;

import javax.annotation.Resource;

import hust.ioic.oa.base.BaseServicePSImpl;
import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.dao.ServerDao;
import hust.ioic.oa.domain.Server;

import org.springframework.stereotype.Service;

import hust.ioic.oa.service.ServerService;
@Service
public class ServerServiceImpl extends BaseServicePSImpl<Server>  implements ServerService {
	@Resource
	private ServerDao serverDao;
	@Override
	public BaseDaoPS<Server> getDao() {
	return serverDao;
}
/*	public ServerDao getServerDao() {
		return serverDao;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}*/
	@Override
	public Server getByName(String name) {
		// TODO Auto-generated method stub
		return serverDao.getByName(name);
	}

}
