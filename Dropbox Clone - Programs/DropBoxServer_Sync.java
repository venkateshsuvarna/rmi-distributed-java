import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class DropBoxServer_Sync extends Thread {
   private ServerSocket serverSocket;
   
   //Variable declaration starts below
   String inputCode;
   int inputcode_int;
   DataInputStream in;
   DataOutputStream out;
   Socket server;
   
   //Variable declaration ends above
   
   public DropBoxServer_Sync(int port) throws IOException {
      serverSocket = new ServerSocket(port);
     
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for DropBox client on port " + 
               serverSocket.getLocalPort() + "...");
            server = serverSocket.accept();
            
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
		 
		 //The below code is to enable server to accept requests from the client
		 System.out.println("Line 42 executed...");
		 
		 try
		 {
			 System.out.println("Inside try block of input code...");
			//First command is the request type and then perform operation based on the input request code from the client
			inputCode=in.readUTF();
			System.out.println("Input code recieved is : "+inputCode);
			
			//Convert inputcode string to inputcode_int integer
			inputcode_int = Integer.parseInt(inputCode);
			
			switch(inputcode_int)
			{
				case 1:
				
				//Code to get file from the client to server and store the recieved file locally
				
				//System.out.println("Server recieved a command to recieve a file from the client");
				
				//Recieving location of file from the client
				
				try
				{
				
				String locationOfFile = in.readUTF();
				System.out.println("Location of file from client is : "+locationOfFile);
				
				InputStream in = server.getInputStream();
				
				OutputStream out = new FileOutputStream(locationOfFile);
				
				File file = new File(locationOfFile);
				file.createNewFile();
				
				byte[] bytes = new byte[16*1024];

				int count;
				
				System.out.println("Before While Loop");
				
				while ((count = in.read(bytes)) > 0) 
				{
					System.out.println("Count = "+count);
					out.write(bytes, 0, count);
				}
				
				System.out.println("After While Loop");
				
				System.out.println("Count = "+count);
				
				in.close();
				out.close();
				
				}
				catch(Exception e)
				{
					System.out.println("Upload could not be completed due to the following reason "+e);
				}
				
				
				break;
				case 2:
				
				//Code to send file from server to the client, the client code will contain the code to store the file locally
				
				//Code to download a file to server
								
				//System.out.println("Enter the location of the file you want to upload to the server");
				
				try
				{
				
					String locationOfFile = in.readUTF(); 
				
					File file = new File(locationOfFile);
					
									
					// Get the size of the file
					long length = file.length();
					byte[] bytes = new byte[16 * 1024];
					InputStream in1 = new FileInputStream(file);
					OutputStream out1 = server.getOutputStream();

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
				case 3:
				
				//Code to rename a file on server
				
								
				String locationOfFile=in.readUTF();
				
							
				//Create file object with the file name entered by the user
				File file1 = new File(locationOfFile);
				System.out.println("Original Location : "+locationOfFile);
				
				String newFilePath = in.readUTF();
				File file2 = new File(newFilePath);
				System.out.println("New Location : "+newFilePath);
				
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
				}
				
				
				
				
				break;
				case 4:
				
				//Code to delete a file
				
								
				String locationOfFile1=in.readUTF();
				
							
				//Create file object with the file name entered by the user
				File file_delete = new File(locationOfFile1);
				System.out.println("Original Location : "+locationOfFile1);
				
			
				boolean success1=false;

				//Delete file (or directory)
				try
				{
					success1 = file_delete.delete();
					System.out.println("Success : "+success1);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				
				if (!success1) 
				{
					// File was not successfully renamed
					System.out.println("File delete operation failed due to some reasons. Please Try Again.");
				}
				else
				{
					//File rename was a success
					System.out.println("File deleted Successfully.");
				}
				
				break;
				default:
				
				System.out.println("Invalid operation. Please try again.");
				
				break;
			}
			
			
			 
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
		 
		 
		 
      }
   }
   
   public static void main(String[] args) {
      
	  /* The below code asks server admin to enter port number so that server can start running*/
	  
	  //int port = Integer.parseInt(args[0]);
	  System.out.println("**************** Welcome to Drop Box Server! *****************\n");
      int port;
	  System.out.println("Enter the port number of the server :");
	  Scanner sc = new Scanner(System.in);
	  port=sc.nextInt();
	  
	  try {
         Thread t = new DropBoxServer_Sync(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}