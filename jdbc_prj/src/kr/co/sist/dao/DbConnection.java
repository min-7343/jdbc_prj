package kr.co.sist.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.net.ns.Packet;

public class DbConnection {

	private static DbConnection dbcon;

	private DbConnection() {

	}// DbConnection

	public static DbConnection getInstance() {
		if (dbcon == null) {
			dbcon = new DbConnection();
		} // end if

		return dbcon;
	}// end getInstance

	public Connection getConn() throws SQLException {
		Connection con = null;
		// DB작업
		//properties 파일 사용 (소스코드에 계정 정보를 하드코딩하지 않는다.)
		String currentDir=System.getProperty("user.dir");
//		System.out.println(currentDir);
		File file =new File(currentDir+"/src/properties/database.properties");
		if(!file.exists() ) {
			throw new SQLException("database.properties가 지정된 경로에 존재하지 않습니다.");
		}//end if
		
		//properties 생성
		Properties prop=new Properties();
		//properties 파일 로딩
		String driver="";
		String url="";
		String id="";
		String pass="";
		
		try {
			prop.load(new FileInputStream(file));
			driver=prop.getProperty("driverClass");
			url=prop.getProperty("url");
			id=prop.getProperty("id");
			pass=prop.getProperty("pass");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(file.exists());
//		System.out.println(System.getProperty("user.dir"));
		
		
		// 1.드라이버로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		// 2. connection얻기
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	}// getConn
	
	
//	public static void main(String[] args) {
//		try {
////			new DbConnection().getConn();
//			System.out.println(new DbConnection().getConn());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void closeDB(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		try {
			if (rs != null) {rs.close();}
			if (stmt != null) {stmt.close();
			}
		} finally {
			if (rs != null) {rs.close();
			}
		}//finally
	}//closeDB

}// class
