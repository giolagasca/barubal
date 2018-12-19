package com.accenture.tcf.bars.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.accenture.sef.xml.impl.BarsWriteXmlUtils;
import com.accenture.sef.xml.interfce.BarsWriteXMLUtilsInterface;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;

public class BarsXMLUtils {
	
	public void writeXML (List<Record> records) throws BarsException {
		BarsWriteXMLUtilsInterface x = new BarsWriteXmlUtils();
		String fileSeparator = System.getProperty("file.separator");
		
		Document doc = x.createXMLDocument();
		Element rootElement = x.createDocumentElement(doc, "BARS");
		int counter = 1;
		for(Record record : records) {
			Element recordElement = x.createChildElement(doc, rootElement, "record");
			x.createElementAttribute(doc, recordElement, "id", Integer.toString(counter));
			x.createElementTextNode(doc, recordElement, "billing-cycle", Integer.toString(record.getBillingCycle()));
			x.createElementTextNode(doc, recordElement, "start-date", new SimpleDateFormat("yyyy-MM-dd").format(record.getStartDate()));
			x.createElementTextNode(doc, recordElement, "end-date", new SimpleDateFormat("yyyy-MM-dd").format(record.getEndDate()));
			x.createElementTextNode(doc, recordElement, "first-name", record.getFirstName());
			x.createElementTextNode(doc, recordElement, "last-name", record.getLastName());
			x.createElementTextNode(doc, recordElement, "amount", Double.toString(record.getAmount()));
			counter++;
		}
		
		String rootPath = "C:" + fileSeparator + "BARS" + fileSeparator + "Report" + fileSeparator;
		String fileName = "BARS_Report-";
		String fileExtension = ".xml";
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy_HHmmss");
		Date date = new Date();		
		String formattedDate = sdf.format(date);
		String finalFileName = rootPath+fileName+formattedDate+fileExtension;
		
		System.out.println(finalFileName);
		
		File file = new File(finalFileName);
		try {
			if(file.createNewFile()) {
				System.out.println("File created");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BarsException(BarsException.PATH_DOES_NOT_EXIST);
		}
		
		x.transformToXML(doc, finalFileName);
	}
	
	
	public void writeXML () {
		BarsWriteXMLUtilsInterface x = new BarsWriteXmlUtils();
		String fileSeparator = System.getProperty("file.separator");
		
		Document doc = x.createXMLDocument();
		Element rootElement = x.createDocumentElement(doc, "BARS");
		Element recordElement = x.createChildElement(doc, rootElement, "record");		
		x.createElementAttribute(doc, recordElement, "id", "insert record id here");
		x.createElementTextNode(doc, recordElement, "billing-cycle", "insert billing cycle here");
		x.createElementTextNode(doc, recordElement, "start-date", "insert billing start date here");
		x.createElementTextNode(doc, recordElement, "end-date", "insert billing end date here");
		x.createElementTextNode(doc, recordElement, "first-name", "insert customer first name here");
		x.createElementTextNode(doc, recordElement, "last-name", "insert customer last name here");
		x.createElementTextNode(doc, recordElement, "amount", "insert amount here");
		
		String rootPath = "C:" + fileSeparator + "BARS" + fileSeparator + "Report" + fileSeparator;
		String fileName = "BARS_Report-";
		String fileExtension = ".xml";
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy_HHmmss");
		Date date = new Date();		
		String formattedDate = sdf.format(date);
		String finalFileName = rootPath+fileName+formattedDate+fileExtension;
		
		System.out.println(finalFileName);
		
		File file = new File(finalFileName);
		try {
			if(file.createNewFile()) {
				System.out.println("File created");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x.transformToXML(doc, finalFileName);
	}
	
/*	public boolean validateAgainstXSD(File xml, File xsd)
	{
	    try
	    {
	        SchemaFactory factory = 
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new StreamSource(xsd));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        return true;
	    }
	    catch(Exception ex)
	    {
	        return false;
	    }
	}*/
}
