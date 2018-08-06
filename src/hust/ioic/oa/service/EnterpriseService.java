package hust.ioic.oa.service;

import hust.ioic.oa.base.BaseServicePS;
import hust.ioic.oa.domain.Enterprise;

public interface EnterpriseService extends BaseServicePS<Enterprise>{

	Enterprise getByEnprNo(String enprNo);
}
