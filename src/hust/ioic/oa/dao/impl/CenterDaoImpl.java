package hust.ioic.oa.dao.impl;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.dao.CenterDao;
import hust.ioic.oa.domain.Center;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings("unchecked")
public class CenterDaoImpl extends BaseDaoImpl<Center> implements CenterDao {

	/*@Override
	public List<Center> findByEnprNo(String enprNo) {
		return getSession()
				.createQuery("FROM Center c WHERE c.enprNo=? ")
				.setParameter(0, enprNo)//
				.list();
	}*/

	@Override
	public Center getByGprs(String gprs) {
		
		return (Center)getSession()
				.createQuery("FROM Center c WHERE c.gprsNum=? AND c.enprNo=?")
				.setParameter(0, gprs)
				.setParameter(1, getDaoEnprNo())
				.uniqueResult();
	}




}
