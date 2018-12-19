package ncs.test10;

public class Secretary extends Employee implements Bonus{
	
	public Secretary() {}
	
	public Secretary(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}
	
	public double tax() {
		return this.getSalary()*0.1;
	}
	
	public void incentive(int pay) {
		super.setSalary((int)(super.getSalary()+(pay*0.8)));
	}
	
}
