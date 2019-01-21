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

		/*
		 * Set<String> keys = allInfo.stringPropertyNames(); Iterator<String> keyIter =
		 * keys.iterator(); //Properties depositProp = new Properties(); for(int i = 0;
		 * keyIter.hasNext(); i++) { String key = keyIter.next(); String value =
		 * allInfo.getProperty(key); String[] info = value.split(","); String username =
		 * info[0]; char gender = info[1].charAt(0); int age =
		 * Integer.parseInt(info[2]); String bNumber = info[3]; int price =
		 * Integer.parseInt(info[4]); Date deposit = new GregorianCalendar().getTime();
		 * 
		 * // if(bNumber.contains(number) && username.contains(name)) {
		 * if(number.equals(key)) { Bank b = new
		 * Bank(username,gender,age,bNumber,price+p,deposit);
		 * 
		 * 
		 * 
		 * //depositProp.setProperty(key, b.toString());
		 * individualInfo.setProperty(String.valueOf((Integer.valueOf(key)+1)),
		 * b.getOpenDate()+", "+b.getUsername()+", "+b.getPrice()+"원 입금"); //key가 중복되기
		 * 때문에 행으로 추가가 안 됨. //각각 개인 통장 key는 거래순서로 해야 할듯. //예를 들어 //1. 계좌생성 //2. 입금 ....
		 * 등등... try { individualInfo.storeToXML(new FileOutputStream(key+".xml"), "");
		 * } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * } } return individualInfo;
		 */

		// 해당 no의 no.xml을 불러와서 individualInfo에 저장
		// individualInfo.setproperties(key+1,나머지) 하면 될듯
		Properties individualInfo = new Properties();
		try {
			individualInfo.loadFromXML(new FileInputStream(keyword + ".xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String key = "";
		// bankSearch(key);
		int price = 0;
		/*Set<String> keys = individualInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		for (int i = 0; keyIter.hasNext(); i++) {// 0부터하면 처음 입력한 개인정보가 나옴.
			key = keyIter.next();
			if (key.equals("0")) {
				// 키가 0일 때는 통장의 고객정보를 담고 있어 split이 이상해지기 때문에 continue
				continue;
			}
			String value = individualInfo.getProperty(key);
			String[] info = value.split(",");

			System.out.println("확인용 : " + info[2]);
			System.out.println("키 확인용 : " + key);
			System.out.println("사이즈 확인용 : " + individualInfo.size());
			String[] pp = info[2].split("원");
			// 남은 돈 때문에 다시 계산해야 함.
			// 원 입금이라는 단어 때문에 원으로 한번더 split
			price = Integer.parseInt(pp[0]);

			// .XML파일에는 숫자 순서대로 입력되는 것이 아니기 때문에
			// 총 금액을 계산하기 위한 돈에서 자꾸 삑이 남
			// KEY에서 가장 숫자가 높은(거래순서가 제일 마지막) 인것을 찾아야 함
			// individualInfo.size()-1이 현재 저장된 것 중 제일 마지막
			
			 * for(int j=0; j<individualInfo.size()-1;j++) { key = keyIter.next();
			 * if(key.equals(String.valueOf(j))) { continue; } }
			 
		}*/
		String value = individualInfo.getProperty(String.valueOf(individualInfo.size()-1));
		String [] info = value.split(",");
		String[] pp = info[3].split(":|원");
		price = Integer.parseInt(pp[1]);
		Bank b = new Bank();
		
		b.setUsername("");// 자기자신이 입금하는 것이므로 null이 안나오게 빈칸
		individualInfo.setProperty(String.valueOf((individualInfo.size())),
				b.getOpenDate() + "," + b.getUsername() + "," + p + "원 입금,남은금액:" + (price + p) + "원");
		//individualInfo.setProperty("","");
		// allInfo 에 남은 돈 업데이트 해줘야할듯???
		
		try {
			individualInfo.storeToXML(new FileOutputStream(keyword + ".xml"), "");
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
		//String key = "";
		int price = 0;

		/*Set<String> keys = individualInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		for (int i = 0; keyIter.hasNext(); i++) {
			key = keyIter.next();
			if (key.equals("0")) {
				continue;
			}
			String value = individualInfo.getProperty(key);
			String[] info = value.split(",");
			String[] pp = info[2].split("원");
			price = Integer.valueOf(pp[0]);
		}
		Bank b = new Bank();
		b.setUsername("");// 자기자신이 입금하는 것이므로 null이 안나오게 빈칸
		individualInfo.setProperty(String.valueOf((individualInfo.size())),
				b.getOpenDate() + "," + b.getUsername() + "," + p + "원 출금,남은 돈:" + (price - p) + "원");
		// allInfo 에 남은 돈 업데이트 해줘야할듯???
		try {
			individualInfo.storeToXML(new FileOutputStream(key + ".xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return individualInfo;*/
		
		String value = individualInfo.getProperty(String.valueOf(individualInfo.size()-1));
		String [] info = value.split(",");
		String[] pp = info[3].split(":|원");
		price = Integer.parseInt(pp[1]);
		Bank b = new Bank();
		
		b.setUsername("");// 자기자신이 입금하는 것이므로 null이 안나오게 빈칸
		individualInfo.setProperty(String.valueOf((individualInfo.size())),
				b.getOpenDate() + "," + b.getUsername() + "," + p + "원 출금,남은금액:" + (price - p) + "원");
		//individualInfo.setProperty("","");
		// allInfo 에 남은 돈 업데이트 해줘야할듯???
		
		try {
			individualInfo.storeToXML(new FileOutputStream(keyword + ".xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return individualInfo;
	}
	
	public Properties bankExist(String keywordNO) {
		//계좌를 입력받아 bankAccount.xml에 있는지 확인 후
		//존재 유무를 return
		return null;
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
			}
			if(bankInfo[3].equals(keywordNo)) {
				/*break;
				//계좌번호가 같으면 그때 break를 걸어 그때의 bankKey를 획득
			*/	oppositeKey = bankKey;
				oppositeName = bankInfo[0];
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
		String[] oppositePriceArr = oppositeInfo[2].split("원");
		int oppositePrice = Integer.parseInt(oppositePriceArr[0]);
		
		//본인 계좌에서 name 따오기->위에 while문에서 따옴
		
		
		
		oppositeProp.setProperty(String.valueOf((myProp.size())),
				date + "," + myName + "," + p + "원 입금,남은금액:" + (oppositePrice + p) + "원");
		
		
		return myProp;
	}

}
