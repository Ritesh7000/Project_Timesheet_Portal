package logIn_Dashboard;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class LogIn_Form {
	JFrame frame;
    JPanel panel;
    JLabel heading;
    Font font = new Font("",Font.BOLD,30);
    JLabel userLabel;
    static JTextField userText;
    JLabel passwordLabel;
    JPasswordField passwordText;
    JButton buttonLogin;
    JLabel signup;
   // JLabel success;
    LogIn_Form(){
        panel = new JPanel();
        frame = new JFrame();
  
        frame.setResizable(false);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.setTitle("Login Site");

        frame.setSize(400,400);
        panel.setSize(new Dimension(300,300));
        panel.setBounds(50,25,300,300);
        panel.setBackground(Color.decode("#FFE4B5"));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

 
        
        try {
            BufferedImage i = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\Projects\\Login__DashBoard\\src\\logIn_Dashboard\\Images\\Timesheet.png"));
            frame.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        heading = new JLabel("LogIn");
        heading.setFont(font);
        heading.setForeground(Color.BLACK);
        heading.setBounds(10,10,40,20);
        //frame.add(heading);

        userLabel = new JLabel("Username");
        userLabel.setBounds(30,50,100,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(130,50,150,25);
        panel.add(userText);

        passwordLabel =new JLabel("Password");
        passwordLabel.setBounds(30,80,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(130,80,150,25);
        panel.add(passwordText);
        
        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(120,150,80,30);
        buttonLogin.setBackground(Color.GREEN);
        buttonLogin.setBorderPainted(false);
        buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					String role;
					String query = "select * from LogIn_Data where username=? and u_password=?;";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, userText.getText());
					ps.setString(2, passwordText.getText());
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
							role=rs.getString("Role_");
							if(role.equals("Manager")) {
								new Manager_Dashboard();
								frame.dispose();
							}
							else {
								new Dashboard();
								frame.dispose();
							}
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password!!!","Status",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
				
			}
        	
        });
        panel.add(buttonLogin);

        signup = new JLabel("<HTML><U>Not yet Registered?Register Now</U></HTML>");
        signup.setForeground(Color.RED);
        signup.setFont(new Font("Times New Roman",Font.ITALIC,15));
        signup.setBounds(40,200,220,20);
        signup.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                new SignUp_Form();
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.add(signup);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

