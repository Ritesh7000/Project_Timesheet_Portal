package logIn_Dashboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Manager_Dashboard {
	JFrame frame;
	JPanel panel,tablepanel,tablepanel2;
	Font font = new Font("",Font.BOLD,20);
	JLabel welcome,label,projects_under_u;
	JTextField Project,Project_ID;
	JButton addproject,deleteproject,showdetails;
	JTable table,table2;
	Object[] row,row2;
    DefaultTableModel model,model2;
    String prev1,prev2;
    String name;
	Manager_Dashboard(){
		frame = new JFrame("Dashboard");
        panel = new JPanel();
        frame.setJMenuBar(CommonMenu.displayMenu(frame));
        frame.setLayout(null);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFE4B5"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
		
        try {
            BufferedImage i = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\Projects\\Login__DashBoard\\src\\logIn_Dashboard\\Images\\Timesheet.png"));
            frame.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
        formatter.format(date);  
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        prev1=formatter.format( cal.getTime());
        
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -2);
        prev2=formatter.format( cal1.getTime());
        
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");	
			String query = "select * from personal_deatils where user_name=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, LogIn_Form.userText.getText());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
					name=rs.getString("employee_name");
					welcome = new JLabel("Hello " +name+" !!!");
			        welcome.setBounds(20,30,1000,35);
			        welcome.setFont(new Font("Times New Roman",Font.BOLD,30));
			        panel.add(welcome);
			}
        	}catch(Exception e1) {
        		System.out.println(e1);
        	}

        label = new JLabel("Your Last Two Day's Timesheet");
        label.setBounds(20,60,300,50);
        label.setFont(new Font("Times New Roman",Font.PLAIN,20));
        panel.add(label);

        table = new JTable();
        row = new Object[6];
        tablepanel = new JPanel();
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBounds(20,100,1000,660);
        DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tableRenderer);

        table.setBounds(20,100,1000,660);
        model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Date");
        model.addColumn("Task Name");
        model.addColumn("Task Description");
        model.addColumn("Project Manager");
        model.addColumn("Project Name");
        model.addColumn("Time");

        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.RED);
        table.setGridColor(Color.red);
        table.setSelectionForeground(Color.white);
        table.setFont(new Font("", Font.PLAIN,15));
        table.setRowHeight(table.getRowHeight()+20);

        JScrollPane pane = new JScrollPane(table);
        pane.setVisible(true);
        tablepanel.add(pane);
        panel.add(tablepanel);

        Project = new JTextField("Project Name");
        Project .setBounds(1050,100,300,30);
        Project.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(Project.getText().equals("Project Name")) {
					Project.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(Project.getText().equals("")) {
					Project.setText("Project Name");
				}
					
			}
        	
        });
        panel.add(Project);

        Project_ID = new JTextField("Project ID");
        Project_ID.setBounds(1050,150,200,30);
        Project_ID.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(Project_ID.getText().equals("Project ID")) {
					Project_ID.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(Project_ID.getText().equals("")) {
					Project_ID.setText("Project ID");
				}
			}
        	
        });
        panel.add(Project_ID);

        addproject= new JButton("Add Project");
        addproject.setBounds(1100,200,200,30);
        addproject.setForeground(Color.white);
        addproject.setFont(font);
        addproject.setBackground(Color.blue);
        addproject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");	
					if(Project.getText().equals("Project Name") || Project.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Enter Project Name...");
					}
					else if(Project_ID.getText().equals("Project ID") || Project_ID.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Enter Project ID...");
					}
					else {	
						String query4 = "Select * from project_data where project_id=?;";
						PreparedStatement ps = con.prepareStatement(query4);
						ps.setString(1, Project_ID.getText());
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "ID Already Exists...."+"\n"+"Enter Correct One...","Warning",JOptionPane.WARNING_MESSAGE);
						}
						String query1 = "insert into project_data values(?,?,?,?);";
						PreparedStatement ps1 = con.prepareStatement(query1);
						ps1.setString(1, LogIn_Form.userText.getText());
						String query2 = "select * from personal_deatils where user_name=?;";
						PreparedStatement ps2 = con.prepareStatement(query2);
						ps2.setString(1, LogIn_Form.userText.getText());
						ResultSet rs2 = ps2.executeQuery();
						if(rs2.next()) {
								name=rs2.getString("employee_name");
							}
						ps1.setString(2, name);
						ps1.setString(3, Project.getText());
						ps1.setString(4, Project_ID.getText());
						int rs1 = ps1.executeUpdate();
					if(rs1>0) {
						JOptionPane.showMessageDialog(null, "Project Added Successfully...");
					}
					}
			        }catch(Exception e1) {
			        		System.out.println(e1);
			       	}
			}
        	
        });
        panel.add(addproject);
        
        projects_under_u = new JLabel("Projects Under You...");
        projects_under_u.setBounds(1050, 260, 400, 30);
        projects_under_u.setForeground(Color.black);
        projects_under_u.setFont(font);
        panel.add(projects_under_u);
        
        table2 = new JTable();
        row2 = new Object[2];
        tablepanel2 = new JPanel();
        tablepanel2.setLayout(new BorderLayout());
        tablepanel2.setBounds(1050,300,410,400);
        
        DefaultTableCellRenderer tableRenderer2 = new DefaultTableCellRenderer();
        tableRenderer2.setHorizontalAlignment(JLabel.CENTER);
        table2.setDefaultRenderer(Object.class, tableRenderer2);
        table2.setBounds(1050,300,410,400);
        model2 = new DefaultTableModel();
        table2.setModel(model2);
        model2.addColumn("Project Name");
        model2.addColumn("Project ID");
        
        table2.setBackground(Color.white);
        table2.setForeground(Color.BLACK);
        table2.setSelectionBackground(Color.red);
        table2.setGridColor(Color.red);
        table2.setSelectionForeground(Color.white);
        table2.setFont(new Font("", Font.PLAIN,15));
        table2.setRowHeight(table.getRowHeight()+5);
     
        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setVisible(true);
        tablepanel2.add(pane2);
        panel.add(tablepanel2);
        
        deleteproject = new JButton("Delete Project");
        deleteproject.setForeground(Color.WHITE);
        deleteproject.setBorderPainted(false);
        deleteproject.setBackground(Color.BLUE);
        deleteproject.setFont(font);
        deleteproject.setBounds(1050,730,200,30);
        deleteproject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete a project?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					int row = table2.getSelectedRow();
			        String col1 = table2.getModel().getValueAt(row, 0).toString();
			        String col2 = table2.getModel().getValueAt(row, 1).toString();
			        model2.removeRow(row);
			        try {
			    		Class.forName("com.mysql.cj.jdbc.Driver");
			    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
			        	String query ="delete from project_data where user_name=? and project_name=? and project_id=?;";
			        	PreparedStatement ps = con.prepareStatement(query); 
			        	ps.setString(1, LogIn_Form.userText.getText());
			        	ps.setString(2, col1);
			        	ps.setString(3, col2);
			        	ps.executeUpdate();
			        	}catch(Exception e1) {
			        		System.out.println(e1);
			        	}
		            }
			}
        	
        });
        panel.add(deleteproject); 
        
        showdetails = new JButton("Show Details");
        showdetails.setForeground(Color.WHITE);
        showdetails.setBorderPainted(false);
        showdetails.setBackground(Color.BLUE);
        showdetails.setFont(font);
        showdetails.setBounds(1300, 730, 200, 30);
        showdetails.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table2.getSelectedRow();
		        String col1 = table2.getModel().getValueAt(row, 0).toString();
		        String col2 = table2.getModel().getValueAt(row, 1).toString();
				new Project_details(col1,col2);
				frame.dispose();
			}
        	
        });
        panel.add(showdetails);
        
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        showTask();
        showProject();
}

    public ArrayList<viewUserData> taskList(){
    	ArrayList<viewUserData> tasksList = new ArrayList<>();
    	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
		viewUserData user;
    	String query = "select * from add_task where user_name=? and selected_date>=? and selected_date<=?;";
    	PreparedStatement ps = con.prepareStatement(query);
    	ps.setString(1, LogIn_Form.userText.getText());
    	ps.setString(2, prev2);
    	ps.setString(3, prev1);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		 user = new viewUserData(rs.getString("user_name"),rs.getString("selected_date"),rs.getString("task_name"),rs.getString("task_description")
    				 				 ,rs.getString("project_manager"),rs.getString("project_name"),rs.getString("time_spent"));
    		 tasksList.add(user);
    	}
    	}catch(Exception e1) {
    		System.out.println(e1);
    	}
		return tasksList;
    }
    
    public void showTask() {
    	ArrayList<viewUserData> list = taskList();
    	for(int i=0;i<list.size();i++) {
    		row[0]=list.get(i).getselected_date();
    		row[1]=list.get(i).gettask_name();
    		row[2]=list.get(i).gettask_description();
    		row[3]=list.get(i).getproject_manager();
    		row[4]=list.get(i).getproject_name();
    		row[5]=list.get(i).gettime_spent();
    		model.addRow(row);
    	}
    }
    public ArrayList<Manager_data> projectList(){
    	ArrayList<Manager_data> projectssList = new ArrayList<>();
    	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
		Manager_data manager;
    	String query = "select * from project_data where user_name=?;";
    	PreparedStatement ps = con.prepareStatement(query);
    	ps.setString(1, LogIn_Form.userText.getText());
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		 manager = new Manager_data(rs.getString("project_id"),rs.getString("project_name"));
    		 projectssList.add(manager);
    	}
    	}catch(Exception e1) {
    		System.out.println(e1);
    	}
		return projectssList;
    }
    public void showProject() {
    	ArrayList<Manager_data> list = projectList();
    	for(int i=0;i<list.size();i++) {
    		row2[0]=list.get(i).getproject_id();
    		row2[1]=list.get(i).getproject_name();
    		model2.addRow(row2);
    }
}
}
