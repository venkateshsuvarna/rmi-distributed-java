import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class DropBoxClient
{
	public static void main(String[] args)
	{
		try {
		
		String serverName = "10.183.234.234";
		int port = 6066;
		
        System.out.println("Connecting to " + serverName + " on port " + port);
        Socket client = new Socket(serverName, port);
         
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
         
        out.writeUTF("Hello from " + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
         
        System.out.println("Server says " + in.readUTF() +". Test Connection Successful. Enjoy our service!");
		 
		int choice;
		
		
		while(true)
		{
		
			System.out.println("Please select one of the following operations");
			System.out.println("1. Upload a file");
			System.out.println("2. Download a file");
			System.out.println("3. Rename a file");
			System.out.println("4. Delete a file");
			System.out.println("5. Exit");
			
			System.out.println("Enter a choice :");
			Scanner sc = new Scanner(System.in);
			choice=sc.nextInt();
			
			switch(choice)
			{
				case 1:
				
				//Code to upload a file to server
				out.writeUTF("1");
				
				System.out.println("Enter the location of the file you want to upload to the server");
				
				try
				{
				
					sc = new Scanner(System.in);
					String locationOfFile = sc.next(); 
				
					//File file = new File("M:\\test.xml");
					File file = new File(locationOfFile);
					
					out.writeUTF(locationOfFile);
					System.out.println("Location of file sent to server");
				
					// Get the size of the file
					long length = file.length();
					byte[] bytes = new byte[16 * 1024];
					InputStream in1 = new FileInputStream(file);
					OutputStream out1 = client.getOutputStream();

					int count;
					
					System.out.println("Before While Loop");
					
					while ((count = in1.read(bytes)) > 0) 
					{
						out1.write(bytes, 0, count);
						System.out.println("Count = "+count);
					}
					
					System.out.println("After While Loop");
					
					in1.close();
					out1.close();
				
				}
				catch(Exception e)
				{
					System.out.println("Upload could not be completed due to the following reason "+e);
				}
				
				
				break;
				case 2:
				
				//Code to download a file from server
				
				out.writeUTF("2");
				
				System.out.println("Enter the location of the file you want to download from the server");
				
				try
				{
				
					sc = new Scanner(System.in);
					String locationOfFile=sc.next();
					
					out.writeUTF(locationOfFile);
				
					//File file = new File("M:\\test.xml");
					File file = new File(locationOfFile);
					
					
					System.out.println("Location of file to be downloaded is sent to server");
				
					// Get the size of the file
					long length = file.length();
					byte[] bytes = new byte[16 * 1024];
					InputStream in1 = client.getInputStream();
					OutputStream out1 = new FileOutputStream("C:\\distributedsystemfolder\\test_document1.txt");
					//OutputStream out = new FileOutputStream(locationOfFile);

					int count;
					
					//System.out.println("Before While Loop");
					
					while ((count = in1.read(bytes)) > 0) 
					{
						out1.write(bytes, 0, count);
						//System.out.println("Count = "+count);
					}
					
					//System.out.println("After While Loop");
					
					in1.close();
					out1.close();
				
				}
				catch(Exception e)
				{
					System.out.println("Upload could not be completed due to the following reason "+e);
				}
				
				
				break;
				case 3:

					out.writeUTF("3");

					System.out.println("Enter the file location that you want to rename on the server");
					sc=new Scanner(System.in);
					String locationOfFile=sc.next();
					String directory;
//Create file object with the file name entered by the user
					File file1 = new File(locationOfFile);
					out.writeUTF(locationOfFile);
					System.out.println("Enter the new file name for the server");
					String newFileName = sc.next();
//Concatenate the string upto the last occurence of \ character
					int index = locationOfFile.lastIndexOf(92); //ASCII value of backslash
					System.out.println("Index = "+index);
					directory=locationOfFile.substring(0,index);
					String newFilePath = directory + newFileName;
					File file2 = new File(newFilePath);
					out.writeUTF(newFilePath);
				//Code to rename a file on client
				
				/*System.out.println("Enter the file location that you want to rename");
				sc=new Scanner(System.in);
				String locationOfFile=sc.next();
				
				String directory;
				
				//Create file object with the file name entered by the user
				File file1 = new File(locationOfFile);
				
				System.out.println("Enter the new file name");
				String newFileName = sc.next();
				
				//Concatenate the string upto the last occurence of \ character
				int index = locationOfFile.lastIndexOf(92); //ASCII value of backslash
				System.out.println("Index = "+index);
				directory=locationOfFile.substring(0,index);
				
				String newFilePath = directory + newFileName;
				File file2 = new File(newFilePath);
				
				if (file2.exists())
				throw new java.io.IOException("file exists");
			
				boolean success=false;

				// Rename file (or directory)
				try
				{
					success = file1.renameTo(file2);
					System.out.println("Success : "+success);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				
				if (!success) 
				{
					// File was not successfully renamed
					System.out.println("File rename operation failed due to some reasons. Please Try Again.");
				}
				else
				{
					//File rename was a success
					System.out.println("File Renamed Successfully.");
				}*/
				
				
				
				break;
				case 4:
				
					//Code to delete a file on server

					out.writeUTF("4");

					System.out.println("Enter the file location that you want to delete on the server");
					sc=new Scanner(System.in);
					String locationOfFile1=sc.next();
					//Create file object with the file name entered by the user
					File file3 = new File(locationOfFile1);
					out.writeUTF(locationOfFile1);


					/*System.out.println("Enter the file location that you want to delete");
				sc=new Scanner(System.in);
				locationOfFile=sc.next();
				
								
				//Create file object with the file name entered by the user
				file1 = new File(locationOfFile);
				
				if(file1.delete())
				{
					System.out.println("File deleted successfully");
				}
				else
				{
					System.out.println("File delete operation failed due to some reasons. Please try again.");
				}*/
				
				
				
				break;
				case 5:
				
				client.close();
				System.exit(0);
				break;
			}
		
		}
		 
        //client.close();
      }catch(IOException e) {
        e.printStackTrace();
      }
		
	}
}