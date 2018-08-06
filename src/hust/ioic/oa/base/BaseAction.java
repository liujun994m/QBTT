package hust.ioic.oa.base;


import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.qilin.utils.ConfigFileCreator;
import hust.ioic.oa.qilin.utils.DataSourceContextHolder;
import hust.ioic.oa.service.AreaService;
import hust.ioic.oa.service.BaseNumberService;
import hust.ioic.oa.service.CenterService;
import hust.ioic.oa.service.CollectionService;
import hust.ioic.oa.service.CommandStateService;
import hust.ioic.oa.service.DeviceRelationService;
import hust.ioic.oa.service.DeviceService;
import hust.ioic.oa.service.DeviceTmpService;
import hust.ioic.oa.service.DeviceTypeService;
import hust.ioic.oa.service.EnterpriseService;
import hust.ioic.oa.service.CollectionLogService;
import hust.ioic.oa.service.FunctionService;
import hust.ioic.oa.service.LogService;
import hust.ioic.oa.service.OperatorService;
import hust.ioic.oa.service.OptDeviceRecordService;
import hust.ioic.oa.service.PlanCommandQueueService;
import hust.ioic.oa.service.PortService;
import hust.ioic.oa.service.ReadSchemeService;
import hust.ioic.oa.service.RoleService;
import hust.ioic.oa.service.ServerService;
import hust.ioic.oa.service.TempCommandQueueService;
import hust.ioic.oa.service.UncompeletCommandQueueService;







import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	// protected ApplicationContext ac = new ClassPathXmlApplicationContext(
	// "applicationContext.xml");

	// ============================================

	protected T model;
	protected int pageNum = 1;
	protected int pageSize = 10;

	public BaseAction() {

		try {
			DataSourceContextHolder.setEnprNo(getEnprNo());
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T getModel() {
		return this.model;
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public Operator getCurrentOperator() {
		return (Operator) ActionContext.getContext().getSession()
				.get("operator");
	}
	public String getEnprNo(){
		String enpr=(String)ActionContext.getContext().getSession().get("enprNo");
		return enpr;
	}
	/**
	 * 通过提供的hashcode，查询数据库中临时队列表中指定记录的执行状态
	 * 2s查询一次
	 * 状态码1，4，6，return false
	 * 状态码0，2，3 循环等待
	 * 状态码5，return true
	 * @param hashCode
	 * @throws InterruptedException 
	 */
	protected boolean processCommand(int hashCode) throws InterruptedException  {
		String enpr=(String)ActionContext.getContext().getSession().get("enprNo");
		TempCommandQueue tempCommandQueue=null;
		int counter = 10 ;//超时时间1min（页面最多等待时间）
		while(counter-->0){//通过保存的hash值从数据库中获取命令对象判断状态并作相应的处理
			
				TimeUnit.SECONDS.sleep(5);
			//指令发送之后休眠2s
			 tempCommandQueue = tempCommandQueueService.getByHashCode(hashCode);
			 System.out.println("id="+tempCommandQueue.getId() +"  state "+tempCommandQueue.getState() +" counter:"+counter );
			if(tempCommandQueue.getState()==1	||
					tempCommandQueue.getState()==4 ||tempCommandQueue.getState()==6){
				return false;//执行失败
			}else if(tempCommandQueue.getState()==5) //执行成功 
				return true;
		}
		return false;
		
	}


	// ============================================
	// 加载业务数据库对应的service，

	@Resource
	protected OperatorService operatorService;

	@Resource
	protected RoleService roleService;

	@Resource
	protected AreaService areaService;

	@Resource
	protected FunctionService functionService;
	@Resource
	protected DeviceTypeService deviceTypeService;

	@Resource
	protected CenterService centerService;

	@Resource
	protected CollectionService collectionService;


	@Resource
	protected DeviceService deviceService;
	@Resource
	protected DeviceTmpService deviceTmpService;
	@Resource
	protected ReadSchemeService readSchemeService;

	// 加载主数据库的service

	@Resource
	protected EnterpriseService enterpriseService;

	@Resource
	protected DeviceRelationService deviceRelationService;

	@Resource
	protected PlanCommandQueueService planCommandQueueService;

	@Resource
	protected TempCommandQueueService tempCommandQueueService;

	@Resource
	protected CollectionLogService errorLogService;

	@Resource
	protected ServerService serverService;

	@Resource
	protected PortService portService;

	@Resource
	protected OptDeviceRecordService optDeviceRecordService;
	
	@Resource
	protected UncompeletCommandQueueService uncompeletCommandQueueService;

	@Resource
	protected BaseNumberService baseNumberService;
	
	// =====================Getter and Setter==========================
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
