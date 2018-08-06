package hust.ioic.oa.dao;


import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.Collection;


public interface CollectionDao extends BaseDao<Collection>{
	List<Collection> findById(Integer centerId);
	Collection getByName(String name);

}
