package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.stmt.dao.StatementDAO;
import kr.co.sist.stmt.service.StatementService;

class StmtDeleteTest {

	@DisplayName("삭제테스트")
	@Test
	void test() {
//		StatementDAO sd= new StatementDAO();
//		
//		assertDoesNotThrow(()-> sd.insertStmtMember(smVO));
//		try {
//			assertEquals(sd.deleteStmtMember(1),1); //반환형이 int, 반환된값이 해당 값과 같다면
//			assertNotEquals(sd.updatestmtMember(smVO), 0);//반환된 값이 해당값과 같지 않다면
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
		
		int num=2;
		StatementService ss=new StatementService(null);
 		assertTrue(ss.removeStmtMember(num));
		
	}

}
