package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.Device;

public interface DeviceDao extends BaseDao<Device>{

	

	Device hasExist(String deviceNo);

	Device getDeviceByDeviceNo(String deviceNo);

	/**
	 * HQL子查询，查询指定区域下的大表对象
	 * @param areaID
	 * @return
	 */
	List<Device> getBigDevicesByAreaId(Integer areaID);

	List<Device> getByAreaId(Integer areaID);

	Device getByAddr(String iAddr);

	List<Device> getBigDevice();
	List<Device> getByUserNo(String userNo);
	List<Device> findByUserName(String userName);
	List<Device> findByUserAddr(String userAddr);
}
