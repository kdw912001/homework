package product.model.dao;
import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import product.exception.ProductException;
import product.model.dto.Product;


public class ProductDao {
	private Properties prop = new Properties();
	public ProductDao() throws ProductException {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {			
			throw new ProductException(e.getMessage());
		}
	}
	
	
	
	public ArrayList<Product> selectAllList(Connection conn) throws ProductException {
		ArrayList<Product> productList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAll");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setPname(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				productList.add(p);
			}
			if(productList.size() == 0)
				System.out.println("조회할 제품 정보가 존재하지 않습니다.");
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(stmt);
		}
		
		return productList;
	}

	public int insertProduct(Connection conn, Product product) throws ProductException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insert");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, product.getProductId());
			pstmt.setString(2, product.getPname());
			pstmt.setInt(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			result = pstmt.executeUpdate();
			if (result <= 0)
				System.out.println("정보 입력 실패!");
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProduct(Connection conn, Product product) throws ProductException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("update");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, product.getPrice());
			pstmt.setString(2, product.getProductId());
			result = pstmt.executeUpdate();
			if (result <= 0)
				System.out.println("정보 변경 실패!");
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteProduct(Connection conn, String productId) throws ProductException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("delete");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
			if (result <= 0)
				System.out.println("정보 삭제 실패!");
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
		return result;
	}

	public ArrayList<Product> selectProductName(Connection conn, String productName) throws ProductException {
		ArrayList<Product> productList = new ArrayList<>();
		Product p = null;
		PreparedStatement rstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectName");
		try {
			rstmt = conn.prepareStatement(query);
			rstmt.setString(1, "%"+productName+"%");
			rset = rstmt.executeQuery();
			while(rset.next()) {
				p = new Product();
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setPname(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				productList.add(p);
			}
			if(productList.size() == 0)
				System.out.println(productName + " 의 이름을 가진 제품이 존재하지 않습니다.");
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(rstmt);
		}
		return productList;
	}



	public Product selectProductId(Connection conn, String productId) throws ProductException {
		Product p = null;
		PreparedStatement rstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectId");
		try {
			rstmt = conn.prepareStatement(query);
			rstmt.setString(1, productId);
			rset = rstmt.executeQuery();
			if(rset.next()) {
				p = new Product();
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setPname(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));	
			}
			if(p == null)
				System.out.println(productId + "아이디 정보 조회 실패");
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		} finally {
			close(rset);
			close(rstmt);
		}
		return p;
	}

}
