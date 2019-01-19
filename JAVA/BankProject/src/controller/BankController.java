package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import model.vo.Bank;

public class BankController {
	
	
	private Properties allInfo = new Properties();//고객 전체 정보
	private Properties customerInfo = new Properties();//고객 각각 정보를 저장하기위한 변수 
	int bNo;
	
	public BankController() {//기본생성자에서 bankAccount.xml을 load
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
	}
	
	public void bankInsert(Bank b) {
			//주석은 신경 쓰지말고 주석 외의 부분만 신경 쓰면 됨.
		/*기본생성자에 loadFromXML을 안하면 조회를 하기 위해 loadFromXML을 한번 더 해야 함.
		 *
		 * try {
			pp.loadFromXML(new FileInputStream("bank.xml"));
			bNo = pp.size();
			pp.setProperty(String.valueOf(bNo), b.toString());
			
		}catch(FileNotFoundException e2) {//bank.xml파일이 없을경우
			pp.setProperty(String.valueOf(bNo), b.toString());
		}catch(InvalidPropertiesFormatException e1) {//bak.xml내용이 비었을 경우
			bNo = pp.size();
			pp.setProperty(String.valueOf(bNo), b.toString());
		}catch (IOException e) { //기본 Exception
			e.printStackTrace();
		}
		
		try {
			pp.storeToXML(new FileOutputStream("bank.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//bNo = allInfo.size();
		
		
		allInfo.setProperty(String.valueOf(bNo), b.toString());
		customerInfo.setProperty(String.valueOf(bNo), b.toString());
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
	
	public void bankSearch() {}
	
	public void bankDeposit() {}
	
	public void bankWithdraw() {}
	
	public void bankAcctransfer() {}
	
}
