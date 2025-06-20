package kr.co.sist.pstmt.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.pstmt.dao.PreparedDAO;
import kr.co.sist.stmt.dao.StatementDAO;
import kr.co.sist.stmt.design.StatementWindow;
import kr.co.sist.vo.PstmtMemberVO;
import kr.co.sist.vo.StatementMemberVO;
import oracle.jdbc.proxy.annotation.Pre;

/**
 * BL( Business Logic)을 구현하기위한 클래스.
 */
public class PreparedService {

	public PreparedService() {
	}// PreparedService

	/**
	 * 업무로직 : 나이는 20~30대만 입력 만약 해당 나이가아니면 20으로 설정
	 * 
	 * @param smVO
	 */
	public boolean addPstmtMember(PstmtMemberVO pmVO) {
		boolean flag = false;
		PreparedDAO pDAO = PreparedDAO.getIstance();
		try {
			pDAO.insertPstmtMember(pmVO);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}// addStmtMember

	public boolean modifyPstmtMember(PstmtMemberVO pmVO) {
		boolean flag = false;
		
		PreparedDAO pDAO=PreparedDAO.getIstance();
		try {
			flag=pDAO.updatePstmtMember(null)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
		return flag;
	}// modifyPstmtMember

	public boolean removePsmtMember(int num) {
		boolean flag = false;

		PreparedDAO pDAO=PreparedDAO.getIstance();
		try {
			flag=pDAO.deletePstmtMember(num)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}// removeStmtMember

	public int searchAllCnt() {
		int cnt = 0;
		
		PreparedDAO pDAO=PreparedDAO.getIstance();
		try {
			cnt=pDAO.selectPstmtCntMember();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}//try pDAO

		return cnt;
	}// searchAllCnt

	public List<PstmtMemberVO> searchAllMember() {

		List<PstmtMemberVO> list = null;

		PreparedDAO pDAO = PreparedDAO.getIstance();
		try {
			list=pDAO.selectAllMember();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}// searchAllMember
	
	public PstmtMemberVO searchOneMember(int num) {
		PstmtMemberVO pmVO=null;
		PreparedDAO pDAO=PreparedDAO.getIstance();
		try {
			pmVO=pDAO.seletOneMember(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return pmVO;
	}//searchOneMember

}// class
