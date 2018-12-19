package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class TextInputFileImpl extends AbstractInputFile {
	

	@Override
	public List<Request> readFile() throws IOException  {
		List<Request> requestList = new ArrayList<>();
		FileReader fr = new FileReader(super.getFile());
		BufferedReader br = new BufferedReader(fr);
	    Request request = new Request();
		String line;
		try {
			while ((line = br.readLine()) != null) {
			    String billingCycle = line.substring(0, 2);
			    if(Integer.parseInt(billingCycle) <= 0 || Integer.parseInt(billingCycle) > 12) {
			    	throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE);		    	
			    }
			    String startDate = line.substring(2, 10);
			    String endDate = line.substring(10, 18);
				try {
					request = new Request(Integer.parseInt(billingCycle), new SimpleDateFormat("MMddyyyy").parse(startDate), 
							new SimpleDateFormat("MMddyyyy").parse(endDate));
				} catch (Exception e) {
					e.printStackTrace();
				}
				new RequestDAOImpl(MySQLDatasource.getSessionFactory()).insertRequest(request);
			    requestList.add(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
				br.close();
				fr.close();
		}
		return requestList;
	}	
}