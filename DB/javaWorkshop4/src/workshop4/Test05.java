package workshop4;

public class Test05 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		if(num>=1 && num<=5) {
			int sum=0;
			for(int i=num; i<=10;i++) {
				if(i%3==0 || i%5==0) {
					continue;
				}else if(i==8) {
					System.out.print(i);
				}else {
					System.out.print(i+"+");
				}
				sum+=i;
			}
			System.out.println();
			System.out.println("결과 : "+sum);
		}else
			System.out.println("1~5까지의 정수형 데이터가 아닙니다. 다시 입력해주세요.");
	}
}
