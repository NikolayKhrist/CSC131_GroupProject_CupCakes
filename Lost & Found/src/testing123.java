// using as test driver class to drive other classes

public class testing123 {
	public static void main(String[] args) {
		Finder deviceLocation = new Finder(1, -91.234444, 179.99877789);
		System.out.println(deviceLocation);
		String devLocation = deviceLocation.getCoordinates();
		System.out.println(devLocation);
		deviceLocation.sendLocationToServer();

		Finder deviceLocation2 = new Finder(2, -12.4445, 134.665544);
		deviceLocation2.sendLocationToServer();
	}
}
