package hust.ioic.oa.view.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.domain.OptDeviceRecord;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.domain.UncompeletCommandQueue;
import hust.ioic.oa.utils.StaticConstant;
import hust.ioic.oa.utils.TimestampToDateTime;

@Controller
@Scope("prototype")
public class OptDeviceRecordAction extends BaseAction<OptDeviceRecord>{
	
	private Integer ID;
	private String userNo;
	private String bankNum;
	private String deviceNom;
	private String cardID;
	private String iaddr;
	
	public String list(){
		List<OptDeviceRecord>optDeviceRecords = optDeviceRecordService.findByEnprNo();
		ActionContext.getContext().put("optDeviceRecords", optDeviceRecords);
		return "list";
	}
	
	/**
	 * 删除换表记录
	 * @return
	 */
	public String delete(){
		optDeviceRecordService.delete(ID);
		return "toList";
	}
	
	/**
	 * 添加换表页面
	 * @return
	 */
	public  String addUi(){
		return "addUi";
	}
	
	/**
	 * 换表页面
	 * @return
	 */
	public String changeUi(){
		ActionContext.getContext().put("ID", ID);
		return "changeUi";
	}
	
	/**
	 * 换表
	 * update t_device的表记录
	 * save 换表记录表记录
	 * 换表需要写临时队列
	 * @return
	 * @throws InterruptedException 
	 * @throws CloneNotSupportedException 
	 */
	public String change() throws InterruptedException, CloneNotSupportedException{
		//TODO 性能问题？这条查询语句会执行很多无关的sql查询
		Device device = deviceService.getById(ID);
		String oldAddr = device.getAddr();//旧表地址
		//新增换表记录
		OptDeviceRecord optDeviceRecord = new OptDeviceRecord();
		addChangeRecord(device,optDeviceRecord);
		//保存临时队列命名记录
		TempCommandQueue tempCommandQueue= new TempCommandQueue();
		int hashCode = tempCommandQueueSave(device,tempCommandQueue);
		if(processCommand(hashCode)){//成功
			//更新换表命令水表记录
			device.setCardID(cardID);
			device.setAddr(iaddr);
			deviceService.update(device);
			optDeviceRecordService.save(optDeviceRecord);
			int collectionId = device.getDeviceRelation().getCollectionId();
			if(collectionId == StaticConstant.NO_COLLECTION){
				int centerId = device.getDeviceRelation().getCenterId();
				ActionContext.getContext().put("centerId", centerId);
				return "toCenterList";
		}else{
				ActionContext.getContext().put("collectionId", collectionId);
				return "toCollectionList";
		}
		}else {//失败
			//浅复制device对象，否则若对hibernate缓存中的deivce作修改，会被保存到数据库中
			Device tempDevice = (Device) device.clone();
			tempDevice.setCardID(cardID);
			tempDevice.setAddr(iaddr);
			UncompeletCommandQueue uncompeletCommandQueue = new UncompeletCommandQueue(tempCommandQueue);
			if(uncompeletCommandQueueService.getByDeviceAddr(oldAddr, StaticConstant.CHANGE_DEVICE_COMMAND)){
				uncompeletCommandQueue.setJsonofobj(StaticConstant.gson.toJson(tempDevice)+StaticConstant.SPLIT_FLAG+StaticConstant.gson.toJson(optDeviceRecord));
				uncompeletCommandQueueService.save(uncompeletCommandQueue);
			}
			return "fail";
		}
	}
	
	/**
	 * 写入换表命令临时队列
	 * @param device
	 * @return hashCode
	 */
	private int tempCommandQueueSave(Device device,TempCommandQueue tempCommandQueue) {
		tempCommandQueue.setEnprNo((String)ActionContext.getContext().getSession().get("enprNo"));
		Center center = centerService.getById(device.getDeviceRelation().getCenterId());//获得该水表的上级center
		Port port = center.getPort();//查询主数据库，获得集中器的上级port对象
		Server server = port.getServer();//获得port上级server对象
		tempCommandQueue.setPort(server.getLocalIp()+":"+port.getPortNum());//设置主机端口
		tempCommandQueue.setCommand(StaticConstant.CHANGE_DEVICE_COMMAND);//设置换表命令码
		tempCommandQueue.setOperator(((Operator)ActionContext.getContext().getSession().get("operator")).getUsername());//设置换表操作员
		tempCommandQueue.setGenerateTime(TimestampToDateTime.nowTime());//设置当前时间
		tempCommandQueue.setRetryTimes(StaticConstant.TEMP_RETRYTIMES);//设置重试次数
		tempCommandQueue.setRetryIntervalTime(StaticConstant.RETRYINTERVALTIME);//设置重复间隔时间
		tempCommandQueue.setExecuteTime(StaticConstant.TEMP_EXECUTETIME);//设置允许执行时间
		tempCommandQueue.setContentValue1(center.getGprsNum());//设置集中器地址
		if(StaticConstant.NO_COLLECTION !=device.getDeviceRelation().getCollectionId()){//若水表上级为采集器
			Collection collection =collectionService.getById(device.getDeviceRelation().getCollectionId());//查出采集器
			tempCommandQueue.setContentValue2(collection.getAddress());//设置采集器地址
		}else
			tempCommandQueue.setContentValue2(StaticConstant.COLLECTION_ADDR);//若水表上级为集中器，设置标志位
		tempCommandQueue.setContentValue3(device.getAddr());//旧表地址
		tempCommandQueue.setContentValue4(iaddr);//新表地址
		tempCommandQueue.setState(StaticConstant.STATE);//写入命令状态
		tempCommandQueue.setIsLinkedLastCmd(StaticConstant.ISLINKEDLASTCMD);//设置该命令与上条命令无关
		try {
			tempCommandQueue.setIp(InetAddress.getLocalHost().getHostAddress());//写入本机ip地址
		} catch (UnknownHostException e) {
			tempCommandQueue.setIp(null);
		}
		tempCommandQueue.setHashCode(tempCommandQueue.hashCode());
		tempCommandQueueService.save(tempCommandQueue);
		return tempCommandQueue.getHashCode();
	}

