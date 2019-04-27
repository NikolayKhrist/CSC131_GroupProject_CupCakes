import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 * @author Phil Fernandez
 *
 */

public class Finder {
	private double latitude, longitude;
	private int ID;

	public Finder(int ID, double latitude, double longitude) {
		// If latitude or longitude are outside of range
		// round up/down to nearest acceptable value.

		// -90 <= latitude <= 90
		if (latitude > 90)
			latitude = 90;
		if (latitude < -90)
			latitude = -90;

		// -180 <= longitude <= 180
		if (longitude > 180)
			longitude = 180;
		if (longitude < -180)
			longitude = -180;

		this.ID = ID;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCoordinates() {
		return "(" + latitude + ", " + longitude + ")";
	}

	public String toString() {
		return "Latitude: " + latitude + "\nLongitude: " + longitude +
			"\nDevice Identification #: " + ID;
	}

	public void sendLocationToServer() {
		PrintWriter server;
		try {
			server = new PrintWriter("server.dat");
			server.println(this.getCoordinates());
			server.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
