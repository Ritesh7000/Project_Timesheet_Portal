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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SignUp_Form {
	JFrame frame;
    JPanel panel;
    JLabel newuser;
    Font font = new Font("",Font.BOLD,20);
    JLabel newpassword;
    JLabel confirmpassword;
    static JTextField nameText;
    JPasswordField password;
    JPasswordField againpassword;
    JLabel role;
    JComboBox role_combo;
    JButton buttonRegister;
    JLabel login;
    SignUp_Form(){
        frame = new JFrame();
        panel = new JPanel();
        panel.setBackground(Color.decode("#FFE4B5"));
        frame.setTitle("SignUp");
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        frame.setSize(400,400);
        panel.setSize(300,300);
        frame.setResizable(false);
        
        try {
            BufferedImage i = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\Projects\\Login__DashBoard\\src\\logIn_Dashboard\\Images\\Timesheet.png"));
            frame.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        newuser = new JLabel("Username");
        newuser.setBounds(30,50,100,25);
        panel.add(newuser);

        nameText = new JTextField(20);
        nameText.setBounds(180,50,165,25);
        panel.add(nameText);

        newpassword =new JLabel("Password");
        newpassword.setBounds(30,100,100,25);
        panel.add(newpassword);

        password = new JPasswordField(20);
        password.setBounds(180,100,165,25);
        panel.add(password);

        confirmpassword =new JLabel("Confirm Password");
        confirmpassword.setBounds(30,150,150,25);
        panel.add(confirmpassword);

        againpassword = new JPasswordField(20);
        againpassword.setBounds(180,150,165,25);
        panel.add(againpassword);

        role = new  JLabel("Role");
        role.setBounds(30,200, 100, 25);
        panel.add(role);
        
        String role_option[] = {"Employee","Manager"};
        
        role_combo = new JComboBox(role_option);
        role_combo.setBackground(Color.white);
        role_combo.setBounds(180, 200, 100, 25);
        panel.add(role_combo);
        
        buttonRegister = new JButton("Register Now");
        buttonRegister.setBounds(90,250,200,30);
        buttonRegister.setBorderPainted(false);
        buttonRegister.setBackground(Color.GREEN);
        buttonRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					if(nameText.getText().equals("") || password.getText().equals("") || againpassword.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Some field is empty!!!","Alert",JOptionPane.WARNING_MESSAGE);
					}
					else if(!(password.getText().equals(againpassword.getText()))) {
						JOptionPane.showMessageDialog(null, "Password Should be Same!!!","Alert",JOptionPane.WARNING_MESSAGE);
					}
					else if(password.getText().length()!=8) {
						JOptionPane.showMessageDialog(null, "Password Must Contain 8 Chars...","Warning",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						String query2 = "Select * from signup_data where s_username=?;";
						PreparedStatement ps = con.prepareStatement(query2);
						ps.setString(1, nameText.getText());
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Username Already Exists...."+"\n"+"Try Another one...","Warning",JOptionPane.WARNING_MESSAGE);
						}
						else {
							String query1 = "insert into signup_data values(?,?,?,?);";
							PreparedStatement p = con.prepareStatement(query1);
							p.setString(1, nameText.getText());
							p.setString(2, password.getText());
							p.setString(3, againpassword.getText());
							p.setString(4, role_combo.getSelectedItem().toString());
							int r = p.executeUpdate();
							if(r>0) {
								JOptionPane.showMessageDialog(null, "Registered Successfully...");
								String q = "insert into login_data values(?,?,?,?);";
								PreparedStatement pss = con.prepareStatement(q);
								pss.setString(1, nameText.getText());
								pss.setString(2, password.getText());
								pss.setString(3, againpassword.getText());
								pss.setString(4, role_combo.getSelectedItem().toString());
								pss.executeUpdate();
								
								String role;
								String query = "select * from signup_data where s_username=? and s_password=?;";
								PreparedStatement ps2 = con.prepareStatement(query);
								ps2.setString(1, nameText.getText());
								ps2.setString(2, password.getText());
								ResultSet rs1 = ps2.executeQuery();
								if(rs1.next()) {
										role=rs1.getString("Role_");
										if(role.equals("Manager")) {
											new Manager_Dashboard();
											frame.dispose();
										}
										else {
											new Dashboard();
											frame.dispose();
										}
								}
							}
						}
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
				
			}
        	
        });
        panel.add(buttonRegister);

        login = new JLabel("<HTML><U>Already Registered?Login</U></HTML>");
        login.setForeground(Color.RED);
        login.setFont(new Font("Times New Roman",Font.ITALIC,15));
        login.setBounds(110,300,220,20);
        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                new LogIn_Form();
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.add(login);

        panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.setLayout(null);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

