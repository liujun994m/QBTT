package hust.ioic.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServicePSImpl;
import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.dao.EnterpriseDao;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.service.EnterpriseService;

@Service
public class EnterpriseServiceImpl extends BaseServicePSImpl<Enterprise> implements EnterpriseService{
	@Resource
	private EnterpriseDao enterpriseDao;
	
	@Override
	public BaseDaoPS<Enterprise> getDao() {
		return enterpriseDao;
	}

	@Override
	public Enterprise getByEnprNo(String enprNo) {
		
		return enterpriseDao.getByEnprNo(enprNo);
	}

/*	@Override
	public Enterprise getByName(String company) {
		return enterpriseDao.getByName(company);
	}*/



}
