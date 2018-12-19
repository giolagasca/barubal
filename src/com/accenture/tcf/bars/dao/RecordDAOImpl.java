package com.accenture.tcf.bars.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.domain.Request;

public class RecordDAOImpl implements IRecordDAO{
	public RecordDAOImpl(SessionFactory sessionFactory) {
	}
	
	@Override
	public List<Record> retrieveRecords() {
		List<Record> records = new ArrayList<Record>();
		Session session = MySQLDatasource.getSessionFactory().openSession();
    	List results = session
			        .createSQLQuery("SELECT * FROM bars_db.billing")
			        .list();
    	String firstName = "";
    	String lastName = "";
    	int customerId = 0,billingCycle = 0;
    	BigDecimal amount = new BigDecimal(0);
    	Date startDate = new Date();
    	Date endDate = new Date();
    	for (Iterator iter = results.iterator(); iter.hasNext();) {
		    Object[] objs = (Object[])iter.next();
		    for(int x=0;x<objs.length;x++) {
		    	billingCycle = (Integer) objs[1];
		    	amount = (BigDecimal) objs[3];
		    	startDate = (Date) objs[4];
		    	endDate = (Date) objs[5];
			    customerId = (int) objs[7];
		    }
		    List results2 = session
			        .createSQLQuery("SELECT * FROM bars_db.customer WHERE customer_id='"+customerId+"'")
			        .list();
			for (Iterator iter2 = results2.iterator(); iter2.hasNext(); ) {
			    Object[] objs2 = (Object[])iter2.next();
			    firstName = (String) objs2[1];
			    lastName = (String) objs2[2];
			}
		    if(!firstName.equals("")||!lastName.equals("")||Double.compare(amount.doubleValue(), 0) == 0)
				records.add(new Record(billingCycle,startDate,endDate,firstName + " " + lastName,firstName,lastName,amount.doubleValue()));
		}
    	session.close();
		return records;
	}
	
	
}
