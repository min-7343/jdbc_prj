package kr.co.sist.pstmt.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pstmt.design.PreparedWindow;
import kr.co.sist.pstmt.service.PreparedService;
import kr.co.sist.vo.PstmtMemberVO;
import kr.co.sist.vo.StatementMemberVO;

@SuppressWarnings("all")
public class PreparedWindowEvent extends WindowAdapter implements ActionListener, MouseListener {

	private PreparedWindow ew;
	private JButton jbtnAdd;
	private JButton jbtnChange;
	private JButton jbtnDelete;
	private JButton jbtnClose;
	private PreparedService ss;

	private int selectedNum;

	public PreparedWindowEvent(PreparedWindow ew) {
		selectedNum = -1;// 선택되지 않음.

		this.ew = ew;
		jbtnAdd = ew.getJbtnAdd();
		jbtnChange = ew.getJbtnChange();
		jbtnDelete = ew.getJbtnDelete();
		jbtnClose = ew.getJbtnClose();

		// Total JLabel 값 바꾸기
		ss = new PreparedService();

		changeList();// 리스트 갱신
		ew.getJlblCount2().setText(String.valueOf(ss.searchAllCnt()));

	} // ExamWindowEvent

	@Override
	public void mouseClicked(MouseEvent e) {

		boolean flag = JOptionPane.showConfirmDialog(ew, "다이얼로그에서 결과를 확인하시겠습니까?") == JOptionPane.OK_OPTION;
		// 선택한 아이템에서 처음 ','값까지를 잘라서 정수로 변환( 회원의 번호 )
		selectedNum = Integer.parseInt(ew.getJlData().getSelectedValue().split(",")[0]);
		if (flag) {
			StringBuilder output = new StringBuilder();
			output.append(selectedNum).append("회원검색결과\n");

			PreparedService ps = new PreparedService();
			PstmtMemberVO pmVO = ps.searchOneMember(selectedNum);

			if (pmVO == null) {// 번호로 검색된 레코드가 존재하지 않을 때.
				output.append("존재하지 않은 회원입니다.");
			} else { //존재할 때.
				int nowYear= Calendar.getInstance().get(Calendar.YEAR);
				SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
				output
				.append("회원명 : ").append(pmVO.getName()).append("\n")
				.append("나이 : ").append(pmVO.getAge()).append("\n")
				.append("태어난 해 : ").append(nowYear-pmVO.getAge()+1).append("\n")
				.append("성별 : ").append(pmVO.getGender()).append("\n")
				.append("전화번호 : ").append(pmVO.getTel()).append("\n")
				.append("입력일 : ").append(sdf.format(pmVO.getInput_date())).append("(")
				.append(pmVO.getStrInputDate()).append(")");
				
				JTextArea jta=new JTextArea(output.toString(),8,80);
				JScrollPane jsp=new JScrollPane(jta);
				JOptionPane.showMessageDialog(ew,jsp);
				return;
		} // end if

	}// end if

	// 클릭한 아이템의 값을 DLM에서 얻어와서 배열로 저장
	String[] clickDataArr = ew.getJlData().getSelectedValue().split(",");

//		System.out.println("-------clickDataArr--------------"+ 
//		clickDataArr.length );
	if(clickDataArr.length!=7)
	{ // 컬럼의 개수
		return;
	}// end if
		// 선택한 번호를 인스턴스 변수에 설정한다.
	selectedNum=Integer.parseInt(clickDataArr[0]);

	// '입력' 패널창에 차근차근 데이터 담자.
	ew.getJtfName().setText(clickDataArr[1]);ew.getJtfAge().setText(clickDataArr[2]);ew.getJtfPhoneNumber().setText(clickDataArr[4]);

	// 선택한 값과 라디오버튼의 라벨을 비교
	JRadioButton jrbTemp = ew.getJrbWoman();if(clickDataArr[3].equals(ew.getJrbMan().getText()))
	{
		jrbTemp = ew.getJrbMan();
	} // end if
	jrbTemp.setSelected(true);
	}


	/******************** 사용X ************************/
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/******************** 사용X ************************/


	public void windowClosing() {
		ew.dispose();
	} // windowClosing

	public void addMember(PstmtMemberVO pmVO) {
		// 성별 선택 안되어있으면 Early Return
		if ( pmVO.getGender().isEmpty() ) {
			JOptionPane.showMessageDialog(ew, "성별은 필수 선택입니다.!!");
			return;
		} // end if

		//업무로직을 처리
		String msg=pmVO.getName()+"님의 데이터를 추가하지 못하였습니다.";
		if(ss.addPstmtMember(pmVO)) {
			msg=pmVO.getName()+"님의 데이터를 추가하였습니다.";
		}//end if
		JOptionPane.showMessageDialog(ew, msg);
		
		inputFieldReset(); 
		// Total JLabel 값 바꾸기
		ew.getJlblCount2().setText(String.valueOf(ss.searchAllCnt()));
	}// addList

