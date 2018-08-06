package hust.ioic.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoPSImpl;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.dao.PortDao;
@Repository
@SuppressWarnings("unchecked")
public class PortDaoImpl extends BaseDaoPSImpl<Port> implements PortDao{
	@Override
	public List<Port> findById(Integer serverId) {
		return getSession().createQuery("FROM Port p WHERE p.serverId=?")
				.setParameter(0, serverId)//
				.list();
	}

	@Override
	public Port getByName(String name) {
		
		return (Port)getSession().createQuery("FROM Port p WHERE p.name=?")
				.setParameter(0, name)
				.uniqueResult();
	}



}
