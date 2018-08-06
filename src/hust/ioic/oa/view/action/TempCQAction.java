package hust.ioic.oa.view.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.poifs.storage.ListManagedBlock;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import java.util.Collections;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.DeviceTmp;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.qilin.utils.GetDate;
import hust.ioic.oa.utils.StaticConstant;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TempCQAction extends BaseAction<TempCommandQueue> {


	GetDate cdate = new GetDate();
	private Integer parentId;
	private Integer areaId;
	private Integer centerId;
	//centerRead临时队列id
    private Integer[] tempIds;
	private Integer[] deviceIds;
	private Integer[] centerIds;
	private String userName;
	private String userAddr;
	private String userNo;
	private String centerNo;
	private String deviceNom;

	private Integer day;
	private String hour;
	private String minute;
	
	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getUserNo() {
		return userNo;
	}

	public String getCenterNo() {
		return centerNo;
	}

	public void setCenterNo(String centerNo) {
		this.centerNo = centerNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getDeviceNom() {
		return deviceNom;
	}
	public void setDeviceNom(String deviceNom) {
		this.deviceNom = deviceNom;
	}

	public Integer[] getCenterIds() {
		return centerIds;
	}
	public void setCenterIds(Integer[] centerIds) {
		this.centerIds = centerIds;
	}

	public Integer[] getDeviceIds() {

		return deviceIds;
	}
	public void setDeviceIds(Integer[] deviceIds) {

		this.deviceIds = deviceIds;
	}

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
    

	public Integer[] getTempIds() {
		return tempIds;
	}

	public void setTempIds(Integer[] tempIds) {
		this.tempIds = tempIds;
	}

	public String right(){
		return "right";
	}

//查看所有的临时列表
    public String getDayList(){
    	List<TempCommandQueue> tCQList=tempCommandQueueService.findDayList();
    	ActionContext.getContext().put("tCQList", tCQList);	
    	List<Center> centerList1=centerService.findByEnprNo();
		ActionContext.getContext().put("centerList", centerList1);	
    	return "allcommandList";
    }
 //查看读集中器的信息
    public String getCenterReadList(){
    	List<TempCommandQueue> tCQList=	tempCommandQueueService.getByIds(tempIds);
    	ActionContext.getContext().put("tCQList", tCQList);		
    	List<Center> centerList1=centerService.findByEnprNo();
		ActionContext.getContext().put("centerList", centerList1);	
    	return "centerRead";
    }
	public String list() {
		List<Area> areaList = null;
		if (parentId == null) {
			areaList = areaService.getTopArea();
		} else {
			areaList = areaService.findChildren(parentId);
			Area parent = areaService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("areaList", areaList);
		return "list";
	}
//------------------------------------查询水表和集中器--------------------------------------
	public String findByCenterNo(){
		Center center=centerService.getByGprs(centerNo);
		List <Center> centerList=new ArrayList<Center>();
		if(center==null){
			addFieldError("centerNo", "不存在该集中器编号，请重新输入");
		}
		else{
			centerList.add(center);
			ActionContext.getContext().put("centerList", centerList);
		}
		return "centerList";
	}

	public String findByUserNo() {
    List<Device> deviceList =deviceService.getByUserNo(userNo);
    if(deviceList==null){
    	addFieldError("userNo", "不存在该用户编号，请重新输入");
    }else{
    	 Collections.sort(deviceList);
    	ActionContext.getContext().put("deviceList", deviceList);
    }
   
		return "deviceList";
	}
	public String findByUserAddr() {
	    List<Device> deviceList =deviceService.findByUserAddr(userAddr);
	    if(deviceList==null){
	    	addFieldError("userAddr", "不存在该用户地址，请重新输入");
	    }else{
	    	 Collections.sort(deviceList);
	    	ActionContext.getContext().put("deviceList", deviceList);
	    }
	   
			return "deviceList";
		}
	public String findByUserName(){
		  List<Device> deviceList =deviceService.findByUserName(userName);
		  if(deviceList==null){
		    	addFieldError("userName", "不存在该用户名，请重新输入");
		    }else{
		    	 Collections.sort(deviceList);
		    	ActionContext.getContext().put("deviceList", deviceList);
		    }
		return "deviceList";
	}
	public String findBydeviceNo(){
		Device device = deviceService.getByDeviceNo(deviceNom);
		List<Device> deviceList1 = new ArrayList<Device>();
		if (null == device) {
			addFieldError("deviceNom", "不存在该水表编号，请重新输入");
		}else {
			deviceList1.add(device);
			ActionContext.getContext().put("deviceList", deviceList1);
		}
		return "deviceList";
	}	

public String centerList(){
if(centerIds==null){
	Area area=areaService.getById(areaId);
	Set<Center> centers=area.getCenters();
	List<Center> centerList =new ArrayList<>();
	centerList.addAll(centers);
	List<Area> children=areaService.findChildren(areaId);
	if(children!=null){
		for(Area area1:children){
			if(area1.getId()!=null){
				Set<Center> centes =area1.getCenters();
				centerList.addAll(centes);
			}
		}
	}
		Collections.sort(centerList);
		ActionContext.getContext().put("centerList", centerList);	
}else{
	List<Center> centerList2 =centerService.getByIds(centerIds);
	Collections.sort(centerList2);
	ActionContext.getContext().put("centerList", centerList2);
}
	return "centerList";
}

public String deviceList() {
	  
	  String cmonth=cdate.getCurrentMonth();
	  String lmonth=cdate.getLastMonth();
	  Set<Device> deviceList1=new HashSet<Device>();
	// 根据right页面传回的区域id查询t_user表下对应的user
if(centerId!=null&&deviceIds==null&&centerIds==null){ 
	      List<DeviceRelation> dRList=deviceRelationService.getByCenterId(centerId);
	          if(dRList!=null){
	             	for(DeviceRelation dr:dRList){
			           Device device=dr.getDevice();
			           deviceList1.add(device);
		               }
	                   }
		}else if(centerIds!=null&&deviceIds==null&&centerId==null) {
			List<DeviceRelation> dRList1=new ArrayList<>();
			     for(Integer ceid:centerIds){
		          dRList1.addAll(deviceRelationService.getByCenterId(ceid));
		        	}
			     for(DeviceRelation dr:dRList1){
				Device device1=dr.getDevice();
				deviceList1.add(device1);
			        }    
	    }else{
			List<Device> deviceList2=deviceService.getByIds(deviceIds);
			deviceList1.addAll(deviceList2);
	    }
	for (Device device : deviceList1) {

		if (!"".equals(device.getAddr())&&device.getAddr()!= null ){
				 
			DeviceTmp  deviceTmpl = deviceTmpService.getLastRecord(device.getAddr(), cmonth,getEnprNo());
			if(deviceTmpl!=null){
			device.setShowValue(deviceTmpl.getShowValue());
			device.setReadTime(deviceTmpl.getReadTime());
			}
			
			DeviceTmp deviceTmpf=deviceTmpService.getFirstRecord(device.getAddr(), cmonth,getEnprNo());
			if(deviceTmpf!=null){
			if(deviceTmpf.getShowValue()!=null){
				device.setPrShowValue(deviceTmpf.getShowValue());
				device.setPrCeDate(deviceTmpf.getReadTime());
			}else{
				deviceTmpf=deviceTmpService.getLastRecord(device.getAddr(), lmonth,getEnprNo());
				device.setPrShowValue(deviceTmpf.getShowValue());
				device.setPrCeDate(deviceTmpf.getReadTime());
			}
			}
			
			deviceService.update(device);
		}
	}

	List<Device> deviceList = new ArrayList<Device>();
	deviceList.addAll(deviceList1);
	 Collections.sort(deviceList);
	ActionContext.getContext().put("deviceList", deviceList);
	
	return "deviceList";
}
//---------------------------------------水表命令-----------------------------------------
	//对水表发采集命令
	public String tempCollect(){
		List<Device> deviceList=deviceService.getByIds(deviceIds);
		List<Center> centerList= getCenters(deviceList);	
		commonCenterMe(StaticConstant.COLLECT_COMMAND,centerList);	
		ActionContext.getContext().put("deviceIds", deviceIds);
		return "commandList";
	}

    //对水表发数据读取命令
	public String dataLoad(){
		List<Device> deviceList=deviceService.getByIds(deviceIds);	
        List<Center>	centerList= getCenters(deviceList);
        commonCenterMe(StaticConstant.DATA_LOAD,centerList);
		ActionContext.getContext().put("deviceIds", deviceIds);
		return "commandList";
	}
	
	//水表开阀
	public String deviceOpen(){
		System.out.println("deviceIds:"+deviceIds.length);
		List<Device> deviceList=deviceService.getByIds(deviceIds);
		
		for(Device device:deviceList){
			device.setStrobeExecute(1);
		}
		commonDeviceMe(StaticConstant.DEVICE_OPEN, deviceList);
		ActionContext.getContext().put("deviceIds", deviceIds);
		return "commandList";
		}
	//水表关阀
	public String deviceClose(){
			List<Device> deviceList=deviceService.getByIds(deviceIds);
			for(Device device:deviceList){
				device.setStrobeExecute(2);
			}
			commonDeviceMe(StaticConstant.DEVICE_CLOSE, deviceList);
			ActionContext.getContext().put("deviceIds", deviceIds);
			return "commandList";
		}
//--------------------------------集中器命令-------------------------------		
	    //对集中器发采集命令
	    public String centerCollect(){
	    	List<Center> centerList =centerService.getByIds(centerIds);
	    	commonCenterMe(StaticConstant.COLLECT_COMMAND,centerList);	
	    	ActionContext.getContext().put("centerIds", centerIds);
	    	return "commandList";
	    }
	  //对集中器发数据读取命令
		public String centerDataLoad(){
			 List<Center>	centerList=centerService.getByIds(centerIds);
			 commonCenterMe(StaticConstant.DATA_LOAD,centerList);
			 ActionContext.getContext().put("centerIds", centerIds);
			return "commandList";	
		}
	    //读集中器信息
		public String centerRead(){
			List<Center> centerList=centerService.getByIds(centerIds);
			commonCenterMe(StaticConstant.CENTER_READ,centerList);
			int count=10;
			ActionContext.getContext().put("count", count);	
			return "centerRead";
		}
		//设备校时
	public String check() {
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.CENTER_CHECK,centerList);
		ActionContext.getContext().put("centerIds", centerIds);
		return "commandList";
	}	
	//下载用户档案
	public String userDownload() {
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.CENTER_USERDOWNLOAD,centerList);
		ActionContext.getContext().put("centerIds", centerIds);
		return "commandList";
	}
//----------------------------------------------------------------------------------------------

   //由水表拿到集中器list
	private List<Center> getCenters(List<Device> deviceList){
		
		Set<Integer> centerids=new HashSet<Integer>();
		for(Device device:deviceList){
			if(device.getDeviceRelation()!=null){
				if(device.getDeviceRelation().getCenterId()!=null){
		centerids.add(device.getDeviceRelation().getCenterId());
				}		
			}			
		}
		Integer[] cenIds=new Integer[centerids.size()];
		centerids.toArray(cenIds);	
        List<Center> centerList= centerService.getByIds(cenIds);	
        System.out.println("centerList.size:"+centerList.size());
		return centerList;
	}

	//把临时列表公用的字段写入
	private void setCommonValue(TempCommandQueue tempCQ){		
		tempCQ.setState(StaticConstant.STATE);			
		tempCQ.setEnprNo(getEnprNo());
		tempCQ.setGenerateTime(cdate.getCurrentTime());
		tempCQ.setRetryTimes(StaticConstant.TEMP_RETRYTIMES);		
		tempCQ.setOperator(getCurrentOperator().getUsername());
	}

	//由集中器拿到端口和ip
	private String getPortMessage(Center center){
		Port port=center.getPort();	
		Server server=port.getServer();
		String port1=server.getLocalIp()+":"+port.getPortNum();
		return port1;
	}
	//根据集中器list和命令写临时列表
	private void commonCenterMe(String command1,List<Center> centerList) {	
		List<TempCommandQueue> tCQList=new ArrayList<>();
		int len=getExetime(centerList);
		
		for(Center center:centerList){
			String port1="portId of center is null";
			if(center.getPort()!=null){
			 port1=getPortMessage(center);
			}	
			TempCommandQueue tempCQ=new TempCommandQueue();
			setCommonValue(tempCQ);
			if(command1.equals(StaticConstant.COLLECT_COMMAND)){
				String le=String.valueOf(len);
				tempCQ.setContentValue2(le);
				int len1=len*500+10000;
				tempCQ.setExecuteTime(len1);			
			}else if(command1.equals(StaticConstant.DATA_LOAD)){
				String le=String.valueOf(len);
				tempCQ.setContentValue2(le);
				tempCQ.setExecuteTime(StaticConstant.TEMP_EXECUTETIME);
			}
			else{
				tempCQ.setExecuteTime(StaticConstant.TEMP_EXECUTETIME);
			}
			tempCQ.setCommand(command1);	
			tempCQ.setContentValue1(center.getGprsNum());	
			tempCQ.setPort(port1);
			tempCommandQueueService.save(tempCQ);			
			tCQList.add(tempCQ);
		}
		ActionContext.getContext().put("tCQList", tCQList);	
		List<Center> centerList1=centerService.findByEnprNo();
		ActionContext.getContext().put("centerList", centerList1);		
	}
	//根据水表list和命令写临时列表
	private void commonDeviceMe(String command1,List<Device> deviceList) {	
		List<TempCommandQueue> tCQList=new ArrayList<>();
		for(Device device:deviceList){
			if(device.getDeviceRelation()!=null){
			Center center=centerService.getById(device.getDeviceRelation().getCenterId());
			String port1="portId of center is null";
			if(center.getPort()!=null){
			 port1=getPortMessage(center);
			}		
			Collection collection=collectionService.getById(device.getDeviceRelation().getCollectionId());
			TempCommandQueue tempCQ=new TempCommandQueue();
			setCommonValue(tempCQ);
			tempCQ.setCommand(command1);	
			if(center!=null){
				tempCQ.setContentValue1(center.getGprsNum());
			}		
			if(collection!=null){
				tempCQ.setContentValue2(collection.getAddress());
			}	
			tempCQ.setContentValue3(device.getAddr());		
			tempCQ.setPort(port1);
			tempCQ.setExecuteTime(StaticConstant.TEMP_EXECUTETIME);
			tempCommandQueueService.save(tempCQ);
			tCQList.add(tempCQ);
			}		
		}	
		ActionContext.getContext().put("tCQList", tCQList);	
		List<Center> centerList1=centerService.findByEnprNo();
		ActionContext.getContext().put("centerList", centerList1);
	}
	//把公用的东西存入值栈

	private int getExetime(List<Center> centerList){
		Set<DeviceRelation> drList1=new HashSet<DeviceRelation>();
		for(Center center:centerList){
			List<DeviceRelation> drList=deviceRelationService.getByCenterId(center.getId());
	
			if(drList!=null){
			drList1.addAll(drList);
			}
		}
		List<Device> deviceList=new ArrayList<>();
		for(DeviceRelation dr:drList1){
			Device device=dr.getDevice();
			System.out.println(device.getId());
			if(device!=null&&device.getStrobeStatue()!=null&&device.getStrobeStatue()==1){
				deviceList.add(device);
			}
		}
		int len=deviceList.size();
		return len;
	}
	

}
