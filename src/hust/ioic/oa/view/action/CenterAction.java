package hust.ioic.oa.view.action;

import java.util.ArrayList;
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
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.ReadScheme;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.domain.UncompeletCommandQueue;
import hust.ioic.oa.utils.StaticConstant;
import hust.ioic.oa.utils.TimestampToDateTime;

@Controller
@Scope("prototype")
public class CenterAction extends BaseAction<Center> {

	private Integer portId;
	private Integer areaId;
	private Integer centerId;
	private Integer[] centerIds;

	public String addUi() {

		// 为了校验集中器编号和名称不重复传过去的参数
		List<Center> centerList = centerService.findByEnprNo();
		List<String> gprsNumList = new ArrayList<String>();
		List<String> centerNameList = new ArrayList<String>();
		for (Center c : centerList) {
			gprsNumList.add(c.getGprsNum());
			centerNameList.add(c.getName());

		}
		ActionContext.getContext().put("gprsNumList", gprsNumList);
		ActionContext.getContext().put("centerNameList", centerNameList);
		Area area = areaService.getById(areaId);
		ActionContext.getContext().put("area", area);

		Enterprise enterprise = enterpriseService.getByEnprNo(getEnprNo());

		// 该公司所在的不重复的端口列表
		Set<Port> portSet = enterprise.getPorts();
		List<Port> portList = new ArrayList<Port>();

		// 该公司所在的不重复的服务器列表
		for (Port p : portSet) {
			// 只选择正常运行的服务器
			if (p.getServer().getRunStatus() == 1) {
				portList.add(p);
			}
		}
		for (Port p : portList) {
			int portName = p.getPortNum();
			String protocalType = null;
			if (p.getProtocolType() == 1) {
				protocalType = "内部协议";

			} else if (p.getProtocolType() == 2) {
				protocalType = "130协议";
			} else if (p.getProtocolType() == 3) {
				protocalType = "中原油田协议";
			}
			p.setRemark(("服务器：" + p.getServer().getName() + " 端口：" + portName
					+ " 协议：" + protocalType));

		}
		// 端口
		ActionContext.getContext().put("portList", portList);

		// 抄表方案：
		List<ReadScheme> readSchemeList = readSchemeService.findByEnprNo();
		ActionContext.getContext().put("readSchemeList", readSchemeList);

		// 区域：
		ActionContext.getContext().put("areaId", areaId);
		ActionContext.getContext().put("centerId", centerId);
		

		return "add";
	}

