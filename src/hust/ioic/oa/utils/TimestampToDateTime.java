package hust.ioic.oa.utils;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

public class TimestampToDateTime {
	
	private static Date date = new Date();
	
	/**
	 * 获得当前系统时间的timestamp格式时间
	 * @return
	 */
	public static Timestamp nowTime(){
		return new Timestamp(date.getTime());
	}
	
	@Test
	public void time() throws Exception {
		System.out.println(nowTime());
	}
}
