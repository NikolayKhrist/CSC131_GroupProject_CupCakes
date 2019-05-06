import java.io.FileNotFoundException;

public class testing123 {
	public static void main(String[] args) throws FileNotFoundException {
		// testFinderClass();
		// testFinderGeneratedCoords();
		// testDeviceClass();
		clearConsole();
	}

	/**
	 * Temp method for testing Finder class
	 * 
	 * Feel free to delete
	 */
	public static void testFinderClass() {
		final int ID_FINDER1 = 1;
		final int ID_FINDER2 = 2;
		Finder finder1 = new Finder(ID_FINDER1, 85.2233, -120.3342);
		Finder finder2 = new Finder(ID_FINDER2, 12.5963, 174.6312);
		System.out.println(finder1 + "\n\n" + finder2);
		finder1.sendLocationToServer();
		finder2.sendLocationToServer();
	}

	/**
	 * Temp method for testing Finder constructor which 
	 * generates coordinates
	 * 
	 * Feel free to delete
	 */
	public static void testFinderGeneratedCoords() {
		final int ID_FINDER1 = 1;
		Finder finder1 = new Finder(ID_FINDER1);
		System.out.println(finder1);
		finder1.sendLocationToServer();
	}

	public static void testDeviceClass() throws FileNotFoundException {
		Device roomba = new Device("Roomba");
		roomba.registerDevice();




		// System.out.println(pixel3XL);
	}

	public static void clearConsole() {
		System.out.println(System.getProperty("os.name"));
	}
}
