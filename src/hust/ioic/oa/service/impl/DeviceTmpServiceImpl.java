package hust.ioic.oa.service.impl;

import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import hust.ioic.oa.dao.DeviceTmpDao;
import hust.ioic.oa.domain.DeviceTmp;
import org.springframework.stereotype.Service;
import hust.ioic.oa.service.DeviceTmpService;

@Service
public class DeviceTmpServiceImpl implements DeviceTmpService {
	@Resource
	private DeviceTmpDao deviceTmpDao;

	/*
	 * public DeviceTmpDao getDeviceTmpDao() { return deviceTmpDao; }
	 * 
	 * public void setDeviceTmpDao(DeviceTmpDao deviceTmpDao) {
	 * this.deviceTmpDao = deviceTmpDao; }
	 */

	@Override
	public DeviceTmp getLastRecord(String iAddr, String month, String enprNo) {

		return deviceTmpDao.getLastRecord(iAddr, month, enprNo);
	}

	@Override
	public DeviceTmp getFirstRecord(String iAddr, String month, String enprNo) {

		return deviceTmpDao.getFirstRecord(iAddr, month, enprNo);
	}

	@Override
	public List<DeviceTmp> getAllRecords(String iAddr, String month,
			String enprNo) {

		return deviceTmpDao.getAllRecords(iAddr, month, enprNo);
	}

	@Override
	public Collection<String> getAllTables() {
		// TODO Auto-generated method stub
		return deviceTmpDao.getAllTables();
	}

	@Override
	public Collection<Integer> getDays(String month) {
		// TODO Auto-generated method stub
		return deviceTmpDao.getDays(month);
	}

	@Override
	public DeviceTmp getByDate(String iAddr, String month, Integer day) {
		// TODO Auto-generated method stub
		return deviceTmpDao.getByDate(iAddr, month, day);
	}

}
