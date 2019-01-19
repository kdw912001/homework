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
	private Properties individualInfo = new Properties();
	//거래를 하기 위한 Properties
	
	int bNo;
	
	public BankController(String no) {//기본생성자에서 bankAccount.xml을 load
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
		
		try {
			individualInfo.loadFromXML(new FileInputStream(no+".xml"));
		} catch (Exception e) {
			System.out.println("해당 카드와 통장은 유효하지 않습니다. 다시 넣어주세요.");
			System.exit(0);
		}
	}
	
	public void bankInsert(Bank b) {
		
		
		allInfo.setProperty(String.valueOf(bNo), b.toString());
		customerInfo.setProperty(String.valueOf(bNo), b.toString());
		
		//customerInfo 각각 xml 파일 저장시
		//customerInfo를 이뤄야 할 것
		//key : 거래순서
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
	
	public Properties bankDeposit(String no, int p) {//String number, String name, int p) {
		//allInfo 해당 키값의 xml에 입금 시킴
		//여러번 수정 결과 no.xml을 로드한 properties에
		//거래번호+1, 입금할돈 더하면 될듯(price+p)
		Set<String> keys = allInfo.stringPropertyNames();
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
			if(no.equals(key)) {
				Bank b = new Bank(username,gender,age,bNumber,price+p,deposit);
				
				//depositProp.setProperty(key, b.toString());
				individualInfo.setProperty(key, b.toString());
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
		return individualInfo;
		//individualInfo.getProperty(no);
		//return individualInfo;
	}
	
	public void bankWithdraw() {}
	
	public void bankAcctransfer() {}
	
}
