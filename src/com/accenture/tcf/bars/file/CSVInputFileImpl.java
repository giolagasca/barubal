package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class CSVInputFileImpl extends AbstractInputFile {
	
	public List<Request> readFile() throws NumberFormatException, IOException, ParseException, BarsException  {
		List<Request> requestList = new ArrayList<>();
		FileReader fr = new FileReader(super.getFile());
		BufferedReader br = new BufferedReader(fr);
		String line;
		int counter = 1;
		try {
			while ((line = br.readLine()) != null) {
			    String[] split = line.split(",");
			    if(Integer.parseInt(split[0])>12 || Integer.parseInt(split[0])<0)
		    		throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + counter);
				try {
					new SimpleDateFormat("MM/dd/yyyy").parse(split[1]);
				} catch(ParseException e) {
					throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + counter);
				}
				try {
					new SimpleDateFormat("MM/dd/yyyy").parse(split[2]);
				} catch(Exception e) {
					throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + counter);
				}
			    Request request = new Request(Integer.parseInt(split[0]), new SimpleDateFormat("MM/dd/yyyy").parse(split[1]), 
			    		new SimpleDateFormat("MM/dd/yyyy").parse(split[2]));
			    requestList.add(request);
			    counter++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			fr.close();
			br.close();
		}
		if(counter<=1)
			throw new BarsException(BarsException.NO_RECORDS_TO_READ);
		return requestList;
	}	
}