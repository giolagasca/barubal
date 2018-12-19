package com.accenture.tcf.bars.file;

import java.util.List;

import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;

public class XMLOutputFileImpl extends AbstractOutputFile{
	public void writeFile(List<Record> records) throws BarsException {
		System.out.println("Printing Records");
		for(Record r : records) {
    		System.out.println("Record : "+r);
    	}
		new BarsXMLUtils().writeXML(records);
	}

}
