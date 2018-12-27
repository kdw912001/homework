package ncs4.test5;
import java.util.*;
import java.io.*;
public class BookListTest {

	public static void main(String[] args) {
		BookListTest test5 = new BookListTest();
		ArrayList<Book> list = new ArrayList<Book>();
		
		test5.storeList(list);
		test5.saveFile(list);
		
		List<Book> booksList = test5.lodeFile();
		//test5.lodeFile();
		test5.printList(booksList);
	}
	
	public void storeList(List<Book> list) {
		list.add(new Book("자바의 정석","남궁성",30000,"도우출판",0.15));
		list.add(new Book("열혈강의 자바","구정은",29000,"프리렉",0.2));
		list.add(new Book("객체지향 JAVA8","금영욱",30000,"북스홈",0.1));
		
	}
	public void saveFile(List<Book> list) {
		
		try (ObjectOutputStream bout = new ObjectOutputStream(new FileOutputStream("books.dat"))){
			for(int i=0; i<list.size();i++) {
				bout.writeObject(new Book(list.get(i).getTitle(),list.get(i).getAuthor(),list.get(i).getPrice(),list.get(i).getPublisher(),list.get(i).getDiscountRate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Book> lodeFile() {
		int count=0;
		Book[] b = new Book[10];
		List<Book> list = new ArrayList<Book>();
		try(ObjectInputStream bin = new ObjectInputStream(new FileInputStream("books.dat"))){
			int data = 0;
			
			
			while(true) {
				b[count]=(Book) bin.readObject();
				count++;
			}
		}catch(EOFException e) {
			for(int i=0; i<count;i++) {
				list.add(b[i]);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	public void printList(List<Book> list) {
		for(Book b : list) {
			System.out.println(b);
			System.out.println("할인된 가격 : "+(int)(b.getPrice()-(b.getPrice()*b.getDiscountRate()))+"원");
		}
	}

}
