package hust.ioic.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.LogDao;
import hust.ioic.oa.domain.Log;
import hust.ioic.oa.service.LogService;
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
	@Resource
	private LogDao logDao;

	@Override
	public BaseDao<Log> getDao() {
		return logDao;
	}

}
