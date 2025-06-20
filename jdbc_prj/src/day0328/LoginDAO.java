package day0328;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import kr.co.sist.dao.DbConnection;

public class LoginDAO {
	private static LoginDAO LDAO;
	public LoginDAO() {
		
	}//LoginDAO
	public static LoginDAO getIstance() {
		if(LDAO==null) {
			LDAO=new LoginDAO();
			}
		return LDAO;
	}//getIstance
	
	/**
	 * 로그인에 사용되는 method로 사용자의 아이디와 비밀번호를 입력받아서,
	 * 이름을 반환하는 일
	 * @param lVO 로그인에 사용된 아이디와 비번
	 * @return 검색된 이름
	 * @throws SQLException
	 */
	public String selectLogin( LoginVO lVO )throws SQLException{
		String name="";
		//1.드라이버 로딩
		//2.커넥션 얻기
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		DbConnection dbCon =DbConnection.getInstance();
		try {
			con= dbCon.getConn();
		//3.쿼리문 생성 객체 얻기
			stmt=con.createStatement();
		//4.쿼리문 수행 후 결과 얻기
			StringBuilder selectName=new StringBuilder();
//			selectName
//			.append("	select name	")
//			.append("	from test_member	")
//			.append("	where id='").append(lVO.getId())
//			.append("'	and pass='").append(lVO.getPass()).append("'")
//			;
			//괄호 잘 막기
			//인젝션 막기 (blockInjection)
			selectName
			.append("	select name	")
			.append("	from test_member	")
			.append("	where id='").append(blockInjection(lVO.getId()))
			.append("'	and pass='").append(blockInjection(lVO.getPass())).append("'")
			;
			
			
			System.out.println(selectName);
			
			rs=stmt.executeQuery(selectName.toString());
			if(rs.next()) {
				name=rs.getString("name");
			}
		}finally {
		//5. 연결 끊기
			dbCon.closeDB(rs, stmt, con);
		}//end finally
		return name;
	}//selectLogin
	
	public String selectPstmtLogin( LoginVO lVO )throws SQLException{
		String name="";
		//1.드라이버 로딩
		//2.커넥션 얻기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbCon =DbConnection.getInstance();
		try {
			con= dbCon.getConn();
		//3.쿼리문 생성 객체 얻기 - 값과 쿼리 분리
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("	select name	")
			.append("	from test_member	") //테이블 명에 바인드변수(?)를 넣을 수 없다.
			.append("	where id=? and pass=?")
			;
			pstmt=con.prepareStatement(selectName.toString());
		//4.바인드 변수에 값 설정
			pstmt.setString(1,lVO.getId());
			pstmt.setString(2, lVO.getPass());
			//인젝션 막기 (blockInjection) -> 안나와도 된다.
			
			System.out.println(selectName);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				name=rs.getString("name");
			}
		}finally {
		//5. 연결 끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		return name;
	}//selectLogin
	
	//인젝션 막기
	public String blockInjection(String sql) {
		String temp=sql;
		if( temp!=null && !temp.isEmpty()) {
			temp=temp.replaceAll("'", "").replaceAll(" ","")
					.replaceAll("--", "");
			
		}//end if
		
		
		return temp;
	}//blockInjection

}//class
