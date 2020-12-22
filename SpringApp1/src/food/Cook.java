/*
 * 요리사를 정의한다
 * */
package food;

public class Cook {
	//상위 자료형으로 has a관계를 명시했을 때 얻는 장점?
	//하위 자료형이 삭제되거나, 변화가 생기더라도
	//현재 클래스와 has a관계에 있는의존성을 약화시켰기 때문에
	//유지보수성을 높여줄 수 있다.
	private Pan pan;

//	public Cook() {
	//new가 있는한 영원히, 유지보수 문제는 해결될수없다.
	//해결책? new를 제거하자!!
	//pan = new FriPan();
//}
//	
	
	//외부로 부터 필요한 객체를 주입받기 위한 setter메서드
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public void makeFood() {
		pan.boil();
	}
	
}
