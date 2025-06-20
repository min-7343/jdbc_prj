package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.stmt.dao.StatementDAO;
import kr.co.sist.stmt.service.StatementService;
import kr.co.sist.vo.StatementMemberVO;

class StmtTest {

	@DisplayName("update테스트")
//	@DisplayName("insert테스트")
	@Test
	void test() {
		//insert
		StatementMemberVO smVO=
				new StatementMemberVO(1,"테스트",20,"남자333","010-1111-1111",null);
//		StatementDAO sd= new StatementDAO();
//		assertDoesNotThrow(()-> sd.insertStmtMember(smVO));
		
		
		//update
//		StatementMemberVO smVO=
//				new StatementMemberVO(1,null,29,null,"010-9999-9999",null);
//		StatementDAO sd= new StatementDAO();
//		assertDoesNotThrow(()-> sd.insertStmtMember(smVO));
//		try {
//			assertEquals(sd.updatestmtMember(smVO),1); //반환형이 int, 반환된값이 해당 값과 같다면
//			assertNotEquals(sd.updatestmtMember(smVO), 0);//반환된 값이 해당값과 같지 않다면
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
		
//		assertDoesNotThrow(()->sd.updatestmtMember(smVO));//예외가 발생하지 않으면 성공
//		assertThrows(SQLException.class, ()->sd.updatestmtMember(smVO));//예외가 발생하면 성공
		
		//StatmentService
		StatementService ss=new StatementService(null);
		assertTrue(ss.modifyStmtMember(smVO)); //assertEquals에는 boolean 없다. 0->성공
		
		
	}//end main

}//StmtTest
