package Main;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.table.TableModel;
import java.awt.Component;

public class Billing extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Billing frame = new Billing();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Billing() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 752);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 255, 255)); // White background color
        sidebarPanel.setBounds(0, 114, 251, 610); // Adjusted height to fit content
        sidebarPanel.setLayout(null);
        contentPane.add(sidebarPanel);

        JButton btnDashboard = new JButton("Homepage");
        btnDashboard.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnDashboard.setBounds(0, 58, 251, 58);
        btnDashboard.setBackground(new Color(255, 255, 255));
        btnDashboard.setForeground(new Color(0, 0, 0));
        btnDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                homepage homepageFrame = new homepage();
                homepageFrame.setVisible(true);
            }
        });
        sidebarPanel.add(btnDashboard);

        JButton btnApartments = new JButton("Apartments");
        btnApartments.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnApartments.setBounds(0, 114, 251, 58);
        btnApartments.setBackground(new Color(255, 255, 255));
        btnApartments.setForeground(new Color(0, 0, 0));
        btnApartments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Apartments apartmentsFrame = new Apartments();
                apartmentsFrame.setVisible(true);
            }
        });
        sidebarPanel.add(btnApartments);

        JButton btnTenants = new JButton("Tenants");
		btnTenants.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnTenants.setBounds(0, 170, 251, 58);
		btnTenants.setBackground(new Color(255, 255, 255));
		btnTenants.setForeground(new Color(0, 0, 0));
		btnTenants.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		        Tenants tenantsFrame = new Tenants();
		        tenantsFrame.setVisible(true);
		    }
		});
		sidebarPanel.add(btnTenants);

		JButton btnBilling = new JButton("Billing");
		btnBilling.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnBilling.setBounds(0, 226, 251, 58); // Position on the sidebar
		btnBilling.setBackground(new Color(255, 255, 255)); // White background
		btnBilling.setForeground(new Color(0, 0, 0)); // Black text
		btnBilling.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Close the current frame
		        Billing billingFrame = new Billing(); // Create an instance of the Billing frame
		        billingFrame.setVisible(true); // Open the Billing frame
		    }
		});
		sidebarPanel.add(btnBilling);


        JButton btnReservation = new JButton("Reservations");
        btnReservation.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnReservation.setBounds(0, 283, 251, 58);
        btnReservation.setBackground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(0, 0, 0));
        btnReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current Transactions frame
                reservation reservationFrame = new reservation(); // Open the Customers frame
                reservationFrame.setVisible(true); // Set the Customers frame visible
            }
        });
        sidebarPanel.add(btnReservation);

    	JButton btnReports = new JButton("Reports");
    	btnReports.setFont(new Font("Segoe UI", Font.BOLD, 25));
    	btnReports.setBounds(0, 335, 251, 58); // Adjusting position in the sidebar
    	btnReports.setBackground(new Color(255, 255, 255)); // White background
    	btnReports.setForeground(new Color(0, 0, 0)); // Black text
    	btnReports.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        dispose(); // Close the current frame
    	        Reports reportsFrame = new Reports(); // Create an instance of the Reports frame
    	        reportsFrame.setVisible(true); // Display the Reports frame
    	    }
    	});
    	sidebarPanel.add(btnReports);

    	JButton btnSettings = new JButton("Settings");
        btnSettings.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnSettings.setBounds(0, 390, 251, 58);
        btnSettings.setBackground(new Color(255, 255, 255)); // White background
    	btnSettings.setForeground(new Color(0, 0, 0)); // Black text
    	btnSettings.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        dispose(); // Close the current frame
    	        Settings settingsFrame = new Settings(); // Create an instance of the Reports frame
    	        settingsFrame.setVisible(true); // Display the Reports frame
    	    }
    	});
    	sidebarPanel.add(btnSettings);

        // Add the icon next to the table title
        ImageIcon icon = new ImageIcon("resources/icon.png");

        // Column names for the table
        String[] columnNames = {"ID", "Name", "Date", "Amount", "Due Date", "Status"};

        // Empty data for the table (blank object array)
        Object[][] data = {}; // Empty data array, no rows initially

        // DefaultTableModel to bind data to the JTable
      
        
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(183, 183, 47));
        headerPanel.setBounds(0, 0, 1440, 114);
        contentPane.add(headerPanel);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"));
        lblNewLabel.setBounds(20, 10, 190, 100);
        headerPanel.add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBounds(305, 156, 595, 514);
        contentPane.add(scrollPane);
        
        table = new JTable(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"ID", "Name", "Amount", "Date", "Due"
        	}
        ));
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionBackground(new Color(255, 230, 150));
        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
    }
}
