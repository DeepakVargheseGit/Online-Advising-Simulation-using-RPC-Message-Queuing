package lab2;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.*;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

/*
 * Author - Deepak Varghese , Student ID - 1001572764, Net ID - dxv2764
 * 
 * The Server Class includes the window frame,main method,the student constructor,
 * the interface to interact with the RMI
 * 
 *  References are as follows
 * 1)https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 2)https://www.youtube.com/watch?v=3fq4AdaiGFA
 * 3)https://www.youtube.com/watch?v=BxCbxfpwC7Q
 * 4)http://javadevnotes.com/java-string-array-examples
 * 5)https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
 */

public class Server  extends JFrame
implements WindowListener  {
	
/*
 * The member area definition are as follows -
 * 
 * file_scanner - The scanner is used to read the data from the input text file.
 * Messgae_Area - The text area where the student name and course name with the decision pops up.
 * data         - The array list which stores all the data from the different processes.
 */	
	public static Scanner file_scanner;
	public static JTextArea Messgae_Area;
	public static ArrayList<String> data;
	
	 Server() {

/*
 * Creates the GUI for the Server process.		 
 */		 
		 this.setSize(400,400);
		 setVisible(true);
		 this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
/*
 * This method performs the task of writing into the file all the data on the server when it is closed.
 */
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("here");
				try 
				{
				FileWriter	write_file = new FileWriter("Dictionary.txt",false);
				PrintWriter print_file = new PrintWriter(write_file);
				for(int i=0;i<data.size();i++)
				{
				print_file.println(data.get(i));
				} 
				print_file.close();
				closefile();
				}
				catch (Exception ae)
				{
					ae.printStackTrace();// TODO: handle exception
				}
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("here");
				try 
				{
				FileWriter	write_file = new FileWriter("Dictionary.txt",false);
				PrintWriter print_file = new PrintWriter(write_file);
				for(int i=0;i<data.size();i++)
				{
				print_file.println(data.get(i));
				} 
				print_file.close();
				closefile();
				}
				catch (Exception ee)
				{
					ee.printStackTrace();// TODO: handle exception
				}
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setTitle("Registration Server");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		Messgae_Area = new JTextArea();
		getContentPane().add(Messgae_Area);
		Messgae_Area.setEditable(false);
		Messgae_Area.setColumns(5);
		Messgae_Area.setRows(5);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void openfile() {  //Used to open the file and scan it to check the word

		data=new ArrayList<String>();
				try {
					file_scanner = new Scanner(new File("Dictionary.txt")); // Reads the data from the file
					while((file_scanner.hasNextLine())) {
					 //= file_scanner.nextLine();
String striop=file_scanner.nextLine().toString();
				System.out.println(striop);
					data.add(striop);
				}
				}
				catch (Exception e) {
				  System.out.println("Could not find file"+e.getMessage()); // Shows error if file not found.
				  e.printStackTrace();
				}
				
			}
			

			
			public static void closefile() { //Used to close the file 
				file_scanner.close();
			}
    public static void Display () //Displays the data from all the processes in the message area.
    {
    	Messgae_Area.setText("");
    	if (data.isEmpty())
    	{
    		Messgae_Area.setText("No messages to be dsiplayed");	
    	}
    	else
    	{
    		for(int i=0;i<data.size();i++)
    		{
    			//System.out.println(data.get(i));
    			Messgae_Area.append(data.get(i)+"\n");    			
    		}
    	}
    }
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try
		{
			openfile(); // opens the file 
			LocateRegistry.createRegistry(2929); //Creating registry for the server interface
			Server_Implementation si=new Server_Implementation(new Server(),data); //Invokes the server implementation
		    Naming.bind("rmi://localhost:2929/Advising_Simulation",si); 
		    Display(); //Display all the data on message area.
		}
		catch(Exception e)
		  {
		   e.printStackTrace();
		  }
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
