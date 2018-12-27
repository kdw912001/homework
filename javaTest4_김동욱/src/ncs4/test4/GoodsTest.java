package ncs4.test4;

import java.io.*;

public class GoodsTest {
	
	public static void main(String[]args) {
		
	
		BufferedReader br = null;
		Goods goods = new Goods();

		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("상품명 : ");
			goods.setName(br.readLine());
			System.out.print("가격 : ");
			goods.setPrice(Integer.parseInt(br.readLine()));
			System.out.print("수량 : ");
			goods.setQuantity(Integer.parseInt(br.readLine()));
			
			System.out.println(goods);
			System.out.println("총 구매 가격 : "+(goods.getPrice()*goods.getQuantity())+"원");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
