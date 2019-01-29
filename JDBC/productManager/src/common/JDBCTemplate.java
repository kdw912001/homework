package common;

import java.sql.*;
import product.exception.*;
import java.util.*;
import java.io.*;


public class JDBCTemplate {
	
	public static Connection getConnection() throws ProductException {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("properties/dbserver.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
		return conn;
	}
	
	public static void close(Connection conn) throws ProductException {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void close(Statement stmt) throws ProductException {
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void close(ResultSet rset) throws ProductException {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void commit(Connection conn) throws ProductException {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void rollback(Connection conn) throws ProductException {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
}
