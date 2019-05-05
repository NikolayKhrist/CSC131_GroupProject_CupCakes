import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
class DeviceDatabase {
    private static DeviceDatabase instance;
    private static File file = new File("./deviceDatabase.dat");
    private static ArrayList<String> loadDatabaseIntoMemory;

    private DeviceDatabase() {
        // private constructor
    }

    public synchronized static DeviceDatabase getInstance() {
        if(instance == null) {
            loadDatabaseIntoMemory = new ArrayList<String>();

            try {
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    loadDatabaseIntoMemory.add(input.nextLine());
                }
            } catch (Exception e) {
                
            }

            instance = new DeviceDatabase();
            System.out.println("DeviceDatabase created....");
        }
        else {
            System.out.println("Return existing DeviceDatabase....");
        }
        return instance;
    }

    public static ArrayList<String> readDatabase() {
        return loadDatabaseIntoMemory;
    }
}