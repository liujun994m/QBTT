package hust.ioic.oa.utils;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 异常拦截器，拦截service层和dao层被转换成BusinessException的异常和action层的异常
 * 并输出页面显示
 * @author lecky
 *
 */
public class ErrorInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation actioninvocation){
		String result = null; // Action的返回值   
        try {
                // 运行被拦截的Action,期间如果发生异常会被catch住   
                result = actioninvocation.invoke();
                return result;
        } catch (Exception e) {
                 // 处理异常  
        		 String errorMsg = "系统故障，请重试或者联系管理员！";
                if(e instanceof BusinessException){
                	//service层和dao层的异常
                }else {
                    //action层的异常
                	Log log = StaticConstant.getLog();
                    log.error("**************************************************************");
                    log.error("异常Action："+actioninvocation.getProxy().getActionName());
                    log.error("异常方法："+actioninvocation.getProxy().getMethod());
                	log.error(StaticConstant.getStackTrace(e));
				}
                //把自定义错误信息输出页面   
                HttpServletRequest request = (HttpServletRequest) actioninvocation
                                .getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
                request.setAttribute("errorMsg", errorMsg);
                request.setAttribute("reason", e.getMessage());
                return "errorMsg";
        }  
	}
	
}
