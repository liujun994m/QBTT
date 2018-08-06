package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.DeviceType;

public interface DeviceTypeDao extends BaseDao<DeviceType>{

	List<DeviceType> getByName(String bigDeviceName);


}