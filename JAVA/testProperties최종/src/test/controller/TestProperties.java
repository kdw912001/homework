package test.controller;

import java.util.*;
import java.io.*;

import test.entity.Employee;

public class TestProperties {

	public static void main(String[] args) {
		TestProperties test = new TestProperties();

		Properties prop = new Properties();
				
		Employee[] ear = test.readFile(prop);
		test.printConsole(prop);
		test.addEmpData(prop);
		test.printConsole(prop);
		test.saveEmpXML(ear);

	}
	
	public void addEmpData(Properties p) {
		p.setProperty("20130417", "황길동,개발부,40000030,0.1");
		p.setProperty("20130418", "홍삼,개발부,38000030,0.1");
		/*try{
			p.store(new FileWriter("empData.txt"), "2개 추가");
		}catch(IOException e) {
			e.printStackTrace();
		}*/
	}
	public Employee[] readFile(Properties p) {
		
		try {
			p.load(new FileReader("empData.txt"));
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		int i=0;
		
		Set<String> keys = p.stringPropertyNames();
		Employee[] e = new Employee[keys.size()];
		Iterator<String> keyIter = keys.iterator();
		
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			String value = p.getProperty(key);
			String[] values = value.split(",");
			int eld = Integer.parseInt(key);
			String eName = values[0];
			String dept = values[1];
			int salary = Integer.parseInt(values[2]);
			double bonusPoint = Double.parseDouble(values[3]);
			e[i] = new Employee(eld,eName,dept,salary,bonusPoint);
			i++;
		}
		return e;
	}
	public void printConsole(Properties p) {
		p.list(System.out);
	}
	public void saveEmpXML(Employee[] er) {
		Properties prop = new Properties();
		int bonusSalary;
		System.out.println(er.length);
		for(int i=0; i<er.length;i++) {
			bonusSalary = ((int)(er[i].getSalay()+(er[i].getSalay()*er[i].getBouusPoint())));
			prop.setProperty(String.valueOf(er[i].getEld()), er[i].toString()+" "+String.valueOf(bonusSalary));
		}
		
		try {
			prop.storeToXML(new FileOutputStream("empResult.xml"), "보너스 연봉", "UTF-8");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
