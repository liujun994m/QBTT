package hust.ioic.oa.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import hust.ioic.oa.base.BaseDaoPSImpl;
import hust.ioic.oa.domain.Function;
import hust.ioic.oa.dao.FunctionDao;


@Repository
@SuppressWarnings("unchecked")
public class FunctionDaoImpl extends BaseDaoPSImpl<Function> implements FunctionDao{

	@Override
	public List<Function> findTopFunction() {
		return getSession()//
				.createQuery("FROM Function f WHERE f.parent IS NULL ORDER BY f.id ASC")//
				.list();
	}

	@Override
	public Collection<String> getAllFunctionUrls() {
		return getSession().createQuery(//
				"SELECT  f.url FROM Function f WHERE f.url IS NOT NULL ORDER BY f.id DESC")//
				.list();
	}

	@Override
	public List<Function> findChildrenFunction() {
		
		return getSession()//
				.createQuery("FROM Function f WHERE f.parent IS NOT NULL ORDER BY f.id ASC")//
				.list();
	}

	
}
