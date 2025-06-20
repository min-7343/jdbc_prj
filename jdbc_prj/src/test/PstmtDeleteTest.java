package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.pstmt.dao.PreparedDAO;

class PstmtDeleteTest {

	@DisplayName("PstmtDeleteTest")
	@Test
	void test() {
		PreparedDAO pDAO=PreparedDAO.getIstance();
//		assertDoesNotThrow(()->pDAO.deletePstmtMember(1));
		try {
			assertEquals(pDAO.deletePstmtMember(2), 1); //2번 삭제 후 다시 실행하면 없어짐
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//test

}//class
