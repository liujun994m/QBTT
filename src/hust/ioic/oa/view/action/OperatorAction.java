package hust.ioic.oa.view.action;


import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Function;
import hust.ioic.oa.domain.Role;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.qilin.utils.DataSourceContextHolder;
import hust.ioic.oa.qilin.utils.GetDate;
import hust.ioic.oa.utils.StaticConstant;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class OperatorAction extends BaseAction<Operator> {
	/*private Integer id;*/
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	GetDate cdate = new GetDate();
	private Integer[] areaIds;
	private Integer roleId;
	private String newpassword;



	/*private String enprNo;*/



	/**
	 * 列表
	 * 
	 * @return
	 */
	public String list() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源
		List<Operator> operatorList = operatorService.findAllByStatus();
		ActionContext.getContext().put("operatorList", operatorList);
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源
		Operator operator = operatorService.getById(model.getId());
		operator.setStatus(StaticConstant.OPERATOR_STATUS_DROP);
		model.setEndTime(cdate.getCurrentTime());
		operatorService.update(operator);
		return "toList";
	}

	/**
	 * 编辑页面
	 * 
	 * @return
	 */
	public String editUI() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源
		/*
		 * List<Area> topList = areaService.getTopList(); List<Area> areaList =
		 * AreaUtils.getAllArea(topList);
		 * ActionContext.getContext().put("areaList", areaList);
		 */

		List<Role> roleList = roleService.findByEnprNo();
		ActionContext.getContext().put("roleList", roleList);
   
		// 回显数据
		Operator operator = operatorService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(operator);
		if (operator.getRole() != null) {
			int index = 0;
			Role role = operator.getRole();
			roleId = role.getId();
		}
		return "saveUI";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String edit() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源
		// 1.从数据库拿出原数据
		Operator operator = operatorService.getById(model.getId());
		// 2.设置修改的数据
		operator.setUsername(model.getUsername());
		operator.setOperatorNo(model.getOperatorNo());
		operator.setGender(model.getGender());
		operator.setTelNum(model.getTelNum());
		operator.setPhoneNum(model.getPhoneNum());
		operator.setAddress(model.getAddress());
		operator.setBeginTime(model.getBeginTime());
		operator.setEndTime(model.getEndTime());
		operator.setGropuID(model.getGropuID());
