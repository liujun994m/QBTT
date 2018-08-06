package hust.ioic.oa.service.impl;

import javax.annotation.Resource;

import hust.ioic.oa.base.BaseServicePSImpl;
import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.dao.PortDao;
import hust.ioic.oa.domain.Port;

import org.springframework.stereotype.Service;

import hust.ioic.oa.service.PortService;
@Service
public class PortServiceImpl extends BaseServicePSImpl<Port>  implements PortService {
	@Resource
	private PortDao portDao;
	@Override
	public BaseDaoPS<Port> getDao() {
	return portDao;
}
/*	public PortDao getPortDao() {
		return portDao;
	}

	public void setPortDao(PortDao portDao) {
		this.portDao = portDao;
	}*/
	@Override
	public Port getByName(String name) {
		// TODO Auto-generated method stub
		return portDao.getByName(name);
	}

}
