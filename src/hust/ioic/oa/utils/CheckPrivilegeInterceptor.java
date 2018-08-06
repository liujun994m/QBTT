package hust.ioic.oa.utils;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import hust.ioic.oa.domain.Enterprise;
import hust.ioic.oa.domain.Operator;
import hust.ioic.oa.service.EnterpriseService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Resource
	private EnterpriseService enterpriseService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Operator operator = (Operator) ActionContext.getContext().getSession()
				.get("operator");
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();

		// 获取ip
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);

		String ip = getRemoteHost(request);
		ActionContext.getContext().getSession().put("ip", ip);

		String privUrl = namespace + actionName;

		if (operator == null) {
			if (privUrl.startsWith("/operator_login")) {
				return invocation.invoke();
			} else {
				// 返回首页面时，获取水司列表做回显
				getCompany();
				return "loginUI";
			}
		} else {
			// TODO 性能问题，每次请求都会查询数据库
			if (operator.hasFunctionByUrl(privUrl)) {
				return invocation.invoke();
			} else {
				return "noPrivilegeError";
			}
		}
	}

	/**
	 * 登录界面查询数据库，查找出所有的水司
	 */
	private void getCompany() {
		// 找到企业信息表中的所有企业
		List<Enterprise> enterpriseList = enterpriseService.findAll();
		// 将所有的企业信息放入值栈供页面回显
		ActionContext.getContext().put("enterpriseList", enterpriseList);
	}
	
	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	private String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

}
