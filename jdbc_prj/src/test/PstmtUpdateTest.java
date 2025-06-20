package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.pstmt.dao.PreparedDAO;
import kr.co.sist.pstmt.service.PreparedService;
import kr.co.sist.vo.PstmtMemberVO;

class PstmtUpdateTest {

	@DisplayName("변경테스트")
	@Test
	void test() {
		PstmtMemberVO pmVO = new PstmtMemberVO(1, "이장훈", 30, "남자", "010-0000-0000", null, null);

		PreparedDAO pDAO = PreparedDAO.getIstance();
		assertDoesNotThrow(() -> pDAO.updatePstmtMember(pmVO));

		try {
			assertEquals(pDAO.updatePstmtMember(pmVO), 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		PreparedService ps=new PreparedService();
//		assertTrue(ps.addStmtMember(pmVO));
	}

}
