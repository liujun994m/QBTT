package hust.ioic.oa.qilin.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 创建数据源配置文件
 * 静态类，没有交给spring管理
 * 无效类
 * @author lecky
 *
 */
public class ConfigFileCreator {
	/**
	 * 在业务系统classpath目录下，创建数据源newDataSource.xml文件
	 * @param dataSourceName 数据源名称
	 * @param url  连接字符串,不包括数据库名
	 */
	public static void createConfigFile(String dataSourceName, String url){
		//当前项目的全路径
		String cfgStorePath = "K:\\apache-tomcat-7.0.59\\webapps\\QBT\\WEB-INF\\classes";
		//截取出webapps的全路径
		String pathString = cfgStorePath.substring(0, cfgStorePath.indexOf("QBT"));
		//组合出数据源的输出路径
		String classpath = pathString + "QBT\\WEB-INF\\classes";
		try {//生成newDataSource.xml数据源文件，并写入classpath目录下的applicationContext.xml
			writenewDataSourceXml(classpath,dataSourceName,url+dataSourceName);
		} catch (IOException e) {
			//TODO 处理异常并输出日志：写newDataSource.xml文件异常
		}
	}

	/**
	 * 写入newDataSource.Xml文件
	 * @param classpath
	 * @param dataSourceName
	 * @param url
	 * @throws IOException
	 */
	private static void writenewDataSourceXml(String classpath, String dataSourceName, String url) throws IOException {
		String path = classpath+"\\newDataSource.xml";
		File file = new File(path);
		String cfgFile=null;
		if (file.exists()) {// 文件已经存在
			System.out.println(dataSourceName + ".xml 已经存在！");
			file.delete();
		} else {// 不存在数据源文件，那么创建数据源文件
			cfgFile = readConfigFile().replace("$DATASOURCE_NAME",
					dataSourceName).replace("$URL", url);
			if (file.createNewFile()) {
				System.out.println("创建文件成功！目录：" + file.getAbsolutePath());
			} else {
				System.out.println("创建文件失败！");
			}
		}
		FileOutputStream outputStream = new FileOutputStream(path);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				outputStream, "utf-8");
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		bufferedWriter.write(cfgFile);
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	/**
	 * 读取数据源的模板文件
	 * @return
	 */
	private static String readConfigFile() {
		String templPath = ConfigFileReader.getVarByKey("configTempl","saas.properties");
		InputStream in = ConfigFileCreator.class.getClassLoader().getResourceAsStream(templPath);
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(in,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			String separator = System.getProperty("line.separator");
			StringBuffer sb = new StringBuffer();
			while((line = bufferedReader.readLine())!=null){
				sb.append(line+separator);
			}
			bufferedReader.close();
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
