package lab2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;

/*
 * Author - Deepak Varghese , Student ID - 1001572764, Net ID - dxv2764
 * 
 * The Advisor Class includes the window frame,the main method, the advisor constructor
 * and the run and read_data method.
 * 
 *  References are as follows
 * 1)https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 2)https://www.youtube.com/watch?v=3fq4AdaiGFA
 * 3)https://www.youtube.com/watch?v=BxCbxfpwC7Q
 * 4)http://javadevnotes.com/java-string-array-examples
 * 5)https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
 */

public class Advisor extends JFrame implements Runnable{
	
/*
 * The member area definition are as follows -
 * 
 * Course_select_area - The area where the student and his course is shown.
 * Course_decision_area  - The area where the student and his course is shown with the advisor decision.
 * s            - The server interface between the advisor and the server.
 */
	
	public static Server_Interface s;
	public static JTextArea Course_select_area;
	public static JTextArea Course_decision_area;
	
	 Advisor() {

/*
 * Creates the GUI for the Advisor process.		 
 */		 
		 setTitle("Advisor");
		 
		 setVisible(true);
		getContentPane().setLayout(null);
		
		 Course_select_area = new JTextArea();
		Course_select_area.setBounds(114, 0, 320, 113);
		getContentPane().add(Course_select_area);
		
		JLabel lblNewLabel = new JLabel("Course Selection");
		lblNewLabel.setBounds(10, 39, 105, 27);
		getContentPane().add(lblNewLabel);
		
		Course_decision_area = new JTextArea();
		Course_decision_area.setBounds(114, 148, 320, 113);
		getContentPane().add(Course_decision_area);
		
		JLabel lblCourseDecision = new JLabel("Course Decision");
		lblCourseDecision.setBounds(10, 184, 105, 14);
		getContentPane().add(lblCourseDecision);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String url="rmi://localhost:2929/Advising_Simulation"; //Path for interacting with the RMI server.
			s=(Server_Interface)Naming.lookup(url);
			Advisor advisor=new Advisor();
			Thread advisor_thread=new Thread(advisor); //Thread to make the advisor sleep and check every 3 seconds.
			advisor_thread.start();
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Override

/*
 * This method reads the data from the RMI server which the student inputs
 * and also makes the check every 3 seconds.
 */
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			
			try {
				read_data();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
/*
 * This method is used to read the data and make the decision regarding 
 * approving or rejecting the course.
 */
	public static void read_data() {
		try {
			Course_select_area.setText("");
			Course_decision_area.setText("");
			ArrayList<String> data= s.read_data("advisor");
			if(data.isEmpty()) {
				Course_select_area.setText("No message present");
			}
			else {
				for(int i=0;i<data.size();i++) {
					System.out.println(data.get(i));
				Course_select_area.append(data.get(i)+"\n");
				Random random_decision=new Random();
				StringTokenizer strToken=new StringTokenizer(data.get(i), "_");
				String student_name=strToken.nextToken();
				String course_name=strToken.nextToken();
				String decision=random_decision.nextBoolean()+"";
				String combine_str=student_name+"_"+course_name+"_"+decision;
				Course_decision_area.append(combine_str+"\n");
				s.write_data(combine_str);
				}
			}
			

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
