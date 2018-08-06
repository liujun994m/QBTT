package hust.ioic.oa.view.action;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.domain.PlanCommandQueue;
import hust.ioic.oa.qilin.utils.GetDate;
import hust.ioic.oa.utils.StaticConstant;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PlanCQAction extends BaseAction<PlanCommandQueue> {
	private Integer[] deviceIds;
	private Integer[] centerIds;
	private Integer day;
	private String hour;
	private String minute;
	String enpr=(String)ActionContext.getContext().getSession().get("enprNo");
	GetDate cdate = new GetDate();
	public String flush(){
		putAc();
		return "plancommandList";
	}
	public String planCollect(){
		
		Timestamp t=planexTime(day, hour, minute);
		System.out.println(t);
		List<Device> deviceList=deviceService.getByIds(deviceIds);
		List<Center> centerList= getCenters(deviceList);
		commonCenterMe(StaticConstant.COLLECT_COMMAND,centerList,t);	
		putAc();
		return "plancommandList";
	}
	public String centerCollect(){
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.COLLECT_COMMAND,centerList,t);	
		putAc();
		return "plancommandList";
	}
	public String dataLoad(){
		Timestamp t=planexTime(day, hour, minute);
		List<Device> deviceList=deviceService.getByIds(deviceIds);	
        List<Center>	centerList= getCenters(deviceList);
        commonCenterMe(StaticConstant.DATA_LOAD,centerList,t);
        putAc();
        return "plancommandList";
	} 
	public String centerDataLoad(){
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.DATA_LOAD,centerList,t);
	    putAc();
	    return "plancommandList";
	}
	public String centerRead(){
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.CENTER_READ,centerList,t);
	    putAc();
		 return "plancommandList";
	}
	public String deviceOpen(){
		Timestamp t=planexTime(day, hour, minute);
		List<Device> deviceList=deviceService.getByIds(deviceIds);
		for(Device device:deviceList){
			device.setStrobeExecute(1);
		}
		commonDeviceMe(StaticConstant.DEVICE_OPEN, deviceList,t);
		putAc();
		 return "plancommandList";
	}
	public String deviceClose(){
		Timestamp t=planexTime(day, hour, minute);
		List<Device> deviceList=deviceService.getByIds(deviceIds);
		for(Device device:deviceList){
			device.setStrobeExecute(2);
		}
		commonDeviceMe(StaticConstant.DEVICE_CLOSE, deviceList,t);
		putAc();
		 return "plancommandList";
	}
	
	/*public String centerOpen(){
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		
		Set<DeviceRelation> drList=new HashSet<DeviceRelation>();
		for(Center center:centerList){
			List<DeviceRelation> drList2=deviceRelationService.getByCenterId(center.getId());
			if(drList2!=null){
			drList.addAll(drList2);
			}
		}	
		for(DeviceRelation dr:drList){
			Device device=dr.getDevice();
			if(device!=null){
				device.setStrobeExecute(1);
			}
		}
		
		commonCenterMe(StaticConstant.CENTER_OPEN,centerList,t);	
		putAc();
		return "plancommandList";
		}
	public String centerClose(){
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		
		Set<DeviceRelation> drList=new HashSet<DeviceRelation>();
		for(Center center:centerList){
			List<DeviceRelation> drList2=deviceRelationService.getByCenterId(center.getId());
			if(drList2!=null){
			drList.addAll(drList2);
			}
		}	
		for(DeviceRelation dr:drList){
			Device device=dr.getDevice();
			if(device!=null){
				device.setStrobeExecute(2);
			}
		}
		
		commonCenterMe(StaticConstant.CENTER_CLOSE,centerList,t);	
		putAc();
		return "plancommandList";
		}*/
	
	public String check() {
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.CENTER_CHECK,centerList,t);
		putAc();
		return "plancommandList";
	}
	public String userDownload() {
		Timestamp t=planexTime(day, hour, minute);
		List<Center> centerList=centerService.getByIds(centerIds);
		commonCenterMe(StaticConstant.CENTER_USERDOWNLOAD,centerList,t);
		putAc();
		return "plancommandList";
	}
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
		return centerList;
	}
	
	private void commonCenterMe(String command1,List<Center> centerList,Timestamp t) {	
		int len=getExetime(centerList);
		for(Center center:centerList){
			String ip="ip is null";
			if(center.getPort()!=null){
			 ip=getIpMessage(center);
			}	
			PlanCommandQueue planCQ=new PlanCommandQueue();
			setCommonValue(planCQ);
			planCQ.setCommand(command1);	
			if(command1.equals(StaticConstant.COLLECT_COMMAND)){
				String le=String.valueOf(len);
				planCQ.setContentValue2(le);
				int len1=len*500+10000;
				planCQ.setExecuteTime(len1);
			}else if(command1.equals(StaticConstant.DATA_LOAD)){
				String le=String.valueOf(len);
				planCQ.setContentValue2(le);
				planCQ.setExecuteTime(StaticConstant.PLAN_EXECUTETIME);
			}
			else{
				planCQ.setExecuteTime(StaticConstant.PLAN_EXECUTETIME);
			}
			planCQ.setContentValue1(center.getGprsNum());						
			planCQ.setIp(ip);
			planCQ.setPlanExecuteTime(t);
			planCQ.setProtocolType(center.getProtocolType());
			planCommandQueueService.save(planCQ);					
		}
				
	}
	//把临时列表公用的字段写入
	private void setCommonValue(PlanCommandQueue planCQ){		
		planCQ.setState(StaticConstant.STATE);			
		planCQ.setEnprNo(enpr);
		planCQ.setGenerateTime(cdate.getCurrentTime());
		planCQ.setRetryTimes(StaticConstant.PLAN_RETRYTIMES);
		planCQ.setOperator(getCurrentOperator().getUsername());
		planCQ.setCommandType(StaticConstant.COMMANDTYPE);
		planCQ.setCommandSource(StaticConstant.COMMANDSOURCE);
		planCQ.setIsLinkedLastCmd(StaticConstant.ISLINKEDLASTCMD);
		planCQ.setScheduleState(StaticConstant.SCHEDULESTATE);
		planCQ.setRetryIntervalTime(StaticConstant.RETRYINTERVALTIME);
	}
	//由集中器拿到端口和ip
	private String getIpMessage(Center center){
		Port port=center.getPort();		
		Server server=port.getServer();
		String ip=server.getLocalIp();
		return ip;
	}
	//根据集中器list和命令写临时列表
	private void commonDeviceMe(String command1,List<Device> deviceList,Timestamp t) {	
		for(Device device:deviceList){
			if(device.getDeviceRelation()!=null){
			Center center=centerService.getById(device.getDeviceRelation().getCenterId());
			String ip="ip is null";
			if(center.getPort()!=null){
			 ip=getIpMessage(center);
			}		
			Collection collection=collectionService.getById(device.getDeviceRelation().getCollectionId());
			PlanCommandQueue planCQ=new PlanCommandQueue();
			setCommonValue(planCQ);
			planCQ.setCommand(command1);	
			if(center!=null){
				planCQ.setContentValue1(center.getGprsNum());
			}
		  if(collection!=null){
			  planCQ.setContentValue2(collection.getAddress());
		  }		
	    	planCQ.setContentValue3(device.getAddr());	
			planCQ.setExecuteTime(StaticConstant.PLAN_EXECUTETIME);
			planCQ.setIp(ip);
			planCQ.setPlanExecuteTime(t);
			planCommandQueueService.save(planCQ);
			
			}		
		}			
	}
	
	private Integer getExetime(List<Center> centerList){
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
			if(device!=null&&device.getStrobeStatue()!=null&&device.getStrobeExecute()==1){
				deviceList.add(device);
			}
		}
		int len=deviceList.size();
		return len;
	}
	
	private Timestamp planexTime(Integer day,String hour,String minute){
		String rq=cdate.getdate(day-1)+" "+hour+":"+minute+":00";
		 Timestamp time=Timestamp.valueOf(rq);
		 return time;
		
	}
	private void putAc(){
		List<Center> centerList1=centerService.findByEnprNo();
		ActionContext.getContext().put("centerList", centerList1);
	    List<PlanCommandQueue> pCQList=planCommandQueueService.getByOperator(getCurrentOperator().getUsername());
		ActionContext.getContext().put("pCQList", pCQList);		
	}
	public Integer[] getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(Integer[] deviceIds) {
		this.deviceIds = deviceIds;
	}
	public Integer[] getCenterIds() {
		return centerIds;
	}
	public void setCenterIds(Integer[] centerIds) {
		this.centerIds = centerIds;
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
	
}
