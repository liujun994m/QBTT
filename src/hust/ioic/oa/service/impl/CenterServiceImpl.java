package hust.ioic.oa.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.CenterDao;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.service.CenterService;
@Service
public class CenterServiceImpl extends BaseServiceImpl<Center> implements CenterService {
	@Resource	
private CenterDao centerDao;
	@Override
	public BaseDao<Center> getDao() {
	return centerDao;
}

	@Override
	public Center getByGprs(String gprs) {
		
		return centerDao.getByGprs(gprs);
	}


	



}
