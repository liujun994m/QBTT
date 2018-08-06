package hust.ioic.oa.service.impl;


import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import hust.ioic.oa.service.FunctionService;
import hust.ioic.oa.base.BaseServicePSImpl;
import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.dao.FunctionDao;
import hust.ioic.oa.domain.Function;

import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImpl extends BaseServicePSImpl<Function> implements FunctionService{
	@Resource
	private FunctionDao functionDao;
	@Override
	public BaseDaoPS<Function> getDao() {
	return functionDao;
}
/*	public FunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}*/

	@Override
	public List<Function> findTopFunction() {
		return functionDao.findTopFunction();
	}

	@Override
	public Collection<String> getAllFunctionUrls() {
		return functionDao.getAllFunctionUrls();
	}

	@Override
	public List<Function> findChildrenFunction() {
		
		return functionDao.findChildrenFunction();
	}
}
