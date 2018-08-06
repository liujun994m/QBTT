package hust.ioic.oa.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.TempCommandQueue;
public interface TempCommandQueueDao extends BaseDao<TempCommandQueue> {

	List<TempCommandQueue> getByOperator(String command,String operator);
	List<TempCommandQueue> getNotByOperator(String command,String operator);
	TempCommandQueue getByHashCode(int hashCode);
	List<TempCommandQueue> findDayList();
}
