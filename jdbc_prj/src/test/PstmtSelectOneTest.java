package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.pstmt.dao.PreparedDAO;


class PstmtSelectOneTest {

	@DisplayName("PreparedDAO한행조회")
	@Test
	void test() {
		PreparedDAO pDAO=PreparedDAO.getIstance();
		int num=3;
//		assertDoesNotThrow(()->pDAO.seletOneMember(num));
		try {
			assertNotNull(pDAO.seletOneMember(num));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//test

}//class
