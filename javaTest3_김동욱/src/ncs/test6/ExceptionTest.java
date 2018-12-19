package ncs.test6;

import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calculator c = new Calculator();
		try {
			System.out.println(c.getSum(sc.nextInt()));
		}catch(InvalidException e){
			System.out.println(e.getMessage());
		}

	}

}
