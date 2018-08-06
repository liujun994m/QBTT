package hust.ioic.oa.dao.impl;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.dao.CollectionDao;
import hust.ioic.oa.domain.Collection;

import java.util.List;





import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class CollectionDaoImpl extends BaseDaoImpl<Collection> implements CollectionDao {

	@Override
	public List<Collection> findById(Integer centerId) {		
		return getSession()//
				.createQuery("FROM Collection c WHERE c.centerId=? AND c.enprNo=?")
				.setParameter(0, centerId)
				.setParameter(1, getDaoEnprNo())
				.list();
	
	}

	@Override
	public Collection getByName(String name) {
		
		return (Collection)getSession()//
				.createQuery("FROM Collection c WHERE c.name=? AND c.enprNo=?")
				.setParameter(0, name)
				.setParameter(1, getDaoEnprNo())
				.uniqueResult();
	}

	
	/*@Override
	public List<Collection> findByEnprNo(String enprNo) {
	
		return getSession()//
				.createQuery("FROM Collection c WHERE c.enprNo=?")
				.setParameter(0, enprNo)//
				.list();
	}*/
}
