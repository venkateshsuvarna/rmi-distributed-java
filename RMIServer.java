import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
public class RMIServer extends UnicastRemoteObject implements RMIInterface
{
	String key="Mpstme";
	public RMIServer()throws RemoteException
	{
		try
		{
			LocateRegistry.createRegistry(1099);
			Naming.rebind(key,this);
		}
		catch(Exception e)
		{
		 	System.out.println(e);
		}
	}
	public String Hello()throws RemoteException
	{
		return "Hello RMI";
	}
	
	public String Addition(int a,int b)throws RemoteException
	{	
		int add;
		add=a+b;
		String str_a = Integer.toString(add);
		return str_a;
	}
	public String Subtraction(int a,int b)throws RemoteException
	{

		int sub;
		sub=a-b;
		String str_b = Integer.toString(sub);
		return str_b;
	}
	public String Multiplication(int a,int b)throws RemoteException
	{

		int mul;
		mul=a*b;
		String str_c = Integer.toString(mul);
		return str_c;
	}
	public String Division(int a,int b)throws RemoteException
	{

		int div;
		div=a/b;
		String str_d = Integer.toString(div);
		return str_d;
	}

	public static void main(String[] args)
	{
		try
		{
			new RMIServer();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}