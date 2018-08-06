package hust.ioic.oa.view.listener;


import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceTmp;
import hust.ioic.oa.service.DeviceService;
import hust.ioic.oa.service.DeviceTmpService;
import hust.ioic.oa.utils.ImportExecl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

public class ExcelServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		
		String ids = req.getParameter("num");
	String[] mdid=ids.split(":");
	
	String[] md=mdid[1].split(",");
	String month=md[0];
	int day=Integer.parseInt(md[1]);
		String[] dids=mdid[0].split(",");
		Integer[] devices=new Integer[dids.length];
		for(int i=0;i<dids.length;i++){
			devices[i]=Integer.parseInt(dids[i]);
		}
	    WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
		DeviceService deviceService=(DeviceService)ac.getBean("deviceServiceImpl");
		DeviceTmpService deviceTmpService=(DeviceTmpService)ac.getBean("deviceTmpServiceImpl");
		List<Device> deviceList=deviceService.getByIds(devices);
		List<List<String>> deviceArgs=new ArrayList<>();
		List<String> head=new ArrayList<>();
		head.add("userNo(用户编号)");
		head.add("userName(用户名)");
		head.add("userAddr(用户地址)");
		head.add("deviceNo(表编号)");
		head.add("ShowValue(本次读数)");
		head.add("readTime(读取时间)");
		head.add("iAddr(水表地址)");
		head.add("deviceType(设备类型)");
		deviceArgs.add(head);
		List<String> dList=new ArrayList<>();
		for(int j=0;j<deviceList.size();j++){
			dList.clear();	
			DeviceTmp deviceTmp=deviceTmpService.getByDate(deviceList.get(j).getAddr(), month, day);
			dList.add(deviceList.get(j).getUserNo());			
			dList.add(deviceList.get(j).getUserName());
			dList.add(deviceList.get(j).getUserAddr());
			dList.add(deviceList.get(j).getDeviceNo());
			if(deviceTmp!=null){
				dList.add(deviceTmp.getShowValue().toString());
				dList.add(deviceTmp.getReadTime().toString());
			}	
			dList.add(deviceList.get(j).getAddr());
			
			dList.add(deviceList.get(j).getDeviceType().getName());
			deviceArgs.add(dList);
		}
		String webPath = req.getSession().getServletContext().getRealPath("/");
		 System.out.println(webPath);
        File local = new File(webPath);
		if (!local.exists()) local.mkdir();

		File file =new File(local.getAbsolutePath() +"\\deviceTmp.xls");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		HSSFWorkbook wb = null;
		try {		
			fos = new FileOutputStream(file);
			wb = new HSSFWorkbook();
			HSSFSheet sheet = null;		
			sheet = wb.createSheet();				
			writeExcel(sheet,deviceArgs);
			wb.setSheetName(0,"showValue");		
			wb.write(fos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
					wb.close();		
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        Gson gson = new Gson();		
		String result = gson.toJson(head);
		System.out.println(result);
		resp.setContentType("application/json; charset=utf-8");		
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");		
		PrintWriter out = resp.getWriter();		
		out.println(result);		
		out.flush();
	}
	public static void writeExcel(HSSFSheet sheet,List<List<String>> list) {
		List<String> list0=list.get(0);
		HSSFSheet st = sheet;
		for (int i = 0; i < list.size();i++) {
			Row row = st.createRow((i));
			row.setHeight((short) 500);
			for (int j = 0; j <list0.size(); j++) {	
						row.createCell(j).setCellValue(list.get(i).get(j));			
			}	
		}
	}
}
