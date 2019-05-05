import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public class Device {

	// private static DeviceDatabase db = DeviceDatabase.getInstance();
	private static int idCounter = 0;
    private Finder tag;
	private int ID;
	private String friendlyName;

    public Device(String friendlyName) {
		DeviceDatabase.getInstance();

		try {
			// If device database is not empty get the ID of the last
			// device added so that we can increment by 1 for the next ID. 
			// If the database is empty we start at ID=1
			idCounter = Character.getNumericValue(
				DeviceDatabase.readDatabase()
				.get(DeviceDatabase.readDatabase().size()-1).charAt(0)
			);
			System.out.println("ID COUNTER: " + idCounter);
		} catch (ArrayIndexOutOfBoundsException e){
			// e.printStackTrace();
			System.out.println("ArrayList was empty");
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
		System.out.println(DeviceDatabase.readDatabase());
	}

	

}
