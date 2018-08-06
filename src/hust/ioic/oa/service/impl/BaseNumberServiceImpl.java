package hust.ioic.oa.service.impl;


import javax.annotation.Resource;

import hust.ioic.oa.service.BaseNumberService;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.BaseNumberDao;
import hust.ioic.oa.domain.BaseNumber;

@Service
public class BaseNumberServiceImpl extends BaseServiceImpl<BaseNumber>  implements BaseNumberService{
	@Resource
	private BaseNumberDao BaseNumberDao;
	@Override
	public BaseDao<BaseNumber> getDao() {
	return BaseNumberDao;
}

}
