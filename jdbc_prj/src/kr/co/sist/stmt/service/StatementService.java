package kr.co.sist.stmt.service;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.stmt.dao.StatementDAO;
import kr.co.sist.stmt.design.StatementWindow;
import kr.co.sist.vo.StatementMemberVO;

/**
 * BL (Business Logic)을 구현하기 위한 클래스. 
 */
public class StatementService {
	
	private StatementWindow sw;
	public StatementService(StatementWindow sw) {
		this.sw=sw;
	}//StatementService
	
	/**
	 * 업무로직 : 나이는 20~30대만 입력 가능. 해당 나이가 아니면 20으로 설정.
	 * @param smVO
	 */
	public void addStmtMember(StatementMemberVO smVO) {
		StatementDAO sDAO=new StatementDAO();
		
//		//업무로직 처리
//		if(!( smVO.getAge() > 19 && smVO.getAge() < 40)) {
//			smVO.setAge(20);
//		}//end if
		
		StringBuilder resultMsg=new StringBuilder();
		
		try {
			sDAO.insertStmtMember(smVO);
			resultMsg.append(smVO.getName()).append ("님의 회원 정보를 추가 성공하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			switch(e.getErrorCode()) {
				case 1400: resultMsg.append("이름은 필수 입력");break;
				case 1438: resultMsg.append("나이는 0~999까지만 입력가능.");break;
				case 12899: resultMsg.append("이름은 한글 10자, 영어 30자, 전화번호는 '-'포함 13자 입니다.");break;			
			}
			e.printStackTrace();
		}finally {
			JOptionPane.showMessageDialog(sw,resultMsg.toString());
		}//end finally
		
	}//addStmtMember
	
	public boolean modifyStmtMember( StatementMemberVO smVO ) {
		boolean flag=false;
		
		StatementDAO sDAO=new StatementDAO();
		try {
			flag=sDAO.updatestmtMember(smVO) !=0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}//modifyStmtMember

	public boolean removeStmtMember(int num) {
		boolean flag=false;
		
		StatementDAO sDAO=new StatementDAO();
		try {
			flag=sDAO.deleteStmtMember(num) !=0;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return flag; 
	}//removeStmtMember
	
	//0321
	public int searchAllCnt() {
		int cnt=0;
		StatementDAO sDAO=new StatementDAO();
		cnt=sDAO.selectCntStmtMember(); // 행수 담았다.
		
		return cnt;
	}//searchAllCnt
	
	public List<StatementMemberVO> searchAllMember(){
		
		List<StatementMemberVO> list =null;
		StatementDAO sDAO=new StatementDAO();
		
		try {
			list=sDAO.selectAllStmtMember();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchAllMember
	

}//class
