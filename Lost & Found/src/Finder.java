import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

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
			final Path pathToServer = Paths.get("./server.dat");
			Files.write(pathToServer, Arrays.asList(this.getCoordinates()),
			StandardCharsets.UTF_8, 
			Files.exists(pathToServer) ? StandardOpenOption.APPEND 
			: StandardOpenOption.CREATE);
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
