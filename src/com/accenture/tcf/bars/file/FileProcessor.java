package com.accenture.tcf.bars.file;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.accenture.tcf.bars.dao.IRecordDAO;
import com.accenture.tcf.bars.dao.IRequestDAO;
import com.accenture.tcf.bars.dao.RecordDAOImpl;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.factory.InputFileFactory;

public class FileProcessor {
	private Connection conn;
	
	private IInputFile inputFile;
	private IOutputFile outputFile;
	
	private IRecordDAO recordDAO;
	private IRequestDAO requestDAO;
	
	public void execute(File file) throws Exception {
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();
		inputFile = inputFileFactory.getInputFile(file);
		List<Request> reqList = inputFile.readFile();
	    List<Record> records = new ArrayList<Record>();
	    recordDAO = new RecordDAOImpl(MySQLDatasource.getSessionFactory());
		for(Request req : reqList) {
			requestDAO = new RequestDAOImpl(MySQLDatasource.getSessionFactory());
			requestDAO.insertRequest(req);
		    for(Record record : recordDAO.retrieveRecords()) {
		    	if(record.getBillingCycle() == req.getBillingCycle() &&
		    			record.getStartDate().toString().equals(new SimpleDateFormat("yyyy-MM-dd").format(req.getStartDate())) &&
		    			record.getEndDate().toString().equals(new SimpleDateFormat("yyyy-MM-dd").format(req.getEndDate())) )
		    		records.add(record);
		    }
		}
    	if(records.isEmpty())	
    		throw new BarsException(BarsException.NO_RECORDS_TO_WRITE); 
    	writeOutput(records);
	}
	
	public List<Record> retrieveRecordFromDB(File file) throws Exception{
		List<Record> records = new ArrayList<Record>();
		inputFile = InputFileFactory.getInstance().getInputFile(file);
		List<Request> reqList = inputFile.readFile();
		for(Request req : reqList) {
			requestDAO = new RequestDAOImpl(MySQLDatasource.getSessionFactory());
			requestDAO.insertRequest(req);
		    for(Record record : recordDAO.retrieveRecords()) {
		    	if(record.getBillingCycle() == req.getBillingCycle() &&
		    			record.getStartDate().toString().equals(new SimpleDateFormat("yyyy-MM-dd").format(req.getStartDate())) &&
		    			record.getEndDate().toString().equals(new SimpleDateFormat("yyyy-MM-dd").format(req.getEndDate())) )
		    		records.add(record);
		    }
		}
		return records;
	}
	
	public void writeOutput(List<Record> record) throws BarsException {
		outputFile = new XMLOutputFileImpl();
    	outputFile.writeFile(record);
	}
}
