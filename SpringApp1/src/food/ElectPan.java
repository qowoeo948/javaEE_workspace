package food;

public class ElectPan implements Pan{

	@Override
	public void boil() {
		System.out.println("전기로 음식을 익혀요");
	}

}
