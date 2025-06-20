package day0402;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.dao.DbConnection;

public class UseRefCursor {

	public void selectCar( String maker)throws SQLException{
		
		//1.드라이버로딩
		//2.커넥션
		Connection con=null;
		CallableStatement cstmt=null;
		ResultSet rs=null; // 커서의 제어권을 갖는다.
		
		DbConnection db= DbConnection.getInstance();
		try {
			con=db.getConn();
		//3. 쿼리문 생성객체 얻기
			cstmt = con.prepareCall("{ call select_car(?,?,?) }");
		//4. 바인드변수의 값 설정
			//in_parameter
			cstmt.setString(1, maker);
			
			//out_parameter (=오라클의 바인드변수 역할)
			cstmt.registerOutParameter(2, Types.REF_CURSOR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			
		//5. 프로시저 실행
//			cstmt.executeQuery(); : 프로시저는 반환형 없고, out parameter저장
			cstmt.execute();
		//6. out parameter 의 저장된 값 얻기
//			rs=cstmt.getResultSet();// 매개 변수가 없어서 어떤 것을 받아올 지 모른다.
			rs=(ResultSet)cstmt.getObject(2); //오브젝은 어떤것이든 담을 수 있다.
			String errm = cstmt.getString(3);
			
			String model="",car_year="",car_option="";
			int price=0;
			
			while(rs.next()) {
				model=rs.getString("model");
				car_year=rs.getString("car_year");
				car_option=rs.getString("car_option");
				price=rs.getInt("price");
				
				System.out.println(model+","+car_year+","+car_option+","+price);
			}//end while
		}finally {
		//7.
				db.closeDB(rs, cstmt, con);
			}//end finally
		
	}//selectCar
	
	public static void main(String[] args) {
		try {
			new UseRefCursor().selectCar("기아");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}//end try

	}//main

}//class
