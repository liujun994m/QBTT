package hust.ioic.oa.dao.impl;

import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoPSImpl;
import hust.ioic.oa.dao.EnterpriseDao;
import hust.ioic.oa.domain.Enterprise;

@Repository
public class EnterpriseDaoImpl extends BaseDaoPSImpl<Enterprise> implements EnterpriseDao{

/*	@Override
	public Enterprise getByName(String company) {
		return (Enterprise) getSession().
				createQuery("FROM Enterprise e WHERE e.name=?")
				.setParameter(0, company)
				.uniqueResult();
	}
*/
	@Override
	public Enterprise getByEnprNo(String enprNo) {
		
		return (Enterprise) getSession().
				createQuery("FROM Enterprise e WHERE e.enprNo=?")
				.setParameter(0, enprNo)
				.uniqueResult();
	}
}
