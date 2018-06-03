import java.rmi.*;

public interface RMIInterface extends Remote
{
	public String Hello()throws RemoteException;

	public String Addition(int a,int b)throws RemoteException;
	public String Subtraction(int a,int b)throws RemoteException;
	public String Multiplication(int a,int b)throws RemoteException;
	public String Division(int a,int b)throws RemoteException;
}
