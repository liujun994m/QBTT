package hust.ioic.oa.service;

import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.UncompeletCommandQueue;

public interface UncompeletCommandQueueService extends BaseService<UncompeletCommandQueue>{

	List<UncompeletCommandQueue> getByUsername(String username);

	boolean getByDeviceAddr(String addr, String Command);
	
	/**
	 * 根据集中器地址和命令码来筛选记录
	 * @param centerGprsNum
	 * @param command
	 * @return
	 */
	boolean getByCenterAddr(String centerGprsNum,String command);

}
