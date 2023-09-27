package logIn_Dashboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
import java.util.ArrayList;

public class ViewTask {
    JFrame frame;
    Font font = new Font("",Font.BOLD,20);
    JPanel panel,tablepanel;
    JButton startDate;
    JTextField datestart;
    JButton endDate;
    JTextField dateend;
    JButton View;
    JTable table;
    Object[] row;
    DefaultTableModel model;
    ViewTask(){
        frame = new JFrame("View Tasks");
        panel = new JPanel();
        frame.setJMenuBar(CommonMenu.displayMenu(frame));
        frame.setLayout(null);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFE4B5"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);

        try {
            BufferedImage i = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\Projects\\Login__DashBoard\\src\\logIn_Dashboard\\Images\\Timesheet.png"));
            frame.setIconImage(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        datestart=  new JTextField();
        datestart.setEditable(false);
        datestart.setBounds(50,55,150,30);
        panel.add(datestart);

        startDate = new JButton("   From    ");
        startDate.setBackground(Color.white);
        startDate.setBorderPainted(false);
        startDate.setBounds(50,20,150,30);
        startDate.setFont(new Font("Times New Roman",Font.BOLD,20));
        startDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datestart.setText(new DatePicker(frame).setPickedDate());
            }
        });
        panel.add(startDate);

        dateend=  new JTextField();
        dateend.setEditable(false);
        dateend.setBounds(210,55,150,30);
        panel.add(dateend);

        endDate = new JButton("    To    ");
        endDate.setBackground(Color.white);
        endDate.setBorderPainted(false);
        endDate.setBounds(210,20,150,30);
        endDate.setFont(new Font("Times New Roman",Font.BOLD,20));
        endDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateend.setText(new DatePicker(frame).setPickedDate());
            }
        });
        panel.add(endDate);

        View = new JButton("View Task");
        View.setBackground(Color.BLUE);
        View.setBorderPainted(false);
        View.setBounds(380,35,150,30);
        View.setFont(font);
        View.setForeground(Color.white);
        View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		showTask();
            }
        });
        panel.add(View);

        table = new JTable();
        row = new Object[6];
        tablepanel = new JPanel();
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBounds(20,100,1450,650);

        DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tableRenderer);
        table.setBounds(20,100,1450,650);
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
        table.setSelectionBackground(Color.gray);
        table.setGridColor(Color.red);
        table.setSelectionForeground(Color.white);
        table.setFont(new Font("", Font.PLAIN,15));
        table.setRowHeight(table.getRowHeight()+20);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setVisible(true);
        tablepanel.add(pane);
        panel.add(tablepanel);
        
        

        frame.add(panel);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.pink);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    public ArrayList<viewUserData> taskList(){
    	ArrayList<viewUserData> tasksList = new ArrayList<>();
    	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
		viewUserData user;
    	String query = "select * from add_task where user_name=? and selected_date>=? and selected_date<=?;";
    	PreparedStatement ps = con.prepareStatement(query);
    	ps.setString(1, LogIn_Form.userText.getText());
    	ps.setString(2, datestart.getText());
    	ps.setString(3, dateend.getText());
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
    
}
