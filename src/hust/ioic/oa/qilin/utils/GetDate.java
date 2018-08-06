package hust.ioic.oa.qilin.utils;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class GetDate {
	public int getStartDate() {
		String resource = "charge.properties";

		Properties props = null;
		int sdate = 0;
		try {
			props = new Properties();

			InputStream in = ConfigFileReader.class.getClassLoader()
					.getResourceAsStream(resource);

			props.load(in);
			String startdate = props.getProperty("monthChargeStartDate");
			sdate = Integer.parseInt(startdate);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return sdate;
	}
	public String getDatabaseName() {
		String resource = "jdbc.properties";
		String databaseName="";
		Properties props = null;
		int sdate = 0;
		try {
			props = new Properties();
			InputStream in = ConfigFileReader.class.getClassLoader().getResourceAsStream(resource);
			props.load(in);
			String database = props.getProperty("jdbcUrl");
			String[] b=database.split("=");
            databaseName=b[1];
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return databaseName;
	}

	/*
	 * public static void main (String[] args){ GetDate g=new GetDate(); String
	 * a= g.getdate(2); System.out.println(a);
	 * 
	 * }
	 */
	public String getCurrentMonth() {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
		Date now = new Date();
		String month = format1.format(now);
		return month;
	}

	public Timestamp getCurrentTime() {

		SimpleDateFormat format1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		Timestamp time = Timestamp.valueOf(format1.format(now));
		return time;
	}

	public String getdate(int d) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, d);
		String d1 = format1.format((cal.getTime()));
		return d1;
	}

	public String getLastMonth() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return format1.format((cal.getTime()));
	}

	public Timestamp getCurrentDay() {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date now = new Date();
		Timestamp day = Timestamp.valueOf(format1.format(now));
		return day;
	}

	public Timestamp getTomorrowDay() {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date now = new Date();
		Timestamp day = Timestamp.valueOf(format1.format(now));
		return day;
	}
}
