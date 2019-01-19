package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import model.vo.Bank;

public class BankController {
	
	
	private Properties pp = new Properties();
		
	int bNo = pp.size();//초기값은 0
	
	public BankController() {
		System.out.println("어서오세요 KD뱅크입니다.");
		try {
			pp.loadFromXML(new FileInputStream("bankAccount.xml"));
		} catch(FileNotFoundException e2) {//bank.xml파일이 없을경우
			System.out.println("통장개설을 하기 바랍니다.");
		}catch(InvalidPropertiesFormatException e1) {//bak.xml내용이 비었을 경우
			System.out.println("통장개설을 하기 바랍니다.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void bankInsert(Bank b) {
			
		/*기본생성자에 없을 시 
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
		bNo = pp.size();
		pp.setProperty(String.valueOf(bNo), b.toString());
		try {
			pp.storeToXML(new FileOutputStream("bankAccount.xml"), "고객정보");
			pp.storeToXML(new FileOutputStream(bNo+".xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		bNo++;//최종적으로 bNo++
	}
	
	public Properties bankAllPrint() {
		return pp;
	}
	
	public void bankSearch() {}
	
	public void bankDeposit() {}
	
	public void bankWithdraw() {}
	
	public void bankAcctransfer() {}
	
}
