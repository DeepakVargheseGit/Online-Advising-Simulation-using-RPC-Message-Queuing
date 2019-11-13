package lab2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * Author - Deepak Varghese , Student ID - 1001572764, Net ID - dxv2764
 * 
 * The Server_Interface Class implements the server interface.
 * 
 *  References are as follows
 * 1)https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 2)https://www.youtube.com/watch?v=3fq4AdaiGFA
 * 3)https://www.youtube.com/watch?v=BxCbxfpwC7Q
 * 4)http://javadevnotes.com/java-string-array-examples
 * 5)https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
 */

/*
 * This implements the server interface.
 */
public interface Server_Interface extends Remote
{	
	public ArrayList<String> read_data(String str) throws RemoteException;
	public void write_data(String str) throws RemoteException;	
}
