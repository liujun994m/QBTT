package hust.ioic.oa.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.dao.TempCommandQueueDao;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.qilin.utils.GetDate;

@Repository
@SuppressWarnings("unchecked")
public class TempCommandQueueDaoImpl extends BaseDaoImpl<TempCommandQueue>
		implements TempCommandQueueDao {

	@Override
	public List<TempCommandQueue> getByOperator(String command, String operator) {
		GetDate date = new GetDate();
		Timestamp today = date.getCurrentDay();
		Timestamp tomorrowday = date.getTomorrowDay();
		return getSession()
				.createQuery(
						"from TempCommandQueue t where t.enprNo=?  AND t.command=? AND t.Operator=? AND t.GenerateTime between ? AND ? order by t.GenerateTime desc ")
				.setParameter(0, getDaoEnprNo()).setParameter(1, command)
				.setParameter(2, operator).setParameter(3, today)
				.setParameter(4, tomorrowday).list();
	}

	@Override
	public List<TempCommandQueue> getNotByOperator(String command,
			String operator) {
		GetDate date = new GetDate();
		Timestamp today = date.getCurrentDay();
		Timestamp tomorrowday = date.getTomorrowDay();
		return getSession()
				.createQuery(
						"from TempCommandQueue t where t.enprNo=?  AND t.command=? AND t.Operator<>? AND t.GenerateTime between ? AND ? order by t.GenerateTime desc ")
				.setParameter(0, getDaoEnprNo()).setParameter(1, command)
				.setParameter(2, operator).setParameter(3, today)
				.setParameter(4, tomorrowday).list();

	}

	@Override
	public TempCommandQueue getByHashCode(int hashCode) {

		Session session = openSession();
		TempCommandQueue tempCommandQueue = (TempCommandQueue) session
				.createQuery("from TempCommandQueue t where   t.hashCode=?")
				.setParameter(0, hashCode).list().get(0);
		session.flush();
		session.close();
		return tempCommandQueue;

	}

	@Override
	public List<TempCommandQueue> findDayList() {
		GetDate date = new GetDate();
		Timestamp today = date.getCurrentDay();
		Timestamp tomorrowday = date.getTomorrowDay();
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"from TempCommandQueue t where t.enprNo=?   AND t.GenerateTime between ? AND ? order by t.GenerateTime desc ")
				.setParameter(0, getDaoEnprNo()).setParameter(1, today)
				.setParameter(2, tomorrowday).list();
	}

}
