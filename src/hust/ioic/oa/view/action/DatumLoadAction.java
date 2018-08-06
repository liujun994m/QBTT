package hust.ioic.oa.view.action;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.BaseNumber;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.DeviceType;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.OptDeviceRecord;


import hust.ioic.oa.utils.ImportExecl;
import hust.ioic.oa.utils.StaticConstant;
@Controller
@Scope("prototype")
public class DatumLoadAction extends  BaseAction<Area>{
	private File upload;
    private String uploadFileName;
    private Integer parentId;
    String enprNo=getEnprNo();
    private   Map<String, Integer> mapa = new HashMap<String, Integer>();
    private   Map<String, Integer> mapdt = new HashMap<String, Integer>();
	public String list() {

		return "list";
	}
	public String newuser(){
		
		return "newuser";
	}
public String last(){
	return "last";
}
public String excelDownload(){
	
	 addFieldError("do", "excel已经下载到了你的D盘根目录，名字是：");	
	 return "newuser";
}
	public String ofileLoad(){
		ImportExecl poi = new ImportExecl();
		
		List<List<String>> lista = poi.read(uploadFileName,upload,1);
		areaLoad(lista);
		
	
	
		List<List<String>> listce= poi.read(uploadFileName,upload,2);
		centerLoad(listce);
		List<List<String>> listco= poi.read(uploadFileName,upload,3);
		collectionLoad(listco);
			
		List<List<String>> listdt= poi.read(uploadFileName,upload,4);
		deviceTypeLoad(listdt);
		
		List<List<String>> listd= poi.read(uploadFileName,upload,5);
	    deviceLoad(listd);
	    
	    List<List<String>> listbn= poi.read(uploadFileName,upload,6);
	    baseNumberLoad(listbn);
	    
	    List<List<String>> listop= poi.read(uploadFileName,upload,7);
	    optDeviceRecordLoad(listop);
	    
		return "fileLoad";
	}

    public String checkExcel(){
    	ImportExecl poi = new ImportExecl();
    	Set<String> optdeviceNo=new HashSet<String>();   	
       String k="";
    	 List<List<String>> listop= poi.read(uploadFileName,upload,7);
    	 if(listop != null){
			   for (int i = 1; i < listop.size(); i++)
				{
				   List<String> cellList = listop.get(i);
				   optdeviceNo.add(cellList.get(1));		   
				}
		   }
 	    Set<String> basedeviceNo=new HashSet<String>();
 	   List<List<String>> listbn= poi.read(uploadFileName,upload,6);
 	  if(listbn != null){
		   for (int i = 1; i < listbn.size(); i++)
			{
			   List<String> cellList = listbn.get(i);
			   basedeviceNo.add(cellList.get(1));			
	   }		   
 	  }
 	  
 	 Set<String> deNo=new HashSet<String>();
 	 deNo.add(null);
	   deNo.add(k);
 	 Set<String> deColname=new HashSet<String>();
 	 Set<String> deCenterN=new HashSet<String>();
 	 Set<String> deTypeId=new HashSet<String>(); 
	   List<List<String>> listd= poi.read(uploadFileName,upload,5);
	   if(listd != null){
		   for (int i = 1; i < listd.size(); i++)
			{
			   List<String> cellList = listd.get(i);
			   deNo.add(cellList.get(3));
			  
			   deTypeId.add(cellList.get(42));
			   deCenterN.add(cellList.get(47));
			   deColname.add(cellList.get(48));		   
	   }		   
 	  }
	   Set<String> deviceTypeId=new HashSet<String>();	
	   deviceTypeId.add(null);
	   deviceTypeId.add(k);
	   List<List<String>> listdt= poi.read(uploadFileName,upload,4);
	   if(listdt != null){
		   for (int i = 1; i < listdt.size(); i++)
			{
			   List<String> cellList = listdt.get(i);
			   deviceTypeId.add(cellList.get(0));
			  
	   }		   
 	  }


	   Set<String> colName=new HashSet<String>();
	   colName.add(null);
	   colName.add(k);
	   List<List<String>> listco= poi.read(uploadFileName,upload,3);
	   if(listco != null){
		   for (int i = 1; i < listco.size(); i++)
			{
			   List<String> cellList = listco.get(i);
			   colName.add(cellList.get(1));
			  
	   }		   
 	  }
	   Set<String> centerNo=new HashSet<String>();
	   centerNo.add(k);
	   centerNo.add(null);
	   List<List<String>> listcen= poi.read(uploadFileName,upload,2);
	   if(listcen != null){
		   for (int i = 1; i < listcen.size(); i++)
			{
			   List<String> cellList = listcen.get(i);
			   centerNo.add(cellList.get(3));			
	   }		   
 	  }


	   Set<String> areaId=new HashSet<String>();
	   areaId.add(k);
	   areaId.add(null);
	   Set<String> areapId=new HashSet<String>();
	   List<List<String>> lista= poi.read(uploadFileName,upload,1);
	   if(lista != null){
		   for (int i = 1; i < lista.size(); i++)
			{
			   List<String> cellList = lista.get(i);
			   areaId.add(cellList.get(0));	
			   areaId.add(null);	
			   if(getNum(cellList.get(3))!=0){
				   areapId.add(cellList.get(3));
			   }		
	   }		   
 	  }
	 if(!areaId.containsAll(areapId)){
          addFieldError("load", "区域表外键parentId中包含本表中主键id不存在的值,请检查excel！");		   
	   }
	
	   else if(!centerNo.containsAll(deCenterN)){
          addFieldError("load", "水表的centerNo中包含集中器表中字段gprsNum不存在的值,请检查excel！");		   
	   }
	   else if(!colName.containsAll(deColname)){
          addFieldError("load", "水表的collentionName中包含采集器表中字段name不存在的值,请检查excel！");		   
	   } 

	
	   else if(!deviceTypeId.containsAll(deTypeId)){
	          addFieldError("load", "水表外键deviceTypeId中包含设备类型表中主键id不存在的值,请检查excel！");		   
		   }
	   else if(!deNo.containsAll(basedeviceNo)){
		   addFieldError("load", "低数登记表中字段deviceNo中包含设备类型表中字段deviceNo不存在的值,请检查excel！");
	   }
	   else if(!deNo.containsAll(optdeviceNo)){
		   addFieldError("load", "操作记录表中字段deviceNo中包含设备类型表中字段deviceNo不存在的值,请检查excel！");
	   }
	   else{
			   addFieldError("load", "恭喜，要导入的excel符合要求！");
		   }
	
    	return "last";
    }
    
