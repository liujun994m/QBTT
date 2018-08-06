package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.UncompeletCommandQueueDao;
import hust.ioic.oa.domain.UncompeletCommandQueue;
import hust.ioic.oa.service.UncompeletCommandQueueService;
@Service
public class UncompeletCommandQueueServiceImpl extends BaseServiceImpl<UncompeletCommandQueue>
	implements UncompeletCommandQueueService{
	@Resource
	private UncompeletCommandQueueDao uncompeletCommandQueueDao;
	@Override
	public BaseDao<UncompeletCommandQueue> getDao() {
		return uncompeletCommandQueueDao;
	}
	@Override
	public List<UncompeletCommandQueue> getByUsername(String username) {
		return uncompeletCommandQueueDao.getByUsername(username);
	}
	@Override
	public boolean getByDeviceAddr(String addr,String command) {
		return uncompeletCommandQueueDao.getByDeviceAddr(addr,command);
	}
	@Override
	public boolean getByCenterAddr(String centerGprsNum, String command) {
		return uncompeletCommandQueueDao.getByCenterAddr(centerGprsNum, command);
	}


}
