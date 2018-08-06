package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.DeviceRelationDao;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;

import org.springframework.stereotype.Service;

import hust.ioic.oa.service.DeviceRelationService;

@Service

public class DeviceRelationServiceImpl extends BaseServiceImpl<DeviceRelation>
		implements DeviceRelationService {
	@Resource
	private DeviceRelationDao deviceRelationDao;

	@Override
	public BaseDao<DeviceRelation> getDao() {
		return deviceRelationDao;
	}

	/*
	 * @Override public List<DeviceRelation> getByCenterId(int centerId) {
	 * 
	 * return deviceRelationDao.getByCenterId(centerId); }
	 */
	@Override
	public List<DeviceRelation> getByCollectionId(int collectionId) {

		return deviceRelationDao.getByCollectionId(collectionId);
	}

	@Override
	public List<Integer> deleteByCenterId(int centerId) {

		return deviceRelationDao.deleteByCenterId(centerId);
	}

	@Override
	public List<Integer> deleteByCollectionId(int collectionId) {
		return deviceRelationDao.deleteByCollectionId(collectionId);
	}

	@Override
	public List<DeviceRelation> getByCenterId(int centerId) {
		
		return deviceRelationDao.getByCenterId(centerId);
	}

	@Override
	public List<DeviceRelation> getDeviceByCenterId(Integer id) {
		return deviceRelationDao.getDeviceByCenterId(id);
	}


}
