package ncs.test9;

public class Airplane extends Plane{
	public Airplane() {}
	public Airplane(String planeName, int fuelSize) {
		super(planeName,fuelSize);
	}
	
	public void flight(int distance) {
		
		while(true) {
			if(distance<10) break;
			super.setFuelSize(super.getFuelSize()-30);
			distance=distance-10;
		}
	}
}
