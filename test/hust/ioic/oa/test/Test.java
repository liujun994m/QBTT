package hust.ioic.oa.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Test {
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	@Resource
	private TestService testService;
	
	/**
	 * 测试映射关系，生成数据库表结构
	 */
	@org.junit.Test
	public void testSessionFactory() {
		SessionFactory sessionFactory= (SessionFactory) context.getBean("sessionFactory");
		SessionFactory sessionFactoryMaster=(SessionFactory) context.getBean("sessionFactoryMaster");
		System.out.println("sessionFactory"+sessionFactory+"   "+"sessionFactoryMaster"+sessionFactoryMaster);
	}
	
	
	/**
	 * 测试sessionFactory
	 * 测试spring+hibernate整合
	 * @throws Exception
	 */
	@org.junit.Test
	public void testSpringHibernate() throws Exception {
		SessionFactory sfFactory= (SessionFactory) context.getBean("sessionFactory");
		System.out.println("sfFactory");
		TestService testService=(TestService) context.getBean("testService");
	}
	
	/**
	 * 往数据库中写入终端水表测试数据
	 */
	@org.junit.Test
	public void testWriteRole(){
		SessionFactory sFactory=(SessionFactory) context.getBean("sessionFactory");
		TestService testService=(TestService) context.getBean("testService");
		testService.saveDevice(20);
		System.out.println("角色创建完成");
	}
	
	/**
	 * 测试远程服务器sql server数据库连接
	 * @result 成功
	 * @throws Exception
	 */
	@org.junit.Test
	public void testName() throws Exception {
		Connection con;
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 con = DriverManager.getConnection("jdbc:sqlserver://202.114.21.202:1433;databaseName=t_meterdb",
				 "sa","123456");
		 Statement statement = con.createStatement();
		 ResultSet rs = statement.executeQuery("select * from t_area");
		 while(rs.next())
			 System.out.println(rs.getString(1)+" "+rs.getString(2));
	}
}
