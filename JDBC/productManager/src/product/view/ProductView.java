package product.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import product.model.controller.ProductController;
import product.model.dto.Product;

public class ProductView {
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	public ProductView() {}
	
	public void displayMenu() {
		while(true) {
			System.out.print(
					" \n1. 전체 조회 - ArrayList\r\n" + 
					"2. 추가 : 추가후 id로 조회 확인함\r\n" + 
					"3. 수정 : id으로 조회 후 가격 수정함\r\n" + 
					"4. 삭제 : id로 조회 후 삭제함\r\n" + 
					"5. 검색 : 이름으로 조회함 ArrayList\r\n" + 
					"6. 끝내기\n"
					+ "7. 전체 조회 - Map사용\n"
					+ "8. 이름으로 조회 - Map 사용\n"
					+ "메뉴 선택 : ");
			switch(sc.nextInt()) {
			case 1: displayList(pc.selectAllList()); break;
			case 2: displayOne(pc.selectProductId(pc.insertProduct(inputProduct()).getProductId()));break;
			case 3: pc.updateProduct(updateInput()); break;
			case 4: pc.deleteProduct(deleteProduct(inputProductId())); break;
			case 5: displayList(pc.selectProductName(inputProductName())); break;
			case 6: System.out.print("프로그램을 종료 하시겠습니까?[y/n] : ");
					if(sc.next().toUpperCase().charAt(0) == 'Y')
						return;
					break;
			case 7: displayMap(pc.selectAllMap()); break;
			case 8: pc.selectNameMap(inputProductName()); break;
			default : System.out.println("\n 잘못 입력하셨습니다.");
					  System.out.println("다시 입력해주세요.");
			}
		}
	}
	public void displayMap(HashMap<String,Product> productMap) {
		System.out.println(productMap.size() + " 개의 제품이 있습니다.");
		for(String key : productMap.keySet())
			System.out.println(productMap.get(key));
	}

	public String deleteProduct(String productId) {
		displayOne(pc.selectProductId(productId));
		System.out.print("삭제 하시겠습니까?[y/n] : ");
		if(sc.next().toUpperCase().charAt(0) != 'Y')
			displayMenu();

		return productId;
	}
	public void displayList(ArrayList<Product> productList) {
		if(productList == null)
			displayMenu();
		System.out.println("조회된 제품 갯수 : " + productList.size());
		for(Product p : productList)
			System.out.println(p);
	}
	public void displayOne(Product p ) {
		if(p == null)
			displayMenu();
		System.out.println(p);
	}
	public String inputProductId() {
		System.out.print("검색할 제품명 : ");
		return sc.next();
	}
	public String inputProductName() {
		System.out.print("검색할 이름 : ");
		sc.nextLine();
		return sc.nextLine();
	}
	public Product inputProduct() {
		Product p = new Product();
		System.out.print("제품ID : ");
		p.setProductId(sc.next());
		System.out.print("제품명 : ");
		sc.nextLine();
		p.setPname(sc.nextLine());
		System.out.print("가격 : ");
		p.setPrice(sc.nextInt());
		System.out.print("설명 : ");
		sc.nextLine();
		p.setDescription(sc.nextLine());
		return p;
	}
	public Product updateInput() {
		Product p = new Product();
		System.out.print("제품 ID : ");
		p.setProductId(sc.next());
		displayOne(pc.selectProductId(p.getProductId()));
		System.out.print("변경할 가격 : ");
		p.setPrice(sc.nextInt());
		return p;
	}
	
}
