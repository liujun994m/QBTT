package hust.ioic.oa.dao;

import java.util.Collection;
import java.util.List;



import hust.ioic.oa.domain.DeviceTmp;

public interface DeviceTmpDao {
	
	
	DeviceTmp getLastRecord(String iAddr,String month,String enprNo);
	DeviceTmp getFirstRecord(String iAddr,String month,String enprNo);
	List<DeviceTmp> getAllRecords(String iAddr,String month,String enprNo);
	Collection<String> getAllTables();
	Collection<Integer> getDays(String month);
/*	DeviceTmp getById(Integer id);
	List<DeviceTmp> getByIds(Integer[] ids);
	List<DeviceTmp> findAll();*/
	DeviceTmp getByDate(String iAddr,String month,Integer day);
	
	
	/*
	 * 查询某个表的某些水表读书
	 */
//	List<Device> getRecordsByIaddr(String tableName,List<String> iAddrList);
	
	
	
	
}
