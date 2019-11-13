package lab2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*
 * Author - Deepak Varghese , Student ID - 1001572764, Net ID - dxv2764
 * 
 * The Notification Class includes the window frame,the main method, the notification constructor
 * and the run and read_data method.
 * 
 *  References are as follows
 * 1)https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 2)https://www.youtube.com/watch?v=3fq4AdaiGFA
 * 3)https://www.youtube.com/watch?v=BxCbxfpwC7Q
 * 4)http://javadevnotes.com/java-string-array-examples
 * 5)https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
 */


public class Notification extends JFrame implements Runnable{
	
/*
 * The member area definition are as follows -
 * 
 * decision_made - The area where the advisor decision regarding the course is displayed.
 * s             - The server interface between the advisor and the server.
 */
	
	public static Server_Interface s;
	public static JTextArea decision_made;
	
	Notification() {
		
/*
 * Creates the GUI for the Notification process.		 
 */	
		
		 setTitle("Notification");
		 setVisible(true);
		getContentPane().setLayout(null);
		
		 decision_made = new JTextArea();
		decision_made.setBounds(114, 11, 310, 239);
		getContentPane().add(decision_made);
		
		JLabel lblNewLabel = new JLabel("Decision");
		lblNewLabel.setBounds(10, 65, 94, 27);
		getContentPane().add(lblNewLabel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String url="rmi://localhost:2929/Advising_Simulation"; //Path for interacting with the RMI server.
			s=(Server_Interface)Naming.lookup(url);
			Notification notify=new Notification();
			Thread notify_thread=new Thread(notify); //Thread to make the notification sleep and check every 7 seconds.
			notify_thread.start();
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Override
	
/*
 * This method reads the data from the RMI server which the advisor has made a decision for
 * and also makes the check every 7 seconds.
 */	

	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			
			try {
				read_data();
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
/*
 * This method is used to read the data and show the decision made by advisor regarding 
 * the course requested by student.
 */
	
	public static void read_data() {
		try {
			decision_made.setText("");
			
			ArrayList<String> data= s.read_data("notify");
			if(data.isEmpty()) {
				decision_made.setText("No message present");
			}
			else {
				for(int i=0;i<data.size();i++)
				{
				StringTokenizer strToken=new StringTokenizer(data.get(i).toString(), "_");
				String student_name=strToken.nextToken();
				String course_name=strToken.nextToken();
				String decision=strToken.nextToken();
				String Final="";
				if(decision.equals("true"))
				{
					 Final = "Approved";
				}
				else if(decision.equals("false"))
				{
					 Final = "Rejected";
				}
				
				String combine_str= "Student Name : " + student_name+"\n"+"Course Name : "+ course_name+"\n"+"Decision : "+Final+"\n";
				decision_made.append(combine_str);
				//decision_made.append(data.get(i)+"\n");
				
				}
			}
			

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
