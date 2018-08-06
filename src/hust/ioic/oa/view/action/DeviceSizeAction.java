package hust.ioic.oa.view.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceType;
import hust.ioic.oa.utils.StaticConstant;

/**
 * 无法确定大小表的逻辑怎么做，放
 * 大小表配置action
 * 逻辑要求：一个最底级区域，逻辑上只含有一个大表，即每个最低级的区域id仅仅
 * 					对应一个大表对象（要求大表是安装在最低级区域这一物理级别）？？
 * 程序实现逻辑功能：一个最低级区域不需要添加多个大表，自动将同一个区域的小表关联到该区域大表上，
 * 								目前只实现区域为最低级区域。当大表处在更上级区域时，需要该大表所在区域下面的
 * 								子区域所有大表。
 * @author lecky
 *
 */
@Controller
@Scope("prototype")
public class DeviceSizeAction extends BaseAction<Device> {
	//页面左侧区域传回的字段
	private Integer areaID;
	
	//checkbox传回被选中小表的id数组
	private Integer[] ids ;
	
	//页面传回的大表id
	private Integer deviceId;
	
	/**
	 * 显示所有大表列表
	 * 大表表示为parentId=0；
	 * @return
	 */
	public String list(){
		List<Device> bigDevices =  deviceService.getBigDevice();
		ActionContext.getContext().put("bigDevices", bigDevices);
		return "list";
	}
	
	/**
	 * 删除标注的大表，更新bigdeviceflag标志位的标志
	 * 删除单个大表
	 * 删除全部大表
	 */
	public String deleteBigDevice(){
		if(null != this.ids){//为批量删除
			List<Device>devices = deviceService.getByIds(this.ids);
			for(Device d:devices){
				d.setBigDeviceFlag(null);
				for(Device sd:d.getChildren())
					sd.setParent(null);
				deviceService.update(d);
			}
		}
		if(null != this.deviceId){//删除单个大表
			Device device = deviceService.getById(deviceId);
			device.setBigDeviceFlag(null);
			for(Device d :device.getChildren())
				d.setParent(null);
			deviceService.update(device);
		}
		return "toList";
	}
	
	
	/**
	 * 新增大表页面
	 * @return
	 */
	public String addBigDeviceUi(){
		return "addUi";
	}
	
	/**
	 * 区域下所有水表的列表
	 */
	public String deviceList(){
		List<Device> devices = deviceService.getByAreaId(areaID);
		ActionContext.getContext().put("devices", devices);
		this.deviceId=deviceId;
		return "editUi";
	}
	
	/**
	 * 添加大表或者小表
	 */
	public String add(){
		List<Device> devices = deviceService.getByIds(ids);
		if (deviceId == null) {//添加大表
			for (Device d : devices) {
				d.setBigDeviceFlag(StaticConstant.BIG_DEVICE_NUM);
				deviceService.update(d);
			}
		}else {//添加小表
			Device parent = deviceService.getById(deviceId);
			for(Device d:devices){
				d.setParent(parent);
				deviceService.update(d);
			}
		}
		return "toList";
	}
	
	/**
	 * 添加小表
	 */
	public String addSmallDeviceUi(){
		this.deviceId=deviceId;
		return "addUi";
	}
	
	/**
	 * 增加子表页面
	 * @return
	 */
	public String addSmallUi(){
		List<Device> smallDevices = deviceService.getByAreaId(areaID);
		ActionContext.getContext().put("smallDevices", smallDevices);
		this.deviceId=deviceId;
		return "addSmallUi";
	}
	
	public String addSmall(){
		List<Device> smallDevices = deviceService.getByIds(ids);//将页面传回的指定小表拿到
		Device parent = deviceService.getById(deviceId);
		for(Device device:smallDevices){
			device.setParent(parent);
			deviceService.update(device);
		}
		/*this.areaID = parent.getAreaId();*/
		return "toList";
	}
	
