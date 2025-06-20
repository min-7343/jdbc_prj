package kr.co.sist.vo;

import java.sql.Date;

public class PstmtMemberVO {

	private int num;
	private String name;
	private int age;
	private String gender;
	private String tel;
	private Date input_date;
	private String strInputDate;
	
	public PstmtMemberVO() {
	}//PstmtMemberVO

	public PstmtMemberVO(int num, String name, int age, String gender, String tel, Date input_date,
			String strInputDate) {
		super();
		this.num = num;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tel = tel;
		this.input_date = input_date;
		this.strInputDate = strInputDate;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getTel() {
		return tel;
	}

	public Date getInput_date() {
		return input_date;
	}

	public String getStrInputDate() {
		return strInputDate;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}

	public void setStrInputDate(String strInputDate) {
		this.strInputDate = strInputDate;
	}

	@Override
	public String toString() {
		return "PstmtMemberVO [num=" + num + ", name=" + name + ", age=" + age + ", gender=" + gender + ", tel=" + tel
				+ ", input_date=" + input_date + ", strInputDate=" + strInputDate + "]";
	}


	
	
	
}//class
