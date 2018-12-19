package ncs.test9;

public abstract class Plane {
	private String planeName;
	private int fuelSize;
	
	public Plane() {}

	public Plane(String planeName, int fuelSize) {
		this.planeName = planeName;
		this.fuelSize = fuelSize;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public int getFuelSize() {
		return fuelSize;
	}

	public void setFuelSize(int fuelSize) {
		this.fuelSize = fuelSize;
	}
	
	public void refuel(int refuel) {
		this.fuelSize=this.fuelSize+refuel;
	}
	
	public abstract void flight(int distance);
	
}
