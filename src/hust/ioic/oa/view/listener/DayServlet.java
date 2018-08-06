package hust.ioic.oa.view.listener;


import hust.ioic.oa.domain.DeviceTmp;
import hust.ioic.oa.domain.TempCommandQueue;
import hust.ioic.oa.qilin.utils.GetDate;
import hust.ioic.oa.service.DeviceTmpService;
import hust.ioic.oa.service.TempCommandQueueService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class DayServlet extends HttpServlet
{
	GetDate date=new GetDate();	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String month = req.getParameter("num");
		 WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
		 DeviceTmpService deviceTmpService=(DeviceTmpService)ac.getBean("deviceTmpServiceImpl");
		 Collection<Integer> days=deviceTmpService.getDays(month);
		 Iterator iterator=days.iterator();
		 Gson gson = new Gson();	
List<Integer> dayList=new ArrayList<>();
while(iterator.hasNext()){
	int d=(Integer)iterator.next();
	dayList.add(d);
}

String result = gson.toJson(dayList);
System.out.println(result);
		resp.setContentType("application/json; charset=utf-8");
		
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");
		
		PrintWriter out = resp.getWriter();
		
		out.println(result);
		
		out.flush();
	}

}
