import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdministratorPanel extends JPanel{

	public JButton addCourses;
	public JCheckBox isEnrollingAllowed;
	public JCheckBox isDroppingAllowed;
	public JButton updatePassword;

	public AdministratorPanel() {
		super();

		//take information from database
		String[] instructors= {"instructor1","instructor2","instructor3","instructor4"};
		String[] hour={"08.30-09.40","10.00-11.10","17.30-18.40","07.00-08.10"};

		addCourses = new JButton("Add Courses");
		updatePassword = new JButton("Update password");

		isEnrollingAllowed = new JCheckBox("Activate enrolling");
		isEnrollingAllowed.setHorizontalAlignment(0);

		isDroppingAllowed = new JCheckBox("Activate dropping");
		isDroppingAllowed.setHorizontalAlignment(0);

		addCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField enterCourseName;
				JComboBox instructorNames;
				JComboBox hours;
				JButton saveCourse;

				enterCourseName = new JTextField("Enter Course Name");

				try {
					KusisFrame.st.executeUpdate(""); //Get instructor names
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
				instructorNames = new JComboBox(instructors);

				try {
					KusisFrame.st.executeUpdate(""); //Get hours
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
				hours = new JComboBox(hour);



				enterCourseName.setEditable(true);
				instructorNames.setEditable(true);
				hours.setEditable(true);

				//create a JOptionPane
				Object[] options = new Object[] {};
				JOptionPane jop = new JOptionPane("Enter course information",
						JOptionPane.QUESTION_MESSAGE,
						JOptionPane.DEFAULT_OPTION,
						null,options, null);


				saveCourse = new JButton("Save Course");


				saveCourse.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							KusisFrame.st.executeUpdate(""); //INSERT INTO...
						} catch (SQLException e1) {
							e1.printStackTrace();
						}	
					} 
				});
				jop.add(enterCourseName);
				jop.add(instructorNames);
				jop.add(hours);
				jop.add(saveCourse);


				JDialog diag = new JDialog();
				diag.getContentPane().add(jop);
				diag.pack();
				diag.setVisible(true);
			}
		});

		updatePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newPass;
				newPass = JOptionPane.showInputDialog ("Enter new password");
				try {
					KusisFrame.st.executeUpdate("UPDATE students "  //replace students with admins
							+ "SET password = '" + newPass +"'"
							+ " WHERE id = " + KusisFrame.currentUserID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});


		//setLayout(new GridLayout(3,1));
		setLayout(new FlowLayout());
		setSize(400, 400);

		add(addCourses);
		add(isEnrollingAllowed);
		add(isDroppingAllowed);
		add(updatePassword);


	}
}
