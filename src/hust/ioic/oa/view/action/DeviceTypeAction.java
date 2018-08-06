package hust.ioic.oa.view.action;

/**
 * 设备类型数据源在staticConstant类中
 */
import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.DeviceType;
import hust.ioic.oa.utils.StaticConstant;

import java.util.List;

import org.jboss.weld.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DeviceTypeAction extends BaseAction<DeviceType> {

/*	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}*/

	/**
	 * 列表
	 * @return
	 */
	public String list() {
		List<DeviceType> deviceTypeList = deviceTypeService.findByEnprNo();
		ActionContext.getContext().put("deviceTypeList", deviceTypeList);
		return "list";
		
	}

	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		deviceTypeService.delete(model.getId());
		return "toList";
	}

	/**
	 * 更改页面
	 * @return
	 */
	public String editUI() {
		DeviceType deviceType = deviceTypeService.getById(model.getId());
//		ActionContext.getContext().put("deviceType",StaticConstant.DEVICETYPE_MAP );
		ActionContext.getContext().getValueStack().push(deviceType);
		return "saveUI";
	}

	public String edit() {
		// 1.从数据库拿出原数据
		
		DeviceType deviceType = deviceTypeService.getById(model.getId());
		// 2.设置修改的数据
		deviceType.setName(model.getName());
		deviceType.setManufacture(model.getManufacture());
		deviceType.setSpecification(model.getSpecification());
		deviceType.setAddress(model.getAddress());
		deviceType.setImage(model.getImage());
		deviceType.setShowNum(model.getShowNum());
		deviceType.setShowIndex(model.getShowIndex());
		deviceType.setShowConfig(model.getShowConfig());
		deviceType.setRemark(model.getRemark());
		// 3.保存数据
		deviceTypeService.update(deviceType);
		return "toList";
	}

	/**
	 * 添加页面
	 * @return
	 */
	public String addUI() {
//		ActionContext.getContext().put("deviceType",StaticConstant.DEVICETYPE_MAP );
		return "saveUI";
	}


	public String add() {
		String enprNo = (String) ActionContext.getContext().getSession().get("enprNo");
		 model.setEnprNo(enprNo);
		deviceTypeService.save(model);
		return "toList";
	}

	

}
