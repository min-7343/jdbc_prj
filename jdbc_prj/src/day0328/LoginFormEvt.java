package day0328;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFormEvt extends WindowAdapter implements ActionListener {

	private LoginForm lf;
	
	
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JLabel jlblOutput;
	
	
	public LoginFormEvt( LoginForm lf) {
		this.lf=lf;
		//필요한 컴포넌트를 인스턴스 변수(사용할 컴포넌트)에 할당
		jtfId=lf.getJtfId();
		jpfPass=lf.getJpfPass();
		jlblOutput=lf.getJlblOutput();
		
	}//LoginFormEvt
	
	@Override
	public void windowClosing(WindowEvent e) {
		lf.dispose();
	}//windowClosing
	
	
	private boolean idChk() {
		boolean flag=false;
		String id=jtfId.getText().trim();//앞뒤공백을 제거하여 아이디를 저장
		
		flag=!id.isEmpty(); //아이디가 비어있지 않니?
		if( flag ) {//아이디에 값 이 있는경우
			jpfPass.requestFocus();//커서를 패스워드 입력 컴포넌트로 이동
		}else {//아이디가 empty("")인 경우
			jlblOutput.setText("아이디는 필수입력 입니다.!");
			jtfId.requestFocus();//커서를 아이디 입력 컴포넌트로 이동
		}//end if
		
		return flag;
	}//idChk
	
	private void passChk() {
		if( !idChk() ) {// 아이디가 없다면
			return;//호출한 곳으로 돌아가
		}//end if
		
		//char[]을 하나의 문자열로 만들어서 저장 => String에서 제공하는 모든 기능을 사용할 수 있다.
		String pass=new String( jpfPass.getPassword() );
		///비밀번호가 입력되었는지 확인
		
		if( pass.isEmpty() ) {//비밀번호가 없음
			jlblOutput.setText("비밀번호 필수 입력!!");
			jpfPass.requestFocus();
			return;//호출한 곳으로 돌아가
		}//end if
		
		//아이디와 비밀번호 있음
		//로그인 수행
		String id=jtfId.getText();
		String msg="아이디나 비번을 확인";
		
		LoginVO lVO=new LoginVO(id,pass);
		
		LoginService ls= new LoginService();
		String name=ls.login(lVO);
		if(!ls.login(lVO).isEmpty()) {
			msg=name+"님 어서오고!";
		}//end if
		
		jlblOutput.setText(msg);
	}//passChk

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jtfId) {
			idChk();
		}//end if
		
		if(ae.getSource() ==jpfPass) {
			passChk();
		}//end if
	}//actionPerformed

}//class
