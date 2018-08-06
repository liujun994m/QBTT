package hust.ioic.oa.qilin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Repository;

/**
 * 读取模板文件相关的目录
 * 该类为静态类，没有交给spring管理
 * @author lecky
 *
 */
public class ConfigFileReader {
	
	private static final String JDBCPROPERTIES = "jdbc.properties";
	private static final String SAASPROPERTIES = "saas.properties";
	
	/**
	 * 延迟初始化Holder类
	 * 延迟初始化读取property文件
	 * @author lecky
	 *
	 */
	private  static class PropFile{
		//数据库配置文件
		public static Properties jdbcProperties = reader(JDBCPROPERTIES);
		//其他属性配置文件
		public static Properties saasProperties = reader(SAASPROPERTIES);
	}
	
	public static String getVarByKey(String key,String fileName){
		String var =null;
		if(JDBCPROPERTIES.equals(fileName)){
			var = PropFile.jdbcProperties.getProperty(key);
		}else {
			var = PropFile.saasProperties.getProperty(key);
		}
//		Properties p = reader(fileName);
//		String var = p.getProperty(key);
		return var;
	}
	
	private  static Properties reader(String path) {
		InputStream in = ConfigFileReader.class.getClassLoader().getResourceAsStream(path);
		Properties p = new Properties();   
		try {    
		   p.load(in);    
		} catch (IOException e1) {    
		   e1.printStackTrace();    
		}    
		return p;
	}
}
