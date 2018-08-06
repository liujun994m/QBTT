package hust.ioic.oa.dao;

import java.util.Collection;
import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.Area;


public interface AreaDao extends BaseDao<Area>{
	List<Area> getTopArea();
	List<Area> findChildren(Integer parentId);
	
	Collection<Integer> getAllAreaIds();
}
