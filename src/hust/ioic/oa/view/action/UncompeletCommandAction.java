package hust.ioic.oa.view.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.domain.OptDeviceRecord;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.domain.UncompeletCommandQueue;
import hust.ioic.oa.utils.StaticConstant;
import hust.ioic.oa.utils.TimestampToDateTime;
/**
 * 未完成队列处理action
 * @author lecky
 *
 */
@Controller
@Scope("prototype")
public class UncompeletCommandAction extends BaseAction<UncompeletCommandQueue> {
	String enpr=(String)ActionContext.getContext().getSession().get("enprNo");
	public String list() {
		// 根据当前登录的操作员选择相应的未完成命令
		Operator operator = (Operator) ActionContext.getContext().getSession().get("operator");
		List<UncompeletCommandQueue> unCommandQueues = uncompeletCommandQueueService.getByUsername(operator.getUsername());
		ActionContext.getContext().put("unCommandQueues", unCommandQueues);
		Map<String, String> commandMap = new HashMap<String, String>();// 命令描述map
		commandMap.put(StaticConstant.DELETE_DEVICE_COMMAND, "删除水表");
		commandMap.put(StaticConstant.ADD_DEVICE_COMMAND, "新增水表");
		commandMap.put(StaticConstant.CHANGE_DEVICE_COMMAND, "换表");
		ActionContext.getContext().put("commandMap", commandMap);
		return "list";
	}

	public String delete() {
		uncompeletCommandQueueService.delete(getModel().getId());
		return "toList";
	}

	/**
	 * 未完成队列命令再执行 1，发送命令 2，等待执行结果 3，根据执行结果做出相应的操作
	 * 
	 * @throws InterruptedException
	 */
	public String execute() throws InterruptedException {
		UncompeletCommandQueue unCompeletCommandQueue = uncompeletCommandQueueService
				.getById(getModel().getId());
		// TODO 类型转换问题，（不能使用向上自动转换）
		TempCommandQueue temp = new TempCommandQueue(unCompeletCommandQueue);
		temp.setHashCode(temp.hashCode());
		// = (TempCommandQueue)unCompeletCommandQueue;
		tempCommandQueueService.save(temp);// 发送命令
		if (processCommand(temp.getHashCode())) {// 执行成功
			if (StaticConstant.DELETE_DEVICE_COMMAND.equals(temp.getCommand())) {
				deleteDevice(unCompeletCommandQueue);//
			} else if (StaticConstant.ADD_DEVICE_COMMAND.equals(temp
					.getCommand())) {
				addDevice(unCompeletCommandQueue);//
			} else if (StaticConstant.CHANGE_DEVICE_COMMAND.equals(temp
					.getCommand())) {
				changeDevice(unCompeletCommandQueue);//
			}
			// 如果是添加集中器前测试是否集中器已安装，即添加集中器命令
			else if (StaticConstant.ADD_CENTER_FLAG_COMMAND.equals(temp.getCommand())) {
				addCenter(unCompeletCommandQueue);
				// 修改集中器编号的时候，更新集中器地址
			} else if (StaticConstant.CENTER_USERDOWNLOAD.equals(temp
					.getCommand())) {
				updateCenter(unCompeletCommandQueue);
				// 修改定时采集的时候
			} else if (StaticConstant.SET_CENTER_COLLECTION_TIME_COMMAND
					.equals(temp.getCommand())) {
				updateCenter(unCompeletCommandQueue);
			}

			// 关于添加集中器时候的其余的几个命令：设置定时抄表时间，设置model信息，下载用户档案，执行成功的时候不做操作
			uncompeletCommandQueueService
					.delete(unCompeletCommandQueue.getId());
			return "toList";
		}
		return "fail";
	}

	/**
	 * 换表
	 * 
	 * @param unCommandQueue数据源
	 */
	private void changeDevice(UncompeletCommandQueue unCommandQueue) {
		Device device = deviceService.getByAddr(unCommandQueue
				.getContentValue3());
		String[] jsonString = unCommandQueue.getJsonofobj().split(
				StaticConstant.SPLIT_FLAG);
		Device tempDevice = StaticConstant.gson.fromJson(jsonString[0],
				Device.class);
		OptDeviceRecord optDeviceRecord = StaticConstant.gson.fromJson(
				jsonString[1], OptDeviceRecord.class);
		device.setCardID(tempDevice.getCardID());
		device.setAddr(tempDevice.getAddr());
		deviceService.update(device);
		optDeviceRecordService.save(optDeviceRecord);
	}

