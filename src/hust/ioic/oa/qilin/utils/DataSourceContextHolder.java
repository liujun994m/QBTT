package hust.ioic.oa.qilin.utils;


public class DataSourceContextHolder {

	private static final ThreadLocal contextHolder=new ThreadLocal();
	
	public static void setEnprNo(String enprNo){
		contextHolder.set(enprNo);
	}
	
	public static String getEnprNo(){
		return (String) contextHolder.get();
	}
	
	public static void clearDataSourceType(){
		contextHolder.remove();
	}
	
}
