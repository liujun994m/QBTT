package hust.ioic.oa.view.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.DeviceType;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.domain.UncompeletCommandQueue;
import hust.ioic.oa.qilin.utils.GetDate;
import hust.ioic.oa.utils.BusinessException;
import hust.ioic.oa.utils.DeviceObject;
import hust.ioic.oa.utils.StaticConstant;
import hust.ioic.oa.utils.TimestampToDateTime;

/**
 * 目前实现为：操作员在最低级区域下添加：用户--》小表//用户--》大表，则该大表为小表统计表
 * 操作员可以在更上级区域添加水表，若添加为大表，则为统计该区域所有子区域的用水量 若添加为小表，则作为小表统计处理。
 * 
 * @author lecky
 * 
 */

@Controller
@Scope("prototype")
public class DeviceAction extends BaseAction<Device> {

	private  Integer ID ;
	// 上级页面传递过来的查看水表的用户
	private Integer[] ids;
	// 水表型号对象的id
	private Integer deviceTypeID;

	private String IDS;

	// 增加或者修改水表信息
	private String deviceInfor;

	/**
	 * 页面传过来的区域id
	 */
	private Integer mareaId;

	/**
	 * 页面传过来的集中器id
	 */
	private Integer centerId;

	/**
	 * 页面传过来的采集器Id
	 */
	private Integer collectionId;

	// //页面传回的水表地址设置字段addr
	// private String addr;
	/**
	 * 用户水表列表 需要上级页面传递用户id
	 * 
	 * @return
	 */
	public String list() {
		// 取到区域列表
		List<Area> areaList = areaService.getTopArea();
		ActionContext.getContext().put("areaList", areaList);
		if (mareaId != null)
			ActionContext.getContext().put("areaId", mareaId);
		return "list";
	}

