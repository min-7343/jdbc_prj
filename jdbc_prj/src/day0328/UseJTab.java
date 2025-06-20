package day0328;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class UseJTab extends JFrame {

	public UseJTab() {
		super("탭");
		
		JTabbedPane jtp=new JTabbedPane();
		jtp.add("상품", new ProductPane());
		jtp.add("추천", new RecomPane());
		jtp.add("회원정보", new MemberPane());
		
		add("Center", jtp);
		
		setBounds(100,100,500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	} //UseJTab
	
	public static void main(String[] args) {
		new UseJTab();
	} //main

} //class
