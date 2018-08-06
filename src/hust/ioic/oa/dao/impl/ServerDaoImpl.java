package hust.ioic.oa.dao.impl;

import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoPSImpl;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.dao.ServerDao;
@Repository
public class ServerDaoImpl extends BaseDaoPSImpl<Server> implements ServerDao{

	@Override
	public Server getByName(String name) {
		// TODO Auto-generated method stub
		return (Server)getSession().createQuery("FROM Server s WHERE s.name=?")
				.setParameter(0, name)
				.uniqueResult();
	}



}
