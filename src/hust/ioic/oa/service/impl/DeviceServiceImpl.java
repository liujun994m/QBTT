package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.DeviceDao;
import hust.ioic.oa.domain.Device;

import org.springframework.stereotype.Service;

import hust.ioic.oa.service.DeviceService;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements
		DeviceService {
	@Resource
	private DeviceDao deviceDao;

	@Override
	public BaseDao<Device> getDao() {
		return deviceDao;
	}



	@Override
	public Device hasExist(String deviceNo) {
		return deviceDao.hasExist(deviceNo);
	}

	@Override
	public Device getByDeviceNo(String deviceNo) {
		return deviceDao.getDeviceByDeviceNo(deviceNo);
	}

	@Override
	public List<Device> getBigDevicesByAreaId(Integer areaID) {
		return deviceDao.getBigDevicesByAreaId(areaID);
	}

	@Override
	public List<Device> getByAreaId(Integer areaID) {
		return deviceDao.getByAreaId(areaID);
	}

	@Override
	public Device getByAddr(String iAddr) {
		return deviceDao.getByAddr(iAddr);
	}

	@Override
	public List<Device> getBigDevice() {
		return deviceDao.getBigDevice() ;
	}



	@Override
	public List<Device> getByUserNo(String userNo) {
		return deviceDao.getByUserNo(userNo);
	}



	@Override
	public List<Device> findByUserName(String userName) {
		
		return deviceDao.findByUserName(userName);
	}



	@Override
	public List<Device> findByUserAddr(String userAddr) {
		// TODO Auto-generated method stub
		return deviceDao.findByUserAddr(userAddr);
	}




}
