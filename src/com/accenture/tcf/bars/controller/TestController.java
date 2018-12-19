package com.accenture.tcf.bars.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.accenture.tcf.bars.dao.RecordDAOImpl;
import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.factory.InputFileFactory;
import com.accenture.tcf.bars.file.BarsXMLUtils;
import com.accenture.tcf.bars.file.FileProcessor;
import com.accenture.tcf.bars.file.IInputFile;

public class TestController {

	public static void main(String[] args) throws Exception {
		Session session = MySQLDatasource.getSessionFactory().openSession();
		Request request = new Request(1,new SimpleDateFormat("MMddyyyy").parse("01012010"),new SimpleDateFormat("MMddyyyy").parse("01012010"));
		try {
	        session.beginTransaction();
	        session.save(request);
	        session.getTransaction().commit();
	    }
	    catch (HibernateException e) {
	       e.printStackTrace();
	       session.getTransaction().rollback();
	    }
		
		//Query query = session.createQuery("from bars_db.billing WHERE bars_db.billing_cycle=1 AND bars_db.start_date=2013-01-15 AND bars_db.end_date=2013-02-14");
		/*List results =
			    session
			        .createSQLQuery("SELECT * FROM bars_db.billing WHERE billing_cycle='1' AND start_date='2013-01-15' AND end_date='2013-02-14'")
			        .list();
		int customerId=0;
		for (Iterator iter = results.iterator(); iter.hasNext(); ) {
		    Object[] objs = (Object[])iter.next();
		    for(int x=0;x<objs.length;x++) {
			    System.out.println(x + "\t"+objs[x]);
			    customerId = (int) objs[7];
		    }
		}
		results = session
		        .createSQLQuery("SELECT * FROM bars_db.customer WHERE customer_id='"+customerId+"'")
		        .list();
		for (Iterator iter = results.iterator(); iter.hasNext(); ) {
		    Object[] objs = (Object[])iter.next();
		    for(int x=0;x<objs.length;x++) {
			    System.out.println(objs[x]);
		    }
		}*/
		;
		/*InputFileFactory inputFileFactory = InputFileFactory.getInstance();
	    IInputFile iif = inputFileFactory.getInputFile(new File("C://BARS//Report//temp.csv"));
	    iif.setFile(new File("C://BARS//Report//temp.csv"));
	    List<Request> reqList = iif.readFile();
	    for(Request req : reqList) {
	    	System.out.println(req);
	    }*/
	}

}
