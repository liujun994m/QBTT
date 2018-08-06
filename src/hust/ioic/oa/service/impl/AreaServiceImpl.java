package hust.ioic.oa.service.impl;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import hust.ioic.oa.domain.Area;
import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.service.AreaService;
import hust.ioic.oa.dao.AreaDao;

import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl extends BaseServiceImpl<Area> implements
		AreaService {
	@Resource
	private AreaDao areaDao;

	@Override
	public BaseDao<Area> getDao() {
		return areaDao;
	}

	/*
	 * public void setDao(AreaDao areaDao) { this.areaDao = areaDao; }
	 */

	@Override
	public List<Area> getTopArea() {
		return areaDao.getTopArea();
	}

	@Override
	public List<Area> findChildren(Integer parentId) {
		return areaDao.findChildren(parentId);
	}

	@Override
	public Collection<Integer> getAllAreaIds() {
		return areaDao.getAllAreaIds();
	}



}
