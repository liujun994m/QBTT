package hust.ioic.oa.base;

import hust.ioic.oa.domain.PageBean;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional(value="txManager")
public class BaseDaoPSImpl<T> implements BaseDaoPS<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDaoPSImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();// 获取父类的泛型参数
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];// 获取泛型参数类型
		System.out.println("---------->" + clazz);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Integer id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
		
		
	}

	@Override
	public T getById(Integer id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	@Override
	public List<T> getByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession()//
					.createQuery("FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}

	}

	@Override
	public List<T> findAll() {
		return getSession()//
				.createQuery("FROM " + clazz.getSimpleName())//
				.list();
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters) {
		Query listQuery = getSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize).list();

		List recordList= listQuery.list();
		
		
		
		Query countQuery = getSession().createQuery("SELECT COUNT(*) "+hql);
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		
		Integer count = (Integer) countQuery.uniqueResult();


		return new PageBean(pageNum, pageSize, count.intValue(), recordList);
	}

}
