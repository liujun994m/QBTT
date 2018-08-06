package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import hust.ioic.oa.service.OperatorService;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.OperatorDao;
import hust.ioic.oa.domain.Operator;

@Service
public class OperatorServiceImpl extends BaseServiceImpl<Operator> implements
		OperatorService {
	@Resource
	private OperatorDao operatorDao;

	@Override
	public BaseDao<Operator> getDao() {
		return operatorDao;
	}

	/*
	 * public OperatorDao getOperatorDao() { return operatorDao; } public void
	 * setOperatorDao(OperatorDao operatorDao) { this.operatorDao = operatorDao;
	 * }
	 */
	@Override
	public Operator findByUsernameAndPassowrd(String username, String password) {
		return (Operator) operatorDao.findByUsernameAndPassowrd(username,password);
	}

	@Override
	public List<Operator> findAllByStatus() {

		return operatorDao.findAllByStatus();
	}

	@Override
	public List<String> getAllOperatorNos() {

		return operatorDao.getAllOperatorNos();
	}

}
