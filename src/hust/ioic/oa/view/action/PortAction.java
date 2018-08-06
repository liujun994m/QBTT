package hust.ioic.oa.view.action;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;

@Controller
@Scope("prototype")
public class PortAction extends BaseAction<Port> {
	private Integer serverId;
	
	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String addUi()
	{
		Server server=serverService.getById(serverId);
		
		ActionContext.getContext().put("server", server);
		return "add";
	}
	
	public String add()
	{
		Server server=serverService.getById(serverId);
		model.setServer(server);
		portService.save(model);
		
		return "toList";
	}
	
	public String delete()
	{
		
		portService.delete(model.getId());
		return "toList";
	}
	
	public String editUi()
	{
		
		Port port=portService.getById(model.getId());
		model.setName(port.getName());
		model.setManagePortConfig(port.getCollectionPortConfig());
		model.setCollectionPortConfig(port.getCollectionPortConfig());
		model.setOvertime(port.getOvertime());
		model.setProtocolType(port.getProtocolType());
		model.setRemark(port.getRemark());
		model.setServer(port.getServer());
		model.setPortNum(port.getPortNum());
	/*	List<Server> serverList=serverService.findAll();
		ActionContext.getContext().put("serverList", serverList);*/
		
		return "edit";
	}
	
	public String edit()
	{
		Server server=serverService.getById(serverId);
		Port port=portService.getById(model.getId());
		port.setName(model.getName());
		port.setType(model.getType());
		port.setManagePortConfig(model.getManagePortConfig());
		port.setCollectionPortConfig(model.getCollectionPortConfig());
		port.setOvertime(model.getOvertime());
		port.setProtocolType(model.getProtocolType());
		port.setRemark(model.getRemark());
		model.setPortNum(model.getPortNum());
		port.setServer(server);	
		
		portService.update(port);
		return "toList";
	}
	
	

}
