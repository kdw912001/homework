package workshop4;

public class Test02 {

	public static void main(String[] args) {
		int[] arr3 = new int[5];
		int sum=0;
		for(int i=0; i<arr3.length;i++) {
			int rnum = (int)(Math.random()*10)+1; 
			arr3[i] = rnum;
			if(i > 0) {
				//중복 체크 처리
				for(int j=0; j < i; j++) {
					if(arr3[j] == arr3[i]) {
						i--;
						break;
					}
				}
			}
			
		}
		for(int i=0; i<arr3.length;i++) {
			System.out.print(arr3[i] + " ");
			sum += arr3[i];
		}
		System.out.println();
		System.out.println("sum = " + sum);
		System.out.printf("avg = %.0f", (double)sum/arr3.length);
	}
}
