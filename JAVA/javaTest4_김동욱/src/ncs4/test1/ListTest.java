package ncs4.test1;
import java.util.*;
public class ListTest {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<10;i++) {
			list.add((int)(Math.random()*100)+1);
		}
		
		System.out.print("정렬 전 : " );
		display(list);
	
		list.sort(new Decending());
		System.out.print("정렬 후 : ");
		display(list);
		
	}
	
	public static void display(List<Integer> list) {
		
		for(int i=0; i<list.size();i++) {
			System.out.print(+ list.get(i)+"   ");
		}
		System.out.println();
	}

}
