package ncs.test3;

import java.util.*;
import java.text.*;
public class DateTest {

	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		int year = 1987;
		int month = 5;
		int date = 27;
		gc.set(GregorianCalendar.YEAR, 1987);
		gc.set(GregorianCalendar.MONTH, 4);
		gc.set(GregorianCalendar.DATE, 27);
		
		GregorianCalendar gc1 = new GregorianCalendar();
		/*int year = gc.get(GregorianCalendar.YEAR);
		int month = gc.get(GregorianCalendar.MONTH);
		int date = gc.get(GregorianCalendar.DATE);*/
		
		int day = gc.get(GregorianCalendar.DAY_OF_WEEK);
		
		String str=null;
		switch(day) {
		case 1: str = "일요일"; break;
		case 2: str = "월요일"; break;
		case 3: str = "화요일"; break;
		case 4: str = "수요일"; break;
		case 5: str = "목요일"; break;
		case 6: str = "금요일"; break;
		case 7: str = "토요일"; break;
		}
		
		SimpleDateFormat sdfBirth = new SimpleDateFormat("yyyy'년' MM'월' dd'일'"+str);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'년' MM'월' dd'일'");
		
		System.out.println(sdfBirth.format(gc.getTime()));
		System.out.println("현재 : "+sdf.format(gc1.getTime()));
		System.out.println("나이 : "+(gc1.get(GregorianCalendar.YEAR)-gc.get(GregorianCalendar.YEAR))+" 세");
		//System.out.println("나이 : "+);
	}

}
