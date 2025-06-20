package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0328.LoginDAO;
import day0328.LoginVO;

class TestLogin {

	@DisplayName("로그인 테스트")
	@Test
	void test() {
		LoginVO lVO=new LoginVO("kang","1234");
		LoginDAO lDAO=LoginDAO.getIstance();
		
//		assertDoesNotThrow(()-> lDAO.selectLogin(lVO));
		
		try {
			assertNotEquals(lDAO.selectLogin(lVO),"");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}//test

}//class
