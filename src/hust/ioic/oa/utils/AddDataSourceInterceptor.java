package hust.ioic.oa.utils;


import hust.ioic.oa.qilin.utils.ConfigFileReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 新加水司时url的拦截器，只有当提供的口令正确时，才能正确调用
 * @author lecky
 *
 */
public class AddDataSourceInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//业务系统留给配置系统的接口口令
		String token = ConfigFileReader.getVarByKey("token","saas.properties");
		HttpServletRequest request = ServletActionContext.getRequest();
		if(token.equals(request.getParameter("token")))
			return invocation.invoke();
		else
			return "noPrivilegeError";
	}
}
