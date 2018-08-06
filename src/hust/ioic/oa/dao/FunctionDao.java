package hust.ioic.oa.dao;

import java.util.Collection;
import java.util.List;

import hust.ioic.oa.base.BaseDaoPS;
import hust.ioic.oa.domain.Function;

public interface FunctionDao extends BaseDaoPS<Function>{

	List<Function> findTopFunction();

	Collection<String> getAllFunctionUrls();
	List<Function> findChildrenFunction();
}
