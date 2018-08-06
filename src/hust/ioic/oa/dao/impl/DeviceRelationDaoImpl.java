package hust.ioic.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.dao.DeviceRelationDao;
import hust.ioic.oa.utils.StaticConstant;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class DeviceRelationDaoImpl extends BaseDaoImpl<DeviceRelation>
		implements DeviceRelationDao {

	@Override
	public List<Integer> deleteByCenterId(int centerId) {
		String sql = "select id from t_deviceRelation  t where t.centerId =? AND t.enprNo=?";
		List<Integer> ids = getSession().createSQLQuery(sql)
				.setParameter(0, centerId)
				.setParameter(1, getDaoEnprNo())
				.list();
		List<Integer> devices = getSession().createSQLQuery("select deviceId from t_deviceRelation  t where t.centerId =? AND t.enprNo=?")
				.setParameter(0, centerId)
				.setParameter(1, getDaoEnprNo())
				.list();
		for (int id : ids)
			delete(id);
		return devices;
	}

	@Override
	public List<Integer> deleteByCollectionId(int collectionId) {
		String sql = "select id from t_deviceRelation  t where t.collectionId =? AND t.enprNo=?";
		List<Integer> ids = getSession().createSQLQuery(sql)
				.setParameter(0, collectionId)
				.setParameter(1, getDaoEnprNo())
				.list();
		List<Integer> devices = getSession().createSQLQuery("select deviceId from t_deviceRelation  t where t.collectionId =? AND t.enprNo=?")
				.setParameter(0, collectionId)
				.setParameter(1,getDaoEnprNo())
				.list();

		for (int id : ids)
			delete(id);
		return devices;
	}

	
	  @Override 
	  public List<DeviceRelation> getByCenterId(int centerId) {
	  return getSession().createQuery("from DeviceRelation d where d.centerId=? AND d.enprNo=?")
			  .setParameter(0, centerId)
			  .setParameter(1, getDaoEnprNo())
			  .list();	  
	 }
	 
	@Override
	public List<DeviceRelation> getByCollectionId(int collectionId) {
		return getSession()
				.createQuery("from DeviceRelation d where d.collectionId=? AND d.enprNo=?")
				.setParameter(0, collectionId)
				.setParameter(1, getDaoEnprNo())
				.list();
	}

	@Override
	public List<DeviceRelation> getDeviceByCenterId(Integer id) {
		return getSession().createQuery("from DeviceRelation d where d.centerId=? AND d.collectionId=? AND d.enprNo=?")
				  .setParameter(0, id)
				  .setParameter(1, StaticConstant.NO_COLLECTION)
				  .setParameter(2, getDaoEnprNo())
				  .list();	
	}


}
