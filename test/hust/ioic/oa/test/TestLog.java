package hust.ioic.oa.test;

import hust.ioic.oa.utils.StaticConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLog {
	
	
//	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void testLog() throws Exception {
//		Log log = LogFactory.getLog(this.getClass());
//		String path = TestLog.class.getClassLoader().getResource("").getPath()+"/log4j.properties";
//		PropertyConfigurator.configure(path); 
		Log log =StaticConstant.getLog();
		try {
			int i=9/0;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e,e.fillInStackTrace());
		}
//		log.debug("这是debug信息");
//		log.info("这是info信息");
//		log.warn("这是warn信息");
//		log.error("这是error信息");
//		log.fatal("这是fatal信息");
	}
	
	@Test
	public void testExtents(){
		}
	
}

class One {  
	private int one;
    public int getOne() {
		return one;
	}
	public void setOne(int one) {
		this.one = one;
	}
	public void foo() {  
        System.out.println("One");  
    }  
}  
  
class Two implements Cloneable{  
	private int two;
	private One one;
    public int getTwo() {
		return two;
	}
	public void setTwo(int two) {
		this.two = two;
	}
	public One getOne() {
		return one;
	}
	public void setOne(One one) {
		this.one = one;
	}
} 