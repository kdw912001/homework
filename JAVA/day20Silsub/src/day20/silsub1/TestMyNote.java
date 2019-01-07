package day20.silsub1;

import java.util.Scanner;

public class TestMyNote {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		menu();
	}
	public static void menu() {
		MyNote mn = new MyNote();
		do {
			int no;
			System.out.print("******   MyNote  *******\r\n" + 
					"\r\n" + 
					"	1. 노트 새로 만들기		//MyNote의 fileSave()\r\n" + 
					"	2. 노트 열기		//MyNote의 fileOpen()\r\n" + 
					"	3. 노트 열어서 수정하기	//MyNote의 fileEdit()\r\n" + 
					"	4. 끝내기		//main() 으로 리턴\r\n" + 
					"\r\n" + 
					"	메뉴 선택 :");
			no=sc.nextInt();
			switch(no) {
			case 1: mn.fileSave(); break;
			case 2: mn.fileOpen(); break;
			case 3: mn.fileEdit(); break;
			case 4: System.out.print("종료 합니다"); return;
			default : System.out.println("잘못 입력하셨습니다"); break;
			}
			
		}while(true);
	}

}
