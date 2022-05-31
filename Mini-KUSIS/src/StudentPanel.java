import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentPanel extends JPanel{
	
	public JButton enrollCourse;
	public JButton viewCourses;
	public JButton viewGrades;
	public JButton updatePassword;
	public JButton classSearch;
	
	
	public StudentPanel() {
		
		
		enrollCourse = new JButton("Enroll courses");
		viewCourses = new JButton("View courses");
		viewGrades = new JButton("View grades");
		updatePassword = new JButton("Update password");
		classSearch = new JButton("Search classes");
		

		updatePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newPass;
				newPass = JOptionPane.showInputDialog ("Enter new password");
				try {
					KusisFrame.st.executeUpdate("UPDATE students "
							+ "SET password = '" + newPass +"'"
							+ " WHERE id = " + KusisFrame.currentUserID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		
		
		enrollCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KusisFrame.st.executeUpdate(""); //TODO
					
					KusisFrame.studentPanel.setVisible(false);
					KusisFrame.lectureHoursPanel = new LectureHoursPanel(); //create according to database data
					KusisFrame.lectureHoursPanel.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		viewCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KusisFrame.st.executeUpdate(""); //TODO
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		viewGrades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KusisFrame.st.executeUpdate(""); //TODO
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		classSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KusisFrame.st.executeUpdate(""); //TODO
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		//setLayout(new GridLayout(1,5));
		setLayout(new FlowLayout());
		setSize(400, 400);
		
		add(enrollCourse);
		add(viewCourses);
		add(viewGrades);
		add(updatePassword);
		add(classSearch);
		
	}

}
