package kr.co.sist.stmt.design;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.stmt.evt.StatementWindowEvent;

public class StatementWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4153015706274120123L;
	
	private JTextField jtfName;
	private JTextField jtfAge;
	private JTextField jtfPhoneNumber;
	private JRadioButton jrbMan;
	private JRadioButton jrbWoman;
	private JList<String> jlData;
	private JButton jbtnAdd;
	private JButton jbtnChange;
	private JButton jbtnDelete;
	private JButton jbtnClose;
	private DefaultListModel<String> dlm;
	private ButtonGroup bg;
	private JLabel jlblCount2; 

	public StatementWindow() {
		super("Statement를 사용한 CRUD");
		
		// 객체 생성
		jtfName = new JTextField(10);
		jtfAge = new JTextField(10);
		jtfPhoneNumber = new JTextField(10);
		jrbMan = new JRadioButton("남자");
		jrbWoman = new JRadioButton("여자");
		jbtnAdd = new JButton("추가");
		jbtnDelete = new JButton("삭제");
		jbtnChange = new JButton("변경");
		jbtnClose = new JButton("종료");
		JLabel jlblAge = new JLabel("나이");
		JLabel jlblGender = new JLabel("성별");
		JLabel jlblName = new JLabel("이름");
		JLabel jlblPhoneNumber = new JLabel("연락처");
		dlm = new DefaultListModel<String>();
		jlData = new JList<String>(dlm);
		bg = new ButtonGroup();
		JLabel jlblCount = new JLabel("Total");
		jlblCount2 = new JLabel( String.valueOf(dlm.getSize()) );
		

		// JList 스크롤 추가
		JScrollPane jsp = new JScrollPane(jlData);

		// 레이아웃 null로 수동 배치해보자!
		setLayout(null);

		// 라디오버튼 그룹 만들기
		bg.add(jrbMan);
		bg.add(jrbWoman);

		// 패널 객체 생성 및 컴포넌트 추가
		// - 어쩌피 수동배치라 순서는 상관 없다 
		JPanel jpWrite = new JPanel();
		JPanel jpButton = new JPanel();
		JPanel jpCount = new JPanel();

		jpWrite.add(jlblName);
		jpWrite.add(jtfName);
		jpWrite.add(jlblAge);
		jpWrite.add(jtfAge);
		jpWrite.add(jlblGender);
		jpWrite.add(jrbMan);
		jpWrite.add(jrbWoman);
		jpWrite.add(jlblPhoneNumber);
		jpWrite.add(jtfPhoneNumber);

		jpButton.add(jbtnAdd);
		jpButton.add(jbtnChange);
		jpButton.add(jbtnClose);
		jpButton.add(jbtnDelete);
		
		jpCount.add(jlblCount);
		jpCount.add(jlblCount2);

		// #. 수동배치 ( 좌표 및 크기 설정 )
		// #-1. 입력 Panel 안 수동배치
		jpWrite.setBorder(new TitledBorder("입력"));
		jpWrite.setLayout(null); // 몰라 다 수동으로 배치해보자.
		jpWrite.setBounds(5, 0, 200, 150);
		jlblName.setBounds(20, 20, 50, 30);
		jlblAge.setBounds(20, 50, 50, 30);
		jlblGender.setBounds(20, 80, 50, 30);
		jlblPhoneNumber.setBounds(20, 110, 50, 30);
		jtfName.setBounds(65, 25, 120, 25);
		jtfAge.setBounds(65, 55, 120, 25);
		jrbMan.setBounds(65, 85, 50, 25);
		jrbWoman.setBounds(125, 85, 50, 25);
		jtfPhoneNumber.setBounds(65, 115, 120, 25);
		// #-2. 버튼 Panel 안 수동배치
		jpButton.setBorder(new TitledBorder("버튼"));
		jpButton.setLayout(null);
		jpButton.setBounds(5, 205, 470, 110);
		jbtnAdd.setBounds(20, 25, 100, 30);
		jbtnChange.setBounds(135, 25, 100, 30);
		jbtnDelete.setBounds(245, 25, 100, 30);
		jbtnClose.setBounds(355, 25, 100, 30);
		
		// #-3. JList 수동 배치
		jsp.setBorder(new TitledBorder("리스트"));
		jsp.setBounds(210, 0, 265, 205);
		// #-4. JLabel 수동 배치
		jpCount.setBorder(new TitledBorder(""));
		jpCount.setLayout(null);
		jpCount.setBounds(5,155,200,40);
		jlblCount.setBounds(30,10,50,20);
		jlblCount2.setBounds(150,10,50,20);

		// 이벤트 추가 ( 객체 생성 및 Lister method )
		StatementWindowEvent ewe = new StatementWindowEvent(this);
		jbtnAdd.addActionListener(ewe);
		jbtnChange.addActionListener(ewe);
		jbtnDelete.addActionListener(ewe);
		jbtnClose.addActionListener(ewe);
		jlData.addMouseListener(ewe);

		// 컴포넌트 추가
		add(jpWrite);
		add(jpButton);
		add(jpCount);
		add(jsp);

		// 프레임 사이즈
		setBounds(300, 100, 500, 360);

		// 가시화
		setVisible(true);

		// 윈도우 종료 이벤트
		addWindowListener(ewe);
	}
	

	public JLabel getJlblCount2() {
		return jlblCount2;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfAge() {
		return jtfAge;
	}

	public JTextField getJtfPhoneNumber() {
		return jtfPhoneNumber;
	}

	public JRadioButton getJrbMan() {
		return jrbMan;
	}

	public JRadioButton getJrbWoman() {
		return jrbWoman;
	}

	public JList<String> getJlData() {
		return jlData;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnChange() {
		return jbtnChange;
	}

	public JButton getJbtnDelete() {
		return jbtnDelete;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

}// class
