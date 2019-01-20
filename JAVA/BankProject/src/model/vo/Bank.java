package model.vo;
import java.text.SimpleDateFormat;
import java.util.*;
public class Bank {
	//private int bNo; //통장고유번호 key값 
					//BankController.java에 선언함 
	private String username;
	private char gender;
	private int age;
	private String bNumber;
	private int price;
	private Date openDate = new GregorianCalendar().getTime();
	
	public Bank() {
	}
	
	
	public Bank(String username, char gender, int age, String bNumber, int price, Date openDate) {
		super();
		this.username = username;
		this.gender = gender;
		this.age = age;
		this.bNumber = bNumber;
		this.price = price;
		this.openDate = openDate;
	}

	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getbNumber() {
		return bNumber;
	}


	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getOpenDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		return sdf.format(openDate);
	}


	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}


	public String toString() {
		return this.username+","+this.gender+","+this.age+","+this.bNumber+","+this.price+","+this.getOpenDate();
	}
	
}
