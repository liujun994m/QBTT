package hust.ioic.oa.view.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.directory.SearchControls;








import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.DeviceTmp;
import hust.ioic.oa.qilin.utils.GetDate;
import hust.ioic.oa.utils.MapKeyComparator;
@Controller
@Scope("prototype")
public class WaterExportAction extends  BaseAction<Area>{
	private String KSRQ;
	private String JSRQ;
    private String month;
    private Integer day;
	private String userNo;
	private String deviceNom;
	private String userName;
	private String userAddr;
	private Integer[] deviceIds;
	private Integer centerId;
	public String getKSRQ() {
		return KSRQ;
	}
	public void setKSRQ(String kSRQ) {
		KSRQ = kSRQ;
	}
	public String getJSRQ() {
		return JSRQ;
	}
	public void setJSRQ(String jSRQ) {
		JSRQ = jSRQ;
	}
	
	public Integer[] getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(Integer[] deviceIds) {
		this.deviceIds = deviceIds;
	}
	public String getUserNo() {
		return userNo;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String list(){
		List<Area> areaList = areaService.getTopArea();	
		ActionContext.getContext().put("areaList", areaList);
		return "list";
	}
	public String right(){
		return "right";
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String deviceList() {
		  Set<Device> deviceList1=new HashSet<Device>();
		 /* List<DeviceTmp> deviceTmps=new ArrayList<>();*/
			Collection<String> tables=deviceTmpService.getAllTables();
			Map<String, String> monthListf=new HashMap<String, String>();
			Map<Integer, Integer> dayList=new HashMap<Integer, Integer>();
			Iterator iterator=tables.iterator();
			while(iterator.hasNext()==true){	
					String m=iterator.next().toString().substring(11);
					monthListf.put(m,m);					
			}
			 Map<String, String> monthList = sortMapByKey(monthListf);
		// 根据right页面传回的区域id查询t_user表下对应的user
	if(deviceIds==null){ 
		      List<DeviceRelation> dRList=deviceRelationService.getByCenterId(centerId);
		          if(dRList!=null){
		             	for(DeviceRelation dr:dRList){
				           Device device=dr.getDevice();
				           deviceList1.add(device);
			               }
		                   }
		      	
		  		//拿到月份遍历
		  		 Iterator iterator2=monthList.entrySet().iterator();
		  		 if(iterator2.hasNext()){
		  		 Map.Entry entry1 =(Map.Entry) iterator2.next();
		  			 Collection<Integer> days=deviceTmpService.getDays(entry1.getKey().toString());
		  			 Iterator iterator3=days.iterator();
		  			 while(iterator3.hasNext()){
		  				 int d=(Integer)iterator3.next();
		  				 dayList.put(d, d);
		  			
		  		 }
		  		 }
			}else{
		
				List<Device> deviceList2=deviceService.getByIds(deviceIds);
				/*deviceList1.addAll(deviceList2);*/
				
		      	for (Device device : deviceList2) {
		      		if(device!=null){
					if (!"".equals(device.getAddr())&&device.getAddr()!= null ){
						DeviceTmp deviceTmp=deviceTmpService.getByDate(device.getAddr(), month, day);
						if(deviceTmp!=null){
						
							device.setShowValue(deviceTmp.getShowValue());
							device.setReadTime(deviceTmp.getReadTime());
							deviceService.update(device);
							deviceList1.add(device);
						}
					/*	deviceTmps.add(deviceTmp);	*/		
					}
		      		}
				}	      	
		      	
		      	 Collection<Integer> daym=deviceTmpService.getDays(month);
		      	 Iterator iteratord=daym.iterator();
		      	while(iteratord.hasNext()){
		      		int d=(Integer)iteratord.next();
		      		 dayList.put(d, d);
		      	}
		      	
		    }
	
		List<Device> deviceList = new ArrayList<Device>();
		deviceList.addAll(deviceList1);
		 Collections.sort(deviceList);
		ActionContext.getContext().put("deviceList", deviceList);
		//拿到所有的表名
	 
	
		 	/*ActionContext.getContext().put("deviceTmps", deviceTmps);	*/
			ActionContext.getContext().put("monthList", monthList);
			ActionContext.getContext().put("dayList", dayList);
			return "deviceTmpList";
		}

	
	public String findByUserNo() {
	    List<Device> deviceList =deviceService.getByUserNo(userNo);
	    if(deviceList==null){
	    	addFieldError("userNo", "不存在该用户编号，请重新输入");
	    }else{
	    	 Collections.sort(deviceList);
	    	ActionContext.getContext().put("deviceList", deviceList);
	    }
	   setCommon();
			return "deviceTmpList";
		}
	public String findByUserName(){
		  List<Device> deviceList =deviceService.findByUserName(userName);
		  if(deviceList==null){
		    	addFieldError("userName", "不存在该用户名，请重新输入");
		    }else{
		    	 Collections.sort(deviceList);
		    	ActionContext.getContext().put("deviceList", deviceList);
		    }
		  setCommon();
		return "deviceTmpList";
	}
	public String findByUserAddr() {
	    List<Device> deviceList =deviceService.findByUserAddr(userAddr);
	    if(deviceList==null){
	    	addFieldError("userAddr", "不存在该用户地址，请重新输入");
	    }else{
	    	 Collections.sort(deviceList);
	    	ActionContext.getContext().put("deviceList", deviceList);
	    }
	    setCommon();
			return "deviceTmpList";
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
		setCommon();
		return "deviceTmpList";
	}
	public void setCommon(){
		Collection<String> tables=deviceTmpService.getAllTables();
		Map<String, String> monthListf=new HashMap<String, String>();
		Map<Integer, Integer> dayList=new HashMap<Integer, Integer>();
		Iterator iterator=tables.iterator();
		while(iterator.hasNext()==true){	
				String m=iterator.next().toString().substring(11);
				monthListf.put(m,m);					
		}
		 Map<String, String> monthList = sortMapByKey(monthListf);
  		 Iterator iterator2=monthList.entrySet().iterator();
  		 if(iterator2.hasNext()){
  		 Map.Entry entry1 =(Map.Entry) iterator2.next();
  			 Collection<Integer> days=deviceTmpService.getDays(entry1.getKey().toString());
  			 Iterator iterator3=days.iterator();
  			 while(iterator3.hasNext()){
  				 int d=(Integer)iterator3.next();
  				 dayList.put(d, d);
  			
  		 }
  		 }
		ActionContext.getContext().put("monthList", monthList);
		ActionContext.getContext().put("dayList", dayList);
	
	}
	private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
 
        sortMap.putAll(map);
 
        return sortMap;
    }
	
}
