package day0328;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LoginForm extends JFrame {
	
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JLabel jlblOutput;
	
	public LoginForm() {
		super("로그인");
		//컴포넌트 선언 및 생성
		JLabel jlblTitle=new JLabel("로그인",JLabel.CENTER);
		jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30) );
		
		JPanel jpCenter=new JPanel();
		jpCenter.setLayout(new GridLayout(3,1));
		jpCenter.setBorder(new TitledBorder("입력정보"));
		
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		jlblOutput=new JLabel();
		
		Font font=new Font("맑은 고딕", Font.BOLD, 25);
		//컴포넌트들에게 동일한 폰트를 설정
		jtfId.setFont(font);
		jpfPass.setFont(font);
		jlblOutput.setFont(font);
		
		//border
		jtfId.setBorder(new TitledBorder("아이디"));
		jpfPass.setBorder(new TitledBorder("비밀번호"));
		jlblOutput.setBorder(new LineBorder(Color.RED));
		
		jpCenter.add(jtfId);
		jpCenter.add(jpfPass);
		jpCenter.add(jlblOutput);
		//레이아웃 설정
		
		//배치
		add("North",jlblTitle);
		add("Center",jpCenter);
		
		LoginFormEvt lfe=new LoginFormEvt(this);
		//윈도우 이벤트
		addWindowListener(lfe);
		//액션이벤트
		jtfId.addActionListener(lfe);
		jpfPass.addActionListener(lfe);
		
		//윈도우 크기설정
		setBounds(50, 50, 300, 280);
		//가시화
		setVisible(true);
	}//TestTitledBorder
	
	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JLabel getJlblOutput() {
		return jlblOutput;
	}



	public static void main(String[] args) {
		new LoginForm();
	}//main

}//class
