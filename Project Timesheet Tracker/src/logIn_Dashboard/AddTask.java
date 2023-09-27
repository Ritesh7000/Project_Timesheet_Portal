package logIn_Dashboard;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class AddTask{
    JFrame frame = new JFrame();
    Font font = new Font("",Font.BOLD,20);
    JPanel panel= new JPanel();
    JTextField taskname;
    JTextArea taskdescription;
    JTextField select_date;
    JButton select;
    JLabel proManager,proName;
	JComboBox projectManager;
    JComboBox projects;
    JTextField time;
    
    JTextField minutes;
    JButton add;
    JButton submit;
    AddTask(){
        frame.setJMenuBar(CommonMenu.displayMenu(frame));
        frame.setTitle("Add Task");
        frame.setLayout(null);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFE4B5"));
        
        try {
            BufferedImage i = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\Projects\\Login__DashBoard\\src\\logIn_Dashboard\\Images\\Timesheet.png"));
            frame.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
        panel.setBounds(0,0,screenSize.width, screenSize.height);

        select_date=  new JTextField();
        select_date.setEditable(false);
        select_date.setBackground(Color.WHITE);
        select_date.setBounds(50,60,150,30);
        panel.add(select_date);

        select = new JButton("Select Date");
        select.setBorderPainted(false);
        select.setBackground(Color.white);
        select.setBounds(50,20,150,30);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select_date.setText(new DatePicker(frame).setPickedDate());
            }
        });
        panel.add(select);

        taskname = new JTextField();
        taskname.setText("Task Name");
        taskname.setBounds(50,100,300,30);
        taskname.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        taskname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if((taskname.getText()).equals("Task Name"))
                taskname.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if((taskname.getText()).equals(""))
                taskname.setText("Task Name");
            }
        });
        panel.add(taskname);

        taskdescription = new JTextArea(20,100);
        taskdescription.setText("Task Description");
        taskdescription.setBounds(355,100,200,100);
        taskdescription.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        taskdescription.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if((taskdescription.getText()).equals("Task Description"))
                    taskdescription.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if((taskdescription.getText()).equals(""))
                    taskdescription.setText("Task Description");
            }
        });
        taskdescription.setLineWrap(true);
        panel.add(taskdescription);

        proManager = new JLabel("Project Manager");
        proManager.setBounds(560,60,200,30);
        proManager.setFont(font);
        panel.add(proManager);
        
        projectManager = new JComboBox();
        projectManager.setBackground(Color.white);
        projectManager.setBounds(560,100,250,40);
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");	
			String query = "select distinct project_manager from project_data;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
		        projectManager.addItem(rs.getString(1));
			}
        }catch(Exception e1) {
        	System.out.println(e1);
        }
        panel.add(projectManager);

        proName = new JLabel("Project Name");
        proName.setBounds(815,60,150,30);
        proName.setFont(font);
        panel.add(proName);
        

        projects = new JComboBox();
        projects.setBackground(Color.white);
        projects.setBounds(815,100,300,40);
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");	
			String query = "select distinct project_name from project_data;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
		        projects.addItem(rs.getString(1));
			}
        }catch(Exception e1) {
        	System.out.println(e1);
        }
        panel.add(projects);
        
        time = new JTextField(5);
        time.setBackground(Color.white);
        time.setBounds(1120,100,75,40);
        time.setFont(new Font("Times New Roman",Font.PLAIN,20));
        time.setText("Hours");
        time.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(time.getText().equals("Hours")) {
					time.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(time.getText().equals("")) {
					time.setText("Hours");
				}
			}
        	
        });
        time.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//String value = time.getText();
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
		               time.setEditable(true);
		            } else {
		               time.setEditable(false);
		               JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
		               time.setEditable(true);
		            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        panel.add(time);

        //String t1[] = { "Minutes","10", "15","20","25" ,"30","35", "40","45","50","55"};

        minutes = new JTextField(5);
        minutes.setBackground(Color.white);
        minutes.setBounds(1200,100,75,40);
        minutes.setFont(new Font("Times New Roman",Font.PLAIN,20));
        minutes.setText("Minutes");
        minutes.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(minutes.getText().equals("Minutes")) {
					minutes.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(minutes.getText().equals("")) {
					minutes.setText("Minutes");
				}
					
			}
        	
        });
        minutes.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//String value = minutes.getText();
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					minutes.setEditable(true);
		            } else {
		            	minutes.setEditable(false);
		               JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
		               minutes.setEditable(true);
		            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        panel.add(minutes);

//        add = new JButton("Add Event");
//        add.setFont(font);
//        add.setBorderPainted(false);
//        add.setForeground(Color.RED);
//        add.setBackground(Color.green);
//        add.setBounds(1080,30,150,30);
//        add.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            }
//        });
//        panel.add(add);

        submit = new JButton("+Submit Timesheet");
        submit.setForeground(Color.WHITE);
        submit.setBorderPainted(false);
        submit.setBackground(Color.BLUE);
        submit.setFont(font);
        submit.setBounds(1080,170,250,30);
        submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");	
				if(select_date.getText().equals("")  || taskname.getText().equals("Task Name") || 
					taskdescription.getText().equals("Task Description") ||
					projectManager.getSelectedItem().equals("Project Manager") || 
					projects.getSelectedItem().equals("Project Manager") ||
					time.getText().equals("Hours") || 
					minutes.getText().equals("Minutes"))
				{
					JOptionPane.showMessageDialog(null, "Some field is empty!!!","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
					String proM = projectManager.getSelectedItem().toString();
					String proN = projects.getSelectedItem().toString();
					String final_time = time.getText().toString() + ":" + minutes.getText().toString();
					String query = "insert into add_task values(?,?,?,?,?,?,?);";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, LogIn_Form.userText.getText());
					//ps.setString(2, LogIn_Form.passwordText.getText());
					ps.setString(2, select_date.getText());
					ps.setString(3, taskname.getText());
					ps.setString(4, taskdescription.getText());
					ps.setString(5, proM);
					ps.setString(6, proN);
					ps.setString(7, final_time);
					int rs = ps.executeUpdate();
					if(rs>0) {
						JOptionPane.showMessageDialog(null, "Timesheet Submitted...");
						AddTask a = new AddTask();
						frame.dispose();
					}	
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
				  }
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
        	
        });
        panel.add(submit);


//        panel.add(panel_1);
//        panel.add(panel_2);
//        panel.add(panel_3);
//        panel.add(panel_4);
//        panel.add(panel_5);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}


