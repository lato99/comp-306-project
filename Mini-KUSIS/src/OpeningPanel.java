import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OpeningPanel extends JPanel {

	private JLabel userLabel;
	private JButton logInButton;

	private JLabel welcome;

	private JLabel secondLogoImage;
	public JTextField usernameField;
	public JButton login;
	public JPasswordField passwordField;
	public JLabel passwordLabel;
	public JLabel userNameLabel;
	public JButton resetButton;
	public JComboBox userTypes;


	public OpeningPanel() {
		super();
		Color[]colorArr = {Color.GREEN,Color.RED,Color.YELLOW,Color.CYAN};


		Random rand = new Random();
		int length = colorArr.length;
		int index = rand.nextInt(length);


		usernameField = new JTextField(50);
		login= new JButton();
		passwordField = new JPasswordField();
		passwordLabel = new JLabel();
		userNameLabel = new JLabel();
		resetButton = new JButton();

		welcome= new JLabel();
		welcome.setText("Welcome to KUSIS");
		welcome.setFont(new Font("Serif", Font.PLAIN, 24));

		String[] types= {"Student","Instructor","Admin"};
		userTypes=new JComboBox(types);


		ImageIcon logo = new ImageIcon("logo.png");
		Image logoImage = logo.getImage();
		Image logoImageScaled = logoImage.getScaledInstance(UIConstants.widthButton*2, UIConstants.widthButton*2,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		logo = new ImageIcon(logoImageScaled);
		secondLogoImage= new JLabel(logo);

		usernameField.setBounds(UIConstants.middleX+UIConstants.widthButton*1/2,UIConstants.middleY-2*UIConstants.heightButton,UIConstants.widthButton,UIConstants.heightButton);
		passwordField.setBounds(UIConstants.middleX+UIConstants.widthButton*1/2,UIConstants.middleY-UIConstants.heightButton,UIConstants.widthButton,UIConstants.heightButton);

		userNameLabel.setText("Your Username: ");
		userNameLabel.setBounds(UIConstants.middleX-UIConstants.widthButton*1/2,UIConstants.middleY-2*UIConstants.heightButton,UIConstants.widthButton,UIConstants.heightButton);
		passwordLabel.setText("Your Password: ");
		passwordLabel.setBounds(UIConstants.middleX-UIConstants.widthButton*1/2,UIConstants.middleY-UIConstants.heightButton,UIConstants.widthButton,UIConstants.heightButton);


		login.setText("Login");
		login.setBounds(UIConstants.middleX+UIConstants.widthButton*1/2,UIConstants.middleY,UIConstants.widthButton,UIConstants.heightButton);


		userTypes.setBounds(UIConstants.middleX-UIConstants.widthButton*1/2,UIConstants.middleY,UIConstants.widthButton,UIConstants.heightButton);

	
		secondLogoImage.setBounds(UIConstants.width/2-UIConstants.widthButton,UIConstants.middleY-2*UIConstants.heightButton-2*UIConstants.widthButton,2*UIConstants.widthButton,2*UIConstants.widthButton);


		this.add(welcome);


		this.add(secondLogoImage);
		this.add(passwordLabel);
		this.add(userNameLabel);
		this.add(usernameField);
		this.add(passwordField);
		this.add(login);

		this.add(userTypes);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = usernameField.getText();
				String password = passwordField.getText();
				if(userExist(username, password)) {
					KusisFrame.openingPanel.setVisible(false);
					KusisFrame.studentPanel.setVisible(true);

					// our SQL SELECT query. 
					// if you only need a few columns, specify them by name instead of using "*"


					// create the java statement

					String query = "SELECT * FROM students WHERE username = '" + username +"'";
					// execute the query, and get a java resultset
					ResultSet rs;
					try {
						rs = KusisFrame.st.executeQuery(query);
						while (rs.next())
						{
							int id = rs.getInt("id");
							KusisFrame.currentUserID = id;
							System.out.println(id);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					// iterate through the java resultset

				} else {
					JOptionPane.showMessageDialog(login, "User does not exist");
				}
			}

		});


		this.setBackground(colorArr[index]);
		this.setLayout(null);
	}





	public JLabel getUserLabel1() {
		return userLabel;
	}


	public JButton getLogInButton() {
		return logInButton;
	}

	public JLabel getWelcome() {
		return welcome;
	}

	public void setUserLabel1(JLabel userLabel) {
		this.userLabel = userLabel;
	}


	public void setLogInButton(JButton logInButton) {
		this.logInButton = logInButton;
	}


	public void setWelcome(JLabel welcome) {
		this.welcome = welcome;
	}



	public Boolean userExist(String u, String p) {

		String tableName=null;
		if(userTypes.getSelectedItem().equals("Student")) tableName="students";
		else if(userTypes.getSelectedItem().equals("Student")) tableName="instructors";
		else if(userTypes.getSelectedItem().equals("Student")) tableName="admins";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginDB?characterEncoding=utf8", "root", "123456");
			java.sql.Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT username, password FROM " + tableName + " WHERE username = '"+u+"' AND password = '" + p +"'");
			if(rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



}