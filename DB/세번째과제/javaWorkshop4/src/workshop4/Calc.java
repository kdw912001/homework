package workshop4;

public class Calc {
	public int calculate(int data) {
		System.out.print("짝수 : ");
		int sum = 0;
		for(int i=1; i<=data;i++) {
			if(i % 2 == 0) {
				System.out.print(i+" ");
				sum+=i;
			}
		}
		System.out.println();
		return sum;
	}
}
