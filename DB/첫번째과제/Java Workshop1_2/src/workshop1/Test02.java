package workshop1;

public class Test02 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		double sum = 0, times = 1;
		int count=0;
		if(num>=5 && num<=10 ) {
			for(int i=1;i<=num;i++) {
				sum += i;
				times *= i;
				count++;
			}
			System.out.println("합 : "+sum);
			System.out.println("곱 : "+times);
			System.out.println("평균 : "+ sum/count);
		}
		else
			System.out.println("다시 입력하세요");

	}

}
