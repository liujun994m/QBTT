package hust.ioic.oa.service.impl;



import java.util.List;

import javax.annotation.Resource;

import hust.ioic.oa.service.DeviceTypeService;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.DeviceTypeDao;
import hust.ioic.oa.domain.DeviceType;

@Service
public class DeviceTypeServiceImpl extends BaseServiceImpl<DeviceType> implements DeviceTypeService{
	@Resource
private 	DeviceTypeDao deviceTypeDao;
	@Override
	public BaseDao<DeviceType> getDao() {
	return deviceTypeDao;
}
/*public DeviceTypeDao getDeviceTypeDao() {
	return deviceTypeDao;
}

public void setDeviceTypeDao(DeviceTypeDao deviceTypeDao) {
	this.deviceTypeDao = deviceTypeDao;
}*/
	@Override
	public List<DeviceType> getByName(String bigDeviceName) {
		return deviceTypeDao.getByName(bigDeviceName);
	}


}
