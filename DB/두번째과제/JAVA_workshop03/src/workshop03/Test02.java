package workshop03;

public class Test02 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		
		if(num >= 5 && num <= 10) {
			
			System.out.println("결과 : " + new Calc().calculate(num));
		}else {
			System.out.println("다시 입력하세요");
		}

	}

}
