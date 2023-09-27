package logIn_Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Personal {
    JFrame frame;
    Font font1 = new Font("Times New Roman",Font.BOLD,20);
    Font font2 = new Font("Times New Roman",Font.BOLD,15);
    Font font3 = new Font("Times New Roman",Font.PLAIN,15);
    JPanel mainpanel,p_1,p_2,p_3;
    JLabel h_1,h_2,h_3;
    JLabel title,name,email,ID,address,country,phone,dob,marital,bank,place_of_birth,nationality,personal_ID,SSN;
    JComboBox title_combo,marital_combo;
    JTextArea  address_text;
    static JTextField name_text;
    JTextField email_text, country_text, phone_text,ID_text,bank_text;
    JTextField dob_text,palce_birth_text,nationality_text,personal_ID_text,SSN_text;
    JButton saveNew , saveChanges;
    Personal (){
        frame=new JFrame();
        frame.setJMenuBar(CommonMenu.displayMenu(frame));
        frame.setTitle("Personal Details");
        frame.setLayout(null);
        frame.setFont(font1);
        mainpanel=new JPanel();
        mainpanel.setLayout(new GridLayout(1,3));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        mainpanel.setBounds(0,0,screenSize.width, screenSize.height);

        p_1= new JPanel();
        p_1.setLayout(null);
        p_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        p_2= new JPanel();
        p_2.setLayout(null);
        p_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        p_3= new JPanel();
        p_3.setLayout(null);
        p_3.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        h_1 = new JLabel("Address And Financial");
        h_1.setFont(new Font("Times New Roman",Font.BOLD,20));
        h_1.setBounds(5,10,250,20);
        h_1.setForeground(Color.RED);
        p_1.add(h_1);

        h_2 = new JLabel("Other Information");
        h_2.setFont(new Font("Times New Roman",Font.BOLD,20));
        h_2.setBounds(20,10,250,20);
        h_2.setForeground(Color.RED);
        p_2.add(h_2);

        h_3 = new JLabel("Sensitive Information");
        h_3.setFont(new Font("Times New Roman",Font.BOLD,20));
        h_3.setBounds(30,10,250,20);
        h_3.setForeground(Color.RED);
        p_3.add(h_3);

        title = new JLabel("Title:");
        title.setFont(font2);
        title.setBounds(5,60,200,20);
        p_1.add(title);

        String s1[] = {"Mr.","Mrs.","Miss"};

        title_combo = new JComboBox(s1);
        title_combo.setBounds(205,60,200,20);
        title_combo.setFont(font3);
        p_1.add(title_combo);

        name = new JLabel("Name:");
        name.setFont(font2);
        name.setBounds(5,100,200,20);
        p_1.add(name);

        name_text = new JTextField();
        name_text.setBounds(205,100,200,20);
        name_text.setFont(font3);
        p_1.add(name_text);

        email = new JLabel("E-mail Address:");
        email.setFont(font2);
        email.setBounds(5,140,200,20);
        p_1.add(email);

        email_text = new JTextField();
        email_text.setBounds(205,140,200,20);
        email_text.setFont(font3);
        p_1.add(email_text);

        address = new JLabel("Address:");
        address.setFont(font2);
        address.setBounds(5, 180,200,20);
        p_1.add(address);

        address_text = new JTextArea(15,30);
        address_text.setBounds(205,180,200,75);
        address_text.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        address_text.setFont(font3);
        p_1.add(address_text);

        country = new JLabel("Country:");
        country.setFont(font2);
        country.setBounds(5,270,200,20);
        p_1.add(country);

        country_text = new JTextField();
        country_text.setBounds(205,270,200,20);
        country_text.setFont(font3);
        p_1.add(country_text);

        phone = new JLabel("Phone no.:");
        phone.setFont(font2);
        phone.setBounds(5,310,200,20);
        p_1.add(phone);

        phone_text = new JTextField();
        phone_text.setBounds(205,310,200,20);
        phone_text.setFont(font3);
        phone_text.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					phone_text.setEditable(true);
		            } else {
		            	phone_text.setEditable(false);
		               JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
		              phone_text.setEditable(true);
		            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        p_1.add(phone_text);

        ID = new JLabel("ID card:");
        ID.setFont(font2);
        ID.setBounds(5,350,200,20);
        p_1.add(ID);

        ID_text = new JTextField();
        ID_text.setBounds(205,350,200,20);
        ID_text.setFont(font3);
        ID_text.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					ID_text.setEditable(true);
		            } else {
		            	ID_text.setEditable(false);
		               JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
		              ID_text.setEditable(true);
		            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        p_1.add(ID_text);

        bank = new JLabel("Bank acc no.:");
        bank.setFont(font2);
        bank.setBounds(5,390,200,20);
        p_1.add(bank);

        bank_text = new JTextField();
        bank_text.setBounds(205,390,200,20);
        bank_text.setFont(font3);
        p_1.add(bank_text);

        dob = new JLabel("DOB:");
        dob.setFont(font2);
        dob.setBounds(20,60,200,20);
        p_2.add(dob);

        dob_text = new JTextField();
        dob_text.setBounds(220,60,200,20);
        dob_text.setFont(font3);
        p_2.add(dob_text);

        place_of_birth = new JLabel("Place of Birth:");
        place_of_birth.setFont(font2);
        place_of_birth.setBounds(20,100,200,20);
        p_2.add(place_of_birth);

        palce_birth_text = new JTextField();
        palce_birth_text.setBounds(220,100,200,20);
        palce_birth_text.setFont(font3);
        p_2.add(palce_birth_text);

        nationality = new JLabel("Nationality:");
        nationality.setFont(font2);
        nationality.setBounds(20,140,200,20);
        p_2.add(nationality);

        nationality_text = new JTextField();
        nationality_text.setBounds(220,140,200,20);
        nationality_text.setFont(font3);
        p_2.add(nationality_text);

        marital = new JLabel("Marital Status:");
        marital.setFont(font2);
        marital.setBounds(20,180,200,20);
        p_2.add(marital);

        String s2[] = {"Married","Unmarried","Separated"};

        marital_combo = new JComboBox(s2);
        marital_combo.setBounds(220,180,200,20);
        marital_combo.setFont(font3);
        p_2.add(marital_combo);

        personal_ID = new JLabel("Aadhar Card No..:");
        personal_ID.setFont(font2);
        personal_ID.setBounds(30,60,200,20);
        p_3.add(personal_ID);

        personal_ID_text = new JTextField();
        personal_ID_text.setBounds(235,60,200,20);
        personal_ID_text.setFont(font3);
        personal_ID_text.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					personal_ID_text.setEditable(true);
		            } else {
		            	personal_ID_text.setEditable(false);
		               JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
		               personal_ID_text.setEditable(true);
		            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        p_3.add(personal_ID_text);

        SSN = new JLabel("Social Security no.:");
        SSN.setFont(font2);
        SSN.setBounds(30,100,200,20);
        p_3.add(SSN);

        SSN_text = new JTextField();
        SSN_text.setBounds(235,100,200,20);
        SSN_text.setFont(font3);
        SSN_text.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					SSN_text.setEditable(true);
		            } else {
		            	SSN_text.setEditable(false);
		               JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
		              SSN_text.setEditable(true);
		            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        p_3.add(SSN_text);
        
        saveNew = new JButton("Save New");
        saveNew.setBackground(Color.WHITE);
        saveNew.setBounds(50, 350, 170, 30);
        saveNew.setBorderPainted(false);
        saveNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");	
				if(name_text.getText().equals("")  || email_text.getText().equals("") || 
					address_text.getText().equals("") || country_text.getText().equals("") ||
					phone_text.getText().equals("") || ID_text.getText().equals("") ||
					bank_text.getText().equals("") || dob_text.getText().equals("") ||
					palce_birth_text.getText().equals("") || nationality_text.getText().equals("") ||
					personal_ID_text.getText().equals("") || SSN_text.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Some field is empty!!!","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String value1 = LogIn_Form.userText.getText() ;
					String value2 = title_combo.getSelectedItem().toString();
					String value3 = name_text.getText();
					String value4 = email_text.getText();
					String value5 = address_text.getText();
					String value6 = country_text.getText();
					String value7 = phone_text.getText();
					String value8 = ID_text.getText();
					String value9 = bank_text.getText();
					String value10 = dob_text.getText();
					String value11 = palce_birth_text.getText();
					String value12 = nationality_text.getText();
					String value13 = marital_combo.getSelectedItem().toString();
					String value14 = personal_ID_text.getText();
					String value15 = SSN_text.getText();
					try {
					String query = "insert into personal_deatils values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, value1);
					ps.setString(2, value2);
					ps.setString(3, value3);
					ps.setString(4, value4);
					ps.setString(5, value5);
					ps.setString(6, value6);
					ps.setString(7, value7);
					ps.setString(8, value8);
					ps.setString(9, value9);
					ps.setString(10, value10);
					ps.setString(11, value11);
					ps.setString(12, value12);
					ps.setString(13, value13);
					ps.setString(14, value14);
					ps.setString(15, value15);
					int rs = ps.executeUpdate();
					if(rs>0) {
						JOptionPane.showMessageDialog(null, "Details Stored Successfully...");
					}	
					}catch(Exception e1) {
						e1.printStackTrace();
					}
		
				  }
				} catch (ClassNotFoundException | SQLException e2) {
					e2.printStackTrace();
				}
			}
        	
        });
        p_3.add(saveNew);
        
        saveChanges = new JButton("Save Changes");
        saveChanges.setBackground(Color.WHITE);
        saveChanges.setBounds(300,350, 170, 30);
        saveChanges.setBorderPainted(false);
        saveChanges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");	
					if(name_text.getText().equals("")  || email_text.getText().equals("") || 
							address_text.getText().equals("") || country_text.getText().equals("") ||
							phone_text.getText().equals("") || ID_text.getText().equals("") ||
							bank_text.getText().equals("") || dob_text.getText().equals("") ||
							palce_birth_text.getText().equals("") || nationality_text.getText().equals("") ||
							personal_ID_text.getText().equals("") || SSN_text.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Some field is empty!!!","Warning",JOptionPane.WARNING_MESSAGE);
						}
					else if(phone_text.getText().length()>10 || phone_text.getText().length()<10 ) {
						JOptionPane.showMessageDialog(null, "Mobile No. Should contain 10 Digits...");
					}
					else if(ID_text.getText().length()>9 || ID_text.getText().length()<9) {
						JOptionPane.showMessageDialog(null, "Office ID No. Should contain 9 Digits...");
					}
					else if(personal_ID_text.getText().length()>12 || personal_ID_text.getText().length()<12) {
						JOptionPane.showMessageDialog(null, "Aadhar No. Should contain 12 Digits...");
					}
					else if(SSN_text.getText().length()>9 || SSN_text.getText().length()<9 ) {
						JOptionPane.showMessageDialog(null, "SSN Should contain 9 Digits...");
					}
					else {
						String value1 = LogIn_Form.userText.getText() ;
						//String value2 = LogIn_Form.passwordText.getText();
						String value3 = title_combo.getSelectedItem().toString();
						String value4 = name_text.getText();
						String value5 = email_text.getText();
						String value6 = address_text.getText();
						String value7 = country_text.getText();
						String value8 = phone_text.getText();
						String value9 = ID_text.getText();
						String value10 = bank_text.getText();
						String value11 = dob_text.getText();
						String value12 = palce_birth_text.getText();
						String value13 = nationality_text.getText();
						String value14 = marital_combo.getSelectedItem().toString();
						String value15 = personal_ID_text.getText();
						String value16 = SSN_text.getText();
						String query = "update personal_deatils set user_name ='"+value1+"',title='"+value3+"'"
										+ ",employee_name='"+value4+"',email='"+value5+"',address='"+value6+"',country='"+value7+"',phone_no='"+value8+"'"
										+ ",Office_ID_No='"+value9+"',Bank_acc_no='"+value10+"',DOB='"+value11+"',Place_of_birth='"+value12+"'"
										+ ",Natioanlity='"+value13+"',Marital_Status='"+value14+"',Personal_ID_No='"+value15+"',SSN='"+value16+"'"
										+ "where user_name ='"+value1+"';";
						PreparedStatement ps = con.prepareStatement(query);
						ps.execute();
						JOptionPane.showMessageDialog(null, "Updated Successfully","Confirmation",JOptionPane.DEFAULT_OPTION);
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
        	
        });
        p_3.add(saveChanges);
        
        mainpanel.add(p_1);
        mainpanel.add(p_2);
        mainpanel.add(p_3);

        frame.add(mainpanel);
        frame.setVisible(true);
        showDetailsByDeafault();
    }
    
    public void showDetailsByDeafault() {
    	try{
    		Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");	
			String query = "select * from personal_deatils where user_name=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, LogIn_Form.userText.getText());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
					title_combo.setSelectedItem(rs.getString("title"));	
					name_text.setText(rs.getString("employee_name"));
					email_text.setText(rs.getString("email"));
					address_text.setText(rs.getString("address"));
					country_text.setText(rs.getString("country"));
					phone_text.setText(rs.getString("phone_no"));
					ID_text.setText(rs.getString("Office_ID_No"));
					bank_text.setText(rs.getString("Bank_acc_no"));
					dob_text.setText(rs.getString("DOB"));
					palce_birth_text.setText(rs.getString("Place_of_birth"));
					nationality_text.setText(rs.getString("Natioanlity"));
					marital_combo.setSelectedItem(rs.getString("Marital_Status"));
					personal_ID_text.setText(rs.getString("Personal_ID_No"));
					SSN_text.setText(rs.getString("SSN"));
			}
			else {
				JOptionPane.showMessageDialog(null, "Fill all the details...");
			}
    	}catch(Exception e1) {
    		System.out.println(e1);
    	}
    }
}
