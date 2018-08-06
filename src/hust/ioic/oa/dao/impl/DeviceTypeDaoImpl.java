package hust.ioic.oa.dao.impl;

import java.util.List;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.domain.DeviceType;
import hust.ioic.oa.dao.DeviceTypeDao;






import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class DeviceTypeDaoImpl extends BaseDaoImpl<DeviceType> implements DeviceTypeDao{

	
	@Override
	public List<DeviceType> getByName(String bigDeviceName) {
		return getSession()
				.createQuery("FROM DeviceType d WHERE d.name=? AND d.enprNo=?")
				.setParameter(0, bigDeviceName)
				.setParameter(1, getDaoEnprNo())
				.list();
	}

	/*@Override
	public List<DeviceType> findByEnprNo(String enprNo) {
	
		return getSession()
				.createQuery("FROM DeviceType d WHERE d.enprNo=?")
				.setParameter(0, enprNo)
				.list();
	}*/
}