	/**
	 * 小表列表
	 * @return
	 */
	public String smallList(){
		if(null !=this.deviceId){
			Device parent = deviceService.getById(deviceId);
			List<Device> smallDevices = new ArrayList<>(parent.getChildren());
			ActionContext.getContext().put("smallDevices", smallDevices);
		}
		return "smallList";
	}
	
	/**
	 * 删除大表下的关联小表
	 * 删除单个小表
	 * 批量删除小表
	 * @return
	 */
	public String deleteSmallDevice(){
		if(null !=this.ids){
			List<Device>devices = deviceService.getByIds(this.ids);
			for(Device d:devices){
				d.setParent(null);
				deviceService.update(d);
			}
		}
		if(null != this.deviceId){
			Device device = deviceService.getById(deviceId);
			this.deviceId=device.getParent().getId();
			device.setParent(null);
			deviceService.update(device);
		}
		return "toSmallList";
	}
	
	/**
	 * 查看子表页面
	 * 根据t_deivce的areaId来查看大小表关联关系
	 * 根据页面传过来的areaId分两种情况：（使用area.getchildren()是否为空来判断是否为最低级区域）
	 * 1，该areaId对应的区域为最低级区域
	 * 2，该areaId对应的区域为上级区域：递归查出该区域下所有子区域内的小表
	 */
	/*public String smallList(){
		List<Device>smallDevices = new ArrayList<>();
		Area area = areaService.getById(areaID);//拿到页面传过来的区域对象
		if(null ==area.getChildren()){//若为空，这说明为最低级区域
			//查询areaID下所有的小表
			smallDevices = deviceService.getByAreaId(areaID);
		}else {//否则为更上级区域
			smallDevices = deviceService.getByAreaId(areaID);//查出目标区域下的小表，若不包含，则list为空
			Set<Area> areas = area.getChildren();
			addSmallDevice(areas,smallDevices);
		}
		ActionContext.getContext().put("smallDevices", smallDevices);
		return "smallList";
	}*/
	
	/**
	 * 递归函数，从最上级区域递归查找下级区域，将每一个区域的小表添加到smallDevice中
	 * @param areas
	 * @param smallDevices
	 */
	/*private void addSmallDevice(Set<Area> areas, List<Device> smallDevices) {
		for (Area area : areas) {//对于每个子区域
			List<Device>devices = deviceService.getByAreaId(area.getId());//取出该子区域下的小表并添加到list中（若有的话）
			if(null !=devices)
				smallDevices.addAll(devices);
			addSmallDevice(area.getChildren(), smallDevices);//遍历子区域
		}
	}*/

	/**
	 * 漏损分析页面
	 * @return
	 */
	public String waste(){
		//TODO 漏损分析函数
		return "waste";
	}
	
	/**
	 * 大表列表函数(一个区域id对应多个大表对象)
	 * @return
	 */
	/*public String bigDeviceList(){
		List<DeviceType> deviceTypes = deviceTypeService.getByName(StaticConstant.BIG_DEVICE_NAME);
		List<Device> bigDevices = new ArrayList<>();
		for(DeviceType deviceType:deviceTypes){
			List<Device> temp = deviceService.getBigDevicesByAreaId(areaID,deviceType.getId());//查询指定区域id下大表对象
			if(!temp.isEmpty())
				bigDevices.addAll(temp);
		}
		//TODO 页面显示集中器和采集器信息，使用map做（relastion作为key，集中器/采集器对象作为value）
		ActionContext.getContext().put("bigDevices", bigDevices);
		return "bigDeviceList";
	}*/
	
	/**
	 * 左侧区域列表函数
	 * @return
	 */
	public String left(){
		List<Area> areaList = areaList = areaService.getTopArea();
		ActionContext.getContext().put("areaList", areaList);
		this.deviceId=deviceId;
		return "left";
	}
	
	/**
	 * 右侧空白页面函数
	 * @return
	 */
	public String right(){
		return "right";
	}

	
	//*******************************getter setter************************************//
	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

}

