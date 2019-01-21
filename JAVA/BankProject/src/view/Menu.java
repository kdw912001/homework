package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Scanner;

import controller.BankController;
import model.vo.Bank;



/*앞으로 만들 방향
 * ---관리자메뉴
1. 통장개설
2. 통장 전체 조회
3. 통장 검색 조회
4. 통장 해지

---사용자메뉴
1. 입금
2. 무통장 송금
3. 출금
4. 계좌이체
5. 조회

allInfo
private String username;
	private char gender;
	private int age;
	private String bNumber;
	private int price;
	private Date openDate;

customerInfo
거래번호(키를 날짜로 해도 될듯)//거래일자

입금/출금/계좌이체
찾으신 금액/맡기신 금액 --찾으신 금액 맡기신 금액은 합쳐도 될듯+메모
남은금액
 * 
 */
public class Menu {//Properties 저장
	private Scanner sc = new Scanner(System.in);
	/*private String no;
	{
		System.out.print("본인의 카드, 통장 고유번호를 입력 : ");
		//ATM 기기나 은행 창구로 가면 카드나 통장을 건내므로 
		//고유 번호로 그것을 대신함.
		//controller에서는 기능만 수행하기 위해 view에서 입력받음.
		no = sc.next();
	}*/
	
//	BankController bc = new BankController(no);
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
		Properties allProp = bc.bankAllPrint();
		allProp.list(System.out);
	}
	public void bankSearch() {
		System.out.print("조회할 고객정보 번호 입력 : ");
		String keyword = sc.next();
		Properties searchProp = bc.bankSearch(keyword);
		if(searchProp == null) {
			System.out.println("조회한 번호가 없습니다. 메인메뉴로 돌아갑니다.");
		}else 
			searchProp.list(System.out);
	}
	public void bankDeposit() {
		System.out.print("입금할 고객정보 번호 입력 : ");
		String keyword = sc.next();
		Properties depositProp = bc.bankSearch(keyword);
		if(depositProp == null) {
			System.out.println("조회한 고객정보가 없습니다. 메인메뉴로 돌아갑니다.");
			return;
		}/*else {
			try {
				depositProp.loadFromXML(new FileInputStream(keyword+".xml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		System.out.print("입금할 돈 : ");
		int price = sc.nextInt();
		//Properties depositProp = bc.bankDeposit(number,name,price);
		depositProp = bc.bankDeposit(keyword,price);
		if(depositProp.isEmpty()) 
			System.out.println("계좌가 없거나 고객정보가 잘못 되었습니다. 메인메뉴로 돌아갑니다.");
		else
			depositProp.list(System.out);
	}
	public void bankWithdraw() {
		System.out.print("출금할 고객정보 입력 : ");
		String keyword = sc.next();
		Properties withdrawProp = bc.bankSearch(keyword);
		if(withdrawProp == null) {
			System.out.println("조회한 고객정보가 없습니다.");
			return;
		}
		System.out.print("출금할 돈 : ");
		int price = sc.nextInt();
		withdrawProp = bc.bankWithdraw(keyword, price);
		if(withdrawProp.isEmpty())
			System.out.println("계좌가 없거나 고객정보가 잘못 되었습니다.");
		else
			withdrawProp.list(System.out);
		//list로 출력하기 보다는 입금이나 출금 완료되었습니다. 멘트만 나오면 될듯
	}
	public void bankAccTransfer() {
		System.out.println("본인의 정보 입력 : ");//카드나 통장을 넣는 것
		String keyword = sc.next();
		Properties transferProp = bc.bankSearch(keyword);
		if(transferProp == null) {
			System.out.println("조회한 고객정보가 없습니다. 처음으로 돌아갑니다.");
			return;
		}
		System.out.print("계좌이체할 상대방 계좌번호 입력 : ");
		String keywordNo = sc.next();
		Properties transferProp2 = bc.bankSearch(keywordNo);
		if(transferProp2 == null) {
			System.out.println("상대 계좌가 존재하지 않습니다. 처음으로 돌아갑니다.");
			return;
		}
		System.out.println("계좌이체할 금액입력 : ");
		int price = sc.nextInt();
		transferProp = bc.bankAcctransfer(keyword, keywordNo, price);
		if(transferProp.isEmpty()) {
			System.out.println("계좌가 없거나 고객정보가 잘못 되었습니다. 메인메뉴로 돌아갑니다.");
		}else {
			transferProp.list(System.out);
		}
		
	}
	
	public void adminMenu() {
		while(true) {
			System.out.println("===========관리자 메뉴========");
		}
	}
	
	public void customerMenu() {
		while(true) {
			System.out.println("========사용자 메뉴======");
		}
	}
	
}
