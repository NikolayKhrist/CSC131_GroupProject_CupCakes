import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int menuChoice = displayMenu(scan);

        if (menuChoice == 1) {
            Device newDevice = registerDevice(scan);
            System.out.println(newDevice);
        }
    }

    public static int displayMenu(Scanner scan) {
        System.out.println("Welcome to the CSC 131 CupCakes Device Finder!\n\n");
        System.out.println("Please Choose From the Following Options: \n\n");
        System.out.println("1. Register a new device.\n");
        System.out.println("2. Find current location of registered device\n");
        System.out.print("Please Enter 1 or 2: ");
        int choice = Integer.parseInt(scan.nextLine());
        return choice;
    }

    public static Device registerDevice(Scanner scan) {
        Device newRegisteredDevice;
        String deviceName;
        System.out.print("Enter a name for your device. eg Roomba, iPhone, etc: ");

        deviceName = scan.nextLine();
        newRegisteredDevice = new Device(deviceName);
        return newRegisteredDevice;
    }
}