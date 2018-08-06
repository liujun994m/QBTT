package hust.ioic.oa.utils;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 存放程序中需要的常量
 * 
 * @author lecky
 * 
 */
public class StaticConstant {
	/**
	 * 用户状态:使用中
	 */
	public static final int USER_STATUS_USE = 0;
	public static final int USER_STATUS_DROP = 1;

	/**
	 * json对象
	 */
	public static final Gson gson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * 水表直接关联集中器时，采集器的标志位
	 */
	public static final int NO_COLLECTION = -1;

	/**
	 * 使用水表
	 */
	public static final int DEVICE_USE = 0;

	/**
	 * 水表未使用
	 */
	public static final int DEVICE_NOT_USE = 1;

	/**
	 * 水表用量异常
	 */
	public static final int DEVICE_USE_EXCEPTION = 2;

	/**
	 * 手工停止
	 */
	public static final int DEVICE_STOP_USE = 3;

	/**
	 * 水表弃用 0:正常使用 1:弃用
	 */
	public static final int DEVICE_STATUS_USE = 0;
	public static final int DEVICE_STATUS_DROP = 1;

	/**
	 * 水表是否使用下拉标签数据源
	 */

	public static final Map<Integer, String> ISUSE_MAP = new HashMap<Integer, String>();
	static {
		ISUSE_MAP.put(DEVICE_USE, "使用");
		ISUSE_MAP.put(DEVICE_NOT_USE, "未使用");
		ISUSE_MAP.put(DEVICE_USE_EXCEPTION, "用量异常");
		ISUSE_MAP.put(DEVICE_STOP_USE, "停止使用");
	}

	/**
	 * 水表类型数据源
	 */
	public static final Map<String, String> DEVICETYPE_MAP = new HashMap<>();
	static {
		DEVICETYPE_MAP.put("小表", "小表");
		DEVICETYPE_MAP.put("大表", "大表");
	}
	/**
	 * 大表标志位
	 */
	public static final Integer BIG_DEVICE_NUM = -1;
	public static final String BIG_DEVICE_NAME = "大表";
	public static final String SMALL_DEVICE_NAME = "小表";

	/**
	 * 换表命令码
	 */
	public static final String CHANGE_DEVICE_COMMAND = "000201";

	/**
	 * 新增水表命令码
	 */
	public static final String ADD_DEVICE_COMMAND = "000202";

	/**
	 * 新增水表失败的标志位
	 */
	public static final Integer ADD_FLAG = 1;
	public static final Integer ADD_FLAG_DROP = -1;
	/**
	 * 删除水表命令码
	 */
	public static final String DELETE_DEVICE_COMMAND = "000203";

	/**
	 * 临时抄表
	 */
	public static final String COLLECT_COMMAND = "000101";
	/**
	 * 数据读取
	 */
	public static final String DATA_LOAD = "000102";

	/**
	 * 水表控制开阀
	 */
	public static final String DEVICE_OPEN = "000302";
	/**
	 * 水表控制关阀
	 */
	public static final String DEVICE_CLOSE = "000304";

	public static final String CENTER_READ = "000103";
	/**
	 * 临时重复次数
	 */
	public static final Integer TEMP_RETRYTIMES = 1;
	/**
	 * 定时重复次数
	 */
	public static final Integer PLAN_RETRYTIMES = 3;
	/**
	 * 重复间隔时间
	 */
	public static final Integer RETRYINTERVALTIME = 300000;

	/**
	 * 命令初始写入状态
	 */
	public static final Integer STATE = 0;
	/**
	 * 是否按照计划时间执行
	 */
	public static final Integer SCHEDULESTATE = 1;
	/**
	 * 命令类型
	 */
	public static final Integer COMMANDTYPE = 1;
	/**
	 * 计划命令来源
	 */
	public static final Integer COMMANDSOURCE = 1;
	/**
	 * 命令与上条命令无关
	 */
	public static final Integer ISLINKEDLASTCMD = 0;

	/**
	 * 临时允许超时时间
	 */
	public static final Integer TEMP_EXECUTETIME = 10000;
	/**
	 * 计划允许超时时间
	 */
	public static final Integer PLAN_EXECUTETIME = 2000;
	/**
	 * 写临时队列 水表直接连集中器，无采集器时，采集器地址标志位
	 */
	public static final String COLLECTION_ADDR = "100000000000";
	/**
	 * 操作员使用与未使用
	 * 
	 */
	public static final int OPERATOR_STATUS_USE = 0;
	public static final int OPERATOR_STATUS_DROP = 1;

	/**
	 * 程序中的分隔符
	 */
	public static final String SPLIT_FLAG = "@";
	
	
	

	/*
	 * &&&&&&&&&&&&&&&&&&&&&&&&&&&集中器设置命令码&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	 */
	/**
	 * 集中器控制开阀
	 */
	public static final String CENTER_OPEN = "000301";
	/**
	 * 集中器控制关阀
	 */
	public static final String CENTER_CLOSE = "000303";
	/**
	 * 集中器设备校时
	 */
	public static final String CENTER_CHECK = "000204";
	/**
	 * 下载用户档案，也叫设置集中器编号
	 */
	public static final String CENTER_USERDOWNLOAD = "000208";
	/**
	 * 读集中器信息
	 */

	/**
	 * 设置集中器定时采集命令码
	 */
	public static final String SET_CENTER_COLLECTION_TIME_COMMAND = "000205";

	/**
	 * 设置集中器的modle信息
	 */
	public static final String SET_CENTER_Model_INFO_COMMAND = "000207";

	
	/**
	 * 添加集中器前测试是否集中器已安装
	 */
	public static final String ADD_CENTER_FLAG_COMMAND="000209";
	
	/**
	 * 日志延迟初始化线程安全类
	 */
	private static class Logger{
		public static final Log log = LogFactory.getLog(StaticConstant.class.getClass());
		static{
			String path = StaticConstant.class.getClassLoader().getResource("").getPath()+"/log4j.properties";
			PropertyConfigurator.configure(path); 
		}
	}
	public static Log getLog(){
		return Logger.log;
	}
	
	  /**
     * 将异常堆栈转换为字符串
     * @param aThrowable 异常
     * @return String
     */
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
      }
    
    public static Log handleLog = LogFactory.getLog("handleLog"); 
    
}