	public String addBigForSelected() {
		Integer[] bigDeviceId;
		if (IDS != null) {
			String[] ids = IDS.split(",");
			bigDeviceId = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++)
				bigDeviceId[i] = Integer.parseInt(ids[i]);
			List<Device> devices = deviceService.getByIds(bigDeviceId);
			List<Device> bigDevices = deviceService
					.getBigDevicesByAreaId(devices.get(0).getAreaId());
			ActionContext.getContext().put("bigDevices", bigDevices);
			ActionContext.getContext().put("IDS", IDS);
		}
		return "add_device";
	}

	public String setBig() {
		String[] ids = this.IDS.split(",");
		Integer[] selected = new Integer[ids.length];
		for (int i = 0; i < ids.length; i++)
			selected[i] = Integer.parseInt(ids[i]);
		Device bigdevice = deviceService.getById(this.ids[0]);
		List<Device> devices = deviceService.getByIds(selected);
		for (Device d : devices)
			d.setParent(bigdevice);
		int collectionId = devices.get(0).getDeviceRelation().getCollectionId();
		if(collectionId != StaticConstant.NO_COLLECTION){
			ActionContext.getContext().put("collectionId",collectionId	);
			return "toCollectionList";
		}else{
			int centerId = devices.get(0).getDeviceRelation().getCenterId();
			ActionContext.getContext().put("centerId", centerId);
			return "toCenterList";
		}
	}

	/**
	 * 将选中的水表设置为总表
	 * 
	 * @return
	 */
	public String addBigDevice() {
		List<Device> devices = deviceService.getByIds(ids);
		for (Device d : devices) {
			d.setBigDeviceFlag(StaticConstant.BIG_DEVICE_NUM);
			deviceService.update(d);
		}
		if(collectionId != null){
			ActionContext.getContext().put("collectionId", collectionId);
			return "toCollectionList";
		}else{
			int centerId = devices.get(0).getDeviceRelation().getCenterId();
			ActionContext.getContext().put("centerId", centerId);
			return "toCenterList";
		}
	}

	/**
	 * 右边页面，主要是说明的作用
	 * 
	 * @return
	 */
	public String right() {
		return "right";
	}

	public String area() {
		/**
		 * 根据页面传过来的区域id:areaId来查询区域
		 **/
		Area area = areaService.getById(mareaId);
		ActionContext.getContext().put("area", area);
		return "area";
	}

	public String center() {
		Center center = centerService.getById(centerId);
		ActionContext.getContext().put("center", center);
		ActionContext.getContext().put("areaId", center.getArea().getId());
/*<<<<<< HEAD
		*//***************************************************************************//*
		List<DeviceRelation> deviceRelations = deviceRelationService.getDeviceByCenterId(center.getId());
		List<Device> devices = new ArrayList<>();
		for(DeviceRelation d : deviceRelations)
			devices.add(d.getDevice());
		ActionContext.getContext().put("devices", devices);
=======*/
		List<DeviceRelation> deviceRelations = deviceRelationService
				.getByCenterId(centerId);
		if(! deviceRelations.isEmpty()){
			List<Device> deviceList = new ArrayList<>();
			for (DeviceRelation dr : deviceRelations) {
				if (dr.getCollectionId() == StaticConstant.NO_COLLECTION)
					deviceList.add(dr.getDevice());
			}
			ActionContext.getContext().put("deviceList", deviceList);
		}
		return "center";
	}

	public String collection() {
		ActionContext.getContext().put("collectionId", collectionId);
		ActionContext.getContext().put("collectionName",collectionService.getById(collectionId).getName());
		List<DeviceRelation> device = deviceRelationService.getByCollectionId(collectionId);
		Integer[] devices = new Integer[device.size()];
		for (int i = 0; i < devices.length; i++)
			devices[i] = device.get(i).getDevice().getId();
		List<Device> deviceList = deviceService.getByIds(devices);
		ActionContext.getContext().put("deviceList", deviceList);

		return "collection";
	}

	/**
	 * 删除操作 删除水表时，不能删除数据库记录，将水表status标志位设置为弃用：1
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String delete() throws InterruptedException {
		// 删除表时，不要删除数据库记录，设置表无效标志位
		// 设置水表status标志位为1，即弃用水表，不删除
		List<Device> devices = deviceService.getByIds(ids);
		// devices.get(0).setAddr("sfsdf");
		// deviceService.update(devices.get(0));
		// 发送临时队列指令并返回temp对象的hash值（默认实现，对象在内存当中的物理地址）
		int hashCodes = 0;
		TempCommandQueue tempCommand = null;
		for (int i = 0; i < devices.size(); i++) {
			tempCommand = new TempCommandQueue();
			hashCodes = addTempCommand(devices.get(i).getDeviceRelation(),
					devices.get(i), StaticConstant.DELETE_DEVICE_COMMAND,
					tempCommand);
			// int hashCode = addTempCommand(device.getDeviceRelation(), device,
			// StaticConstant.DELETE_DEVICE_COMMAND, tempCommand);
			if (processCommand(hashCodes)) {// 执行成功
				devices.get(i).setStatus(StaticConstant.DEVICE_STATUS_DROP);
				deviceService.update(devices.get(i));
				int collectionId = devices.get(0).getDeviceRelation().getCollectionId();
				if(collectionId != StaticConstant.NO_COLLECTION){
					ActionContext.getContext().put("collectionId", collectionId);
					return "toCollectionList";
				}else{
					centerId = devices.get(0).getDeviceRelation().getCenterId();
					ActionContext.getContext().put("centerId", centerId);
					return "toCenterList";
				}
			} else {// 命令执行失败
				if (uncompeletCommandQueueService
						.getByDeviceAddr(devices.get(i).getAddr(),
								StaticConstant.DELETE_DEVICE_COMMAND)) {
					UncompeletCommandQueue uncompeletCommandQueue = new UncompeletCommandQueue(
							tempCommand);
					uncompeletCommandQueueService.save(uncompeletCommandQueue);// 写入未完成表
				}
			}
		}
		return "fail";
	}

	/**
	 * 新增水表操作 deviceRelations:关联关系表中的所有关系对象
	 * 
	 * @return
	 */
	public String addUi() {
		// 传递参数用户id
		ActionContext.getContext().put("isUse", StaticConstant.ISUSE_MAP);
		// 放入表型号数据源
		List<DeviceType> deviceTypes = deviceTypeService.findByEnprNo();
		ActionContext.getContext().put("deviceTypes", deviceTypes);
		if(collectionId!=null){
			ActionContext.getContext().put("collectionId", collectionId);
		return "dcolsaveUi";
		}else
		{
			ActionContext.getContext().put("centerId", centerId);
			return "dcensaveUi";
		}
		
	}

	/**
	 * 将集中器和采集器做成中转对象并放入list作为jsp页面数据源
	 */
	private List<DeviceObject> makeTree() {
		List<DeviceObject> deviceObjects = new ArrayList<>();
		// 查找所有的集中器和采集器，做成树形结构list
		List<Center> centerList = centerService.findByEnprNo();
		for (Center center : centerList) {
			DeviceObject centerObject = new DeviceObject(center.getId(),
					center.getName(), "true");
			deviceObjects.add(centerObject);
			for (Collection collection : center.getCollections()) {
				DeviceObject collectionObject = new DeviceObject(
						collection.getId(), "  ┣ " + collection.getName(),
						"false");
				deviceObjects.add(collectionObject);
			}
		}
		return deviceObjects;
	}

	/**
	 * 新增水表对象
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String add() throws InterruptedException {
		TempCommandQueue tempCommand = new TempCommandQueue();
		DeviceType deviceType = deviceTypeService.getById(deviceTypeID);
		model.setEnprNo((String) ActionContext.getContext().getSession()
				.get("enprNo"));
		// TODO 后续不使用parentId字段，使用区域来区分大表以及关联的小表
		// 若为大表，则将model标志位设为大表的标志位
		model.setDeviceType(deviceType);
		model.setStatus(StaticConstant.DEVICE_STATUS_USE);// 新添水表时，默认为启用，标志位设为0;
		model.setSetupDate(new GetDate().getCurrentTime());// 添加安装日期为当前日期
		// 将下一跳需要的参数放入值栈
		DeviceRelation relation = new DeviceRelation();
		relation.setEnprNo((String) ActionContext.getContext().getSession()
				.get("enprNo"));
		// setRelation(relation);
		if (collectionId != null) {
			Collection collection = collectionService.getById(collectionId);
			model.setAreaId(collection.getCenter().getArea().getId());
			relation.setCollectionId(collectionId);
			relation.setCenterId(collection.getCenter().getId());
		} else {
			Center center = centerService.getById(centerId);
			model.setAreaId(center.getArea().getId());
			relation.setCollectionId(StaticConstant.NO_COLLECTION);
			relation.setCenterId(centerId);
		}
		relation.setDevice(model);
		model.setDeviceRelation(relation);
		// 去重复判断
		Device tempDevice = deviceService.hasExist(model.getDeviceNo());
		if (null == tempDevice) {
			// 写入新增表的临时队列命令
			int hashCode = addTempCommand(relation, model,
					StaticConstant.ADD_DEVICE_COMMAND, tempCommand);
			if (processCommand(hashCode)) {// 执行成功
				// 保存先后顺序不能更改
				deviceService.save(model);
				deviceRelationService.save(relation);
				if(collectionId != null){
					ActionContext.getContext().put("collectionId", collectionId);
					return "toCollectionList";
				}else{
					ActionContext.getContext().put("centerId", centerId);
					return "toCenterList";
				}
			} else {// 执行失败,将device对象和relation对象转换成json字符串存储,父类向下转换成子类，不能强转，否则ClassCastException
				UncompeletCommandQueue uncompeletCommandQueue = new UncompeletCommandQueue(
						tempCommand);
				// UncompeletCommandQueue uncompeletCommandQueue =
				// (UncompeletCommandQueue) tempCommand;
				if (uncompeletCommandQueueService.getByDeviceAddr(
						model.getAddr(), StaticConstant.ADD_DEVICE_COMMAND)) {
					uncompeletCommandQueue.setJsonofobj(StaticConstant.gson
							.toJson(model));
					uncompeletCommandQueueService.save(uncompeletCommandQueue);
				}
				// deviceService.save(model);
				// deviceRelationService.save(relation);
				// TODO 新增失败的处理
				return "fail";
			}
		} else {
			addFieldError("deviceNo", "该表编号已存在");
			ActionContext.getContext().getValueStack().push(model);
			// 设备类型回显name和下拉数据源
			if(model.getDeviceType()!=null)
				deviceTypeID = model.getDeviceType().getId();
			List<DeviceType> deviceTypes = deviceTypeService.findByEnprNo();
			ActionContext.getContext().put("deviceTypes", deviceTypes);
//			// 将集中器和采集器做成树形结构
//			ActionContext.getContext().put("deviceObjects", makeTree());
			// 放入水表是否使用下拉标签就数据源
			ActionContext.getContext().put("isUse", StaticConstant.ISUSE_MAP);
//			// 获得当前device的上级设备name
//			deviceInfor = getUpGrade(model);
			if(collectionId!=null){
				ActionContext.getContext().put("collectionId", collectionId);
			return "dcolsaveUi";
			}else
			{
				ActionContext.getContext().put("centerId", centerId);
				return "dcensaveUi";
			}
//			return "saveUi";
		}
	}

	/**
	 * 水表操作写入临时队列命令
	 * 
	 * @param relation
	 * @param model
	 *            页面传回的水表参数类
	 * @param addDeviceCommand
	 *            命令码
	 */
	private int addTempCommand(DeviceRelation relation, Device model,
			String command, TempCommandQueue tempCommand) {
		Center center = centerService.getById(relation.getCenterId());
		Port port = center.getPort();
		Server server = port.getServer();
		// = new TempCommandQueue();
		tempCommand.setHashCode(tempCommand.hashCode());// 存储该对象的hash值，作为查询的依据
		tempCommand.setCommand(command);// 设置新增表命令码
		tempCommand.setContentValue1(center.getGprsNum());// 设置集中器地址
		if (StaticConstant.NO_COLLECTION != relation.getCollectionId())// 若有采集器，增加采集器地址
			tempCommand.setContentValue2(collectionService.getById(
					relation.getCollectionId()).getAddress());
		else
			tempCommand.setContentValue2(StaticConstant.COLLECTION_ADDR);
		tempCommand.setContentValue3(model.getAddr());// 设置水表地址
		tempCommand.setOperator(((Operator) ActionContext.getContext()
				.getSession().get("operator")).getUsername());// 设置操作员
		tempCommand.setGenerateTime(TimestampToDateTime.nowTime());// 设置命令生成时间
		tempCommand.setExecuteTime(StaticConstant.TEMP_EXECUTETIME);// 设置允许执行时间
		tempCommand.setState(StaticConstant.STATE);// 写入命令初始状态
		tempCommand.setRetryTimes(StaticConstant.TEMP_RETRYTIMES);// 设置重试次数
		tempCommand.setRetryIntervalTime(StaticConstant.RETRYINTERVALTIME);// 设置重复间隔时间
		 tempCommand.setPort(server.getLocalIp() + ":" + port.getPortNum());//
		// 设置主机端口
//		tempCommand.setPort("115.156.179.111:0000");// 设置主机端口

		tempCommand.setIsLinkedLastCmd(StaticConstant.ISLINKEDLASTCMD);// 设置该命令与上条命令无关
		try {
			tempCommand.setIp(InetAddress.getLocalHost().getHostAddress());// 写入本机ip地址
		} catch (UnknownHostException e) {
			throw new BusinessException("无法获取本机IP!");
		}
		tempCommand.setEnprNo((String) ActionContext.getContext().getSession()
				.get("enprNo"));
		tempCommandQueueService.save(tempCommand);
		return tempCommand.hashCode();
	}

	/**
	 * 增加水表逻辑，获取页面提交的上级设备id和isCenter字段，创建相应的对象
	 * 
	 * @param relation
	 */
	private void setRelation(DeviceRelation relation) {
		String[] device = deviceInfor.split(StaticConstant.SPLIT_FLAG);
		int deviceId = Integer.parseInt(device[0]);
		boolean isCenter = Boolean.parseBoolean(device[1]);
		if (isCenter) {
			// TODO 后续添加页面响应以及用户体验相关字段再次处
			relation.setCenterId(deviceId);
			relation.setCollectionId(StaticConstant.NO_COLLECTION);
		} else {
			relation.setCenterId(collectionService.getById(deviceId)
					.getCenter().getId());
			relation.setCollectionId(deviceId);
		}
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String editUI() {
		// TODO 修改水表时，集中器下拉选项有问题
		// 回显数据
		Device device = deviceService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(device);
		// 设备类型回显name和下拉数据源
		// if (null != device.getDeviceType())
		// deviceTypeID = device.getDeviceType().getId();
		List<DeviceType> deviceTypes = deviceTypeService.findByEnprNo();
		ActionContext.getContext().put("deviceTypes", deviceTypes);
		// 将集中器和采集器做成树形结构
		// ActionContext.getContext().put("deviceObjects", makeTree());
		// 放入水表是否使用下拉标签就数据源
		ActionContext.getContext().put("isUse", StaticConstant.ISUSE_MAP);
		Integer collectionId = device.getDeviceRelation().getCollectionId();
		if(collectionId!=StaticConstant.NO_COLLECTION){
			ActionContext.getContext().put("collectionId", collectionId);
			return "dcolsaveUi";
		}else{
			ActionContext.getContext().put("centerId", device.getDeviceRelation().getCenterId());
			return "dcensaveUi";
		}
	}

	/**
	 * 根据device对象中的关联关系查找当前device的上级设备name
	 * */
	private String getUpGrade(Device device) {
		DeviceRelation relation = device.getDeviceRelation();
		if (StaticConstant.NO_COLLECTION == relation.getCollectionId()) {
			return centerService.getById(relation.getCenterId()).getName();
		} else
			return " ┣"
					+ collectionService.getById(relation.getCollectionId())
							.getName();
	}

	public String edit() {
		DeviceType deviceType = deviceTypeService.getById(deviceTypeID);
		// 获得数据库中的原数据对象
		Device device = deviceService.getById(model.getId());
		device.setDeviceNo(model.getDeviceNo());
		device.setName(model.getName());
		device.setAddr(model.getAddr());// device类的iAddr字段不能自动设置，故改称这个字段
		device.setDeviceType(deviceType);
		device.setMaxNum(model.getMaxNum());
		device.setImultiple(model.getImultiple());
		device.setNeedAlarm(model.getNeedAlarm());
		device.setAlarmGetType(model.getAlarmGetType());
		device.setAlarmMax(model.getAlarmMax());
		device.setAlarmMin(model.getAlarmMin());
//		// TODO 可以修改水表的上级
//		 device.setParentId(model.getParentId());
		device.setCardID(model.getCardID());
		device.setCreateDate(model.getCreateDate());
		device.setSetupDate(model.getSetupDate());
		device.setStopUse(model.getStopUse());
		device.setGetMoneyType(model.getGetMoneyType());
		device.setUserCountType(model.getUserCountType());
/*		 if (!"".equals(deviceInfor)) {// 如果修改提交的页面修改了device上级设备
			 DeviceRelation relation = deviceRelationService.getById(device
			 .getDeviceRelation().getId());
			 setRelation(relation);
			 relation.setDevice(device);
			 device.setDeviceRelation(relation);
			 deviceRelationService.update(relation);
		 }*/
		deviceService.update(device);// 若执行过update（relation）此句不会执行
		Integer collectionId = device.getDeviceRelation().getCollectionId();
		if (collectionId ==StaticConstant.NO_COLLECTION){
			ActionContext.getContext().put("centerId", centerId);
			return "toCenterList";
		}else{
			ActionContext.getContext().put("collectionId", collectionId);
			return "toCollectionList";
		}
	}

	public String fail() {
		return "fail";
	}

	// *************************************************************//

	public String getDeviceInfor() {
		return deviceInfor;
	}

	public void setDeviceInfor(String deviceInfor) {
		this.deviceInfor = deviceInfor;
	}

	public Integer getDeviceTypeID() {
		return deviceTypeID;
	}

	public void setDeviceTypeID(Integer deviceTypeID) {
		this.deviceTypeID = deviceTypeID;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getMareaId() {
		return mareaId;
	}

	public void setMareaId(Integer mareaId) {
		this.mareaId = mareaId;
	}

	public String getIDS() {
		return IDS;
	}

	public void setIDS(String iDS) {
		IDS = iDS;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

}
