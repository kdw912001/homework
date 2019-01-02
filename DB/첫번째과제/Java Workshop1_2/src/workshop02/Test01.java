package workshop02;

public class Test01 {

	public static void main(String[] args) {
		System.out.println("입력값 : "+args[0]+" "+args[1]+" "+args[2]);
		int max = Integer.parseInt(args[0]);
		int min = Integer.parseInt(args[0]);

		for(int i=0; i<args.length;i++) {
			if(max<Integer.parseInt(args[i])) {
				max=Integer.parseInt(args[i]);
			}
			if(min>Integer.parseInt(args[i])) {
				min=Integer.parseInt(args[i]);
			}
		}
		System.out.println("최대값 : "+max);
		System.out.println("최소값 : "+min);
	}

}