	private void areaLoad(List<List<String>> la){		
		if(la != null){
			for (int i = 1; i < la.size(); i++)
			{
				List<String> cellList = la.get(i);
				if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(4))){
					continue;
				}
				Area area=new Area();
				area.setName(cellList.get(1));
				area.setRemark(cellList.get(2));
				if(""!=cellList.get(3)&&cellList.get(3)!=null&&mapa.get(cellList.get(3))!=null){
					Area areap=null;
					if(getNum(cellList.get(3))!=0){
						 areap=areaService.getById(mapa.get(cellList.get(3)));
					}				
					area.setParent(areap);
				}
			
					areaService.save(area);
					mapa.put(cellList.get(0), area.getId());
				
				
			}	
			}
		System.out.println("areaLoad succeed!");
	}
	
	
/*	private void serverLoad(List<List<String>> lse){
		if(lse != null){
			for (int i = 1; i < lse.size(); i++)
			{
				List<String> cellList = lse.get(i);
				if(""==cellList.get(0)||cellList.get(0)==null){
					continue;
				}
				Server server=new Server();
				server.setName(cellList.get(1));
				server.setLastLoginTime(getTimestamp(cellList.get(2)));
				server.setRunStatus(getNum(cellList.get(3)));
				server.setOvertime(getNum(cellList.get(4)));
				server.setLocalIp(cellList.get(5));
				Server server1=serverService.getByName(cellList.get(1));
				if(server1==null){
					serverService.save(server);
					mapse.put(cellList.get(0), server.getId());
				}else{
					mapse.put(cellList.get(0), server1.getId());
				}
				
			}	
		}
	}
	private void portLoad(List<List<String>> lo){
		if(lo != null){
			for (int i = 1; i < lo.size(); i++)
			{
				List<String> cellList = lo.get(i);
				if(""==cellList.get(0)||cellList.get(0)==null){
					continue;
				}
				Port port=new Port();
				port.setName(cellList.get(1));
				port.setType(getNum(cellList.get(2)));
				port.setManagePortConfig(cellList.get(3));
				port.setOvertime(getNum(cellList.get(4)));
				port.setProtocolType(getNum(cellList.get(5)));
				port.setRemark(cellList.get(6));
				if(""!=cellList.get(7)&&cellList.get(7)!=null&&mapse.get(cellList.get(7))!=null){
					
					Server server=serverService.getById(mapse.get(cellList.get(7)));
					port.setServer(server);
					Port port1=portService.getByName(cellList.get(1));
					if(port1==null){
						portService.save(port);
						mappo.put(cellList.get(0), port.getId());
					}
					else{
						mappo.put(cellList.get(0), port1.getId());
					}
					
				}
			}	
		}
	}*/
	private void centerLoad(List<List<String>> lce){		
		if(lce != null){
			for (int i = 1; i < lce.size(); i++)
			{
				List<String> cellList = lce.get(i);
				if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(11))||getNum(cellList.get(2))==0){
					continue;
				}
				Center center=new Center();
				center.setName(cellList.get(1));
				if(getNum(cellList.get(2))==null){

				}
				else if(getNum(cellList.get(2))==1){
					center.setProtocolType(1);
				}else if(getNum(cellList.get(2))==4||getNum(cellList.get(2))==2){
					center.setProtocolType(2);
				}else{
					center.setProtocolType(4);
				}
				
				center.setGprsNum(cellList.get(3));
				center.setReadTime(getTimestamp(cellList.get(4)));
				center.setReadPeriod(getNum(cellList.get(5)));
				center.setReadType(getNum(cellList.get(6)));
				center.setRunStatue(getNum(cellList.get(7)));
				center.setIsUse(getNum(cellList.get(8)));
				center.setConfig(cellList.get(9));
				center.setRemark(cellList.get(10));
				center.setEnprNo(cellList.get(11));
					 centerService.save(center); 
				
			}	
		}
		System.out.println("centerLoad succeed!");

	}
	
	

	
	private void collectionLoad(List<List<String>> lco){
			if(lco != null){
				for (int i = 1; i < lco.size(); i++)
				{
					List<String> cellList = lco.get(i);
					if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(10))){
						continue;
					}
					Collection collection=new Collection();
					collection.setName(cellList.get(1));
					collection.setAddress(cellList.get(2));
					collection.setReadTime(getTimestamp(cellList.get(3)));
					collection.setReadCount(getNum(cellList.get(4)));
					collection.setReadType(getNum(cellList.get(5)));
					collection.setRunStatue(getNum(cellList.get(6)));
					collection.setIsUse(getNum(cellList.get(7)));
					collection.setRemark(cellList.get(8));
					collection.setEnprNo(cellList.get(10));
					 if(""!=cellList.get(9)&&cellList.get(9)!=null){
						 Center center=centerService.getByGprs(cellList.get(9));
						 collection.setCenter(center);
						 collectionService.save(collection); 
					 }		
					
						
					
					
				}	
			}
			System.out.println("collectionLoad succeed!");
	   }
	
	private void deviceTypeLoad(List<List<String>> ldt){
		   if(ldt != null){
			   for (int i = 1; i < ldt.size(); i++)
				{
				   List<String> cellList = ldt.get(i);
				   if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(10))){
						continue;
					}
				   DeviceType deviceType=new DeviceType();
				   deviceType.setName(cellList.get(1));
				   deviceType.setManufacture(cellList.get(2));
				   deviceType.setSpecification(cellList.get(3));
				   deviceType.setAddress(cellList.get(4));
				   deviceType.setImage(cellList.get(5));
				   deviceType.setShowNum(getNum(cellList.get(6)));
				   deviceType.setShowIndex(getNum(cellList.get(7)));
				   deviceType.setShowConfig(cellList.get(8));
				   deviceType.setRemark(cellList.get(9));
				   deviceType.setEnprNo(cellList.get(10));
					   deviceTypeService.save(deviceType);
					    mapdt.put(cellList.get(0),deviceType.getId());
				}
		   }
		   System.out.println("deviceTypeLoad succeed!");

	   }


	private void deviceLoad(List<List<String>> ld){

		   if(ld != null){
			   for (int i = 1; i < ld.size(); i++)
				{
				   List<String> cellList = ld.get(i);
				   if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(47))){
					   continue;
				   }
				   
				   Device device=new Device();
				    device.setName(cellList.get(1));
					device.setAddr(cellList.get(2));
					device.setDeviceNo(cellList.get(3));
					device.setReadTime(getTimestamp(cellList.get(4)));		
					device.setMaxNum(getBigDecimal(cellList.get(5)));
					device.setCeValue(getBigDecimal(cellList.get(6)));
					device.setOldValue(getBigDecimal(cellList.get(7)));
					device.setImultiple(getBigDecimal(cellList.get(8)));		
					device.setShowValue(getBigDecimal(cellList.get(9)));				
					device.setRunStatue(getNum(cellList.get(10)));
					
					device.setNeedAlarm(getNum(cellList.get(11)));
					device.setAlarmGetType(getNum(cellList.get(12)));
					device.setAlarmMax(getBigDecimal(cellList.get(13)));
					device.setAlarmMin(getBigDecimal(cellList.get(14)));
					device.setNowStatue(getNum(cellList.get(15)));
					device.setCommStatue(getNum(cellList.get(16)));
					device.setDeviceStatue(getNum(cellList.get(17)));
					device.setStrobeStatue(getNum(cellList.get(18)));	
					device.setAlarmDate(getTimestamp(cellList.get(19)));
					device.setStatus(getNum(cellList.get(20)));
					
					device.setCardID(cellList.get(21));
					device.setCeType(getNum(cellList.get(22)));
					device.setCreateDate(getTimestamp(cellList.get(23)));
					device.setSetupDate(getTimestamp(cellList.get(24)));
					device.setStopUse(getNum(cellList.get(25)));
					device.setShowType(getNum(cellList.get(26)));
					device.setStrobeDate(getTimestamp(cellList.get(27)));
					device.setStrobeExecute(getNum(cellList.get(28)));
					device.setAllowOper(getNum(cellList.get(29)));
					device.setManualOpen(getNum(cellList.get(30)));
					
					device.setPrShowValue(getBigDecimal(cellList.get(31)));
					device.setPrCeDate(getTimestamp(cellList.get(32)));
					device.setMonthCount(getBigDecimal(cellList.get(33)));
					device.setStopType(getNum(cellList.get(34)));
					device.setErrData(getBigDecimal(cellList.get(35)));
					device.setErrDate(getTimestamp(cellList.get(36)));
					device.setGetMoneyType(getNum(cellList.get(37)));
					device.setPrSaveValue(getBigDecimal(cellList.get(38)));	
			        device.setPrSaveDate(getTimestamp(cellList.get(39)));
			        device.setUserCountType(getNum(cellList.get(40)));
			 Area area=new Area();
			        if(""!=cellList.get(41)&&cellList.get(41)!=null&&mapa.get(cellList.get(41))!=null ){
			        	area=areaService.getById(mapa.get(cellList.get(41)));
			        device.setAreaId(mapa.get(cellList.get(41)));
			        
			        }
			        
			        if(""!=cellList.get(42)&&cellList.get(42)!=null&&mapdt.get(cellList.get(42))!=null ){
			        	  DeviceType deviceType=deviceTypeService.getById(mapdt.get(cellList.get(42)));	
					        device.setDeviceType(deviceType);
			        }
			        device.setUserNo(cellList.get(43));
			        device.setUserName(cellList.get(44));
			        device.setUserAddr(cellList.get(45));
			        if(""!=cellList.get(46)&&cellList.get(46)!=null){
			        	 Device devicep=deviceService.getByDeviceNo(cellList.get(46));
					        device.setParent(devicep);
			        }
			        if(""!=cellList.get(47)&&cellList.get(47)!=null&&""!=cellList.get(48)&&cellList.get(48)!=null){
			        	Center center=centerService.getByGprs(cellList.get(47));
			        	center.setArea(area);
			        	Collection collection=collectionService.getByName(cellList.get(48));
			        	
			            String co=collection.getName();
			        	DeviceRelation deviceRelation=new DeviceRelation();
			        	deviceRelation.setCenterId(center.getId());
			        	deviceRelation.setDevice(device);
			        	if(center.getProtocolType()!=null&&center.getProtocolType()==1&&chec(co,'9')==co.length()){
			        		deviceRelation.setCollectionId(-1);
			        	}else if(center.getProtocolType()!=null&&center.getProtocolType()==2&&chec(co,'0')==co.length()){
			        		deviceRelation.setCollectionId(StaticConstant.NO_COLLECTION);
			        	}else{
			        		deviceRelation.setCollectionId(collection.getId());
			        	}
			        	device.setDeviceRelation(deviceRelation);
			        	device.setEnprNo(cellList.get(49));
			        	deviceService.save(device);
			        	deviceRelationService.save(deviceRelation);
			        }			        
						    						
				}
		   }
		   System.out.println("deviceLoad succeed!");
	   }
	
	private void baseNumberLoad(List<List<String>> lbn){
				 if(lbn != null){
					   for (int i = 1; i < lbn.size(); i++)
						{
						   List<String> cellList = lbn.get(i);
						   if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(6))){
								continue;
							}
						  BaseNumber baseNumber=new BaseNumber();
						  Device device=deviceService.getByDeviceNo(cellList.get(1));
						  baseNumber.setDeviceId(device.getId());
						  baseNumber.setDeviceAddr(device.getAddr());
						  baseNumber.setCollectionAddr(cellList.get(2)); 
						  baseNumber.setCenterAddr(cellList.get(3));
						  baseNumber.setDeviceData(getBigDecimal(cellList.get(4)));
						  baseNumber.setWriteDate(getTimestamp(cellList.get(5)));
						  baseNumber.setEnprNo(cellList.get(6));
						  baseNumberService.save(baseNumber);		
						  
						}
				   }	
				 System.out.println("baseNumberLoad succeed!");
		}
	private void optDeviceRecordLoad(List<List<String>> lo){		
		   if(lo != null){
			   for (int i = 1; i < lo.size(); i++)
				{
				   List<String> cellList = lo.get(i);
				   if(""==cellList.get(0)||cellList.get(0)==null||!enprNo.equals(cellList.get(12))){
						continue;
					}
				   OptDeviceRecord optDeviceRecord=new OptDeviceRecord();			  
		           Device device=deviceService.getByDeviceNo(cellList.get(1));
		           optDeviceRecord.setDeviceId(device.getId());
				   optDeviceRecord.setDeviceNo(cellList.get(1));
				   optDeviceRecord.setDeviceName(device.getName());
				   optDeviceRecord.setOperateDate(getTimestamp(cellList.get(2)));
				   optDeviceRecord.setOperateMan(cellList.get(3));
				   optDeviceRecord.setOldReadData(getBigDecimal(cellList.get(4)));
				   optDeviceRecord.setNewReadData(getBigDecimal(cellList.get(5)));
				   optDeviceRecord.setOldDeviceNo(cellList.get(6));
				   optDeviceRecord.setNewDeviceNo(cellList.get(7));
				   optDeviceRecord.setOldDevID(cellList.get(8));
				   optDeviceRecord.setNewDevID(cellList.get(9));	
				   optDeviceRecord.setUserNo(cellList.get(10));			  
				   optDeviceRecord.setRemark(cellList.get(11));
				   optDeviceRecord.setEnprNo(cellList.get(12));
					   optDeviceRecordService.save(optDeviceRecord);

				   
				}
		   }
		   System.out.println("optDeviceRecordLoad succeed!");
	   }
	
			


	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
/*	private Integer getNum(String str1){
		String[] str=null;
		Integer lo=null;
		if(!"".equals(str1)&&str1!=null){
	    str=str1.split("\\.");
	    lo=Integer.parseInt(str[0]);
		}	
			
		
		return lo;
	}*/
	private Integer getNum(String str1){
		Integer lo=null;
		if(!"".equals(str1)&&str1!=null){
	   
	    lo=Integer.parseInt(str1);
		}	
			
		
		return lo;
	}
	private Timestamp getTimestamp(String st){
		Timestamp timestamp=null;
		if(!"".equals(st)&&st!=null){
			timestamp=Timestamp.valueOf(st);
			}	
		return timestamp;
	}
	private BigDecimal getBigDecimal(String str){

		if(!"".equals(str)&&str!=null){
			BigDecimal bg=new BigDecimal(str);
			return bg;
			}else{
				return null;
			}
		
	}
	private int chec(String col,char b){
		int le=col.length();
		int c=0;
		for(int k=0;k<le;k++){
			if(col.charAt(k)==b){
				c++;
			}
		}
		System.out.println(c);
		return c;
	}
	
}
