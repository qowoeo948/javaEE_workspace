package blood.model;

public class BloodAdvisor {

	public String getAdvice(String blood) {
		String msg=null;
		
		if(blood.equals("A")){
			msg="꼼꼼하고 세심하다 착하고 솔직하다, 그러나 때론 소심하다";
		}else if(blood.equals("B")){
			msg="여자는 엉뚱매력 쿨하고 활발하다, 그러나 남자는 고집이 쎄다";
		}else if(blood.equals("O")){
			msg="사교성 있고, 둥글둥글하다 그러나 쓸데없이 오지랖이 넓다..";
		}else if(blood.equals("AB")){
			msg="결정이 자꾸 바뀐다, 뒤집는다, 4차원적이다";
		}
		return msg;
	}
}