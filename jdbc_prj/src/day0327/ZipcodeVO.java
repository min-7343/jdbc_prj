package day0327;

public class ZipcodeVO {

	private String zipcode, sido, gugun, dong, bunji;

	public ZipcodeVO() {
		super();
	}
	
	public ZipcodeVO(String zipcode, String sido, String gugun, String dong, String bunji) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.bunji = bunji;
	}



	public String getZipcode() {
		return zipcode;
	}

	public String getSido() {
		return sido;
	}

	public String getGugun() {
		return gugun;
	}

	public String getDong() {
		return dong;
	}

	public String getBunji() {
		return bunji;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	@Override
	public String toString() {
		return "ZipcodeVO [zipcode=" + zipcode + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", bunji="
				+ bunji + "]";
	}

	
}//class
