package product.model.controller;

import java.util.ArrayList;

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
			productError(e.getMessage());			
		}
	}
	

	public void productError(String message) {
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
			productError(e.getMessage());
			new ProductView().displayMenu();
		}
		return productList;
	}

	public Product insertProduct(Product product) {
		try {
			if(pservice.insertProduct(product) > 0);
				System.out.println("\n새 제품 등록 성공!");
		} catch (Exception e) {
			productError(e.getMessage());
			new ProductView().displayMenu();
		}
		return product;
	}

	public void updateProduct(Product product) {
		try {
			if(pservice.updateProduct(product) > 0)
				System.out.println("\n제품 가격 변경 성공!");
		} catch (Exception e) {
			productError(e.getMessage());			
			new ProductView().displayMenu();
		}

	}

	public void deleteProduct(String productId) {
		try {
			if(pservice.deleteProduct(productId) > 0)
				System.out.println("\n제품 정보 삭제 성공!");
		} catch (Exception e) {
			productError(e.getMessage());
			new ProductView().displayMenu();
		}

	}

	public ArrayList<Product> selectProductName(String productName) {
		ArrayList<Product> productList = new ArrayList<>();
		try {
			productList = pservice.selectProductName(productName);
			if (productList.size() > 0)
				System.out.println("\n전체조회 성공!");
		} catch (Exception e) {
			productError(e.getMessage());
			new ProductView().displayMenu();
		}
		return productList;		
	}


	public Product selectProductId(String productId) {
		Product product = null;
		try {
			product = pservice.selectProductId(productId);
		} catch (Exception e) {
			productError(e.getMessage());
			new ProductView().displayMenu();
		}
		return product;
	}

}
