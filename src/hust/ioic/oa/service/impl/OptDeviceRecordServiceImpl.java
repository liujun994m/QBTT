package hust.ioic.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.OptDeviceRecordDao;
import hust.ioic.oa.domain.OptDeviceRecord;
import hust.ioic.oa.service.OptDeviceRecordService;
@Service
public class OptDeviceRecordServiceImpl extends BaseServiceImpl<OptDeviceRecord> implements OptDeviceRecordService {
	@Resource
	private OptDeviceRecordDao optDeviceRecordDao;
	
	@Override
	public BaseDao<OptDeviceRecord> getDao() {
		return optDeviceRecordDao;
	}

}
