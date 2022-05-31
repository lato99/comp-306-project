import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class InstructorPanel extends JPanel{

	private JButton enterEditGrades;
	private JButton updatePassword;
	private JButton showSchedule;


	public InstructorPanel(){ 

		super();
		Color[]colors = {Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};

		Random rn = new Random();
		int colLen = colors.length;
		int randInd = rn.nextInt(colLen);


		enterEditGrades = new JButton();
		enterEditGrades.setText("Enter or edit grades");
		enterEditGrades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KusisFrame.st.executeUpdate(""); //TODO
					KusisFrame.instructorPanel.setVisible(false);

					KusisFrame.studentsGradesPanel = new StudentsGradesPanel(); //create panel according to database data
					KusisFrame.studentsGradesPanel.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});



		updatePassword = new JButton();
		updatePassword.setText("Change Password");
		updatePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newPass;
				newPass = JOptionPane.showInputDialog ("Enter new password");
				try {
					KusisFrame.st.executeUpdate("UPDATE students "  //replace students with instructors
							+ "SET password = '" + newPass +"'"
							+ " WHERE id = " + KusisFrame.currentUserID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
        

		showSchedule = new JButton("show schedule");
		showSchedule.addActionListener(new ActionListener() {
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

		//setLayout(new GridLayout(1,3));
		setLayout(new FlowLayout());
		setSize(400, 400);

		add(enterEditGrades);
		add(updatePassword);
		add(showSchedule);

		setBackground(colors[randInd]);

	}

}
