package day0327;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;


public class ZipcodeDAO {
	private static ZipcodeDAO zDAO;

	private ZipcodeDAO() {

	}//ZipcodeDAO

	public static ZipcodeDAO getInstance() {
		if (zDAO == null) {
			zDAO = new ZipcodeDAO();
		}
		return zDAO;
	}//ZipcodeDAO

	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException {
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();

		//1.드라이버로딩
		//2.커넥션연결
		Connection con = null;
		PreparedStatement pstmt = null;
		DbConnection dbCon = DbConnection.getInstance();
		ResultSet rs=null;
		
		try {
			con = dbCon.getConn();
			//3.쿼리문을 넣어서 쿼리문 생성

			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode
			.append("select zipcode, sido, gugun, dong,		")
			.append("	nvl(bunji,''	) bunji				")
			.append("	from zipcode						")
			.append("	where dong like ? ||'%'				");

			pstmt = con.prepareStatement(selectZipcode.toString());
			//4.bind 변수 값 넣기
			pstmt.setString(1, dong);

			//5.쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			
			ZipcodeVO zVO=null;
			while(rs.next()) {
				zVO=new ZipcodeVO(rs.getString ("zipcode"),
						rs.getString("sido"),rs.getString("gugun"),
						rs.getString("dong"),rs.getString("bunji"));
				list.add(zVO);
			}//while

		} finally {
			//6. 연결끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		return list;
	}//list
	
	public List<ZipcodeVO> selectStmtZipcode(String dong) throws SQLException {
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();

		//1.드라이버로딩
		//2.커넥션연결
		Connection con = null;
		Statement stmt = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		ResultSet rs=null;
		
		try {
			con = dbCon.getConn();
			//3.쿼리문을 넣어서 쿼리문 생성

			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode
			.append("select zipcode, sido, gugun, dong,		")
			.append("	nvl(bunji,'	'	) bunji				")
			.append("	from zipcode						")
			.append("	where dong like '").append( blockInjection(dong) ).append("%'");

			stmt = con.createStatement();
			//4.bind 변수 값 넣기
//			pstmt.setString(1, dong);

			//5.쿼리문 수행 후 결과 얻기
			rs = stmt.executeQuery(selectZipcode.toString());
			
			ZipcodeVO zVO=null;
			while(rs.next()) {
				zVO=new ZipcodeVO(rs.getString ("zipcode"),
						rs.getString("sido"),rs.getString("gugun"),
						rs.getString("dong"),rs.getString("bunji"));
				list.add(zVO);
			}//while

		} finally {
			//6. 연결끊기
			dbCon.closeDB(rs, stmt, con);
		}//end finally
		return list;
	}//selectStmtZipcode
	
	public String blockInjection(String sql) {
		String temp=sql;
		if( temp!=null && !temp.isEmpty()) {
			temp=temp.replaceAll("'", "").replaceAll(" ","")
					.replaceAll("--", "");
			
		}//end if
		
		
		return temp;
	}//blockInjection
	
	
	
	
	
//	public static void main(String[] args) {
//		
//		try {
//			System.out.println(ZipcodeDAO.getInstance().selectStmtZipcode("상도동"));
////			ZipcodeDAO.getInstance().selectZipcode("상도동"));
//					
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end try
//	}//main
}// class
