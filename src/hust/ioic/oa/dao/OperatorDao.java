package hust.ioic.oa.dao;

import java.util.List;

import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.domain.Operator;

public interface OperatorDao extends BaseDao<Operator>{

	Operator findByUsernameAndPassowrd(String username, String password);
    List<Operator> findAllByStatus();
    List<String> getAllOperatorNos();
}
