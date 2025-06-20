package kr.co.sist.pstmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.vo.PstmtMemberVO;

public class PreparedDAO {
	private static PreparedDAO pDAO;
	
	private PreparedDAO() {
		
	}//PreparedDAO

	public static PreparedDAO getIstance() {
		if(pDAO==null) {
			pDAO=new PreparedDAO();
		}//end if
		return pDAO;
	}//getInstance
	
	/**
	 * preparede_member 테이블에 레코드를 추가
	 * @param pmVO
	 * @throws SQLException
	 */
	public void insertPstmtMember(PstmtMemberVO pmVO) throws SQLException{
		
		//1.드라이버로딩
		//2.커넥션 얻기
		 Connection con=null;
		 PreparedStatement pstmt=null;
		 
		 DbConnection dbCon=DbConnection.getInstance();
		 try {
			 con=dbCon.getConn();
		 //3.쿼리문을 넣어서 쿼리문 생성객체 얻기
			 StringBuilder insertMember=new StringBuilder();
			 insertMember
			 .append("insert into prepared_member(num,name,age,gender,tel)")
			 .append("values ( seq_pstmt.nextval, ?, ?, ?, ?)");
			 
			 pstmt=con.prepareStatement(insertMember.toString());
		//4.바인드변수에 값 할당
			 pstmt.setString(1,pmVO.getName());
			 pstmt.setInt(2,pmVO.getAge());
			 pstmt.setString(3, pmVO.getGender());
			 pstmt.setString(4, pmVO.getTel());
			 
		//5. 쿼리문 수행 후 결과 얻기
			 pstmt.executeUpdate();
		 }finally {
		//6. 연결 끊기
			 dbCon.closeDB(null,pstmt,con);
		 }//end finally
		
		
	}//insertPstmtMember
	
	public int updatePstmtMember( PstmtMemberVO pmVO) throws SQLException{
		int rowCnt=0;
		
		//1.드라이브로딩
		//2.커넥션 얻기
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		try {
			con=dbCon.getConn();
		//3.쿼리문 생성객체 얻기
			StringBuilder updateMember=new StringBuilder();
			updateMember
			.append("	update	prepared_member ")
			.append("	set		age=?,tel=?		")
			.append("	where	num=?			");
			
			pstmt=con.prepareStatement(updateMember.toString());
		//4.바인드변수 값 설정
			pstmt.setInt(1, pmVO.getAge());
			pstmt.setString(2, pmVO.getTel());
			pstmt.setInt(3, pmVO.getNum());

			
		//5.쿼리문 수행 후 결과 얻기
			rowCnt=pstmt.executeUpdate();
		}finally {
			//6.연결 끊기
			dbCon.closeDB(null, pstmt, con);
		}//finally
		
	
		return rowCnt;
	}//updatePstmtMember
	
	
	public int deletePstmtMember(int num)throws SQLException{
		int rowCnt=0;
		
		//1.드라이브로딩
		//2.커넥션 얻기
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		try {
			con=dbCon.getConn();
		//3.쿼리문 생성객체 얻기
			StringBuilder deleteMember=new StringBuilder();
			deleteMember
			.append("	delete from	prepared_member ")
			.append("	where	num=?				");
			
			pstmt=con.prepareStatement(deleteMember.toString());
		//4.바인드변수 값 설정
			pstmt.setInt(1, num);

		//5.쿼리문 수행 후 결과 얻기
			rowCnt=pstmt.executeUpdate();
		}finally {
			//6.연결 끊기
			dbCon.closeDB(null, pstmt, con);
		}//finally
		
	
		return rowCnt;
	}//deletePstmtMember
	
	
	
//	public static void main(String[] args) {
//		
//		PstmtMemberVO pmVO=new PstmtMemberVO(0,"이장훈",26,"남자","010-1234-4321",
//				null,null);
//		try {
//			PreparedDAO.getIstance().insertPstmtMember(pmVO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public List<PstmtMemberVO> selectAllMember() throws SQLException{
		List<PstmtMemberVO> list=new ArrayList<PstmtMemberVO>();
		
