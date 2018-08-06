package hust.ioic.oa.service;

import java.util.List;

import hust.ioic.oa.domain.Center;
import hust.ioic.oa.base.BaseService;

public interface CenterService extends BaseService<Center> {

	Center getByGprs(String gprs);
}