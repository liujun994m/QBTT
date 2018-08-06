package hust.ioic.oa.view.action;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.utils.AreaUtils;
import hust.ioic.oa.utils.BusinessException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class AreaAction extends BaseAction<Area> {
	
	private Integer parentId;
  
	/**
	 * 列表
	 * @return
	 */
	public String list(){
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

	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		areaService.delete(model.getId());
		return "toList";
	}

	/**
	 * 更改页面
	 * @return
	 */
	public String editUI() {
		List<Area> topList = areaService.getTopArea();
		List<Area> areaList = AreaUtils.getAllArea(topList);
		ActionContext.getContext().put("areaList", areaList);

		Area area = areaService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(area);
		if (area.getParent() != null) {
			parentId = area.getParent().getId();
		}
		return "saveUI";
	}

	/**
	 * 更改
	 * @return
	 */
	public String edit() {
		Area area = areaService.getById(model.getId());
		area.setName(model.getName());
		area.setRemark(model.getRemark());
		Area parent = areaService.getById(parentId);
		area.setParent(parent);
      
		areaService.update(area);
		return "toList";
	}

	/**
	 * 添加页面
	 * @return
	 */
	public String addUI() {
		List<Area> topList = areaService.getTopArea();
		List<Area> areaList = AreaUtils.getAllArea(topList);
		ActionContext.getContext().put("areaList", areaList);
        List<String> areaName=new ArrayList<String>();
        List<Area> allList=areaService.findByEnprNo();
        for(Area area:allList){
        	if(area!=null){
        	areaName.add(area.getName());
        	}
        }
        ActionContext.getContext().put("areaName", areaName);
		return "saveUI";
	}

	/**
	 * 添加
	 * @return
	 */
	public String add() {
		Area parent = areaService.getById(parentId);
		model.setParent(parent);
        model.setEnprNo(getEnprNo());
		areaService.save(model);
		return "toList";
	}

	
	//====================Getter and Setter==================================
	
	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
