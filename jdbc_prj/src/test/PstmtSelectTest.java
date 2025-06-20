package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.pstmt.dao.PreparedDAO;

class PstmtSelectTest {
	@DisplayName("PreparedDAO 전체행 검색")
	@Test
	void test() {
		PreparedDAO pDAO=PreparedDAO.getIstance();
//		assertDoesNotThrow(()->pDAO.selectAllMember());
		try {
			assertEquals(pDAO.selectAllMember().size(),7,3);
			//4 ~ 10 개 범위내에 있으면 성공
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
