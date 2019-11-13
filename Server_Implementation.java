package lab2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/*
 * Author - Deepak Varghese , Student ID - 1001572764, Net ID - dxv2764
 * 
 * The Server_Implementation Class includes the functionality for reading and writing the data for 
 * the different processes present.
 * 
 *  References are as follows
 * 1)https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 2)https://www.youtube.com/watch?v=3fq4AdaiGFA
 * 3)https://www.youtube.com/watch?v=BxCbxfpwC7Q
 * 4)http://javadevnotes.com/java-string-array-examples
 * 5)https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
 */

public class Server_Implementation extends UnicastRemoteObject implements Server_Interface
{
/*
 * server - Uses the server defined in server class.
 * data   - The data which is used amongst all the processes present.
 */
	Server server;
	ArrayList<String> data;
	public Server_Implementation(Server serverPassed,ArrayList<String> data ) throws RemoteException {
		// TODO Auto-generated constructor stub
		server=serverPassed;
		this.data=data;
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	
/*
 * This method implements the read data functionality 
 */
	public ArrayList<String> read_data(String class_called) { 
		// TODO Auto-generated method stub
		ArrayList<String> data_not_read=new ArrayList<String>(); 
		ArrayList<String> data_read=new ArrayList<String>();
		for(int i=0;i<data.size();i++) {
			if(data.get(i).contains("_null")) {
				data_not_read.add(data.get(i)); // The data that advisor process accesses.
				//data.remove(i);
				
			}
			else {
				data_read.add(data.get(i)); // The data that notification process accesses.
				//data.remove(i);
			}
		}
		
		if(class_called.equals("notify")) {
			data.removeAll(data_read); //Clears the data which have been displayed on notification process.
			server.Display();
			return data_read;
		}
		else {
			System.out.println(data_not_read.size());
				data.removeAll(data_not_read); //Clears the data which have been displayed on advisor process.
				server.Display();
			return data_not_read;

		}
		
		
	}

	@Override
	
/*
 * Writes the data into the text file and displays the data on server
 */
	public void write_data(String str) {
		// TODO Auto-generated method stub
		server.data.add(str); 
		server.Display();
	}

	

}
