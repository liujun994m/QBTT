package hust.ioic.oa.service;

import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.Device;



public interface DeviceService extends BaseService<Device> {

	/**
	 * 根据用户id查找device
	 * @param userid
	 * @return
	 */


	Device hasExist(String deviceNo);

	Device getByDeviceNo(String deviceNo);

	List<Device> getBigDevicesByAreaId(Integer areaID);

	List<Device> getByAreaId(Integer areaID);

	Device getByAddr(String iAddr);

	List<Device> getBigDevice();
	List<Device> getByUserNo(String userNo);
	List<Device> findByUserName(String userName);
	List<Device> findByUserAddr(String userAddr);
}
