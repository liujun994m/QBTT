package hust.ioic.oa.service;


import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.Operator;



public interface OperatorService extends BaseService<Operator> {

	Operator findByUsernameAndPassowrd(String username, String password);
	List<Operator> findAllByStatus();
	List<String> getAllOperatorNos();
}
