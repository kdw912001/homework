package test.entity;

public class Employee implements java.io.Serializable{
	private static final long serialVersionUID = -7096963999182922347L;
	
	private int eld;
	private String eName;
	private String dept;
	private int salary;
	double bonusPoint;
	
	public Employee() {}

	public Employee(int eld, String eName, String dept, int salary, double bonusPoint) {
		super();
		this.eld = eld;
		this.eName = eName;
		this.dept = dept;
		this.salary = salary;
		this.bonusPoint = bonusPoint;
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(double bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return this.eld+" "+this.eName+" "+this.dept+" "+this.salary+" "+this.bonusPoint;
	}
	
}
