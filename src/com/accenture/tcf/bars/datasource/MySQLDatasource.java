package com.accenture.tcf.bars.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySQLDatasource {
	
	private static Connection conn;
	public static SessionFactory factory;
	
	public static Connection getConnection() {
		if(conn==null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bars_db?useSSL=false&serverTimezone=UTC", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			return conn;
		return conn;
	}
	
	public static SessionFactory getSessionFactory() {
        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return factory;
    }
}
