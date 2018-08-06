package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.Center;

public interface CenterDao extends BaseDao<Center> {
	/**
	 * 根据水司编号查找集中器
	 * 
	 * @param enpNo
	 * @return
	 */

	Center getByGprs(String gprs);

}
