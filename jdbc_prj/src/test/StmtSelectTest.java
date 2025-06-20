package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.stmt.dao.StatementDAO;

class StmtSelectTest {
	@DisplayName("select 테스트")
	@Test
	void test() {
		StatementDAO sd=new StatementDAO();
//		assertNotEquals(sd.selectCntStmtMember(),0);
		try {
//			assertEquals(sd.selectAllStmtMember(),4);
			assertEquals(sd.selectAllStmtMember().size(),4, 4);
			//총 오차범위를 잡을 수 있다. : 조회된 레코드의 수, 예상조회레코드 수, 오차범위
			//레코드의 수가 4라면 0~8개 오차범위성공 
			//오차범위 +, -
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
