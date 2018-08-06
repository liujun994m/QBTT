package hust.ioic.oa.service;

import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.PlanCommandQueue;

public interface PlanCommandQueueService  extends BaseService<PlanCommandQueue>{
	List<PlanCommandQueue> getByOperator(String operator);
}
