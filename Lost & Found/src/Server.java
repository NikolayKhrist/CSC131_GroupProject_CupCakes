import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
class Server {
    private static Server instance;
    private static File file = new File("./deviceDatabase.dat");
    private static ArrayList<String> loadDatabaseIntoMemory;
    private static Scanner input;

    /**
     * Singleton Server Class
     */

    private Server() {
        // private constructor
    }

    public synchronized static Server getInstance() {
        if(instance == null) {
            loadDatabaseIntoMemory = new ArrayList<String>();
            updateDatabase();
            instance = new Server();
            // System.out.println("Server created....");
        }
        else {
            loadDatabaseIntoMemory.clear();
            updateDatabase();
            // System.out.println("Return existing Server....");
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
        loadDatabaseIntoMemory.clear();
        updateDatabase();
        return loadDatabaseIntoMemory;
    }
}