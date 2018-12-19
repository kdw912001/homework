package ncs.test9;

public class PlaneTest {

	public static void main(String[] args) {
		Airplane air = new Airplane("L747",1000);
		Cargoplane cargo = new Cargoplane("C40",1000);
		
		
		System.out.println("Plane\tfuelSize");
		System.out.println("---------------------");
		System.out.println(air.getPlaneName()+"\t"+air.getFuelSize());
		System.out.println(cargo.getPlaneName()+"\t"+cargo.getFuelSize());
		System.out.println();
		
		System.out.println("100 운항");
		System.out.println("Plane\tfuelSize");
		System.out.println("---------------------");
		air.flight(100);
		cargo.flight(100);
		System.out.println(air.getPlaneName()+"\t"+air.getFuelSize());
		System.out.println(cargo.getPlaneName()+"\t"+cargo.getFuelSize());
		System.out.println();
		
		System.out.println("200 주유");
		System.out.println("Plane\tfuelSize");
		System.out.println("---------------------");
		air.refuel(200);
		cargo.refuel(200);
		System.out.println(air.getPlaneName()+"\t"+air.getFuelSize());
		System.out.println(cargo.getPlaneName()+"\t"+cargo.getFuelSize());
		System.out.println();

	}

}
