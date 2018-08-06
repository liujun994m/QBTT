package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.UncompeletCommandQueue;

public interface UncompeletCommandQueueDao extends BaseDao<UncompeletCommandQueue>{

	List<UncompeletCommandQueue> getByUsername(String username);

	boolean getByDeviceAddr(String addr, String command);
	
	/**
	 * 根据集中器地址和命令码来筛选记录
	 * @param centerGprsNum
	 * @param command
	 * @return
	 */
	boolean getByCenterAddr(String centerGprsNum,String command);
	

}
