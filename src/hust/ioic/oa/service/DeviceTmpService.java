package hust.ioic.oa.service;

import java.util.Collection;
import java.util.List;

import hust.ioic.oa.domain.DeviceTmp;






public interface DeviceTmpService  {

	DeviceTmp getLastRecord(String iAddr,String month,String enprNo);
	DeviceTmp getFirstRecord(String iAddr,String month,String enprNo);
	List<DeviceTmp> getAllRecords(String iAddr,String month,String enprNo);
	Collection<String> getAllTables();
	Collection<Integer> getDays(String month);
	DeviceTmp getByDate(String iAddr,String month,Integer day);
}
