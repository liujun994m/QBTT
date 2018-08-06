package hust.ioic.oa.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.CommandState;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.domain.PlanCommandQueue;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;
import hust.ioic.oa.domain.TempCommandQueue;


@Controller
@Scope("prototype")
public class MonitorAction extends BaseAction<Server> {

	public String list() {
		return "list";
	}

	public String right() {
		return "right";
	}

	public String server() {
		Set<Server> servers = new HashSet<>();
		// session中取出enterpriseId
	
		Set<Port> ports = enterpriseService.getByEnprNo(getEnprNo()).getPorts();
		for (Port port : ports) {
			servers.add(port.getServer());
		}
		ActionContext.getContext().put("servers", servers);
		return "server";
	}

	public String left() {
		return "left";
	}

	public String center() {

		List<Center> centers = findCentersByOperater();
		ActionContext.getContext().put("centers", centers);
		return "center";
	}

	public String collection() {
		List<Collection> collections = collectionService.findByEnprNo();
		ActionContext.getContext().put("collections", collections);
		return "collection";
	}

	public String device() {
		List<Device> devices = deviceService.findByEnprNo();
		ActionContext.getContext().put("devices", devices);
		return "device";
	}

	public String planCommandQueue() {

		List<PlanCommandQueue> planCommandQueues = planCommandQueueService.findByEnprNo();
		ActionContext.getContext().put("planCommandQueues", planCommandQueues);
		return "planCommandQueue";
	} 

	public String tempCommandQueue() {
		// 未完成的临时队列
		List<TempCommandQueue> tempCommandQueues = tempCommandQueueService.findByEnprNo();
		ActionContext.getContext().put("tempCommandQueues", tempCommandQueues);

		return "tempCommandQueue";
	}

	/**
	 * 根据session中的操作员找到操作员管理的集中器
	 * 
	 * @return
	 */
	private List<Center> findCentersByOperater() {
	/*	// 根据 操作员关联的区域，找到水表，根据水表找到该操作员管理的集中器
		Operator operator = (Operator) ActionContext.getContext().getSession()
				.get("operator");
		Set<Area> areaSet = operator.getAreas();

		// 由区域找到用户
		Set<User> userSet = new HashSet<User>();
		for (Area area : areaSet) {
			Set<User> muserList = area.getUsers();
			for (User user : muserList) {
				if (user != null) {
					userSet.add(user);
				}
			}
		}

		// 用户找到水表
		Set<Device> deviceSet = new HashSet<Device>();
		for (User user : userSet) {
			List<Device> tdevices = deviceService.getDevicesById(user.getId());
			for (Device device : tdevices) {
				if (device != null) {
					deviceSet.add(device);
				}
			}
		}

		// 水表找到集中器
		Set<Integer> centerids = new HashSet<Integer>();
		for (Device device : deviceSet) {
			if (device.getDeviceRelation() != null) {
				if (device.getDeviceRelation().getCenterId() != null) {
					centerids.add(device.getDeviceRelation().getCenterId());
				}
			}
		}
		Integer[] cenIds = new Integer[centerids.size()];
		centerids.toArray(cenIds);
		List<Center> centerList = centerService.getByIds(cenIds);
		*/
		return null;

	}

}
