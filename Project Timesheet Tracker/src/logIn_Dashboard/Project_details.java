package logIn_Dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Project_details {
	Font head_font = new Font("Times New Roman",Font.BOLD,40);
	Font font = new Font("Times New Roman",Font.BOLD,20);
	Font text_font = new Font("Times New Roman",Font.PLAIN,18);
	JFrame frame ;
	JPanel panel;
	JLabel project_details,project_Name,project_ID,project_customer,project_description,project_status;
	JComboBox status;
	JTextField name_text,id_text,customer_text;
	JTextArea description_text;
	JButton saveNew,saveChanges;
	
	public Project_details(String col1,String col2){
		frame = new JFrame("Project Details");
		frame.setJMenuBar(CommonMenu.displayMenu(frame));
		frame.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0,0,screenSize.width, screenSize.height);
	
        panel = new JPanel();
		panel.setLayout(null);
        panel.setBackground(Color.decode("#FFE4B5"));
        panel.setSize(screenSize.width,screenSize.height);
		 
        try {
            BufferedImage i = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\Projects\\Login__DashBoard\\src\\logIn_Dashboard\\Images\\Timesheet.png"));
            frame.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        project_details = new JLabel("Project Details");
        project_details.setFont(head_font);
        project_details.setBounds(650, 30, 300, 50);
        panel.add(project_details);
      
        project_Name =  new JLabel("Project Name");
        project_Name.setBounds(40, 100, 200, 30);
        project_Name.setFont(font);
        panel.add(project_Name);
        
        name_text = new JTextField();
        name_text.setBounds(40, 135, 1000, 30);
        name_text.setText(col1);
        name_text.setBackground(Color.white);
        name_text.setEditable(false);
        name_text.setFont(text_font);
        panel.add(name_text);
        
        project_ID = new JLabel("Project ID");
        project_ID.setBounds(40, 190, 200, 30);
        project_ID.setFont(font);
        panel.add(project_ID);
        
        id_text = new JTextField();
        id_text.setBounds(40, 225, 500, 30);
        id_text.setText(col2);
        id_text.setBackground(Color.white);
        id_text.setEditable(false);
        id_text.setFont(text_font);
        panel.add(id_text);
        
        project_description = new JLabel("Project Description");
        project_description.setBounds(40, 280, 200, 30);
        project_description.setFont(font);
        panel.add(project_description);
        
        description_text = new JTextArea(20,1000);
        description_text.setBounds(40, 315, 1000, 100);
        description_text.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        description_text.setFont(text_font);
        panel.add(description_text);
        
        JScrollPane pane1 = new JScrollPane(description_text);
        pane1.setVisible(true);
        pane1.setBounds(40, 315, 1000, 100);
        panel.add(pane1);
        
        project_customer = new JLabel("Customer Name");
        project_customer.setBounds(40, 440, 200, 30);
        project_customer.setFont(font);
        panel.add(project_customer);
        
        customer_text = new JTextField();
        customer_text.setBounds(40, 475, 1000, 30);
        customer_text.setFont(text_font);
        panel.add(customer_text);
        
        project_status = new JLabel("Project Status");
        project_status.setBounds(40, 530, 200, 30);
        project_status.setFont(font);
        panel.add(project_status);
        
        String []s = {"In Progress","Restricted"};
        
        status = new JComboBox(s);
        status.setBackground(Color.white);
        status.setBounds(40,565,150,30);
        panel.add(status);
        
        saveNew = new JButton("Save New");
        saveNew.setFont(font);
        saveNew.setBounds(40,660,200,40);
        saveNew.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");	
				if(name_text.getText().equals("")  || id_text.getText().equals("") || 
					description_text.getText().equals("") || customer_text.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Some field is empty!!!","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String value1 = LogIn_Form.userText.getText() ;
					String value2 = name_text.getText();
					String value3 = id_text.getText();
					String value4 = description_text.getText();
					String value5 = customer_text.getText();
					String value6 = status.getSelectedItem().toString();
					try {
					String query = "insert into project_details values(?,?,?,?,?,?);";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, value1);
					ps.setString(2, value2);
					ps.setString(3, value3);
					ps.setString(4, value4);
					ps.setString(5, value5);
					ps.setString(6, value6);
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
        saveNew.setBackground(Color.WHITE);
        saveNew.setBorderPainted(false);
        panel.add(saveNew);
        
        saveChanges = new JButton("Save Changes");
        saveChanges.setBounds(280,660,200,40);
        saveChanges.setFont(font);
        saveChanges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");	
					if(name_text.getText().equals("")  || id_text.getText().equals("") || 
							description_text.getText().equals("") || customer_text.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Some field is empty!!!","Alert",JOptionPane.WARNING_MESSAGE);
						}
					else {
						String value1 = LogIn_Form.userText.getText() ;
						String value2 = name_text.getText();
						String value3 = id_text.getText();
						String value4 = description_text.getText();
						String value5 = customer_text.getText();
						String value6 = status.getSelectedItem().toString();
						String query = "update project_details set project_description='"+value4+"',customer_name='"+value5+"'"
										+ ",project_status = '"+value6+"'where user_name =? and project_name=? and project_id=?;";
						PreparedStatement ps = con.prepareStatement(query);
						ps.setString(1, value1);
						ps.setString(2, value2);
						ps.setString(3, value3);
						ps.execute();
						JOptionPane.showMessageDialog(null, "Updated Successfully","Confirmation",JOptionPane.DEFAULT_OPTION);
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
        	
        });
        saveChanges.setBackground(Color.WHITE);
        saveChanges.setBorderPainted(false);
        panel.add(saveChanges);
        
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		showDetailsByDeafault(col1,col2);
	}
	 public void showDetailsByDeafault(String col1 ,String col2) {
	    	try{
	    		Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");	
				String query = "select * from project_details where user_name=? and project_name =? and project_id =?;";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, LogIn_Form.userText.getText());
				ps.setString(2, col1);
				ps.setString(3, col2);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
						name_text.setText(rs.getString("project_name"));
						id_text.setText(rs.getString("project_id"));
						description_text.setText(rs.getString("project_description"));
						customer_text.setText(rs.getString("customer_name"));
						status.setSelectedItem(rs.getString("project_status"));
				}
				else {
					JOptionPane.showMessageDialog(null, "Fill all the details...");
				}
	    	}catch(Exception e1) {
	    		System.out.println(e1);
	    	}
	    }
}
