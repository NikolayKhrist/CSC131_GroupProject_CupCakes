
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
		return "Latitude: " + latitude + "\nLongitude: " + longitude + "\nDevice Identification #: " + ID;
	}

	public void sendLocationToServer() {
		try {
			Files.write(Paths.get("./server.dat"), 
			this.getCoordinates().getBytes(),
			StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
