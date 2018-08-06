package hust.ioic.oa.service;


import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.DeviceType;



public interface DeviceTypeService extends BaseService<DeviceType>{

	List<DeviceType> getByName(String bigDeviceName);


	
}