/*
 * �丮�縦 �����Ѵ�
 * */
package food;

public class Cook {
	//���� �ڷ������� has a���踦 ������� �� ��� ����?
	//���� �ڷ����� �����ǰų�, ��ȭ�� �������
	//���� Ŭ������ has a���迡 �ִ��������� ��ȭ���ױ� ������
	//������������ ������ �� �ִ�.
	private Pan pan;

//	public Cook() {
	//new�� �ִ��� ������, �������� ������ �ذ�ɼ�����.
	//�ذ�å? new�� ��������!!
	//pan = new FriPan();
//}
//	
	
	//�ܺη� ���� �ʿ��� ��ü�� ���Թޱ� ���� setter�޼���
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public void makeFood() {
		pan.boil();
	}
	
}
