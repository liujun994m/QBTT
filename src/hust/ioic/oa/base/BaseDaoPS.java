package hust.ioic.oa.base;

import hust.ioic.oa.domain.PageBean;

import java.util.List;

public interface BaseDaoPS<T> {
	
	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查找
	 * 
	 * @param id
	 * @return
	 */
	T getById(Integer id);

	/**
	 * 按id查找
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Integer[] ids);

	List<T> findAll();

	/**
	 * 查找分页
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param hql 查询的HQL语句
	 * @param parameters HQL语句中的参数，顺序与HQL语句参数顺序一样
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters);
}
