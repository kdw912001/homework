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
	
	
	private Properties allInfo = new Properties();//고객 전체 정보
	private Properties customerInfo = new Properties();//고객 각각 정보를 저장하기위한 변수 
	//private Properties individualInfo = new Properties();
	//거래를 하기 위한 Properties
	
	int bNo;
	
//	public BankController(String no) {//기본생성자에서 bankAccount.xml을 load
	public BankController() {
		System.out.println("어서오세요 KD뱅크입니다.");
		try {
			allInfo.loadFromXML(new FileInputStream("bankAccount.xml"));
		} catch(FileNotFoundException e2) {//bankAccount.xml파일이 없을경우
			System.out.println("통장개설을 하기 바랍니다.");
		}catch(InvalidPropertiesFormatException e1) {//bankAccount.xml내용이 비었을 경우
			System.out.println("통장개설을 하기 바랍니다.");
		}catch (IOException e) {
			e.printStackTrace();
		}//모두 Exception 으로 처리해도 되지만 나올만한 예외를 명시했음.
		bNo = allInfo.size();//나 같은 경우 키 값을 0부터 시작했는데 이건 자기 마음대로하면 될듯 
		
		/*try {
			individualInfo.loadFromXML(new FileInputStream(no+".xml"));
			System.out.println(no+" 회원님 환영합니다.");
			
		} catch (Exception e) {
			if(bNo == 0) {
				System.out.println("해당 카드와 통장은 유효하지 않습니다. 통장개설을 하기 바랍니다.");
			}
			else {
				System.out.println("저희는 해당 정보가 없습니다. 다시 실행해주시거나 통장개설을 하기 바랍니다.");
			}
		}*/
	}
	
	public void bankInsert(Bank b) {
		
		
		allInfo.setProperty(String.valueOf(bNo), b.toString());
		//customerInfo.setProperty(String.valueOf(bNo), b.toString());
		customerInfo.setProperty("0", b.toString());//처음고객정보
		customerInfo.setProperty("1", b.getOpenDate()+","+b.getUsername()+","+b.getPrice()+"원 입금 남은돈 :"+b.getPrice()+"원");
		//customerInfo 각각 xml 파일 저장시
		//customerInfo를 이뤄야 할 것
		//key : 거래순서나 시간? search를 하기 위해선 
		//value : 계좌번호, 시간, 입출금액(+/-표시), 잔액
		try {
			allInfo.storeToXML(new FileOutputStream("bankAccount.xml"), "고객정보");
			customerInfo.storeToXML(new FileOutputStream(bNo+".xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		bNo++;//최종적으로 bNo++
		customerInfo=new Properties();//Properties가 저장되기 때문에 각각 저장하기 위해 저장 후에 초기화
		//allInfo를 초기화하면 전체 조회하려면 loadFromXML을 한번 더 해야 할 듯.
	}
	
	public Properties bankAllPrint() {
		return allInfo;//전체 계좌조회를 위해 Properties를 리턴
	}
	
	public Properties bankSearch(String keyword) {
		//상식적으로 이름을 조회하는게 맞는데 동명이인 고려해야하니 일단 키로 search
		//직원(관리자) 입장에서 해당 계좌를 조회하는 것이기 때문에
		//계좌번호와 이름으로 조회하면 될듯
		Properties searchInfo = new Properties();
		/*for(int i=0; i<allInfo.size();i++) {
			allInfo.get
		}
		allInfo.getProperty(keyword);*/
		
		try {
			searchInfo.loadFromXML(new FileInputStream(keyword+".xml"));
		} catch(FileNotFoundException e2) {//keyword.xml파일이 없을경우
			return null;
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return searchInfo;
	}
	
	public Properties bankDeposit(String keyword, int p) {//String number, String name, int p) {
		//allInfo 해당 키값의 xml에 입금 시킴
		//여러번 수정 결과 no.xml을 로드한 properties에
		//거래번호+1, 입금할돈 더하면 될듯(price+p)
		
		
		/*Set<String> keys = allInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		//Properties depositProp = new Properties();
		for(int i = 0; keyIter.hasNext(); i++) {
			String key = keyIter.next();
			String value = allInfo.getProperty(key);
			String[] info = value.split(",");
			String username = info[0];
			char gender = info[1].charAt(0);
			int age = Integer.parseInt(info[2]);
			String bNumber = info[3];
			int price = Integer.parseInt(info[4]);
			Date deposit = new GregorianCalendar().getTime();

		//	if(bNumber.contains(number) && username.contains(name)) {
			if(number.equals(key)) {
				Bank b = new Bank(username,gender,age,bNumber,price+p,deposit);
				
				
				
				//depositProp.setProperty(key, b.toString());
				individualInfo.setProperty(String.valueOf((Integer.valueOf(key)+1)),
						b.getOpenDate()+", "+b.getUsername()+", "+b.getPrice()+"원 입금");
				//key가 중복되기 때문에 행으로 추가가 안 됨.
				//각각 개인 통장 key는 거래순서로 해야 할듯.
				//예를 들어
				//1. 계좌생성
				//2. 입금  .... 등등...
				try {
					individualInfo.storeToXML(new FileOutputStream(key+".xml"), "");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		return individualInfo;*/
		
			//해당 no의 no.xml을 불러와서 individualInfo에 저장
			//individualInfo.setproperties(key+1,나머지) 하면 될듯
		Properties individualInfo = new Properties();
		try {
			individualInfo.loadFromXML(new FileInputStream(keyword+".xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String key = "";
			//bankSearch(key);
			int price = 0;
			Set<String>keys = individualInfo.stringPropertyNames();
			Iterator<String>keyIter = keys.iterator();
			for(int i=0; keyIter.hasNext();i++) {//0부터하면 처음 입력한 개인정보가 나옴.
				key = keyIter.next();
				if(key.equals("0")) {
			//키가 0일 때는 통장의 고객정보를 담고 있어 split이 이상해지기 때문에 continue 
					continue;
				}
				String value = individualInfo.getProperty(key);
				String[] info = value.split(",");
				String[] pp = info[2].split("원");
				//남은 돈 때문에 다시 계산해야 함.
				//원 입금이라는 단어 때문에 원으로 한번더 split
				price = Integer.parseInt(pp[0]);
			}
			Bank b = new Bank();
			
			b.setUsername("");//자기자신이 입금하는 것이므로 null이 안나오게 빈칸
			individualInfo.setProperty(String.valueOf((individualInfo.size())),
				b.getOpenDate()+","+b.getUsername()+","+p+"원 입금 남은 돈 : "+(price+p)+"원");
			//allInfo 에 남은 돈 업데이트 해줘야할듯???
			try {
				individualInfo.storeToXML(new FileOutputStream(key+".xml"), "");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return individualInfo;
	
	}
	
	public Properties bankWithdraw(String keyword, int p) {
		
		Properties individualInfo = new Properties();
		try {
			individualInfo.loadFromXML(new FileInputStream(keyword+".xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String key = "";
		int price = 0;
		
		Set<String> keys = individualInfo.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		for(int i=0; keyIter.hasNext();i++) {
			key = keyIter.next();
			if(key.equals("0")) {
				continue;
			}
			String value = individualInfo.getProperty(key);
			String[] info = value.split(",");
			String[] pp = info[2].split("원");
			price = Integer.valueOf(pp[0]);
		}
		Bank b = new Bank();
		b.setUsername("");//자기자신이 입금하는 것이므로 null이 안나오게 빈칸
		individualInfo.setProperty(String.valueOf((individualInfo.size())),
			b.getOpenDate()+","+b.getUsername()+","+p+"원 출금 남은 돈 : "+(price-p)+"원");
		//allInfo 에 남은 돈 업데이트 해줘야할듯???
		try {
			individualInfo.storeToXML(new FileOutputStream(key+".xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return individualInfo;
	}
	
	public void bankAcctransfer() {
		//menu에서 본인의 정보를 입력받고
		//상대의 계좌번호, 금액을 입력받고
		//상대의 이름, 금액, 계좌번호 맞는지? 멘트
		//본인의 계좌에서 돈 줄이고
		//상대의 계좌에는 돈 올림
		//allInfo에서 해당 고객 정보의 돈을 수정해야 함.
		
		
	}
	
}
