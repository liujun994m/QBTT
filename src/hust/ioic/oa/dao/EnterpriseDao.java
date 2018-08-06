package hust.ioic.oa.dao;

import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.domain.Enterprise;

public interface EnterpriseDao extends BaseDaoPS<Enterprise> {

	/*Enterprise getByName(String company);*/
	Enterprise getByEnprNo(String enprNo);
}
