package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.pstmt.dao.PreparedDAO;
import kr.co.sist.pstmt.service.PreparedService;
import kr.co.sist.vo.PstmtMemberVO;


class PstmtInsertTest {
	@DisplayName("추가")
	@Test
	void test() {
		PstmtMemberVO pmVO = new PstmtMemberVO(0, "이장훈", 26, "남자", "010-1234-4321", null, null);

//		PreparedDAO pDAO = PreparedDAO.getIstance();
//		assertDoesNotThrow(() -> pDAO.insertPstmtMember(pmVO));

		PreparedService ps=new PreparedService();
		assertTrue(ps.addPstmtMember(pmVO));
	}
}