	public String add() throws InterruptedException {

		// 设置对象属性
		Port p = portService.getById(portId);
		model.setPort(p);

		Area area = areaService.getById(areaId);
		model.setArea(area);

		model.setEnprNo(getEnprNo());
		model.setProtocolType(p.getProtocolType());

		ActionContext.getContext().put("areaId", areaId);

		// 集中器设备校时，作为添加集中器的依据
		TempCommandQueue tcq = getTempCommandQueue();
		tcq.setCommand(StaticConstant.ADD_CENTER_FLAG_COMMAND);
		tcq.setContentValue1(model.getGprsNum());
		tempCommandQueueService.save(tcq);

		// 再开线程处理其余三个命令出现失败的情况：定时抄表设置 ，下载用户档案，设置model信息

		/**
		 * 根据设置系统校时来判断是否添加该集中器
		 */

		if (processCommand(tcq.hashCode())) {

			centerService.save(model);
			// 如果添加集中器成功,就再发生3个临时命令
			final List<TempCommandQueue> temps = addTempCommand(model);
			if (temps != null && temps.size() > 0) {
				new Thread() {
					public void run() {
						for (TempCommandQueue t : temps) {
							try {
								if (!processCommand(t.hashCode())) {
									UncompeletCommandQueue uncompeletCommandQueue = new UncompeletCommandQueue(
											t); //
									// 如果未完成队列有这条记录，则不做操作
									if (uncompeletCommandQueueService
											.getByCenterAddr(
													model.getGprsNum(),
													t.getCommand())) { // 不需要填充json
										uncompeletCommandQueueService
												.save(uncompeletCommandQueue);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}

				}.start();
			}
			return "tolist";

		} else {

			return "fail";
		}

	}

	public String editUi() {

		ActionContext.getContext().put("areaId", areaId);
		Center center = centerService.getById(centerId);
		model.setId(centerId);
		model.setConfig(center.getConfig());
		model.setGprsNum(center.getGprsNum());
		model.setIsUse(center.getIsUse());
		model.setName(center.getName());
		model.setProtocolType(center.getProtocolType());
		model.setRemark(center.getRemark());
		model.setReadPeriod(center.getReadPeriod());
		model.setIsAutoCollection(center.getIsAutoCollection());
		model.setReadSchemeId(center.getReadSchemeId());
		model.setPort(center.getPort());
		model.setInstallAddr(center.getInstallAddr());

		Enterprise enterprise = enterpriseService.getByEnprNo(getEnprNo());

		// 该公司所在的不重复的端口列表
		Set<Port> portSet = enterprise.getPorts();
		List<Port> portList = new ArrayList<Port>();

		// 该公司所在的不重复的服务器列表
		for (Port p : portSet) {
			// 只选择正常运行的服务器和该集中器协议类型一致的端口
			if (p.getServer().getRunStatus() == 1
					&& p.getProtocolType() == center.getProtocolType()) {
				portList.add(p);
			}
		}

		// 端口
		ActionContext.getContext().put("portList", portList);
		// 抄表方案：
		List<ReadScheme> readSchemeList = readSchemeService.findByEnprNo();
		ActionContext.getContext().put("readSchemeList", readSchemeList);

		// 为了校验集中器编号和名称不重复传过去的参数

		List<Center> centerList = centerService.findByEnprNo();
		List<String> gprsNumList = new ArrayList<String>();
		List<String> centerNameList = new ArrayList<String>();
		centerList.remove(center);
		for (Center c : centerList) {
			gprsNumList.add(c.getGprsNum());
			centerNameList.add(c.getName());

		}
		ActionContext.getContext().put("gprsNumList", gprsNumList);
		ActionContext.getContext().put("centerNameList", centerNameList);

		return "edit";
	}

	public String edit() throws InterruptedException {
		Center center = centerService.getById(centerId);
		String addr = center.getGprsNum();

		center.setConfig(model.getConfig());

		center.setIsUse(model.getIsUse());
		center.setName(model.getName());
		// center.setRemark(model.getRemark());
		center.setReadPeriod(model.getReadPeriod());

		Port port = portService.getById(portId);
		center.setPort(port);
		center.setProtocolType(port.getProtocolType());

		center.setReadSchemeId(model.getReadSchemeId());
		
		center.setInstallAddr(model.getInstallAddr());

		// 如果集中器编号修改了，则发送写编号信息，让其重新下载水表信息
		if (!addr.equals(model.getGprsNum())) {

			// 设置集中器编号
			TempCommandQueue tcq = getTempCommandQueue();
			tcq.setCommand(StaticConstant.CENTER_USERDOWNLOAD);
			tcq.setContentValue1(model.getGprsNum());
			tempCommandQueueService.save(tcq);

			/**
			 * 查看修改集中器编号是否成功
			 */
			if (processCommand(tcq.hashCode())) {
				// 集中器编号一般不修改，修改代表换集中器
				center.setGprsNum(model.getGprsNum());
				centerService.update(center);
				return "tolist";

			} else {

				UncompeletCommandQueue uncompeletCommandQueue = new UncompeletCommandQueue(
						tcq);
				// 如果未完成队列有这条记录，则不做操作
				if (uncompeletCommandQueueService.getByCenterAddr(
						model.getGprsNum(), StaticConstant.CENTER_USERDOWNLOAD)) {
					uncompeletCommandQueue.setJsonofobj(StaticConstant.gson
							.toJson(model));
					uncompeletCommandQueueService.save(uncompeletCommandQueue);
				}
				return "fail";

			}

		}

		// 是否改变设置抄表时间
		if (center.getIsAutoCollection() == 0
				&& model.getIsAutoCollection() == 1) {

			TempCommandQueue tcq = getTempCommandQueue();
			tcq.setCommand(StaticConstant.SET_CENTER_COLLECTION_TIME_COMMAND);
			tcq.setContentValue1(center.getGprsNum());
			tcq.setContentValue2(2 + "");
			tcq.setContentValue3(0 + "");
			tempCommandQueueService.save(tcq);
			
			
			/**
			 * 查看设置抄表时间是否成功
			 */
			if (processCommand(tcq.hashCode())) {
				System.out.println("修改的集中器："+center.getId());
				Center mcenter = centerService.getById(center.getId());
				mcenter.setIsAutoCollection(center.getIsAutoCollection());
				centerService.update(mcenter);
				return "tolist";

			} else {

				UncompeletCommandQueue uncompeletCommandQueue = new UncompeletCommandQueue(
						tcq);
				// 如果未完成队列有这条记录，则不做操作
				if (uncompeletCommandQueueService.getByCenterAddr(
						center.getGprsNum(),
						StaticConstant.SET_CENTER_COLLECTION_TIME_COMMAND)) {
					uncompeletCommandQueue.setJsonofobj(StaticConstant.gson
							.toJson(center));
					uncompeletCommandQueueService.save(uncompeletCommandQueue);
				}
				return "fail";

			}

		}
		centerService.update(center);
		return "tolist";
	}

	public String delete() {

		for (Integer i : centerIds)
			centerService.delete(i);
		ActionContext.getContext().put("areaId", areaId);

		return "tolist";
	}

	/**
	 * 获取TempCommandQueue工具方法，已经填充 enterpriseId operator state port hashcode
	 * generateTime等字段
	 * 
	 * @return
	 */
	private TempCommandQueue getTempCommandQueue() {
		TempCommandQueue tcq = new TempCommandQueue();
		tcq.setGenerateTime(TimestampToDateTime.nowTime());
		tcq.setEnprNo(getEnprNo());
		tcq.setOperator((((Operator) ActionContext.getContext().getSession()
				.get("operator")).getUsername()));
		tcq.setState(0);
		// 获取该集中器所在的端口
		Port port = portService.getById(portId);
		// ip：port
		tcq.setPort(port.getServer().getLocalIp() + ":" + port.getPortNum());

		tcq.setHashCode(tcq.hashCode());

		return tcq;
	}

	/**
	 * 添加集中器操作时写入临时命令表，包含定时采集设置、设置model信息、写集中器编号这三个命令
	 * 
	 * @param center
	 * @return 临时队列List
	 */
	public List<TempCommandQueue> addTempCommand(Center center) {
		List<TempCommandQueue> temps = new ArrayList<TempCommandQueue>();
		if (center.getIsAutoCollection() == 1) {
			// 设置定时采集
			TempCommandQueue tcq = getTempCommandQueue();
			tcq.setCommand(StaticConstant.SET_CENTER_COLLECTION_TIME_COMMAND);
			tcq.setContentValue1(center.getGprsNum());
			tcq.setContentValue2(2 + "");
			tcq.setContentValue3(0 + "");
			tempCommandQueueService.save(tcq);
			temps.add(tcq);
		}

		{// 设置集中器的配置信息
			TempCommandQueue tcq = getTempCommandQueue();
			tcq.setCommand(StaticConstant.SET_CENTER_Model_INFO_COMMAND);
			tcq.setContentValue1(center.getGprsNum());
			tcq.setContentValue2(center.getModem());
			tempCommandQueueService.save(tcq);
			temps.add(tcq);
		}

		// 添加集中器的时候写编号到集中器中,即下载用户档案
		{
			TempCommandQueue tcq = getTempCommandQueue();
			tcq.setCommand(StaticConstant.CENTER_USERDOWNLOAD);
			tcq.setContentValue1(center.getGprsNum());
			tempCommandQueueService.save(tcq);
			temps.add(tcq);
		}

		return temps;

	}

	/***************** getter&&setter ******************************/

	public Integer getPortId() {
		return portId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
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

	public Integer[] getCenterIds() {
		return centerIds;
	}

	public void setCenterIds(Integer[] centerIds) {
		this.centerIds = centerIds;
	}

}
