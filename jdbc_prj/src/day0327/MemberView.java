package day0327;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MemberView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfZipcode;
	private JTextField jtfAddress;
	private JTextField jtfDetails;
	private JButton jbtnSerch;

	
	public JTextField getJtfZipcode() {
		return jtfZipcode;
	}

	public JTextField getJtfAddress() {
		return jtfAddress;
	}

	public JTextField getJtfDetails() {
		return jtfDetails;
	}

	public JButton getJbtnSerch() {
		return jbtnSerch;
	}


	public MemberView() {
		getContentPane().setLayout(null);
		
		JLabel jlblZipcode = new JLabel("우편번호");
		jlblZipcode.setBounds(40, 40, 70, 20);
		jlblZipcode.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jlblZipcode);
		
		JLabel jlblAddress = new JLabel("주소");
		jlblAddress.setBounds(40, 80, 70, 20);
		jlblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jlblAddress);
		
		JLabel jlblDetails = new JLabel("상세주소");
		jlblDetails.setBounds(40, 120, 70, 20);
		jlblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jlblDetails);
		
		jtfZipcode = new JTextField();
		jtfZipcode.setBounds(120, 40, 130, 20);
		getContentPane().add(jtfZipcode);
		jtfZipcode.setColumns(10);
		
		jtfAddress = new JTextField();
		jtfAddress.setBounds(120, 80, 250, 20);
		jtfAddress.setColumns(10);
		getContentPane().add(jtfAddress);
		
		jtfDetails = new JTextField();
		jtfDetails.setBounds(120, 120, 250, 20);
		jtfDetails.setColumns(10);
		getContentPane().add(jtfDetails);
		
		jbtnSerch = new JButton("검색");
		jbtnSerch.setBounds(262, 39, 100, 25);
		getContentPane().add(jbtnSerch);
		
		// # 이벤트
		MemberViewEvt fe = new MemberViewEvt(this);
		jbtnSerch.addActionListener(fe);
		
		setBounds(100, 100, 450, 219);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}//MemberView
}//class
