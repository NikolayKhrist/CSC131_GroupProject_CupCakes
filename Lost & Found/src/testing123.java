// using as test driver class to drive other classes

public class testing123 {
	public static void main(String[] args) {
		testFinderClass();
	}


	/**
	 * Temp method for testing Finder class
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
}
