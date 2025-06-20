package day0317;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBMS와 연결된 Connection 얻기.
 */
public class DBConnection2 {
	public DBConnection2() throws SQLException {
		//1. Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2. Connection 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con=null;
		Statement stmt=null;
		try {
			//2. Connerction 얻기
		con = DriverManager.getConnection(url,id,pass);
		System.out.println("DB연동 객체 "+ con);
		
		//3.쿼리문 생성객체 얻기
		stmt=con.createStatement(); // 실행 할 쿼리문을 모른다.
		//4.쿼리문 수행 후 결과 얻기
		int deptno=99;
		String dname="SM";
		String loc="대구";
		
//		String insertDept2="insert into dept2(deptno,dname, loc) values("+deptno
//				+",'"+dname+"','"+loc"')";
		
		StringBuilder insertDept2 =new StringBuilder();
		insertDept2.append("insert into dept2(deptno,dname,loc) values(")
		.append(deptno).append(",'").append(dname).append("','")
		.append(loc).append("')");
		
		System.out.println(insertDept2);
		
//		int cnt=stmt.executeUpdate(insertDept2.toString()); // executeUpdate는 String형을 받는다. toString() 필요.
//		
//		System.out.println(cnt+"건 추가되었습니다.");
		
		
		}finally {
			//5. 연결 끊기
			if(stmt !=null) {stmt.close();}
			if(con !=null) {con.close();}
			
		}//end finally
	}//DBConnection
	

	public static void main(String[] args) {
		try {
			new DBConnection2();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// main

}//class
