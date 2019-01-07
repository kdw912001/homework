package test.entity;

public class Employee {
	private int eld;
	private String eName;
	private String dept;
	private int salay;
	private double bouusPoint;
	
	public Employee() {}

	public Employee(int eld, String eName, String dept, int salay, double bouusPoint) {
		super();
		this.eld = eld;
		this.eName = eName;
		this.dept = dept;
		this.salay = salay;
		this.bouusPoint = bouusPoint;
	}

	public int getEld() {
		return eld;
	}

	public void setEld(int eld) {
		this.eld = eld;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSalay() {
		return salay;
	}

	public void setSalay(int salay) {
		this.salay = salay;
	}

	public double getBouusPoint() {
		return bouusPoint;
	}

	public void setBouusPoint(double bouusPoint) {
		this.bouusPoint = bouusPoint;
	}
	
	public String toString() {
		return this.eld+" "+this.eName+" "+this.dept+" "+this.salay+" "+this.bouusPoint;
	}
	
}
