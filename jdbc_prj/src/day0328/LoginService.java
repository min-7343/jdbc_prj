package day0328;

import java.sql.SQLException;

public class LoginService {
	public String login(LoginVO lVO) {
		String name="";
		
		try {
			//statement : SQLInjection 발생 > 대비코드 작성 (blockInjection)
			name=LoginDAO.getIstance().selectLogin(lVO);
			
			//PreparedStatement : SQLInjection발생 가능성이 없다.
			name=LoginDAO.getIstance().selectPstmtLogin(lVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}//end catch
		return name;
		
	}//login
	

}//LoginService
