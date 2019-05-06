import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public class Device {

	private static int idCounter = 0;
    private Finder tag;
	private int ID;
	private String friendlyName;

    public Device(String friendlyName) {
		// Create Singleton Server instance
		Server.getInstance();

		try {
			// If device database is not empty get the ID of the last
			// device added so that we can increment by 1 for the next ID. 
			// If the database is empty we start at ID=1
			idCounter = Integer.parseInt(
				Server.readDatabase()
				.get(Server.readDatabase().size()-1).substring(0,2)
				.replaceAll("\\s+", "")
			);
		} catch (ArrayIndexOutOfBoundsException e){
			// pass
		}
		// Create ID that is last recored ID + 1
		this.ID = ++idCounter;
        this.tag = new Finder(ID);
		this.friendlyName = friendlyName;
	}

	/**
	 *  
	 * @return this.ID
	 */
	public int getID() {
		return this.ID;
	}

	/**
	 * Print info about Device via System.out
	 */
	public String toString() {
		String toPrint = "Device: ";
		if (friendlyName != null) {
			toPrint += this.friendlyName + ", ";
		}
		else {
			toPrint += "Un-named, ";
		}
		toPrint += "ID: " + this.ID;
		return toPrint;
	}
	
	/**
	 * Write device ID and Friendly Name to device datbase.
	 */
	public void registerDevice() {
		try {
			final Path deviceDB = Paths.get("./deviceDatabase.dat");
			Files.write(deviceDB, Arrays.asList(this.ID + " : " + this.friendlyName), 
				StandardCharsets.UTF_8, 
				Files.exists(deviceDB) ? StandardOpenOption.APPEND 
				: StandardOpenOption.CREATE);
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
		this.tag.sendLocationToServer();

	}
}
