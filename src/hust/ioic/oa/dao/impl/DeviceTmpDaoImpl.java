package hust.ioic.oa.dao.impl;


import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hust.ioic.oa.dao.DeviceTmpDao;
import hust.ioic.oa.domain.DeviceTmp;
import hust.ioic.oa.qilin.utils.DataSourceContextHolder;
import hust.ioic.oa.qilin.utils.GetDate;
@Repository

@SuppressWarnings("unchecked")
@Transactional(value="txManager")
public class DeviceTmpDaoImpl  implements DeviceTmpDao{

	 @Resource(name="sessionFactory")
		private SessionFactory sessionFactory;

		private Session getSession()
		{
			
			
			return sessionFactory.getCurrentSession();
		}
		GetDate date=new GetDate();	

	

	@Override
	public DeviceTmp getLastRecord(String iAddr,String month,String enprNo) {
		
		String database=date.getDatabaseName();
	String tablenamel="["+database+"].[dbo].[t_deviceTmp" +month+"]";
	String	tablelast=tablenamel+" where iAddr='"+iAddr+"' and enprNo='"+enprNo+"'"; 
		 DeviceTmp deviceTmp=(DeviceTmp) getSession().createSQLQuery("select * from "+ tablelast + " and readDate in (select max(readDate) from "+ tablelast+")" ).setResultTransformer(Transformers.aliasToBean(DeviceTmp.class)).uniqueResult();
		return deviceTmp;
	}
	
	@Override

	public DeviceTmp getFirstRecord(String iAddr,String month,String enprNo) {
		
        String database=date.getDatabaseName();
		int startdate=date.getStartDate();
		String tablenamef="["+database+"].[dbo].[t_deviceTmp" +month+"]";
		String tablefirst=tablenamef+" where iAddr='"+iAddr+"' and enprNo='"+enprNo+"'";
		DeviceTmp deviceTmp=(DeviceTmp) getSession().createSQLQuery("select * from "+ tablefirst + " and readDate ="+ startdate ).setResultTransformer(Transformers.aliasToBean(DeviceTmp.class)).uniqueResult();

		return deviceTmp;		
	}


	@Override
	public List<DeviceTmp> getAllRecords(String iAddr,String month,String enprNo) {
		String database=date.getDatabaseName();
	    String tablename="["+database+"].[dbo].[t_deviceTmp" +month+"]";
		String tables=tablename+" where iAddr='"+ iAddr+"' and enprNo='"+enprNo+"'" ;
		List<DeviceTmp> deviceTmpList=(List<DeviceTmp>) getSession().createSQLQuery("select * from "+tables+" order by readDate asc").setResultTransformer(Transformers.aliasToBean(DeviceTmp.class)).list();
		return deviceTmpList;
	}

	@Override
	public Collection<String> getAllTables() {
				Query query =getSession().createSQLQuery("select name from sysobjects where xtype='U'and name like 't_deviceTmp%'").addScalar("name", Hibernate.STRING);
				Collection<String> tables = query.list();
		return tables;
	}

	@Override
	public Collection<Integer> getDays(String month) {
		String database=date.getDatabaseName();
		String tablename="["+database+"].[dbo].[t_deviceTmp" +month+"]";
		String enprNo=DataSourceContextHolder.getEnprNo();
		Query query =getSession().createSQLQuery("select distinct readDate from"+tablename+" where showValue is not null and enprNo= "+enprNo+" order by readDate asc").addScalar("readDate", Hibernate.INTEGER);
		Collection<Integer> days = query.list();
		return days;
	}

	@Override
	public DeviceTmp getByDate(String iAddr, String month, Integer day) {
		String database=date.getDatabaseName();
		String tablename="["+database+"].[dbo].[t_deviceTmp" +month+"]";
		
		String enprNo=DataSourceContextHolder.getEnprNo();
		String table=tablename+" where iAddr='"+ iAddr+"' and enprNo='"+enprNo+"'" ;
		DeviceTmp deviceTmp=(DeviceTmp) getSession().createSQLQuery("select * from "+ table + " and readDate ="+ day ).setResultTransformer(Transformers.aliasToBean(DeviceTmp.class)).uniqueResult();
		return deviceTmp;
	}

}
