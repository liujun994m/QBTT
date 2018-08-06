package hust.ioic.oa.view.action;

import hust.ioic.oa.base.BaseAction;

import hust.ioic.oa.domain.Function;
import hust.ioic.oa.domain.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Integer[] functionIds;
	/**
	 * 列表
	 * 
	 * @return
	 */
	public String list() {
		List<Role> roleList = roleService.findByEnprNo();
		ActionContext.getContext().put("roleList", roleList);// 将集合放入map栈
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	public String addUI() {
		 List<String> roleNames=new ArrayList<String>();
	        List<Role> roleList=roleService.findByEnprNo();
	        for(Role role:roleList){
	        	if(role!=null){
	        	roleNames.add(role.getName());
	        	}	
	        }
	        ActionContext.getContext().put("roleNames", roleNames);
		return "saveUI";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		model.setEnprNo(getEnprNo());
		roleService.save(model);
		return "toList";
	}

	/**
	 * 更改页面
	 * 
	 * @return
	 */
	public String editUI() {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);// 将值推入值栈栈顶
		return "saveUI";
	}

	/**
	 * 更改
	 * 
	 * @return
	 */
	public String edit() {
		Role role=roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}

	/**
	 * 设置权限页面
	 * 
	 * @return
	 */
	public String setFunctionUI() {
		// 回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		if (role.getFunctions() != null) {
			functionIds = new Integer[role.getFunctions().size()];
			int index = 0;
			for (Function privi : role.getFunctions()) {
				functionIds[index++] = privi.getId();
			}
		}
		
		//准备数据
		List<Function> functionList = functionService.findAll();
		ActionContext.getContext().put("functionList", functionList);

		return "setFunctionUI";
	}

	/**
	 * 设置权限
	 * 
	 * @return
	 */
	public String setFunction() {
		//从数据库取回原数据
		Role role = roleService.getById(model.getId());
		
		//设置修改的数据
		role.setFunctions(new HashSet<Function>(functionService.getByIds(functionIds)));
		
		//保存数据
		roleService.update(role);

		return "toList";
	}

	// =======================Getter and Setter======================
	public Integer[] getFunctionIds() {
		return functionIds;
	}
   
	public void setFunctionIds(Integer[] functionIds) {
		this.functionIds = functionIds;
	}
}
