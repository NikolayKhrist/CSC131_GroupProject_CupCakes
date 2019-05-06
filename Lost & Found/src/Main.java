import java.io.FileNotFoundException;
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
                    deviceDump();
                    menuChoice = displayMenu(scan);
                    break;
                default:
                    menuChoice = displayMenu(scan);
                    break;
            }
        }
    }

    public static int displayMenu(Scanner scan) {
        clearScreen();
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
            System.out.println("Invalid Input");
            displayMenu(scan);
        }

        return choice;
    }

    public static Device registerDevice(Scanner scan) {
        clearScreen();
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

    public static void showLocation(Scanner scan) 
        throws FileNotFoundException {
        Server.getInstance();
        clearScreen();

        try {
            System.out.print("Enter your device id: ");
            int ID = Integer.parseInt(scan.nextLine());
            String location = Finder.findDevice(ID);
            System.out.println(location);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ivalid ID...");
        } catch (Exception e) {
            System.out.println("Invalid input...");
        }
        
        System.out.print("\n\nPress the ENTER key to continue... ");
        scan.nextLine();
    }

    public static void deviceDump() {
        Server.getInstance();
        clearScreen();
        for(String device : Server.readDatabase()) {
            System.out.println(device);
        }
        delay(5);
    }

    public static void welcomeScreen() {
        clearScreen();
        System.out.println("Welcome to the CSC 131 CupCakes Device Finder!");
        delay(1);
    }

    /**
     * clears console screen for UI transistion
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
