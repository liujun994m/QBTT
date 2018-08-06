package hust.ioic.oa.test;

import hust.ioic.oa.domain.Device;



import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Service("testService")
public class TestService {
	@Resource
	private SessionFactory sessionFactory;
	
	/**
	 * 写入device表的测试数据
	 * @param id
	 */
	@Transactional
	public void saveDevice(int id){
		Session session = sessionFactory.getCurrentSession();

	}

	@Transactional
	public void saveRole(int i) {
		Session session=sessionFactory.getCurrentSession();
	}

}
