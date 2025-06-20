package day0401;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import kr.co.sist.dao.DbConnection;

public class UseInserProc {
	public void insertCpEmp(CpEmpVO ceVO) throws SQLException {
		// 1.
		// 2.
		Connection con = null;
		CallableStatement cstmt = null;
		DbConnection db=DbConnection.getInstance();
		try {
			con=db.getConn();
			//3.
			cstmt=con.prepareCall("{call insert_cp_emp(?,?,?,?,?,?)	}");
			//4. 바이든 변수에 값 넣기
			//in parameter
			cstmt.setInt(1,ceVO.getEmpno());
			cstmt.setString(2, ceVO.getEname());
			cstmt.setString(3, ceVO.getJob());
			cstmt.setInt(4, ceVO.getSal());
			//out parameter
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			//5.
			cstmt.execute();
			//6.쿼리문 수행 후 out parameter에 존재하는 값 얻기
			int cnt=cstmt.getInt(5);// sql%rowcount
			String msg=cstmt.getString(6);
			
			String outMsg="추가 실패";
			if(cnt==1) {
				outMsg="추가 성공";
			}
			JOptionPane.showMessageDialog(null, outMsg);
			System.out.println("PL/SQL에서 생성한 메시지 : "+ msg);
			
		} finally {
			//7.
		}//end finally

	}// insertCpEmp

	public static void main(String[] args) {
		CpEmpVO ceVO = new CpEmpVO();
		ceVO.setEmpno(2);
		ceVO.setEname("이장훈");
		ceVO.setJob("개발자");
		ceVO.setSal(3600);

		try {
			new UseInserProc().insertCpEmp(ceVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// main

}// class
