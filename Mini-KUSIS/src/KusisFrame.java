

import java.awt.Color;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.ResultSet;



public class KusisFrame{
	
	public static JFrame frame= new JFrame();
	public static OpeningPanel openingPanel;
	public static InstructorPanel instructorPanel;
	public static StudentsGradesPanel studentsGradesPanel;
	public static AdministratorPanel administratorPanel;
	public static LectureHoursPanel lectureHoursPanel;
    public static StudentPanel studentPanel;
    
    public static java.sql.Connection con;
    public static java.sql.Statement st;
    
    public static int currentUserID = -1;
    
	public static void main(String[] args) throws IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginDB?characterEncoding=utf8", "root", "123456");
			st = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeDatabase();

		frame = new JFrame();
		frame.setTitle("KUSIS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(UIConstants.width, UIConstants.height);      

		frame.setLayout(null);

		
		openingPanel = new OpeningPanel();
		openingPanel.setVisible(true);
		openingPanel.setSize(UIConstants.width, UIConstants.height);      
		frame.add(openingPanel);
		
		instructorPanel = new InstructorPanel();
		instructorPanel.setVisible(false);
		instructorPanel.setSize(UIConstants.width,UIConstants.height);
		frame.add(instructorPanel);
		
		
		studentsGradesPanel = new StudentsGradesPanel();
		studentsGradesPanel.setVisible(false);
		studentsGradesPanel.setSize(UIConstants.width,UIConstants.height);
		frame.add(studentsGradesPanel);
		
		
		administratorPanel = new AdministratorPanel();
		administratorPanel.setVisible(false);
		administratorPanel.setSize(UIConstants.width,UIConstants.height);
		frame.add(administratorPanel);
		
		
		lectureHoursPanel = new LectureHoursPanel();
		lectureHoursPanel.setVisible(false);
		lectureHoursPanel.setSize(UIConstants.width,UIConstants.height);
		frame.add(lectureHoursPanel);
		
		
		studentPanel = new StudentPanel();
		studentPanel.setVisible(false);
		studentPanel.setSize(UIConstants.width,UIConstants.height);
		frame.add(studentPanel);
		
		
		
		frame.setVisible(true);
		

	}

	

	private static void initializeDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginDB?characterEncoding=utf8", "root", "123456");
			java.sql.Statement st = con.createStatement();
			
			DatabaseMetaData dbm = (DatabaseMetaData) con.getMetaData();
			// check if "employee" table is there
			ResultSet tables = (ResultSet) dbm.getTables(null, null, "students", null);
			if (tables.next()) {
			  // Table exists
				System.out.println("table already exists");
			}
			else {
			  // Table does not exist
				st.executeUpdate("CREATE TABLE students (id integer, name varchar(255), surname varchar(255), username varchar(255), password varchar(255))");
				st.executeUpdate("INSERT INTO students values"
				+ "(1, 'n', 's', 'u1', 's1'), "
				+ "(2, 'n', 's', 'u2', 's2'),"
				+ "(3, 'n', 's', 'u3', 's3'),"
				+ "(4, 'n', 's', 'u4', 's4'),"
				+ "(5, 'n', 's', 'u5', 's5'),"
				+ "(6, 'n', 's', 'u6', 's6'),"
				+ "(7, 'n', 's', 'u', 's'),"
				+ "(8, 'n', 's', 'u', 's'),"
				+ "(9, 'n', 's', 'u', 's'),"
				+ "(10, 'n', 's', 'u', 's'),"
				+ "(11, 'n', 's', 'u', 's'),"
				+ "(12, 'n', 's', 'u', 's'),"
				+ "(13, 'n', 's', 'u', 's'),"
				+ "(14, 'n', 's', 'u', 's'),"
				+ "(15, 'n', 's', 'u', 's'),"
				+ "(16, 'n', 's', 'u', 's'),"
				+ "(17, 'n', 's', 'u', 's'),"
				+ "(18, 'n', 's', 'u', 's'),"
				+ "(19, 'n', 's', 'u', 's'),"
				+ "(20, 'n', 's', 'u', 's')");
				
				//CREATE OTHER TABLES SUCH AS INSTRUCTORS, ADMINS, COURSES, STUDENTCOURSES ...
				
				
				
				System.out.println("tables are created");
			}
			//st.executeUpdate("CREATE TABLE IF NOT EXISTS users (username varchar(255), password varchar(255));");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	


}

