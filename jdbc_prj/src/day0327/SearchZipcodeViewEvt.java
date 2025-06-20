package day0327;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchZipcodeViewEvt extends MouseAdapter implements ActionListener {
	private SearchZipcodeView szv;
	
	public SearchZipcodeViewEvt(SearchZipcodeView szv) {
		this.szv=szv;
	}//SearchZipcodeViewEvt

	@Override
	public void mouseClicked(MouseEvent e) {
		sendZipcode();
	}//mouseClicked

	@Override
	public void actionPerformed(ActionEvent e) {
		//선택한 row의 값을 부모로 보낼 때 사용
		searchZipcode();
	}//actionPerformed
	
	public void sendZipcode() {
		int selectedBtn=JOptionPane.showConfirmDialog(szv, "우편번호를 사용하시겠습니까?");
		switch(selectedBtn) {
		case JOptionPane.OK_OPTION :
			
			JTable table=szv.getTable();
			//JTable에 선택된 행의 값을 얻기
			System.out.println("행수 : "+ table.getSelectedRow()
			+ ", 열의 번호 : "+ table.getSelectedColumn());
			
			int seletedRow=table.getSelectedRow();
			MemberView mv=szv.getMv();
			mv.getJtfZipcode().setText((String)table.getValueAt(seletedRow, 0));
			mv.getJtfAddress().setText(table.getValueAt(seletedRow, 1).toString());
			mv.getJtfDetails().requestFocus();
			szv.dispose();
			
//			int colmnCnt=table.getSelectedColumnCount();
//			int selecteddCol=table.getSelectedColumn();
//			System.out.println(table.getSelectedColumnCount());
//			System.out.println(table.getValueAt(seletedRow, selectedBtn));
			
//			for(int col=0;col<=colmnCnt; col++) {
//				System.out.println(table.getValueAt(seletedRow, col));
//			}
		
		
		
		}//end switch
	}//sendZipcode
	
	
	public void searchZipcode() {
		String dong=szv.getJtfDongName().getText().trim();
		if(dong.isEmpty()) {
			JOptionPane.showMessageDialog(szv, "동이름은 필수 입력");
			return;
		}//end if
		
		SearchZipcodService szs=new SearchZipcodService();
		List<ZipcodeVO> list=szs.searchZipcod(dong);
		
		//조회결과를 JTable에 추가
		//1.조회결과로 배열을 만들고
		String[] data=null;
		Iterator<ZipcodeVO> ita=list.iterator(); // 한번쓰고 지나가기 때문에 (1회)-포인터
		
		ZipcodeVO zVO=null;
		StringBuilder addr=new StringBuilder();
		
		DefaultTableModel dtm=szv.getDtm();//모델을 받는다.
		
		//없는것 검색했을때 예외
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(szv, dong+"은(는) 존재하지 않습니다.");
			szv.getJtfDongName().setText("");
			return;
		}//end if
		
		//기존에 행수가 하나 이상이라면 
		System.out.println("---------"+dtm.getRowCount());
		if(	dtm.getRowCount() > 0 ) {
			//행수를 초기화한다.
			dtm.setRowCount(0); //몇 개 남겨라 -> 0 개 남겨라
		}//end if
		//새로운 데이터를 채운다.
		
		
		while(ita.hasNext()) { // 다음이 존재하면
			zVO=ita.next();
			addr.delete(0, addr.length()); // 다 지우기
			
			addr.append(zVO.getSido()).append(" ")
			.append(zVO.getGugun()).append(" ")
			.append(zVO.getDong()).append(" ")
			.append(zVO.getBunji()).append(" ");
			
			data=new String[2];//우편번호, 주소
			data[0]=zVO.getZipcode();
			data[1]=addr.toString();
			//2.DefaultTableModel에 추가 (addRow)
			dtm.addRow(data);
			
		}//end while
		
		
	}//searchZipcode

}//class
