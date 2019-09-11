package com.sunday.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionMaker {
	
	private final Logger LOG = Logger.getLogger(ConnectionMaker.class);
	
	public Connection getConnection(){
		String dbUrl 	= "jdbc:oracle:thin:@211.238.142.124:1521:orcl";
		String dbUser 	= "SUNDAY";
		String dbPass 	= "SUNDAY1024";
		Connection conn = null;
		
		//1.jdbc드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			LOG.debug("------------------------------");
			LOG.debug("ClassNotFoundException:"+e.getMessage());
			LOG.debug("------------------------------");
		}
		LOG.debug("1.jdbc 드라이버 로딩");
		
		//2.데이터베이스 커넥션 생성
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			LOG.debug("------------------------------");
			LOG.debug("SQLException:"+e.getMessage());
			LOG.debug("------------------------------");
		}
		LOG.debug("2.데이터베이스 커넥션 생성:"+conn);
		
		return conn;
	}
}
