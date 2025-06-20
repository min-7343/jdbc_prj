package day0328;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UsePanel extends JFrame implements ActionListener {

	private CardLayout cl;
	private JPanel mainPanel;
	private JButton jbtnPrd, jbtnMem, jbtnRecom;
	
	private MemberPane mp;
	private RecomPane rp;
	private ProductPane pp;
	
	public UsePanel() {
		super("카드레이아웃 연습");
		jbtnPrd = new JButton("상품목록");
		jbtnMem = new JButton("회원목록");
		jbtnRecom = new JButton("추천상품");
		
		JPanel jpNorth=new JPanel();
		jpNorth.add(jbtnPrd);
		jpNorth.add(jbtnMem);
		jpNorth.add(jbtnRecom);
		
//		CardLayout 설정
		cl=new CardLayout();
//		main패널이 여러 패널을 변경하여 보여줘야 하기 때문에 CardLayout을 설정
		mainPanel=new JPanel(cl);
		
//		카드레이아웃에 배치된 패널
		mp=new MemberPane();
		rp=new RecomPane();
		pp=new ProductPane();
		
		mainPanel.add(mp, "mp");
		mainPanel.add(rp, "rp");
		mainPanel.add(pp, "pp");
		
		add("North", jpNorth);
		add("Center", mainPanel);
		
		jbtnMem.addActionListener(this);
		jbtnPrd.addActionListener(this);
		jbtnRecom.addActionListener(this);
		
		setBounds(100,100,500,400);
		setVisible(true);
		setDefaultCloseOperation(ABORT);
		
	} //UsePanel
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트가 발생하면 CardLayout에 보여줄 패널을 설정하여 보여준다.
		if(e.getSource() == jbtnMem) {
			cl.show(mainPanel, "mp");
		} //end if
		if(e.getSource() == jbtnPrd) {
			cl.show(mainPanel, "pp");
		} //end if
		if(e.getSource() == jbtnRecom) {
			cl.show(mainPanel, "rp");
		} //end if
	} //actionPerformed

	public static void main(String[] args) {

	} //main

} //class
