package product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import static common.JDBCTemplate.*;

import product.exception.ProductException;
import product.model.dao.ProductDao;
import product.model.dto.Product;

public class ProductService {
	private ProductDao pdao;
	public ProductService() throws ProductException {
		pdao = new ProductDao();
	}
	
	public ArrayList<Product> selectAllList() throws ProductException {
		Connection conn = getConnection();
		ArrayList<Product> productList = pdao.selectAllList(conn);
		close(conn);
		return productList;
	}

	public int insertProduct(Product product) throws ProductException {
		Connection conn = getConnection();
		int result = pdao.insertProduct(conn, product);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}

	public int updateProduct(Product product) throws ProductException {
		Connection conn = getConnection();
		int result = pdao.updateProduct(conn, product);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}

	public int deleteProduct(String productId) throws ProductException {
		Connection conn = getConnection();
		int result = pdao.deleteProduct(conn, productId);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}

	public ArrayList<Product> selectProductName(String productName) throws ProductException {
		Connection conn = getConnection();
		ArrayList<Product> productList = pdao.selectProductName(conn, productName);
		close(conn);
		return productList;
	}

	public Product selectProductId(String productId) throws ProductException {
		Connection conn= getConnection();
		Product product = pdao.selectProductId(conn, productId);
		close(conn);
		return product;
	}

	public HashMap<String, Product> selectAllMap() throws ProductException {
		Connection conn = getConnection();
		HashMap<String, Product> productMap = pdao.seletMap(conn);
		close(conn);
		
		return productMap;
	}

	public HashMap<String, Product> selectNameMap(String productName) throws ProductException {
		Connection conn = getConnection();
		HashMap<String, Product> productMap = pdao.selectNameMap(conn, productName);
		return productMap;
	}

}
