package ncs.test9;

public class Cargoplane extends Plane{
	public Cargoplane() {}
	public Cargoplane(String planeName, int fuelSize) {
		super(planeName,fuelSize);
	}
	
	public void flight(int distance) {
		while(true) {
			if(distance<10) break;
			super.setFuelSize(super.getFuelSize()-50);
			distance=distance-10;
		}
	}
}
