package hust.ioic.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.CollectionLogDao;
import hust.ioic.oa.domain.CollectionLog;
import hust.ioic.oa.service.CollectionLogService;
@Service
public class CollectionLogServiceImpl extends BaseServiceImpl<CollectionLog> implements
		CollectionLogService {

	@Resource
	private CollectionLogDao errorLogDao;

	@Override
	public BaseDao<CollectionLog> getDao() {
		return errorLogDao;
	}

}
