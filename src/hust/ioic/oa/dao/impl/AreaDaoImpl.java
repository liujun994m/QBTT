package hust.ioic.oa.dao.impl;

import hust.ioic.oa.base.BaseDaoImpl;
import hust.ioic.oa.dao.AreaDao;
import hust.ioic.oa.domain.Area;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;


import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao {

	@Override
	public List<Area> getTopArea() {
		return getSession()//
				.createQuery("FROM Area a WHERE a.parent IS NULL AND a.enprNo=?")
				.setParameter(0,getDaoEnprNo())//
				.list();
	}

	@Override
	public List<Area> findChildren(Integer parentId) {
		return getSession()//
				.createQuery("FROM Area a WHERE a.parent.id=? AND a.enprNo=?")//
				.setParameter(0, parentId)
				.setParameter(1,getDaoEnprNo())
				.list();
	}
	@Override
	public Collection<Integer> getAllAreaIds() {
		return getSession().createQuery("SELECT  a.id FROM Area a WHERE a.id IS NOT NULL ORDER BY a.id DESC AND a.enprNo=?")
				.setParameter(0,getDaoEnprNo())//
				.list();
	}

	
	
	
}
