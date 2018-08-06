package hust.ioic.oa.service;

import java.util.Collection;
import java.util.List;

import hust.ioic.oa.domain.Area;
import hust.ioic.oa.base.BaseService;


public interface AreaService extends BaseService<Area>{

	List<Area> getTopArea();

	List<Area> findChildren(Integer parentId);
	
	Collection<Integer> getAllAreaIds();


}
