package hust.ioic.oa.base;

import hust.ioic.oa.domain.PageBean;
import hust.ioic.oa.base.BaseDao;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("unchecked")
@Transactional(value="txManager")
public abstract class BaseServicePSImpl<T> implements BaseServicePS<T>{
	
	private Class<T> clazz;
	public abstract BaseDaoPS<T>  getDao();
	public BaseServicePSImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();// 获取父类的泛型参数
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];// 获取泛型参数类型
	
		System.out.println("<---------->" + clazz);
	}


	@Override
	public void save(T entity) {
		getDao().save(entity);
	}
	@Override
	public void delete(Integer id) {
		Object obj = getById(id);
		if (obj != null) {
			getDao().delete(id);
		}
	}
	@Override
	public void update(T entity) {
		getDao().update(entity);
	}
	@Override
	public T getById(Integer id) {	
		if (id == null) {
			return null;
		}else{
			return (T) getDao().getById(id);
		}
	}
	@Override
	public List<T> getByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getDao().getByIds(ids);
		
		}
	}
	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}
	
	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters) {

		return getDao().getPageBean(pageNum, pageSize, hql, parameters);
	}
}
