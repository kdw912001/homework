package workshop4;

public class Test06 {

	public static void main(String[] args) {
		if(args[0]==null ||args[1]==null || args.length>2) {
			System.out.println("다시 입력하세요");
			return;
		}
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
		if((num1>=1 && num1<=5) || (num1>=1 && num1<=5) ) {
			int[][] arr = new int[num1][num2];
			double sum=0;
			double count=0;
			for(int i=0; i<arr.length;i++) {				
				for(int j=0; j<arr[i].length;j++) {
					arr[i][j] = (int)(Math.random()*5)+1;
					sum+=arr[i][j];
					count++;
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("sum="+sum);
			System.out.println("avg="+(sum/count));
		}else {
			System.out.println("숫자를 확인 하세요.");
		}

	}

}
