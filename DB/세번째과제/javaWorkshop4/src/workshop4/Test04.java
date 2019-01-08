package workshop4;

public class Test04 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		System.out.println("결과 : "+new Calc().calculate(num));
	}

}
