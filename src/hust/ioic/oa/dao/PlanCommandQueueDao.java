package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.PlanCommandQueue;

public interface PlanCommandQueueDao extends BaseDao<PlanCommandQueue> {

	List<PlanCommandQueue> getByOperator(String operator);
}
