package test.controller;

import java.util.*;

import test.entity.Employee;

import java.io.*;

public class TestProperties {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		prop.setProperty("20130412", "민병조,개발부,40000030,0.3");
		prop.setProperty("20130413", "김성수,개발부,39000030,0.2");
		prop.setProperty("20130414", "윤선용,개발부,38000030,0.13");
		prop.setProperty("20130415", "김동욱,개발부,35000030,0.1");
		prop.setProperty("20130416", "윤석호,개발부,30000030,0.1");
		
		try{
			prop.store(new FileWriter("empData.txt"), "empData.txt");
		}catch(IOException e) {
			e.printStackTrace();
		}
		TestProperties test = new TestProperties();
		
		Employee[]ear = test.readFile(prop);
		test.readFile(prop);
		test.printConsole(prop);
		test.addEmpData(prop);
		test.printConsole(prop);
		test.saveEmpXML(ear);
	}
	
	public void addEmpData(Properties p) {
		p.setProperty("19920104", "안정은,개발부,40000030,0.1");
		p.setProperty("199110105", "정민경,개발부,38000030,0.1");
		try{
			p.store(new FileWriter("empData.txt"), "2개 추가");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public Employee[] readFile(Properties p) {
		Set<String> keys = p.stringPropertyNames();
		Employee[] e = new Employee[keys.size()];
		Iterator<String> keyIter = keys.iterator();
		
		try {
			p.load(new FileReader("empData.txt"));
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		//p.list(System.out);
		/*for(int i=0; keyIter.hasNext();i++) {
			String key = keyIter.next();
			String value = p.getProperty(key);
			String[] values = value.split(",");
						
			int eld = Integer.parseInt(key);
			String eName = values[0];
			String dept = values[1];
			int salary = Integer.parseInt(values[2]);
			double bonusPoint = Double.parseDouble(values[3]);
			e[i] = new Employee(eld,eName,dept,salary,bonusPoint);
		}*/
		int i=0;
		/*while(keyIter.hasNext()) {
			String key = keyIter.next();
			String value = p.getProperty(key);
			String[] values = value.split("=|,");
			System.out.println(key);
			int eld = Integer.parseInt(key);
			String eName = values[0];
			String dept = values[1];
			int salary = Integer.parseInt(values[2]);
			double bonusPoint = Double.parseDouble(values[3]);
			e[i] = new Employee(eld,eName,dept,salary,bonusPoint);
			i++;
		}*/
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			String value = p.getProperty(key);
			String[] values = value.split(",");
			//System.out.println(key);
			int eld = Integer.parseInt(key);
			//int eld = Integer.parseInt(values[0]);
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
		System.out.println(er.length);
		System.out.println(er[0].toString());
		for(int i=0; i<er.length;i++) {
			er[i].setSalary((int)(er[i].getSalary()+(er[i].getSalary()*er[i].getBonusPoint())));
			prop.setProperty(String.valueOf(er[i].getEld()), er[i].toString());
			System.out.println("ss = "+er[i].toString());
		}
		/*for(Employee e:er)
			prop.setProperty(String.valueOf(e.getEld()), e.toString());
		*/
		try {
			prop.storeToXML(new FileOutputStream("empResult.xml"), "보너스 연봉", "UTF-8");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
