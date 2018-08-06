package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.PlanCommandQueueDao;
import hust.ioic.oa.domain.PlanCommandQueue;
import hust.ioic.oa.service.PlanCommandQueueService;
@Service
public class PlanCommandQueueServiceImpl extends BaseServiceImpl<PlanCommandQueue> implements PlanCommandQueueService{
    @Resource
	private PlanCommandQueueDao planCommandQueueDao;
	@Override
	public BaseDao<PlanCommandQueue> getDao() {
		return planCommandQueueDao;
	}
	


	@Override
	public List<PlanCommandQueue> getByOperator(String operator) {
		// TODO Auto-generated method stub
		 return planCommandQueueDao.getByOperator(operator);
	}

}
