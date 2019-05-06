import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public class Device {

	// private static Server db = Server.getInstance();
	private static int idCounter = 0;
    private Finder tag;
	private int ID;
	private String friendlyName;

    public Device(String friendlyName) {
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
			e.printStackTrace();
		}
		// Create ID that is last recored ID + 1
		this.ID = ++idCounter;
        this.tag = new Finder(ID);
		this.friendlyName = friendlyName;
	}

	// public Device(String friendlyName) {
	// 	this();
	// }

	public int getID() {
		return this.ID;
	}

	// public String lastRecordedLocation(int ID) throws FileNotFoundException {
	// 	return this.tag.findDevice(ID);
	// }


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
