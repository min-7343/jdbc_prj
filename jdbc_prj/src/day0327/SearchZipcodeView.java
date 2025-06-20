package day0327;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class SearchZipcodeView extends JDialog {

	private JTextField jtfDongName;
	private JTable table;
	
	private DefaultTableModel dtm;
	private JButton btnSearch;
	private MemberView mv;

	public SearchZipcodeView(MemberView mv) {
		super(mv, "검색창", true);
		this.mv=mv;
		getContentPane().setLayout(null);
		
		
		JLabel jlblTitle = new JLabel("우편번호 검색");
		jlblTitle.setBounds(224, 10, 208, 40);
		jlblTitle.setFont(new Font("굴림", Font.BOLD, 24));
		jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jlblTitle);
		
		JLabel jlblDongName = new JLabel("동이름");
		jlblDongName.setBounds(30, 70, 80, 20);
		jlblDongName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jlblDongName);
		
		jtfDongName = new JTextField();
		jtfDongName.setBounds(120, 70, 430, 20);
		getContentPane().add(jtfDongName);
		jtfDongName.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(570, 70, 80, 25);
		getContentPane().add(btnSearch);
		
		dtm = new DefaultTableModel();
		String[] columnName= {"우편번호", "주소"};
		dtm=new DefaultTableModel(columnName,0);
		
		table = new JTable(dtm);
//		table.setDefaultEditor(Object.class, null); // 1-1편집못하게 막은것
		table.getColumnModel().getColumn(0).setMinWidth(100); // 최소 너비
		table.getColumnModel().getColumn(0).setMaxWidth(100); // 최대 너비
		table.getColumnModel().getColumn(0).setPreferredWidth(100); // 기본 너비
		//행의 크기를 설정
		 table.setRowHeight(30);
		
		JScrollPane jspSerchBoard = new JScrollPane(table);
		jspSerchBoard.setBounds(12, 110, 660, 340);
		add(jspSerchBoard);

		setResizable(false);
		
	/////이벤트객체와 has a 관계로 객체를 생성
		SearchZipcodeViewEvt szve=new SearchZipcodeViewEvt(this);
		//이벤트 등록
		btnSearch.addActionListener(szve);
		jtfDongName.addActionListener(szve);//필드작성 후 엔터
//		table.addMouseListener(szve);//1-2더블클릭하면 편집 가능

		setBounds(100, 100, 700, 500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}//SearchZipcodeView



	public JTextField getJtfDongName() {
		return jtfDongName;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getDtm() {
		return dtm;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}//getBtnSearch

	public MemberView getMv() {
		return mv;
	}
	
	
}//class
