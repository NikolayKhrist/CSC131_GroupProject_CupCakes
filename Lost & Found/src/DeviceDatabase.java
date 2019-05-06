import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
class DeviceDatabase {
    private static DeviceDatabase instance;
    private static File file = new File("./deviceDatabase.dat");
    private static ArrayList<String> loadDatabaseIntoMemory;
    private static Scanner input;

    private DeviceDatabase() {
        // private constructor
    }

    public synchronized static DeviceDatabase getInstance() {
        if(instance == null) {
            loadDatabaseIntoMemory = new ArrayList<String>();
            updateDatabase();
            instance = new DeviceDatabase();
            System.out.println("DeviceDatabase created....");
        }
        else {
            loadDatabaseIntoMemory.clear();
            updateDatabase();
            System.out.println("Return existing DeviceDatabase....");
        }
        input.close();
        return instance;
    }

    public static void updateDatabase() {
        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                loadDatabaseIntoMemory.add(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readDatabase() {
        updateDatabase();
        return loadDatabaseIntoMemory;
    }
}