/*		operator.setStatus(model.getStatus());*/

		// >>设置用户所属岗位
		operator.setRole(roleService.getById(roleId));

		// 3.保存数据
		operatorService.update(operator);

		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	public String addUI() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源
		// 加载部门
		/*
		 * List<Area> topList = areaService.getTopList(); List<Area> areaList =
		 * AreaUtils.getAllArea(topList);
		 * ActionContext.getContext().put("areaList", areaList);
		 */
 
		// 加载岗位
		List<Role> roleList = roleService.findByEnprNo();
		ActionContext.getContext().put("roleList", roleList);
        List<String> operatorNos=operatorService.getAllOperatorNos();
        ActionContext.getContext().put("operatorNos", operatorNos);
		return "saveUI";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {

		// 从页面拿到水司名称，将数据源设置为相应的数据源
		model.setRole(roleService.getById(roleId));
		
        model.setStatus(StaticConstant.OPERATOR_STATUS_USE);
		// 设置默认密码为1234
		String md5Digest = DigestUtils.md5Hex("1234");
		
		model.setPassword(md5Digest);	
			model.setBeginTime(cdate.getCurrentTime());
	        model.setEnprNo(getEnprNo());
			operatorService.save(model);
			return "toList";
		
	}

	public String changePassword() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源

		Operator operator = operatorService.getById(model.getId());
		String md5Digest = DigestUtils.md5Hex(newpassword);
		operator.setPassword(md5Digest);
		operatorService.update(operator);
		
		return "right";
	}

	public String changePasswordUI() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源

		Operator operator = operatorService.getById(model.getId());
	
		ActionContext.getContext().getValueStack().push(operator);

		return "changePasswordUI";
	}

	/**
	 * 初始化密码
	 * 
	 * @return
	 */
	public String initPassword() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源

		Operator operator = operatorService.getById(model.getId());

		String md5Digest = DigestUtils.md5Hex("1234");
		operator.setPassword(md5Digest);

		operatorService.update(operator);

		return "toList";
	}

	/**
	 * 登录页面 用户访问该登录界面的时候，需要查询主数据库，取出所有水司的名字 登录的时候将masterDb数据库的信息读到一个并发容器并放到应用域中
	 * 
	 * @return
	 */
	public String loginUI() {
		return "loginUI";
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login() {

		// 从页面拿到水司名称，将数据源设置为相应的数据源
		ActionContext.getContext().getSession().put("enprNo", model.getEnprNo());	
		DataSourceContextHolder.setEnprNo(model.getEnprNo());
		Enterprise enterprise = enterpriseService.getByEnprNo(model.getEnprNo());
		ActionContext.getContext().getSession().put("enterprise", enterprise);	
		List<Function> topFunctionList = functionService.findTopFunction();
		/*List<Function> ChildrenList=functionService.findChildrenFunction();*/
		ServletContext st = (ServletContext) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.SERVLET_CONTEXT);
		st.setAttribute("topFunctionList", topFunctionList);
		/*st.setAttribute("ChildrenList", ChildrenList);*/
		Collection<String> allFunctionUrls = functionService.getAllFunctionUrls();
		st.setAttribute("allFunctionUrls", allFunctionUrls);
		
		Operator operator = operatorService.findByUsernameAndPassowrd(model.getUsername(), model.getPassword());	
		if (operator != null) {
			ActionContext.getContext().getSession().put("operator", operator);		
			return "toIndex";
		} else {
			addFieldError("login", "用户名或密码错误！");

			//找到企业信息表中的所有企业
			List<Enterprise> enterpriseList =  enterpriseService.findAll();
			//将所有的企业信息放入值栈供页面回显
			ActionContext.getContext().put("enterpriseList", enterpriseList);

			return "loginUI";

	}
	}

	/**
	 * 首次登录之后，设置页面的功能权限
	 * 
	 * @return
	 */
	

	/**
	 * 注销
	 * 
	 * @return
	 */
	public String logout() {
		ActionContext.getContext().getSession().remove("operator");
		return "logout";
	}

	public String setAreaUI() {
		// 从页面拿到水司名称，将数据源设置为相应的数据源
//		DataSourceContextHolder.setDataSourceType((String) ActionContext
//				.getContext().getSession().get("company"));

		// 回显数据
		Operator operator = operatorService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(operator);

		if (operator.getAreas() != null) {
			areaIds = new Integer[operator.getAreas().size()];
			int index = 0;
			for (Area area : operator.getAreas()) {
				areaIds[index++] = area.getId();
			}
		}

		// 准备数据
		List<Area> areaList = areaService.getTopArea();
		ActionContext.getContext().put("areaList", areaList);

		return "setAreaUI";
	}

	public String setArea() {
		/*
		 * Area area=areaService.getById(model.getId()); List<Area> areaList =
		 * areaService.getByIds(areaIds);
		 * 
		 * ActionContext.getContext().put("areaList", areaList);
		 */

		// 从页面拿到水司名称，将数据源设置为相应的数据源
//		DataSourceContextHolder.setDataSourceType((String) ActionContext
//				.getContext().getSession().get("company"));
		// 从数据库取回原数据
		Operator operator = operatorService.getById(model.getId());
		// 设置修改的数据
		operator.setAreas(new HashSet<Area>(areaService.getByIds(areaIds)));

		// 保存数据
		operatorService.update(operator);

		return "toList";
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer[] getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(Integer[] areaIds) {
		this.areaIds = areaIds;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

/*	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}*/


	
	

}