	/**
	 * 添加水表 从数据源中取出json格式的对象字符串并转成对象
	 * 
	 * @param unCommandQueue数据源
	 */
	private void addDevice(UncompeletCommandQueue unCommandQueue) {
		Device device = StaticConstant.gson.fromJson(
				unCommandQueue.getJsonofobj(), Device.class);
		DeviceRelation relation = device.getDeviceRelation();
		relation.setDevice(device);
		// 保存先后顺序不能更改
		deviceService.save(device);
		deviceRelationService.save(relation);
	}

	/**
	 * 删除水表执行成功
	 * 
	 * @param unCommandQueue
	 */
	private void deleteDevice(UncompeletCommandQueue unCommandQueue) {
		Device device = deviceService.getByAddr(unCommandQueue
				.getContentValue3());
		device.setStatus(StaticConstant.DEVICE_STATUS_DROP);
		deviceService.update(device);
	}

	/**
	 * 添加集中器设备
	 * 
	 * @param unCommandQueue
	 */
	private void addCenter(UncompeletCommandQueue unCommandQueue) {
		Center center = StaticConstant.gson.fromJson(
				unCommandQueue.getJsonofobj(), Center.class);
		centerService.save(center);

		if (center.getIsAutoCollection() == 1) {
			// 设置定时采集
			TempCommandQueue tcq = getTempCommandQueue(center);
			tcq.setCommand(StaticConstant.SET_CENTER_COLLECTION_TIME_COMMAND);
			tcq.setContentValue1(center.getGprsNum());
			tcq.setContentValue2(2 + "");
			tcq.setContentValue3(0 + "");
			tempCommandQueueService.save(tcq);
		}

		{// 设置model信息,配置信息
			TempCommandQueue tcq = getTempCommandQueue(center);
			tcq.setCommand(StaticConstant.SET_CENTER_Model_INFO_COMMAND);
			tcq.setContentValue1(center.getGprsNum());
			tcq.setContentValue2(center.getConfig());
			tempCommandQueueService.save(tcq);
		}

		// 添加集中器的时候写编号到集中器中,即下载用户档案
		{
			TempCommandQueue tcq = getTempCommandQueue(center);
			tcq.setCommand(StaticConstant.CENTER_USERDOWNLOAD);
			tcq.setContentValue1(center.getGprsNum());
			tempCommandQueueService.save(tcq);
		}

	}

	/**
	 * 更新集中器
	 * 
	 * @param unCommandQueue
	 */
	private void updateCenter(UncompeletCommandQueue unCommandQueue) {
		if (unCommandQueue.getJsonofobj() != null
				&& (unCommandQueue.getJsonofobj().equalsIgnoreCase("null") == false)) {
			Center center = StaticConstant.gson.fromJson(
					unCommandQueue.getJsonofobj(), Center.class);

			Center mCenter = centerService.getById(center.getId());
			// 修改集中器编号
			if (unCommandQueue.getCommand().equals(
					StaticConstant.CENTER_USERDOWNLOAD)) {
				mCenter.setGprsNum(center.getGprsNum());
			}
			// 设置定时采集
			else if (unCommandQueue.getCommand().equals(
					StaticConstant.SET_CENTER_COLLECTION_TIME_COMMAND)) {
				mCenter.setIsAutoCollection(center.getIsAutoCollection());
			}
			centerService.update(mCenter);
		}
	}

	/**
	 * 获取TempCommandQueue工具方法，已经填充 enterpriseId operator state port hashcode
	 * generateTime等字段
	 * 
	 * @return TempCommandQueue实例
	 */
	private TempCommandQueue getTempCommandQueue(Center center) {
		TempCommandQueue tcq = new TempCommandQueue();
		tcq.setGenerateTime(TimestampToDateTime.nowTime());
		tcq.setEnprNo(enpr);
		tcq.setOperator((((Operator) ActionContext.getContext().getSession()
				.get("operator")).getUsername()));
		tcq.setState(0);
		// 获取该集中器所在的端口
		Port port = center.getPort();
		// ip：port
		tcq.setPort(port.getServer().getLocalIp() + ":" + port.getPortNum());

		tcq.setHashCode(tcq.hashCode());

		return tcq;
	}

}
