package hust.ioic.oa.dao.impl;


import java.util.List;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.dao.OperatorDao;
import hust.ioic.oa.utils.StaticConstant;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
@Repository
@SuppressWarnings("unchecked")
public class OperatorDaoImpl extends BaseDaoImpl<Operator> implements OperatorDao {

	@Override
	public Operator findByUsernameAndPassowrd(String username, String password) {
		String md5Digest = DigestUtils.md5Hex(password);
		
		return (Operator) getSession()//
				.createQuery("From Operator o WHERE o.username=? AND o.password=? AND o.status=? AND o.enprNo=?")//
				.setParameter(0, username)//
				.setParameter(1, md5Digest)//
				.setParameter(2, StaticConstant.OPERATOR_STATUS_USE)
				.setParameter(3, getDaoEnprNo())
				.uniqueResult();
	}

	@Override
	public List<Operator> findAllByStatus() {
		
		return getSession()//
				.createQuery("From Operator o WHERE o.status=? AND o.enprNo=? ")//
				.setParameter(0, StaticConstant.OPERATOR_STATUS_USE)//
				.setParameter(1, getDaoEnprNo())
				.list();
	}

	@Override
	public List<String> getAllOperatorNos() {
		
		return getSession()
			   .createQuery("SELECT  o.operatorNo FROM Operator o WHERE o.username IS NOT NULL AND o.enprNo=?")
			   .setParameter(0, getDaoEnprNo())
			   .list();
	}

	

}

