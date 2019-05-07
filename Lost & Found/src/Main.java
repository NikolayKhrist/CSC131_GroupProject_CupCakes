import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int menuChoice;
        welcomeScreen();
        menuChoice = displayMenu(scan);

        while (menuChoice != 4) {
            switch (menuChoice) {
                case 1:
                    registerDevice(scan);
                    menuChoice = displayMenu(scan);
                    break;
                case 2:
                    showLocation(scan);
                    menuChoice = displayMenu(scan);
                    break;
                case 3:
                    deviceDump(scan);
                    menuChoice = displayMenu(scan);
                    break;
                default:
                    menuChoice = displayMenu(scan);
                    break;
            }
        }
    }

    /**
     * This method prompts the user for what action they want to take
     * inside the program.
     * @param scan
     * @return choice
     */
    public static int displayMenu(Scanner scan) {
        // clearScreen();
        int choice = 0;
        System.out.println("\n\nPlease Choose From the Following Options: \n\n");
        System.out.println("1. Register a new device.\n");
        System.out.println("2. Find current location of registered device\n");
        System.out.println("3. List registered devices.\n");
        System.out.println("4. Exit program\n");
        System.out.print("Please Enter 1, 2, 3, or 4: ");

        try {
            choice = Integer.parseInt(scan.nextLine());
            
        } catch (Exception e) {
            displayMenu(scan);
        }

        return choice;
    }

    /**
     * This method registers a new device and stores it onto the Server
     * @param scan
     * @return newRegisteredDevice
     */
    public static Device registerDevice(Scanner scan) {
        // clearScreen();
        Device newRegisteredDevice;
        String deviceName;
        System.out.print("\nEnter a name for your device. eg Roomba, iPhone, etc: ");

        deviceName = scan.nextLine();
        newRegisteredDevice = new Device(deviceName);
        System.out.println(deviceName + " successfully registered with ID #" + newRegisteredDevice.getID() + "\n");
        newRegisteredDevice.registerDevice();
        delay(4);
        return newRegisteredDevice;
    }

    /**
     * This method hooks into the device database from the server, and the
     * location / timestamp server and matches the ID of the device to that ID
     * in the location server.
     * @param scan
     * @throws FileNotFoundException
     */
    public static void showLocation(Scanner scan) 
        throws FileNotFoundException {
        Server.getInstance();
        // clearScreen();

        try {
            System.out.print("Enter your device id: ");
            int ID = Integer.parseInt(scan.nextLine());
            String location = Finder.findDevice(ID);
            System.out.println("\n\nID     (LATITUDE, LONGITUDE)  " + 
                "    DATE      TIME         DEVICE NAME ");
            System.out.println("-----------------------" + 
                "---------------------------------------------");
            System.out.println(location);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ivalid ID...");
        } catch (Exception e) {
            System.out.println("Invalid input...");
        }
        
        promptForward(scan);
    }

    /**
     * This method shows all the devices currently in users device database
     * @param scan
     */
    public static void deviceDump(Scanner scan) {
        Server.getInstance();
        // clearScreen();
        System.out.println("\nID  DEVICE NAME");
        System.out.println("------------------");
        // Iterator design pattern 
        for(String device : Server.readDatabase()) {
            System.out.println(device);
        }
        promptForward(scan);
    }

    public static void welcomeScreen() {
        // clearScreen();
        System.out.println("Welcome to the CSC 131 CupCakes Device Finder!");
        delay(1);
    }

    /**
     * clears console screen for UI transistion
     */
    public static void clearScreen() {
        String operatingSystem = System.getProperty("os.name");

        // If OS is Windows use this method to clear console
        if (operatingSystem.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // If OS is *nix use this method to clear console
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    /**
     * method 
     * @param scan
     */
    public static void promptForward(Scanner scan) {
        System.out.print("\n\nPress the ENTER key to continue... ");
        scan.nextLine();
    }

    /**
     * adds delay to UI ouput for <seconds> seconds
     * @param seconds
     */
    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
