package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;

public interface DeviceRelationDao extends BaseDao<DeviceRelation> {

	// List<DeviceRelation> getByCenterId(int centerId);

	/**
	 * 根据采集器id查询关系表
	 * 
	 * @param collectionId
	 * @return
	 */
	List<DeviceRelation> getByCollectionId(int collectionId);

	/*
	 * 在删除集中器的时候， 根据集中器的id删除所有的关联关系
	 * 
	 * @param centerId
	 * 
	 * @return 返回该集中器下所有的水表id
	 */
	List<Integer> deleteByCenterId(int centerId);

	/**
	 * 根据采集器的id删除关联关系
	 * 
	 * @param collectionId
	 * @return 返回改采集器下所有的水表的id
	 */
	List<Integer> deleteByCollectionId(int collectionId);
	List<DeviceRelation> getByCenterId(int centerId);

	List<DeviceRelation> getDeviceByCenterId(Integer id);

}
