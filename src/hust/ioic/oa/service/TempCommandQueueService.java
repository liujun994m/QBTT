package hust.ioic.oa.service;

import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.TempCommandQueue;

public interface TempCommandQueueService extends BaseService<TempCommandQueue> {

	List<TempCommandQueue> getByOperator(String command,String operator);
	List<TempCommandQueue> getNotByOperator(String command,String operator);
	TempCommandQueue getByHashCode(int hashCode);
	List<TempCommandQueue> findDayList();
}
