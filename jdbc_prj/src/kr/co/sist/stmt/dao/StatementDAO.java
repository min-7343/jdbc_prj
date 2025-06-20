package kr.co.sist.stmt.dao;

import java.awt.Taskbar.State;
import java.io.FilterWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.vo.StatementMemberVO;

/**
 * CRUD  ( Create Read Update Delete)
 * C : create, insert
 * R : select
 * U : update, alter
 * D : delete, drop, truncate
 */
public class StatementDAO {
	
	/**
	 * STATEMENT_MEMBER 테이블에 레코드를 추가하는 일.<br>
	 * 추가성공 아니면 예외<br>
	 * @param smVO 추가할 값
	 * @throws SQLException 예외
	 */
	public void insertStmtMember( StatementMemberVO smVO) throws SQLException{
		//statement - 5 단계 (pre - 6, call - 7) 
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		
		//2. 로딩된 드라이버를 사용하여 Connection 얻기
		Connection conn=null;
		Statement stmt=null;
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		try {
			conn=DriverManager.getConnection(url,id,pass);
			//3. Connection에서 쿼리문 생성객체 얻기
			stmt=conn.createStatement();
			//4. 쿼리문 실행 후 결과 얻기
			StringBuilder insertStmtMember=new StringBuilder();
			insertStmtMember
			.append("insert into statement_member(num,name,age,gender,tel)")
			.append("values( seq_stmt.nextval, '").append(smVO.getName())
			.append("',").append(smVO.getAge())
			.append(",'").append(smVO.getGender())
			.append("','").append(smVO.getTel()).append("')");
			
//			System.out.println( insertStmtMember );
			
			stmt.executeUpdate(insertStmtMember.toString());
			
			
		}finally {
			//5. 연결 끊기
			if(stmt!=null) {stmt.close();}//end if
			if(conn!=null) {conn.close();}//end if
		}//end finally
		
	}//insertStmtMember
	

	
	/**
	 * STATEMENT_MEMBER 테이블에 레코드를 변경하는 일.<br>
	 * 번호를 사용하여 나이와 전화번호를 변경하는 일.<br>
	 * 0건 : 조건에 사용되는 값이 잘못 되었을 때.<br>
	 * n건 : 조건에 사용되는 값에 해당하는 레코드가 여러행일 때.<br>
	 * 예외 :  쿼리문이 잘못된 경우, DBMS연동에 문제가 발생.
	 * @param smVO 변경할 값을 가진 객체
	 * @return 변경된 행의 수
	 * @throws SQLException 예외
	 */
	public int updatestmtMember( StatementMemberVO smVO) throws SQLException{
		int rowCnt=0;//변경된 행의 수를 저장할 method
		
		//1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}//end catch
		
		
		//2.커넥션얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			conn=DriverManager.getConnection(url,id,pass);
		//3.쿼리문 생성객체 얻기
			stmt=conn.createStatement();
		//4.쿼리문 수행 후 결과 얻기
			StringBuilder updateMBuilder=new StringBuilder();
			updateMBuilder
			.append("	update	statement_member	")
			.append("	set	age=").append(smVO.getAge()).append(",tel='")
			.append( smVO.getTel()).append("'")
			.append("	where	num=").append(smVO.getNum());
			
			rowCnt=stmt.executeUpdate( updateMBuilder.toString());
			
			
		}finally {
		//5.연결 끊기
			if(stmt !=null) {stmt.close(); }//end if
			if(conn !=null) {conn.close(); }//end if
		}//end finally
		
		
		return rowCnt;
	}//updatestmtMember
	
	/**
	 * STATEMENT_MEMBER 테이블에 레코드를 삭제하는 일.<br>
	 * 번호를 사용하여 레코드를 삭제하는 일.<br>
	 * 0건 : 조건에 사용되는 값이 잘못 되었을 때.<br>
	 * n건 : 조건에 사용되는 값에 해당하는 레코드가 여러행일 때.<br>
	 * 예외 :  쿼리문이 잘못된 경우, DBMS연동에 문제가 발생.
	 * @param smVO 변경할 값을 가진 객체
	 * @return 변경된 행의 수
	 * @throws SQLException 예외
	 */
	public int deleteStmtMember (int num)throws SQLException{
		int rowCnt=0;//삭제된 행의 수를 저장할 method
		
		//1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}//end catch
		
		
		//2.커넥션얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			conn=DriverManager.getConnection(url,id,pass);
		//3.쿼리문 생성객체 얻기
			stmt=conn.createStatement();
		//4.쿼리문 수행 후 결과 얻기
			StringBuilder deleteMember=new StringBuilder();
			deleteMember
			.append("	delete	 from	statement_member	")
			.append("	where	num=").append(num);
			
			rowCnt=stmt.executeUpdate( deleteMember.toString());
			
			
		}finally {
		//5.연결 끊기
			if(stmt !=null) {stmt.close(); }//end if
			if(conn !=null) {conn.close(); }//end if
		}//end finally
		
		return rowCnt;
	}//deleteStmtMember
	
	/**
	 * STATEMENT_MEMBER 테이블에 모든 레코드를 검색 하는 일.<br>
	 * 검색된 레코드 하나를 VO에 저장하고, 모든 VO객체를 List에 저장하여 반환.
	 * @return 모든 레코드를 가진 리스트
	 * @throws SQLException 예외
	 */
	public List<StatementMemberVO> selectAllStmtMember() throws SQLException{
		List<StatementMemberVO> list =new ArrayList<StatementMemberVO>();
		
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn=null; //DBMS와 연결 유지, auto commit 설정, 쿼리문 생성객체 얻기
		Statement  stmt=null; // 쿼리무을 실행하는 일
		ResultSet rs=null;// 커서(Cursor)의 제어권을 받기, DBMS 데이터형을 Java의 데이터형으로 변환
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		try { //try ~ with ~ finally
		//2.커넥션얻기
			conn=DriverManager.getConnection(url,id,pass);
		//3.쿼리문 생성객체 얻기
			stmt=conn.createStatement();
		//4.쿼리문 수행 후 결과 얻기
			StringBuilder selectAllMember=new StringBuilder();
			selectAllMember
			.append("	select	num,name,age, gender,tel,input_date	")
			.append("	from	statement_member	");
			
			rs=stmt.executeQuery(selectAllMember.toString());
			//쿼리를 수행 한 수 inline view 앞에 있는 커서(제어권)를 얻기
			
			//몇 줄의 레코드가 있는지 알 수 없다.
			StatementMemberVO smVO=null;
			while(rs.next()) { //레코드가 존재하면
				//컬럼의 값을 가져와서 VO에 설정한다.
//				System.out.println(rs.getString("name"));
				smVO=new StatementMemberVO();
				smVO.setNum( rs.getInt("num"));//정수
				smVO.setName(rs.getString("name"));//문자열
				smVO.setAge( rs.getInt("age"));//정수
				smVO.setGender(rs.getString("gender"));//문자열
				smVO.setTel(rs.getString("tel"));//문자열
				smVO.setInputDate(rs.getDate("input_date"));//날짜
				
//				System.out.println(smVO);//객체마다 한줄 씩 (총 4줄 출력)
				//생성된 레코드의 컬럼값을 가진 VO객체를 List에 추가
				list.add(smVO); 
			}//end while
			
			System.out.println(list);//옆으로, 한줄로 나온다.
			
		}finally{
			//5.연결 끊기
			if( rs !=null) {rs.close();}//end if
			if( stmt !=null) {stmt.close();}//end if
			if( conn !=null) {conn.close();}//end if
			
		}
		
		return list;
	}//selectAllStmtMember
	
	/**
	 * STATEMENT_MEMBER 테이블에서 번호에 해당하는 레코드 하나를 검색하는 일.<br> 
	 * @param num 번호
	 * @return 레코드 하나를 가진 VO객체
	 * @throws SQLException 예외
	 */
	public StatementMemberVO selectOneStmtMember(int num) throws SQLException{
		StatementMemberVO smVO=null;
		return smVO;
	}//selectOneStmtMember
	
	//0321
	/**
	 * STATEMENT_MEMBER테이블에 모든 레코드의 수를 얻기
	 * @return 레코드의 수
	 * @throws SQLException
	 */
	public int selectCntStmtMember(){
		int cnt=0;
		
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		//2. 커넥션얻기
		//3. 쿼리문 생성객체 얻기
		//5. 자동으로 연결이 끊어짐 try~with~ resources
		String selectAllCnt="select count(num) cnt from statement_member";
		try(Connection con=DriverManager.getConnection(url,id,pass);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(selectAllCnt)){
			//4. 쿼리문 실행 후 결과 얻기
			
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}//end catch

		//5. 연결 끊기
		
		
		
		
		return cnt;
	}//selectCntStmtMember
	
	
}//class
