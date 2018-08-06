package hust.ioic.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.dao.DeviceDao;
import hust.ioic.oa.utils.StaticConstant;
@Repository
@SuppressWarnings("unchecked")
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements DeviceDao{
	
	/**
	 * 查询用户id下的水表列表，去除已经弃用的水表，即status=1的
	 */


	/**
	 * deviceNo为唯一，
	 */
	@Override
	public Device hasExist(String deviceNo) {
		return (Device) getSession()
				.createQuery("FROM Device d WHERE d.deviceNo=? AND d.enprNo=?")
				.setParameter(0, deviceNo)
				.setParameter(1, getDaoEnprNo())
				.uniqueResult();
	}

	/**
	 * 去除已弃用的水表
	 */
	@Override
	public Device getDeviceByDeviceNo(String deviceNo) {
		return (Device) getSession().createQuery(
				"FROM Device d WHERE d.deviceNo=? AND d.status=? AND d.enprNo=?")
				.setParameter(0, deviceNo)
				.setParameter(1, StaticConstant.DEVICE_STATUS_USE)
				.setParameter(2, getDaoEnprNo())
				.uniqueResult();
	}

	/**
	 * 查询：查询到该区域id下对应的所有大表
	 * HQL子查询
	 * StaticConstant.BIG_DEVICE_NAME：大表名称
	 * 去除已经弃用的水表
	 */
	@Override
	public List<Device> getBigDevicesByAreaId(Integer areaID) {
		return getSession().createQuery(
				"FROM Device d WHERE d.areaId=? AND d.status=? AND d.enprNo=? AND d.BigDeviceFlag=?")
				.setParameter(0, areaID)
				.setParameter(1, StaticConstant.DEVICE_STATUS_USE)
				.setParameter(2, getDaoEnprNo())
				.setParameter(3, StaticConstant.BIG_DEVICE_NUM)
				.list();
	}

	/**
	 * 查询：查询该areaId下对应的所有水表对象
	 * HQL子查询
	 * StaticConstant.SMALL_DEVICE_NAME：小表名称
	 * 去除已经弃用的水表
	 */
	@Override
	public List<Device> getByAreaId(Integer areaID) {
		return getSession().createQuery(
				"FROM Device d WHERE d.areaId=? AND d.status=? AND d.enprNo=?")
				.setParameter(0, areaID)
				.setParameter(1, StaticConstant.DEVICE_STATUS_USE)
				.setParameter(2, getDaoEnprNo())
				.list();
	}

	@Override
	public Device getByAddr(String iAddr) {
		return (Device) getSession().createQuery("FROM Device d WHERE d.addr=? AND d.enprNo=?")
				.setParameter(0, iAddr)
				.setParameter(1, getDaoEnprNo())
				.uniqueResult();
	}

	@Override
	public List<Device> getBigDevice() {
		return getSession()
				.createQuery("FROM Device d WHERE d.BigDeviceFlag=? AND d.enprNo=?")
				.setParameter(0, StaticConstant.BIG_DEVICE_NUM)
				.setParameter(1, getDaoEnprNo())
				.list();
	}

	@Override
	public List<Device> getByUserNo(String userNo) {
		
		return getSession()
				.createQuery("FROM Device d WHERE d.userNo=? AND d.enprNo=?")
				.setParameter(0, userNo)
				.setParameter(1, getDaoEnprNo())
				.list();
	}

	@Override
	public List<Device> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery("FROM Device d WHERE d.userName=? AND d.enprNo=?")
				.setParameter(0, userName)
				.setParameter(1, getDaoEnprNo())
				.list();
	}

	@Override
	public List<Device> findByUserAddr(String userAddr) {
		// TODO Auto-generated method stub
		 return getSession()
				.createQuery("FROM Device d WHERE d.userAddr like ? AND d.enprNo=?")
				.setParameter(0, userAddr+"%")
				.setParameter(1, getDaoEnprNo())
				.list();
	}
	/*@Override
	public List<Device> findByEnprNo(String enprNo) {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery("FROM Device d WHERE d.enprNo=?")
				.setParameter(0, enprNo)
				.list();
	}*/
	

}
