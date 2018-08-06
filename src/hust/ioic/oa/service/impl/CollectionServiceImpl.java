package hust.ioic.oa.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.CollectionDao;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.service.CollectionService;
@Service
public class CollectionServiceImpl extends BaseServiceImpl<Collection> implements CollectionService {
	@Resource
private CollectionDao collectionDao;
	@Override

	public BaseDao<Collection> getDao() {
	return collectionDao;
}
	@Override
	public Collection getByName(String name) {
		
		return collectionDao.getByName(name);
	}







}
