package hust.ioic.oa.utils;

import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import hust.ioic.oa.domain.DeviceTmp;




import hust.ioic.oa.qilin.utils.ConfigFileReader;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Installer2 {

	
	 @Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
	Session session=	sessionFactory.getCurrentSession();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
	 Date now=new Date();
	 String tablename="t_deviceTmp" + format1.format(now); 
	 List<DeviceTmp> list=session.createSQLQuery("select * from [My_meterdb].[dbo].["+tablename+"]").setResultTransformer(Transformers.aliasToBean(DeviceTmp.class)).list();
	 System.out.println(list.size());
	 String tablename1="[My_meterdb].[dbo].[t_deviceTmp" + format1.format(now)+"] where iAddr=000000000030"; 
	 DeviceTmp deviceTmp=(DeviceTmp) session.createSQLQuery("select * from "+ tablename1 + "and readDate in (select max(readDate) from "+ tablename1+")" ).setResultTransformer(Transformers.aliasToBean(DeviceTmp.class)).uniqueResult();		
	 System.out.println(deviceTmp.getReadTime());

			
	
		
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer2 installer = (Installer2) ac.getBean("installer2");
		System.out.println(installer);
		ApplicationContext acnew = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer2 installernew = (Installer2) ac.getBean("installer2");
		System.out.println(installernew);
//		installer.install();
	}
}
