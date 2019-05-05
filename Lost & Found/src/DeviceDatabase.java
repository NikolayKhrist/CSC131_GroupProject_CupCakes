import java.io.File;
import java.util.Scanner;
class DeviceDataBase {
    private static DeviceDataBase instance;
    private static File file = new File("./deviceDatabase.dat");

    private DeviceDataBase() {
        // private constructor
    }

    public synchronized static DeviceDataBase getInstance() {
        if(instance == null) {

            try {
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    System.out.println(input.nextLine());
                }
            } catch (Exception e) {
                
            }

            instance = new DeviceDataBase();
        }
        return instance;
    }
}