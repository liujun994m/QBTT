package hust.ioic.oa.service;

import java.util.List;

import hust.ioic.oa.base.BaseService;
import hust.ioic.oa.domain.Collection;

public interface CollectionService extends BaseService<Collection>{
	Collection getByName(String name);

}