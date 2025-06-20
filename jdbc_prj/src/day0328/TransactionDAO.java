package day0328;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.crypto.dsig.dom.DOMValidateContext;

import kr.co.sist.dao.DbConnection;

/**
 * 여러개의 쿼리문으로 하나의 DB작업이 구성되는 경우 ( transaction이 여러 개의 쿼리문으로 구성 )
 */
public class TransactionDAO {

	/**
	 * 입력된 이름과 주소를 test_transaction, test_transcation2테이블에 추가
	 * 
	 * @param con
	 * @param name
	 * @param addr
	 * @return
	 * @throws SQLException
	 */
	public int insertTransaction(Connection con, String name, String addr) throws SQLException {
		int totalRow = 0;
		// 하나의 테이블(test_ transaction)
		String insert = "insert into test_transaction(name, addr) values (?,?)";
		PreparedStatement pstmt = con.prepareStatement(insert);
		int insertCnt = 0;
		pstmt.setString(1, name);
		pstmt.setString(2, addr);

		insertCnt = pstmt.executeUpdate();
		System.out.println("첫번째 테이블 성공");

		// 다른 테이블에 insert(test_ transaction2)
		String insert2 = "insert into test_transaction2(name, addr) values (?,?)";
		PreparedStatement pstmt2 = con.prepareStatement(insert2);
		int insertCnt2 = 0;
		pstmt2.setString(1, name);
		pstmt2.setString(2, addr);

		insertCnt2 = pstmt2.executeUpdate();
		System.out.println("첫번째 테이블 성공");

		totalRow = insertCnt + insertCnt2;
		return totalRow;

	}// insertTransaction

	/**
	 * 여러개의 쿼리문이 하나의 트랜젝션을 구성되는 method 호출
	 */
	public void useInsertTransaction() throws SQLException {
		DbConnection dbc = DbConnection.getInstance();
		Connection con = dbc.getConn(); // Connection은 기본으로 auto commit -> 해제안하면 '할 수 없음 '

		con.setAutoCommit(false);//auto commit해제
		
		try {
			//1,2 모두 성공해야 테이블에 들어간다.

//			String name = "강태일";
//			String addr = "인천시";//1,2 정상
//			String name = "강태이";
//			String addr = "인천시 미추호올구"; //  1,2 - 미추호올구가 안들어간다. 
			String name = "강태사";
			String addr = "인천시에 살"; // 1성공하지만 2에 들어가지 않아 모든 테이블에 전부 들어가지 않는다.
			
			// 여러개의 쿼리문을 실행
			int cnt = insertTransaction(con, name, addr);

			if (cnt == 2) {
				con.commit();
				System.out.println("commit");
			}/*else {
				con.rollback();
			}*/ //일반적인 update delete
		}catch(SQLException se) {
			se.printStackTrace();
			con.rollback(); // 작업취소
			System.out.println("rollback");
		} finally {
			if(con!=null) {
				con.close();
			}//end if
		}//end finally
		
		
	}// useInsertTransaction

	public static void main(String[] args) {
		TransactionDAO tDAO=new TransactionDAO();
		try {
			tDAO.useInsertTransaction();
		} catch (SQLException e) {

			e.printStackTrace();
		}//end try catch

	}// main

}// class
