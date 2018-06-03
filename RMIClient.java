import java.rmi.*;
import java.util.Scanner;
public class RMIClient
{
	String key="rmi://localhost:1099/Mpstme";
	public RMIClient()
	{
		int num1;
		int num2;
		
		//Declaring the variables start below
		
		//Declaring the variables ends above
		
		while(true)
		{
			
			//Trying to accomplish contact with the server, if connection is established then okay, else the connection is terminated
			System.out.println("Tring to connect to the Simple File Server...");
					
			try
			{
				RMIInterface ri=(RMIInterface)Naming.lookup(key);
				System.out.println(ri.Hello());
			
				/*Scanner sc=new Scanner(System.in);
				System.out.println("Enter first number:");
				num1=sc.nextInt();
				System.out.println("Enter second number:");
				num2=sc.nextInt();
				System.out.println("Addition :"+ri.Addition(num1,num2));
				System.out.println("Subtraction :"+ri.Subtraction(num1,num2));
				System.out.println("Multiplication :"+ri.Multiplication(num1,num2));
				System.out.println("Division :"+ri.Division(num1,num2));*/
			}
			catch(Exception e)
			{
				System.out.println("Connection to the Simple File Server failed due to the below exception");
				System.out.println(e);
				break;
			}
		
			System.out.println("Welcome to Simple File Client");
			System.out.println("Successfully Connected to the Simple File Server");
			
			//Present to the user the options to do file operations
			System.out.println("Press 1 to Upload a file to the Server");
			System.out.println("Press 2 to Download a file from the Server");
			System.out.println("Press 3 to Rename a file on the Server");
			System.out.println("Press 4 to Delete a file on the Server");
		
		}
	}
	
	public static void main(String[] args)
	{
		new RMIClient();
	}
}