	/**
	 * 创建换表对象
	 * @param device
	 * @param optDeviceRecord
	 */
	private void addChangeRecord(Device device,OptDeviceRecord optDeviceRecord){
		optDeviceRecord.setCenterId(device.getDeviceRelation().getCenterId());
		optDeviceRecord.setCollectionId(device.getDeviceRelation().getCollectionId());
		optDeviceRecord.setDeviceId(device.getId());
		optDeviceRecord.setDeviceNo(device.getDeviceNo());
		optDeviceRecord.setDeviceName(device.getName());
		optDeviceRecord.setNewDeviceNo(iaddr);
		optDeviceRecord.setNewDevID(cardID);
		optDeviceRecord.setNewReadData(model.getNewReadData());
		optDeviceRecord.setOldDeviceNo(device.getAddr());
		optDeviceRecord.setOldDevID(device.getCardID());
		optDeviceRecord.setOldReadData(device.getShowValue());
		optDeviceRecord.setOperateDate(TimestampToDateTime.nowTime());
		Operator operator =(Operator)ActionContext.getContext().getSession().get("operator");
		optDeviceRecord.setOperateMan(operator.getUsername());
		optDeviceRecord.setRemark(model.getRemark());
//		optDeviceRecord.setSumMoney(sumMoney);
		optDeviceRecord.setUserNo(device.getUserNo());
		optDeviceRecord.setUserCount(model.getUserCount());
		optDeviceRecord.setUserMoney(device.getStopUse());
	}
	
	/**
	 * 停止计费
	 * 需要参数:
	 * ID:选中修改对象的id
	 * deviceNom:选中对象的水表编号
	 * @return
	 */
	public String stopCharge(){
		Device device = deviceService.getByDeviceNo(deviceNom);
		//设置水表停止计费标志位
		device.setStopUse(StaticConstant.DEVICE_STOP_USE);
		deviceService.update(device);
		Set<Device> devices = new HashSet<>();
		devices.add(device);
		ActionContext.getContext().put("devices", devices);
		return "addUi";
	}
	
	/**
	 * 开始计费
	 * 需要参数:
	 * ID:选中修改对象的id
	 * deviceNom:选中对象的水表编号
	 * @return
	 */
	public String beginCharge(){
		Device device = deviceService.getByDeviceNo(deviceNom);
		//设置水表开始计费标志位
		device.setStopUse(StaticConstant.DEVICE_USE);
		deviceService.update(device);
		Set<Device> devices = new HashSet<>();
		devices.add(device);
		ActionContext.getContext().put("devices", devices);
		return "addUi";
	}
	
	/**
	 * 根据用户编号查询
	 * @return
	 */
	public String findByUserNo(){
		
		return null;
	}
	/**
	 * 根据用户卡号查询
	 * @return
	 */
	public String findBybankNum(){
	
		return null;
	}
	/**
	 * 根据设备编号查询
	 * @return
	 */
	public String findBydeviceNo(){
		Device device = deviceService.getByDeviceNo(deviceNom);
		if (null ==device) {
			addFieldError("deviceNom", "不存在该水表编号，请重新输入");
		}else {
			Set<Device>devices = new HashSet<>();
			devices.add(device);
			//筛选出正常使用水表
			ActionContext.getContext().put("DEVICE_STATUS_USE", StaticConstant.DEVICE_STATUS_USE);
			ActionContext.getContext().put("devices", devices);
		}
		return "addUi";
	}

	
	
	//***************************************************************************************//
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getDeviceNom() {
		return deviceNom;
	}

	public void setDeviceNom(String deviceNom) {
		this.deviceNom = deviceNom;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getIaddr() {
		return iaddr;
	}

	public void setIaddr(String iaddr) {
		this.iaddr = iaddr;
	}


	
	
}
