/*���ϰ� ���õ� ������ ����� ��Ƴ��� Ŭ����*/
package common;

public class FileManager {
   //Ȯ���ڸ� �����ϱ�
   public static String getExtend(String path) {
      int lastIndex = path.lastIndexOf(".");
      String ext = path.substring(lastIndex+1, path.length());
      System.out.println(lastIndex);
      return ext;
   }
   
   //�̸� ���� �׽�Ʈ �غ��� ����
   /*
   public static void main(String[] args) {
      //���� ������ ����� ����.jpg
      String filename = "D:\\User\\yong\\Makes\\Want_Project\\Want_Logo\\favicon.png";
      getExtend(filename);
   }
   */
}