package hust.ioic.oa.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Port;
import hust.ioic.oa.domain.Server;

@Controller
@Scope("prototype")
public class ServerAction extends BaseAction<Server> {

	public String addUi() {

		return "add";
	}

	public String add() {

		serverService.save(model);

		return "toList";
	}

	public String editUi() {
		model = serverService.getById(model.getId());
		ActionContext.getContext().put("server", model);
		return "edit";
	}

	public String edit() {

		Server server = serverService.getById(model.getId());
		server.setLocalIp(model.getLocalIp());
		server.setName(model.getName());
		server.setOvertime(model.getOvertime());
		serverService.update(server);

		return "toList";
	}

	public String delete() {
		serverService.delete(model.getId());
		return "toList";
	}

	public String list() {

		return "list";
	}

	public String left() {
		
		Enterprise enterprise=enterpriseService.getByEnprNo(getEnprNo());
		Set<Port> portList=enterprise.getPorts();
		Set<Server> serverList=new HashSet<Server>();
		for(Port p:portList)
		{
			serverList.add(p.getServer());		
		}
			
		ActionContext.getContext().put("serverList", serverList);
		ActionContext.getContext().put("portList", portList);

		List<Center> centerList = centerService.findByEnprNo();
		List<Collection> collectionList = collectionService.findByEnprNo();

		ActionContext.getContext().put("centerList", centerList);
		ActionContext.getContext().put("collectionList", collectionList);
		return "left";
	}

	public String right() {
		return "right";
	}
}
