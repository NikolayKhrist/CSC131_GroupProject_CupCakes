import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

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

	/**
	 * 
	 * @return string representation of latitude and longitude
	 */
	public String getCoordinates() {
		return "(" + latitude + ", " + longitude + ")";
	}

	/**
	 * private helper method
	 * @return current time and date aka Timestamp
	 */
	private Timestamp timeStamp() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * provide toString method so instances can be printed out to console
	 * @return string literal
	 */
	public String toString() {
		return "Device ID: " + ID + "\nLatitude: " + latitude + 
			"\nLongitude: " + longitude;
	}

	/**
	 * sends latitude and lonitude coordinates to server, along with date
	 * and timestamp
	 * @return void
	 */
	public void sendLocationToServer() {
		try {
			// create Path object to file acting as server
			final Path pathToServer = Paths.get("./server.dat");
			// Create Files object for writing to file (aka server) <singleton?>
			Files.write(pathToServer, Arrays.asList(this.getCoordinates() + " "
			+ timeStamp()), StandardCharsets.UTF_8, 
			// If file exists append coordinates and timestamp
			Files.exists(pathToServer) ? StandardOpenOption.APPEND 
			// Else create file to be written to and write corridinates 
			// and timestamp
			: StandardOpenOption.CREATE);
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
