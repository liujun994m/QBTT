package hust.ioic.oa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.dao.PlanCommandQueueDao;
import hust.ioic.oa.domain.PlanCommandQueue;
@Repository
@SuppressWarnings("unchecked")
public class PlanCommandQueueDaoImpl extends BaseDaoImpl<PlanCommandQueue> implements PlanCommandQueueDao{

	

	@Override
	public List<PlanCommandQueue> getByOperator(String operator) {
		Session session=openSession();
		session.flush();
		session.clear();
		// TODO Auto-generated method stub
		return  getSession().createQuery("from PlanCommandQueue p where p.enprNo=?  AND p.Operator=?  order by p.GenerateTime desc ")
				.setParameter(0, getDaoEnprNo()).
				setParameter(1, operator).
				list();
	}

}
