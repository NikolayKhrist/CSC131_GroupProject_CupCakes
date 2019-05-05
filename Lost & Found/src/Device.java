/*
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
*/

public class Device {
	private static int idCounter = 0;
    private Finder tag;
	private int ID;
	private String friendlyName;

    public Device() {
		this.ID = ++idCounter;
        this.tag = new Finder(ID);
	}

	public Device(String friendlyName) {
		this();
		this.friendlyName = friendlyName;
	}

	public void setFriendlyString(String friendlName) {
		this.friendlyName = friendlName;
	}

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
	

    // private void recordDeviceID() {
	// 	try {
	// 		// create Path object to file acting as server
	// 		final Path pathToServer = Paths.get("./server.dat");
	// 		// Create Files object for writing to file (aka server) <singleton?>
	// 		Files.write(pathToServer, Arrays.asList(this.getCoordinates() + " "
	// 		+ timeStamp()), StandardCharsets.UTF_8, 
	// 		// If file exists append coordinates and timestamp
	// 		Files.exists(pathToServer) ? StandardOpenOption.APPEND 
	// 		// Else create file to be written to and write corridinates 
	// 		// and timestamp
	// 		: StandardOpenOption.CREATE);
	// 	} catch (final IOException ioe) {
	// 		ioe.printStackTrace();
	// 	}
    // }
}
