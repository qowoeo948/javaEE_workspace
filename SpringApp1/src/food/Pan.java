/*
 * 일반 프라이팬이건, 전기프라이펜이건, 증기 프라이팬이건
 * 모두를 가리킬 수 있는 최상위 인터페이스
 * (추상클래스도 가능하지만, 다중 상속이 될 우려가 있기 떄문에
 * 사실상 인터페이스가 압도적으로 많이 쓰인다)
 * */
package food;

public interface Pan {
	//구현 강제할 메서드
	public void boil();
	
	
}
