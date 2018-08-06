package hust.ioic.oa.service;

import java.util.Collection;
import java.util.List;

import hust.ioic.oa.base.BaseServicePS;
import hust.ioic.oa.domain.Function;



public interface  FunctionService extends BaseServicePS<Function>{
	List<Function> findTopFunction();

	Collection<String> getAllFunctionUrls();
	List<Function> findChildrenFunction();
}
