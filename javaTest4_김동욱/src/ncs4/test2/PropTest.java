package ncs4.test2;
import java.io.*;
import java.util.*;
public class PropTest {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("1", "apple,1200,3");
		prop.setProperty("2", "banana,2500,2");
		prop.setProperty("3", "grape,4500,5");
		prop.setProperty("4", "orange,800,10");
		prop.setProperty("5", "melon,5000,2");
		
		fileSave(prop);
		fileOpen(prop);
	}
	public static void fileSave(Properties p) {
		try {
			p.storeToXML(new FileOutputStream("data.xml"), "data.xml파일 입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void fileOpen(Properties p) {
		try {
			p.loadFromXML(new FileInputStream("data.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Fruit[] f = new Fruit[5];
		Set<String> keys = p.stringPropertyNames();
		Iterator<String> keyIter = keys.iterator();
		int index=0;
		String[] keyStore = new String[5];
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			keyStore[index]=key;
			String[] str = p.getProperty(key).split(",");
			String name = str[0];
			int price = Integer.valueOf(str[1]);
			int quantity = Integer.valueOf(str[2]);
			
			f[index] = new Fruit(name,price,quantity);
			index++;
		}
		String tmp=null;
		Fruit temp=null;
		for(int i=0; i<f.length-1;i++) {
			for(int j=i; j<f.length;j++) {
				if(keyStore[i].compareTo(keyStore[j])>0) {
					tmp = keyStore[i];
					keyStore[i]=keyStore[j];
					keyStore[j]=tmp;
					temp= f[i];
					f[i]=f[j];
					f[j]=temp;
				}
			}
		}
		for(int i=0; i<f.length;i++) {
			System.out.println(keyStore[i]+" = "+f[i].toString());
		}
		
	}

}
