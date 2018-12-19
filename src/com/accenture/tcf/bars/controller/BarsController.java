package com.accenture.tcf.bars.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.tcf.bars.dao.IRequestDAO;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.file.FileProcessor;

@Controller
public class BarsController {
	private FileProcessor fileProcessor;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/process.htm")
	public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			FileItem fileItem = null;
			while (it.hasNext()) {
				fileItem = it.next();
				String fileName = "C:/BARS/Report/temp." + fileItem.getName().split("\\.")[fileItem.getName().split("\\.").length-1];
				BufferedOutputStream bs = null;
				try {
				    FileOutputStream fs = new FileOutputStream(new File(fileName));
				    bs = new BufferedOutputStream(fs);
				    bs.write(fileItem.getString().getBytes());
				    bs.close();
				    fileProcessor = new FileProcessor();
				    fileProcessor.execute(new File(fileName));
				    mav.addObject("records", fileProcessor.retrieveRecordFromDB(new File(fileName)));
				} catch (BarsException e) {
				    if(e.getMessage().contains(BarsException.BILLING_CYCLE_NOT_ON_RANGE))
				    	mav.setViewName("error_billing_cycle");
				    if(e.getMessage().contains(BarsException.INVALID_END_DATE_FORMAT))
				    	mav.setViewName("error_invalid_end_date");
				    if(e.getMessage().contains(BarsException.INVALID_START_DATE_FORMAT))
				    	mav.setViewName("error_invalid_start_date");
				    if(e.getMessage().equals(BarsException.NO_RECORDS_TO_READ))
				    	mav.setViewName("error_no_request");
				    if(e.getMessage().equals(BarsException.NO_RECORDS_TO_WRITE))
				    	mav.setViewName("error_no_records");
				    if(e.getMessage().equals(BarsException.NO_SUPPORTED_FILE))
				    	mav.setViewName("error_format");
				    if(e.getMessage().equals(BarsException.PATH_DOES_NOT_EXIST))
				    	mav.setViewName("error_format");
				}
				catch (Exception e) {
				    e.printStackTrace();
				}
				IRequestDAO requestDAO = new RequestDAOImpl(MySQLDatasource.getSessionFactory());
			    requestDAO.deleteRequest();
				new File(fileName).delete();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return mav;
	}

}
