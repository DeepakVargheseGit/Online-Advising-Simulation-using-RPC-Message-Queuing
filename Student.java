package lab2;

import java.rmi.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
/*
 * Author - Deepak Varghese , Student ID - 1001572764, Net ID - dxv2764
 * 
 * The Student Class includes the window frame,main method,the student constructor,
 * the interface to interact with the RMI
 * 
 *  References are as follows
 * 1)https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 2)https://www.youtube.com/watch?v=3fq4AdaiGFA
 * 3)https://www.youtube.com/watch?v=BxCbxfpwC7Q
 * 4)http://javadevnotes.com/java-string-array-examples
 * 5)https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
 */
public class Student extends JFrame
implements WindowListener {

/*
 * The member area definition are as follows -
 * 
 * student_name - The area where the student inputs his name.
 * course_name  - The area were the student inputs the course he wants to register for this semester.
 * s            - The server interface between the student and the server.
 */
	private JTextField student_name;
	private JTextField course_name;
	public static Server_Interface s;
	
	 Student()
	 {
/*
 * Creates the GUI for the Student process.		 
 */
		 //super(name);
		 setTitle("Student Course Registration");
		 this.addWindowListener(this);
		 this.setSize(400,200);
		 this.setResizable(true);
		 getContentPane().setLayout(null);
		 
		 JLabel lblNewLabel = new JLabel("Student Name");
		 lblNewLabel.setBounds(10, 11, 100, 14);
		 getContentPane().add(lblNewLabel);
		 
		 student_name = new JTextField();
		 student_name.setBounds(107, 8, 250, 20);
		 getContentPane().add(student_name);
		 student_name.setColumns(50);
		 
		 JLabel lblNewLabel_1 = new JLabel("Course Name");
		 lblNewLabel_1.setBounds(10, 47, 100, 14);
		 getContentPane().add(lblNewLabel_1);
		 
		 course_name = new JTextField();
		 course_name.setBounds(107, 44, 250, 20);
		 getContentPane().add(course_name);
		 course_name.setColumns(50);
		 
		 JButton btnNewButton = new JButton("Register Course");
		 btnNewButton.setBounds(107, 92, 145, 23);
		 getContentPane().add(btnNewButton);
		 this.setVisible(true);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
/*
 * addActionListener is used to perform actions when search is clicked
 */		 
		 btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String String_Combo = student_name.getText() + "_" + course_name.getText() + "_null"; //combines the student name and course name and adds null where null represents that advisor decision is not made. 
				try {
					s.write_data(String_Combo); //writes the data into the server interface
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	 }
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
			String url="rmi://localhost:2929/Advising_Simulation"; //The path for the RMI interface.
			s=(Server_Interface)Naming.lookup(url); //Sets up the server interface.
		    Student student = new Student(); //Invokes the student class.
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
