import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


/**
 * 
 * @author Phil Fernandez
 *
 */

public class Finder {
	private double latitude, longitude;
	private int ID;

	/**
	 * Constructor randomly generates lotitude and longitude coordinates
	 * @param ID
	 */
	public Finder(int ID) {
		// generate latitude and longitude coordinates
		double lowerBoundLatitude = -90;
		double upperBoundLatitude = 90;
		double lowerBoundLongitude = -180;
		double upperBoundLongitude = 180;

		this.latitude = range(lowerBoundLatitude, upperBoundLatitude);
		this.longitude = range(lowerBoundLongitude, upperBoundLongitude);
		this.ID = ID;
	}

	/**
	 * Constructor takes specific latitude and longitude
	 * coordinates.
	 * @param ID
	 * @param latitude
	 * @param longitude
	 */
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
		// format decimal output of longitude and latitude
		DecimalFormat formatLatitude = new DecimalFormat("##.######");
		DecimalFormat formatLongitude = new DecimalFormat("###.######");

		return "(" + formatLatitude.format(latitude) + 
			", " + formatLongitude.format(longitude) + ")";
	}

	/**
	 * provide toString method so instances can be printed out to console
	 * @return string literal
	 */
	public String toString() {
		// return string for printing Finder object to console
		return "Device ID: " + ID + "\nCoordinates: " +
			getCoordinates();
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
			Files.write(pathToServer, Arrays.asList(this.ID + " : " + this.getCoordinates() + " "
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

	/**
	 * 
	 * @param ID
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String findDevice(int ID) throws FileNotFoundException {
		ArrayList<String> serverInMemory = new ArrayList<String>();
		File file = new File("./server.dat");
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			serverInMemory.add(input.nextLine());
		}
		// System.out.println(Server.readDatabase());
		// System.out.println(serverInMemory);
		String deviceAndLocation = serverInMemory.get(ID-1) +
			Server.readDatabase().get(ID-1);
		input.close();
		return deviceAndLocation;
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
	 * This is a helper method for generating random coordinates
	 * @param lower
	 * @param upper
	 * @return random double between lower and upper
	 */
	private double range(double lower, double upper) {
		return Math.random() * (upper - lower) + lower;
	}

}
