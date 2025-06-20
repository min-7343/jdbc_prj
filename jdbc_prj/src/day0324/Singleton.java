package day0324;

/**
 * Singleton pattern이 도입된 클래스
 */
public class Singleton {
	private static Singleton s;
	//1.클래스외부에서 직접 객체화 할 수 없도록 생성자를 private으로 설정
	private Singleton() {
		System.out.println("객체 생성");
	}//Sigleton -> private 로 외부에서 객체화가 되지 않는다.

	//2. 객체명 없이 객체를 얻어가는 일을 하는 method
	public static Singleton getInstance() {
		if(s==null) {// 객체가 없거나, 사용중에 객체가 소멸되었다면 
			//객체를 생성한다
			s=new Singleton();
		}//end if
		
		return s;
		
	}//getInstance


}//Singleton
