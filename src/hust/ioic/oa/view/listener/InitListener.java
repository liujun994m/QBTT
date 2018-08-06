package hust.ioic.oa.view.listener;

import hust.ioic.oa.domain.Area;
import hust.ioic.oa.domain.Function;
import hust.ioic.oa.service.AreaService;
import hust.ioic.oa.service.FunctionService;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	/*	ApplicationContext ac = WebApplicationContextUtils//
				.getWebApplicationContext(sce.getServletContext());
		FunctionService functionService = (FunctionService) ac.getBean("functionServiceImpl");
	

		List<Function> topFunctionList = functionService.findTopFunction();
		sce.getServletContext().setAttribute("topFunctionList", topFunctionList);
		
		
        
		Collection<String> allFunctionUrls = functionService.getAllFunctionUrls();
		sce.getServletContext().setAttribute("allFunctionUrls", allFunctionUrls);*/
	}
	

}
