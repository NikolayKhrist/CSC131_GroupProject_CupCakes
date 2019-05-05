import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int menuChoice;
        welcomeScreen();
        menuChoice = displayMenu(scan);

        while (menuChoice != 3) {
            if (menuChoice == 1) {
                Device newDevice = registerDevice(scan);
                delay(1);
                menuChoice = displayMenu(scan);
            } 
            else if (menuChoice == 2) {

            }
        }
    }

    public static int displayMenu(Scanner scan) {
        clearScreen();
        System.out.println("\n\nPlease Choose From the Following Options: \n\n");
        System.out.println("1. Register a new device.\n");
        System.out.println("2. Find current location of registered device\n");
        System.out.println("3. Exit program\n");
        System.out.print("Please Enter 1, 2 or 3: ");
        int choice = Integer.parseInt(scan.nextLine());
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
        delay(8);
        return newRegisteredDevice;
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
