package hust.ioic.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.dao.UncompeletCommandQueueDao;
import hust.ioic.oa.domain.UncompeletCommandQueue;
import hust.ioic.oa.utils.StaticConstant;
@Repository
@SuppressWarnings("unchecked")
public class UncompeletCommandQueueDaoImpl extends BaseDaoImpl<UncompeletCommandQueue> 
	implements UncompeletCommandQueueDao{

	@Override
	public List<UncompeletCommandQueue> getByUsername(String username) {
		return getSession().createQuery(
				"FROM UncompeletCommandQueue u WHERE u.Operator=? and u.enprNo=?")
				.setParameter(0, username)
				.setParameter(1,getDaoEnprNo())
				.list();
	}

	@Override
	public boolean getByDeviceAddr(String addr,String command) {
		if( null == getSession().createQuery(
				"FROM UncompeletCommandQueue u WHERE u.ContentValue3=? AND u.command=?")
				.setParameter(0, addr).setParameter(1, command)
				.uniqueResult())
			return true;
		return false;
	}

	@Override
	public boolean getByCenterAddr(String centerGprsNum, String command) {
		if( null == getSession().createQuery(
				"FROM UncompeletCommandQueue u WHERE u.ContentValue1=? AND u.command=?")
				.setParameter(0, centerGprsNum).setParameter(1, command)
				.uniqueResult())
			return true;
		
		return false;
	}

}
