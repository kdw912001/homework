package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import model.vo.Bank;

public class BankController {

	private Properties allInfo = new Properties();// 고객 전체 정보
	private Properties customerInfo = new Properties();// 고객 각각 정보를 저장하기위한 변수
	// private Properties individualInfo = new Properties();
	// 거래를 하기 위한 Properties

	int bNo;

//	public BankController(String no) {//기본생성자에서 bankAccount.xml을 load
	public BankController() {
		System.out.println("어서오세요 KD뱅크입니다.");
		try {
			allInfo.loadFromXML(new FileInputStream("bankAccount.xml"));
		} catch (FileNotFoundException e2) {// bankAccount.xml파일이 없을경우
			System.out.println("통장개설을 하기 바랍니다.");
		} catch (InvalidPropertiesFormatException e1) {// bankAccount.xml내용이 비었을 경우
			System.out.println("통장개설을 하기 바랍니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} // 모두 Exception 으로 처리해도 되지만 나올만한 예외를 명시했음.
		bNo = allInfo.size();// 나 같은 경우 키 값을 0부터 시작했는데 이건 자기 마음대로하면 될듯

		/*
		 * try { individualInfo.loadFromXML(new FileInputStream(no+".xml"));
		 * System.out.println(no+" 회원님 환영합니다.");
		 * 
		 * } catch (Exception e) { if(bNo == 0) {
		 * System.out.println("해당 카드와 통장은 유효하지 않습니다. 통장개설을 하기 바랍니다."); } else {
		 * System.out.println("저희는 해당 정보가 없습니다. 다시 실행해주시거나 통장개설을 하기 바랍니다."); } }
		 */
	}

	public void bankInsert(Bank b) {

		allInfo.setProperty(String.valueOf(bNo), b.toString());
		// customerInfo.setProperty(String.valueOf(bNo), b.toString());
		customerInfo.setProperty("0", b.getUsername() + "," + b.getbNumber());// 처음고객정보
		customerInfo.setProperty("1",
				b.getOpenDate() + "," + b.getUsername() + "," + b.getPrice() + "원 입금,남은돈:" + b.getPrice() + "원");
		// customerInfo 각각 xml 파일 저장시
		// customerInfo를 이뤄야 할 것
		// key : 거래순서나 시간? search를 하기 위해선
		// value : 계좌번호, 시간, 입출금액(+/-표시), 잔액
		try {
			allInfo.storeToXML(new FileOutputStream("bankAccount.xml"), "고객정보");
			customerInfo.storeToXML(new FileOutputStream(bNo + ".xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		bNo++;// 최종적으로 bNo++
		customerInfo = new Properties();// Properties가 저장되기 때문에 각각 저장하기 위해 저장 후에 초기화
		// allInfo를 초기화하면 전체 조회하려면 loadFromXML을 한번 더 해야 할 듯.
	}

	public Properties bankAllPrint() {
		return allInfo;// 전체 계좌조회를 위해 Properties를 리턴
	}

	public Properties bankSearch(String keyword) {
		// 상식적으로 이름을 조회하는게 맞는데 동명이인 고려해야하니 일단 키로 search
		// 직원(관리자) 입장에서 해당 계좌를 조회하는 것이기 때문에
		// 계좌번호와 이름으로 조회하면 될듯
		Properties searchInfo = new Properties();
		/*
		 * for(int i=0; i<allInfo.size();i++) { allInfo.get }
		 * allInfo.getProperty(keyword);
		 */

		try {
			searchInfo.loadFromXML(new FileInputStream(keyword + ".xml"));
		} catch (FileNotFoundException e2) {// keyword.xml파일이 없을경우
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return searchInfo;
	}

	public Properties bankDeposit(String keyword, int p) {// String number, String name, int p) {
		// allInfo 해당 키값의 xml에 입금 시킴
		// 여러번 수정 결과 no.xml을 로드한 properties에
		// 거래번호+1, 입금할돈 더하면 될듯(price+p)

		Properties individualInfo = new Properties();
		try {
			individualInfo.loadFromXML(new FileInputStream(keyword + ".xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int myPrice = 0;
		
		String myValue = individualInfo.getProperty(String.valueOf(individualInfo.size()-1));
		String [] myInfo = myValue.split(",");
		String[] pp = myInfo[3].split(":|원");
		myPrice = Integer.parseInt(pp[1]);
		Bank b = new Bank();
		
		b.setUsername("");// 자기자신이 입금하는 것이므로 null이 안나오게 빈칸
		individualInfo.setProperty(String.valueOf(individualInfo.size()),
				b.getOpenDate() + "," + b.getUsername() + "," + p + "원 입금,남은금액:" + (myPrice + p) + "원");
		// allInfo 에 남은 돈 업데이트 해줘야할듯???
		Set<String> keys = allInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		for(int i=0; keyIter.hasNext();i++) {
			String key = keyIter.next();
			String value = allInfo.getProperty(key);
			String[] values = value.split(",");
			
			if(key.equals(keyword)) {
				allInfo.setProperty(keyword,values[0]+","+values[1]+","
			+values[2]+","+values[3]+","+(myPrice + p)+","+values[5]);
			}
		}
		
		
		try {
			individualInfo.storeToXML(new FileOutputStream(keyword + ".xml"), "");
			allInfo.storeToXML(new FileOutputStream("bankAccount.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return individualInfo;

	}

	public Properties bankWithdraw(String keyword, int p) {

		Properties individualInfo = new Properties();
		try {
			individualInfo.loadFromXML(new FileInputStream(keyword + ".xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int myPrice = 0;
			
		String myValue = individualInfo.getProperty(String.valueOf(individualInfo.size()-1));
		String [] myInfo = myValue.split(",");
		String[] pp = myInfo[3].split(":|원");
		myPrice = Integer.parseInt(pp[1]);
		Bank b = new Bank();
		
		b.setUsername("");// 자기자신이 입금하는 것이므로 null이 안나오게 빈칸
		individualInfo.setProperty(String.valueOf((individualInfo.size())),
				b.getOpenDate() + "," + b.getUsername() + "," + p + "원 출금,남은금액:" + (myPrice - p) + "원");
		// allInfo 에 남은 돈 업데이트 해줘야할듯???
		Set<String> keys = allInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		for(int i=0; keyIter.hasNext();i++) {
			String key = keyIter.next();
			String value = allInfo.getProperty(key);
			String[] values = value.split(",");
			
			if(key.equals(keyword)) {
				allInfo.setProperty(keyword,values[0]+","+values[1]+","
			+values[2]+","+values[3]+","+(myPrice - p)+","+values[5]);
			}
		}
		
		
		try {
			individualInfo.storeToXML(new FileOutputStream(keyword + ".xml"), "");
			allInfo.storeToXML(new FileOutputStream("bankAccount.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return individualInfo;
	}
	
	public Properties bankExist(String keywordNo) {
		//계좌를 입력받아 bankAccount.xml에 있는지 확인 후
		//존재 유무를 return
		Set<String> keys = allInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		String key ="";
		while(keyIter.hasNext()) {
			key = keyIter.next();
			String value = allInfo.getProperty(key);
			String[] values = value.split(",");
			if(values[3].equals(keywordNo)) {
				break;
			}
		}
		Properties existProp = new Properties();
		try {
			existProp.loadFromXML(new FileInputStream(key+".xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return existProp;
	}
	
	public Properties bankAcctransfer(String keyword, String keywordNo, int p) {
		// menu에서 본인의 정보를 입력받고
		// 상대의 계좌번호, 금액을 입력받고
		// 상대의 이름, 금액, 계좌번호 맞는지? 멘트
		// 본인의 계좌에서 돈 줄이고
		// 상대의 계좌에는 돈 올림
		// allInfo에서 해당 고객 정보의 돈을 수정해야 함.
		
		//본인계좌 상대방 계좌에 대한 Properties 선언
		Properties myProp = new Properties();
		Properties oppositeProp = new Properties();
		
		//본인 계좌, 상대방 계좌정보를 load
		//상대방 계좌정보를 load하려면 계좌번호를 통해 파일이름이나
		//bankAccount.xml에서 계좌번호에 해당하는 키 값을 알아야 함.
		//bankAccount.xml을 load하기 위한 Properties 선언
		Properties bankAccount = new Properties();
		
		try {
			bankAccount.loadFromXML(new FileInputStream("bankAccount.xml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Set<String> keys = bankAccount.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		String bankKey = "";
		String[] bankInfo = new String[10];
		String myName = "";
		String oppositeKey = "";
		String oppositeName = "";
		while(keyIter.hasNext()) {
			bankKey = keyIter.next();
			String bankValue = bankAccount.getProperty(bankKey);
			bankInfo = bankValue.split(",");
			if(bankKey.equals(keyword)) {
				myName = bankInfo[0];
				//myInfoArr = bankInfo;
			}
			if(bankInfo[3].equals(keywordNo)) {
				/*break;
				//계좌번호가 같으면 그때 break를 걸어 그때의 bankKey를 획득
			*/	oppositeKey = bankKey;
				oppositeName = bankInfo[0];
				//oppositeInfoArr = bankInfo;
			}
		}
		
		//본인 계좌, 상대방 계좌정보를 load
		try {
			myProp.loadFromXML(new FileInputStream(keyword+".xml"));
			oppositeProp.loadFromXML(new FileInputStream(oppositeKey+".xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//본인 계좌에서 p만큼 금액 감소
		String myValue = myProp.getProperty(String.valueOf(myProp.size()-1));
		String[] myInfo = myValue.split(",");
		String[] myPriceArr = myInfo[3].split(":|원");
		int myPrice = Integer.parseInt(myPriceArr[1]);
		Bank b = new Bank();
		
		//내 계좌정보에 상대방 이름을 넣어야 하기 때문에 setProperties는 나중에
		//상대 이름을 따오기 위해 bankAccount에서 가져올 지 
		//직접 상대방 파일에서 따와야 할지 고민해야 하지만
		//bankAccount에서 getProperties를 하기 때문에
		//위에서 bankInfo[0]을 가져오는걸로
		String date = b.getOpenDate();//내 통장과 상대방 통장에 동일한 시간이 나오게 하기 위해
		myProp.setProperty(String.valueOf((myProp.size())),
				date + "," + oppositeName + "," + p + "원 계좌이체,남은금액:" + (myPrice - p) + "원");
		
		
		//상대방 계좌에서 p만큼 증가 --계좌번호 처리 해야 함.
		//상대방계좌에서는 본인의 이름이 나와야 함
		String oppositeValue = oppositeProp.getProperty(String.valueOf(oppositeProp.size()-1));
		String[] oppositeInfo = oppositeValue.split(",");
		String[] oppositePriceArr = oppositeInfo[3].split(":|원");
		int oppositePrice = Integer.parseInt(oppositePriceArr[1]);
		
		//본인 계좌에서 name 따오기->위에 while문에서 따옴
				
		oppositeProp.setProperty(String.valueOf((oppositeProp.size())),
				date + "," + myName + "," + p + "원 입금,남은금액:" + (oppositePrice + p) + "원");
		
		while(keyIter.hasNext()) {
			bankKey = keyIter.next();
			String bankValue = bankAccount.getProperty(bankKey);
			bankInfo = bankValue.split(",");
			if(bankKey.equals(keyword)) {
				allInfo.setProperty(keyword,bankInfo[0]+","+bankInfo[1]+","
						+bankInfo[2]+","+bankInfo[3]+","+(myPrice - p)+","+bankInfo[5]);
			}
			if(bankInfo[3].equals(keywordNo)) {
				allInfo.setProperty(keyword,bankInfo[0]+","+bankInfo[1]+","
						+bankInfo[2]+","+bankInfo[3]+","+(myPrice + p)+","+bankInfo[5]);

			}
		}
		
		
		try {
			myProp.storeToXML(new FileOutputStream(keyword+".xml"), "");
			oppositeProp.storeToXML(new FileOutputStream(oppositeKey+".xml"), "");
			allInfo.storeToXML(new FileOutputStream("bankAccount.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return myProp;
	}

}

//ALLINFO에서 해당 KEY값의 PRICE를 변화 시켜야 함.
//키 값 순번대로 나오게  제일 위에는 이름 생성날짜, 게좌이름
//그리고 거래순서, 시간, 거래종, 금액, 남은금액
//계좌번호 INSERT시 계좌번호가 자동 입력되게 구현해야 함.