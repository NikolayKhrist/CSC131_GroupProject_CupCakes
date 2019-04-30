import java.util.Scanner;
//This class is first modified by Nhan Huynh on 04/28/2019, next meeting will firgure out how to connect to others classes. 
//eclipse is stupid
//this class allows to register with ID and password for the product then send it to the server and report when the product is lost.
public class Owner {
	//this function let user enter the product's information, the function will take productID and password as input variables
	public void Reg(int productID, String password) {
	   Scanner reader = new Scanner(System.in); //scanner definition
	   System.out.println("Please enter product ID:");
	   productID = reader.nextInt();
	   System.out.println("Please enter password: ");
	   password = reader.next();
	   //finish reading inputs
	   reader.close();
	}
	//this function let user report item's lost by taking date and time the item went lost, last known location and ID as input
	public void Lost(String date, String time, String LastLocation, int productID, String password) {
		Scanner reader = new Scanner(System.in); //scanner definition
		//Date should be a String input with format MM-DD-YY
		System.out.println("Please enter the approximate date that the item was lost by the format MM-DD-YY:");
		date = reader.next();
		//Time should be a string input with format MM:SS PM/AM
		System.out.println("Please enter the approximate time for the lost item:");
		time = reader.next();
		//Last know location should be stored as a String
		System.out.println("Please enter the last known location of the lost item");
		LastLocation = reader.next();
		System.out.println("Please enter product ID:");
		productID = reader.nextInt();
		System.out.println("Please enter password: ");
		password = reader.next();
		//finish reading inputs
		reader.close(); 
	}
}
