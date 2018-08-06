package hust.ioic.oa.view.listener;


import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.service.TempCommandQueueService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

public class GsonServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String ids = req.getParameter("num");
		System.out.println("id:"+ids);
		String[] tids=ids.split(",");
		Integer[] temp=new Integer[tids.length];
		for(int i=0;i<tids.length;i++){
			temp[i]=Integer.parseInt(tids[i]);
		}
		   WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
		/*ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());*/
		TempCommandQueueService tempCommandQueueService=(TempCommandQueueService)ac.getBean("tempCommandQueueServiceImpl");
		List<TempCommandQueue> tcqList=tempCommandQueueService.getByIds(temp);
		
        Gson gson = new Gson();		
		String result = gson.toJson(tcqList);
		System.out.println(result);
		resp.setContentType("application/json; charset=utf-8");
		
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		
		PrintWriter out = resp.getWriter();
		
		out.println(result);
		
		out.flush();
	}
/*	@Override
	public void init(ServletConfig config) throws ServletException {  
	    ServletContext servletContext = this.getServletContext();  
	    WebApplicationContext wac = null;   
	    wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);  
	    this.setOperationService((OperationService) wac.getBean("operationService"));//Spring 配置 中的 bean id  
	}  */
}
