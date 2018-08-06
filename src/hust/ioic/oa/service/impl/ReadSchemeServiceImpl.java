package hust.ioic.oa.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.ReadSchemeDao;
import hust.ioic.oa.domain.ReadScheme;
import hust.ioic.oa.service.ReadSchemeService;

@Service
public class ReadSchemeServiceImpl extends BaseServiceImpl<ReadScheme>
		implements ReadSchemeService {

	@Resource
	ReadSchemeDao readSchemeDao;

	@Override
	public BaseDao<ReadScheme> getDao() {
		return readSchemeDao;
	}



}