	private void inputFieldReset() {
		//레코드 추가 후 입력 필드를 초기화
		ew.getJtfName().setText("");
		ew.getJtfAge().setText("");
		ew.getJtfPhoneNumber().setText("");
		
		ew.getJtfName().requestFocus();
		
		changeList();//리스트 갱신
	}// inputFieldReset

	private boolean numValidate() {
		boolean flag=false;
		
		if(flag=(selectedNum == -1)) {
			JOptionPane.showMessageDialog(ew, "리스트에서 아이템을 클릭해주세요.");
		}//end if
		return flag;
	}// numValidate

	public void modifyMember(PstmtMemberVO pmVO) {
		
		if(numValidate()) {
			return;
		}//end if
		
		//업무로직을 처리
		pmVO.setNum(selectedNum);
		
		String alertMsg="회원정보 변경 실패";
		if(ss.modifyPstmtMember(pmVO)) { //변경을 수행
			alertMsg="회원정보가 성공적으로 변경되었습니다.";
		}//end if
		
		JOptionPane.showMessageDialog(ew, alertMsg);
		
		inputFieldReset();
		
		selectedNum=-1;
		
	}// modifyMember

	public void removeMember() {
		
		if( numValidate() ) {
			return;
		}//end if
	
		String alterMsg="회원 정보를 삭제하지 못하였습니다.";
		
		if( ss.removePsmtMember( selectedNum ) ) {
			alterMsg="회원정보를 삭제하였습니다.";
		}//end if
		
		JOptionPane.showMessageDialog(ew, alterMsg);
		
		inputFieldReset();
		
		// Total JLabel 값 바꾸기
		ew.getJlblCount2().setText(String.valueOf(ss.searchAllCnt()));
		
		selectedNum = -1;
	}// deleteList

	public void changeList() {
		//DBMS에서 모든 회원정보를 검색하여
		List<PstmtMemberVO> list=ss.searchAllMember();
		//JList에 추가 ( MVC Pattern 개발 > 데이터는 Model이 관리)
		StringBuilder modelData=new StringBuilder();
		//모델에 값을 추가하기전에 모든 데이터를 초기화
		ew.getDlm().removeAllElements();
		//레코드가 존재하지 않을 때
		if( list.isEmpty() ) {
			ew.getDlm().addElement("회원정보가 존재하지 않습니다.");
		}//end if
		
		//레코드가 존재할 때
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy EEEE");
		for(PstmtMemberVO smVO :list) {
			modelData.delete(0, modelData.length());
			modelData
			.append(smVO.getNum()).append(",")
			.append(smVO.getName()).append(",")
			.append(smVO.getAge()).append(",")
			.append(smVO.getGender()).append(",")
			.append(smVO.getTel()).append(",")
			//rs.getDate()를 사용하면 날짜 형식을 다르게 보여줄 수 있다.
			.append(sdf.format(smVO.getInput_date())).append(",")
			//to_char을 사용하면 어디에서든 동일한 형식으로만 날짜를  보여준다
			.append(smVO.getStrInputDate())
			;
			
			ew.getDlm().addElement( modelData.toString() );
		}//end for
		
		
	}// changeList

	@Override
	public void windowClosing(WindowEvent e) {
		windowClosing();
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent e) {
		// 자주 쓰는 것들 변수에 담아보자
		String name = ew.getJtfName().getText();
		String age = ew.getJtfAge().getText();
		String phoneNumber = ew.getJtfPhoneNumber().getText();
		String gender = "";

		// 버튼 별로 다중 이벤트 처리
		if (e.getSource() == jbtnClose) {
			windowClosing();
		} // 종료버튼
		
		if (e.getSource() == jbtnAdd) {
			
			// 라디오버튼 선택값 String 변수 만들기
			if (ew.getJrbMan().isSelected()) {
				gender = ew.getJrbMan().getText();
			} // end if
			if (ew.getJrbWoman().isSelected()) {
				gender = ew.getJrbWoman().getText();
			} // end if
			
			try {
				//입력된 값을 VO객체에 할당하고
				PstmtMemberVO pmVO=new PstmtMemberVO(0, name, 
						Integer.parseInt(age), gender, phoneNumber, null,null);
				
				addMember(pmVO);// 업무로직을 처리
				
			}catch( NumberFormatException nfe) {
				JOptionPane.showMessageDialog(ew, "나이는 숫자 입니다.");
			}//end catch
			
		} // 추가버튼
		
		if (e.getSource() == jbtnChange) {
			try {
				//입력된 값을 VO객체에 할당하고
				PstmtMemberVO pmVO=new PstmtMemberVO(0, name, 
						Integer.parseInt(age), gender, phoneNumber, null,null);
				
				modifyMember(pmVO);// 업무로직을 처리
				
			}catch( NumberFormatException nfe) {
				JOptionPane.showMessageDialog(ew, "나이는 숫자 입니다.");
			}//end catch
			
		} // 변경버튼
		
		if (e.getSource() == jbtnDelete) {
			removeMember();
		} // 삭제버튼

	}// actionPerformed

}// class
