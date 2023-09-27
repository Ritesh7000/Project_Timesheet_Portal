package logIn_Dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class CommonMenu {

    public CommonMenu() {
    }

    public static JMenuBar displayMenu(JFrame f) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(Color.CYAN);
        JMenu dashboard = new JMenu("Dashboard");
        JMenu person = new JMenu("Personal Details");
        JMenu task = new JMenu("Task");
        JMenu logout = new JMenu("Logout");

        dashboard.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                f.setVisible(false);
                try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
					String role;
					String query = "select * from LogIn_Data where username=?;";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, LogIn_Form.userText.getText());
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
							role=rs.getString("Role_");
							System.out.println(role);
							if(role.equals("Manager")) {
								new Manager_Dashboard();
							}
							else {
								new Dashboard();
							}
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password!!!","Status",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
				
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        person.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                f.setVisible(false);
                new Personal();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        logout.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					f.setVisible(false);
					new LogIn_Form();
				}
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        JMenuItem add_task = new JMenuItem("Add Task");
        add_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new AddTask();
            }
        });

        JMenuItem edit_task = new JMenuItem("Edit Task");
        edit_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
               new EditTask();
            }
        });

        JMenuItem view_task = new JMenuItem("View Task");
        view_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new ViewTask();
            }
        });


        task.add(add_task);
        task.addSeparator();
        task.add(edit_task);
        task.addSeparator();
        task.add(view_task);

        menuBar.add(dashboard);
        menuBar.add(person);
        menuBar.add(task);
        menuBar.add(logout);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return menuBar;
    }
}

