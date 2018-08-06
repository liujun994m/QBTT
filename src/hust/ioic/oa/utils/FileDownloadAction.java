package hust.ioic.oa.utils;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

public class FileDownloadAction {
	private String inputpath;
	public void setInputpath(String value){
		inputpath=value;
	}
	public InputStream getTargetFile() throws Exception{
		return ServletActionContext.getServletContext().getResourceAsStream(inputpath);
	}
	}