		//1.드라이버로딩
		//2.커넥션 얻기 
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			con=dbCon.getConn();
			
		//3.쿼리문 생성객체 얻기
			StringBuilder selectMember=new StringBuilder();
			selectMember
			.append("	select	num,name,age,gender,tel,input_date,			")
			.append("	to_char(input_date,'yyyy-mm-dd	q\"분기\"') str_date	")
			.append("	from	prepared_member								");
		
			pstmt=con.prepareStatement(selectMember.toString());
			//4.바인드
		//5.연결끊기
			rs=pstmt.executeQuery();
			
			PstmtMemberVO pmVO=null;
			while(rs.next()) {//레코드가 존재하는지?
				//레코드의 컬럼 값을 VO에 저장하고, 
				pmVO=new PstmtMemberVO();
				
				pmVO.setNum(rs.getInt("num")); //1을 써도 되지만 가독성이 안좋다.
				pmVO.setName(rs.getString("name"));
				pmVO.setAge(rs.getInt("age"));
				pmVO.setGender(rs.getString("gender"));
				pmVO.setTel(rs.getString("tel"));
				pmVO.setInput_date(rs.getDate("input_date"));
				pmVO.setStrInputDate(rs.getString("str_date"));
				//같은 이름의  VO를 여러개 관리하기 위해서 List에 추가 
				list.add(pmVO);
				

//				System.out.println(pmVO);//찍어보기
			}//end while
			
		}finally {
		//6.연결끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		return list;
	}//selectAllMember
	
	public int selectPstmtCntMember() throws SQLException {
		int cnt=0;
		
		//1.드라이브로딩
		//2.커넥션 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		DbConnection dbCon=DbConnection.getInstance();
		try {
			con=dbCon.getConn();
		//3. 쿼리문 넣어서 쿼리문 생성객체 넣기
			StringBuilder selectCntMember=new StringBuilder();
			selectCntMember
			.append("	select count(num) cnt	")
			.append("	from	prepared_member	")
			;
			
			pstmt=con.prepareStatement(selectCntMember.toString());
			
		//4. 바인드 변수 값 넣기
		//5. 쿼리문 실행 후 결과 얻기 
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}//end if
		} finally {
			//6. 연결끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		
		
		return cnt;
	}//selectPstmtCntMember
	
	//단일 검색0325
	public PstmtMemberVO seletOneMember(int num) throws SQLException{
		PstmtMemberVO pmVO=null;
		
		//1.드라이브로딩
		//2.커넥션 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		DbConnection dbCon=DbConnection.getInstance();
		try {
			con=dbCon.getConn();
		//3. 쿼리문 넣어서 쿼리문 생성객체 넣기
			StringBuilder selectOneMember=new StringBuilder();
			selectOneMember
			.append("	select	name,age,gender,tel,input_date,	")
			.append("	to_char('mm-dd-yyyy hh24:mi') str_date	")
			.append("	from	prepared_member					")
			.append("	where num=?								")
			;
			//뒤는 반드시 띄어서 (안그러면 오류발생)
			pstmt=con.prepareStatement(selectOneMember.toString());
			
		//4. 바인드 변수 값 넣기
			pstmt.setInt(1, num);
		//5. 쿼리문 실행 후 결과 얻기 
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//레코드의 컬럼 값을 VO에 저장 
				pmVO=new PstmtMemberVO();
				
				pmVO.setName(rs.getString("name"));
				pmVO.setAge(rs.getInt("age"));
				pmVO.setGender(rs.getString("gender"));
				pmVO.setTel(rs.getString("tel"));
				pmVO.setInput_date(rs.getDate("input_date"));
				pmVO.setStrInputDate(rs.getString("str_date"));

			}//end if
		} finally {
			//6. 연결끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		
		
		return pmVO;
		
	}
	
	
}//class
