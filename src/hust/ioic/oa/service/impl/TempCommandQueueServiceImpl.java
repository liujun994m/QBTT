package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.TempCommandQueueDao;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.service.TempCommandQueueService;

@Service
public class TempCommandQueueServiceImpl extends
		BaseServiceImpl<TempCommandQueue> implements TempCommandQueueService {
	@Resource
	private TempCommandQueueDao tempCommandQueueDao;

	@Override
	public BaseDao<TempCommandQueue> getDao() {
		return tempCommandQueueDao;
	}

	@Override
	public List<TempCommandQueue> getByOperator(String command, String operator) {
		// TODO Auto-generated method stub
		return tempCommandQueueDao.getByOperator(command, operator);
	}

	@Override
	public List<TempCommandQueue> getNotByOperator(String command,
			String operator) {
		// TODO Auto-generated method stub
		return tempCommandQueueDao.getNotByOperator(command, operator);
	}

	@Override
	public TempCommandQueue getByHashCode(int hashCode) {
		return tempCommandQueueDao.getByHashCode(hashCode);
	}

	@Override
	public List<TempCommandQueue> findDayList() {
		// TODO Auto-generated method stub
		return tempCommandQueueDao.findDayList();
	}

}
