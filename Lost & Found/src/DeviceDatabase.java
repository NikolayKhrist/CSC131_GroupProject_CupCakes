import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
class DeviceDatabase {
    private static DeviceDatabase instance;
    private static File file = new File("./deviceDatabase.dat");
    private static ArrayList<String> loadDatabaseIntoMemory;
    // private static Scanner input;

    private DeviceDatabase() {
        // private constructor
    }

    public synchronized static DeviceDatabase getInstance() {
        Scanner input;
        if(instance == null) {
            loadDatabaseIntoMemory = new ArrayList<String>();

            try {
                input = new Scanner(file);
                while (input.hasNextLine()) {
                    loadDatabaseIntoMemory.add(input.nextLine());
                }
            } catch (Exception e) {
                
            }

            instance = new DeviceDatabase();
            System.out.println("DeviceDatabase created....");
        }
        else {
            loadDatabaseIntoMemory.clear();
            try {
                input = new Scanner(file);
                while (input.hasNextLine()) {
                    loadDatabaseIntoMemory.add(input.nextLine());
                }
            } catch (Exception e) {
                
            }

            System.out.println("Return existing DeviceDatabase....");
        }
        return instance;
    }

    public static ArrayList<String> readDatabase() {
        return loadDatabaseIntoMemory;
    }
}