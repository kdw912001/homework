package view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Scanner;

import controller.BankController;
import model.vo.Bank;

public class Menu {//Properties 저장
	private Scanner sc = new Scanner(System.in);
	BankController bc = new BankController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("=======메인메뉴======");
			System.out.print("1. 통장개설\r\n" + 
					"2. 통장 전체 조회\r\n" + 
					"3. 통장 검색 조회\r\n" + 
					"4. 입금\r\n" + 
					"5. 출금\r\n" + 
					"6. 계좌이체\r\n" + 
					"7. 종료\r\n" + 
					"메뉴 입력 : ");
			switch(sc.nextInt()) {
			case 1: bankInsert(); break;
			case 2: bankAllPrint(); break;
			case 3: bankSearch(); break;
			case 4: bankDeposit(); break;
			case 5: bankWithdraw(); break;
			case 6: bankAccTransfer(); break;
			case 7: System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");return;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void bankInsert() {//파일마다 각각 .properties 파일
		System.out.print("이름 입력 : ");
		String userName = sc.next();
		System.out.print("성별 입력 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		System.out.print("계좌번호 입력 : ");
		String bNumber = sc.next();
		System.out.print("첫 입금 금액 : ");
		int price = sc.nextInt();
		Calendar c = new GregorianCalendar();
		//Properties p = new Properties();
		//p.setProperty(key, value);
		Bank b = new Bank(userName, gender,age,bNumber,price,c.getTime());
		bc.bankInsert(b);
		
	}
	public void bankAllPrint() {
		Properties p = bc.bankAllPrint();
		p.list(System.out);
	}
	public void bankSearch() {
		
	}
	public void bankDeposit() {
		
	}
	public void bankWithdraw() {
		
	}
	public void bankAccTransfer() {
		
	}
	
	
	
}
