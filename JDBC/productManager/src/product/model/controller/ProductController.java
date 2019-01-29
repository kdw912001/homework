package product.model.controller;

import java.util.ArrayList;
import java.util.HashMap;

import product.exception.ProductException;
import product.model.dto.Product;
import product.service.ProductService;
import product.view.ProductView;

public class ProductController {
	private ProductService pservice;
	public ProductController() {
		try {
			pservice = new ProductService();
		} catch (ProductException e) {
			printError(e.getMessage());		
			new ProductView().displayMenu();
		}
	}
	

	public void printError(String message) {
		System.out.println("\n프로그램 오류발생!");
		System.out.println("시스템 관리자에게 문의하십시오.");
		System.out.println("오류 메시지 : " + message);
	}
	
	public ArrayList<Product> selectAllList() {
		ArrayList<Product> productList = new ArrayList<>();
		try {
			productList = pservice.selectAllList();
			if (productList.size() > 0)
				System.out.println("\n전체조회 성공!");
		} catch (Exception e) {
			printError(e.getMessage());
		}
		return productList;
	}

	public Product insertProduct(Product product) {
		try {
			if(pservice.insertProduct(product) > 0);
				System.out.println("\n새 제품 등록 성공!");
		} catch (Exception e) {
			printError(e.getMessage());
		}
		return product;
	}

	public void updateProduct(Product product) {
		try {
			if(pservice.updateProduct(product) > 0)
				System.out.println("\n제품 가격 변경 성공!");
		} catch (Exception e) {
			printError(e.getMessage());	
		}

	}

	public void deleteProduct(String productId) {
		try {
			if(pservice.deleteProduct(productId) > 0)
				System.out.println("\n제품 정보 삭제 성공!");
		} catch (Exception e) {
			printError(e.getMessage());
		}

	}

	public ArrayList<Product> selectProductName(String productName) {
		ArrayList<Product> productList = new ArrayList<>();
		try {
			productList = pservice.selectProductName(productName);
			if (productList.size() > 0)
				System.out.println("\n전체조회 성공!");
		} catch (Exception e) {
			printError(e.getMessage());
		}
		return productList;		
	}


	public Product selectProductId(String productId) {
		Product product = null;
		try {
			product = pservice.selectProductId(productId);
		} catch (Exception e) {
			printError(e.getMessage());
		}
		return product;
	}


	public HashMap<String, Product> selectAllMap() {//employee방식
		HashMap<String, Product> productMap = new HashMap<>();
		try {
			productMap = pservice.selectAllMap();
		} catch (Exception e) {
			printError(e.getMessage());
		}
		return productMap;
	}


	public void selectNameMap(String productName) {//book방식
		try {
			new ProductView().displayMap(pservice.selectNameMap(productName));
		} catch (Exception e) {
			printError(e.getMessage());
		}
	}

}
