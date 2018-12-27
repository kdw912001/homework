package ncs4.test3;
import java.util.*;
public class MapTest {

	public static void main(String[] args) {
		Map<String,Inventory> map = new HashMap<String, Inventory>();
		GregorianCalendar g = new GregorianCalendar();
		
		map.put("삼성 갤럭시S7", new Inventory("삼성 갤럭시 S7",new GregorianCalendar(2016,2,15).getTime(),30));
		map.put("LG G5", new Inventory("LG G5",new GregorianCalendar(2016,1,25).getTime(),20));
		map.put("애플 아이패드 Pro", new Inventory("애플 아이패드 Pro",new GregorianCalendar(2016,0,23).getTime(),15));
		
		
		Set<Map.Entry<String, Inventory>> entries = map.entrySet();
		Iterator<Map.Entry<String, Inventory>> entryIter = entries.iterator();
		int index=0;
		
		Inventory[] i = new Inventory[3];
		while(entryIter.hasNext()) {
			Map.Entry<String, Inventory> entry = entryIter.next();
			String key = entry.getKey();
			Inventory value = entry.getValue();
			
			System.out.println(key+"\t"+value);
			
			//맵에 기록된 정보를 Inventory[]로 변환
			
			String productName = value.getProductName();
			Date putDate = value.getPutDate();
			int putAmount = value.getPutAmount();
			i[index] = new Inventory(productName,putDate,putAmount);
			i[index].setGetDate(new Date());
			
			try {
				i[index].setGetAmount(10);
			} catch (AmountNotEnough e) {
				System.out.println(e.getMessage());
			}
			
			index++;
			
		}
		System.out.println("출고수량 10 적용시=======================");
		for(int k=0;k<i.length;k++) {
			System.out.println(i[k]);
		}
		System.out.println("출고수량 부족시==================");
		i[0].setPutAmount(0);
		try {
			i[0].setGetAmount(20);
		} catch (AmountNotEnough e) {
			System.out.println(e.getMessage());
		}
		
	}
}
