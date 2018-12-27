package ncs4.test3;

import java.io.*;
import java.util.*;
import java.text.*;

public class Inventory implements Serializable {
	private static final long serialVersionUID = 8601350809612743241L;

	private String productName;
	private Date putDate;
	private Date getDate;
	private int putAmount;
	private int getAmount;
	private int inventoryAmount;

	public Inventory() {
	}

	public Inventory(String productName, Date putDate, int putAmount) {
		this.productName = productName;
		this.putDate = putDate;
		this.putAmount = putAmount;
		this.inventoryAmount=putAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getPutDate() {
		return putDate;
	}

	public Date getGetDate() {
		return getDate;
	}

	public void setGetDate(Date getDate) {// 출고 날짜를 지정한다.

		this.getDate = getDate;

	}

	public void setPutDate(Date putDate) {
		this.putDate = putDate;
	}

	public int getPutAmount() {
		return putAmount;
	}

	public void setPutAmount(int putAmount) {
		this.putAmount = putAmount;
	}

	public int getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(int getAmount) throws AmountNotEnough {
		/*
		 * 출고 수량을 지정한 다음, 재고수량을 지정한다. (입고수량 – 출고수량) 단, 출고수량이 입고수량보다 많을 경 우에는
		 * AmountNotEnough 예외를 발생시킨다.
		 * 
		 */
		this.getAmount = getAmount;
		if (this.getAmount > this.putAmount) {
			throw new AmountNotEnough("현재 재고가 부족합니다. 재고수량 확인하시기 바랍니다.");
		} else {
			this.inventoryAmount = (this.putAmount - this.getAmount);

		}

	}

	public int getInventoryAmount() {
		return inventoryAmount;
	}

	public void setInventoryAmount(int inventoryAmount) {
		this.inventoryAmount = inventoryAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'년'MM'월'dd'일'");
		String date1 = sdf.format(this.putDate);

		if (this.getDate == null)
			return date1 + " 입고, " + this.putAmount + "개, " + this.getDate + " 출고" + this.getAmount + "개, " + "재고"
					+ /* (this.putAmount-this.getAmount) */this.inventoryAmount + "개";
		else {
			String date2 = sdf.format(this.getDate);
			return this.getProductName()+"\t"+date1 + " 입고, " + this.putAmount + "개, " + date2 + " 출고" + this.getAmount + "개, " + "재고"
					+ /* (this.putAmount-this.getAmount) */this.inventoryAmount + "개";
		}
	}

}
