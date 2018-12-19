package ncs.test4;

public class ProductTest {

	public static void main(String[] args) {
		Product p =new Product("갤럭시 s7",563500,3);
		System.out.println(p.information());
		System.out.println("총 구매 가격 : "+(p.getPrice()*p.getQuantity())+" 원");
	}

}
