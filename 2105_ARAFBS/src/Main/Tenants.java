package Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Tenants extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Tenants frame = new Tenants();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Tenants() {
        setTitle("Tenant Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 752); // Set size of the window
        setLocationRelativeTo(null); // Center the window

        // Initialize the content panel
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Sidebar panel 
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 255, 255)); // White background color
        sidebarPanel.setBounds(0, 122, 251, 602); // Adjusted height to fit content
        sidebarPanel.setLayout(null);
        contentPane.add(sidebarPanel);

        // Buttons in the sidebar
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
                Settings settingsFrame = new Settings(); // Create an instance of the Settings frame
                settingsFrame.setVisible(true); // Display the Settings frame
            }
        });
        sidebarPanel.add(btnSettings);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(183, 183, 47)); // Set header color
        headerPanel.setBounds(0, 0, 1451, 122); // Set position and size for header
        contentPane.add(headerPanel);
        headerPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(20, 10, 190, 100);
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"); // Provide the correct path to your logo image
        lblNewLabel.setIcon(logoIcon);
        headerPanel.add(lblNewLabel);

        // Create the table data and model
        String[] columnNames = {"ID", "Name", "Contact Number", "Email", "Unit", "Other Info"};
        Object[][] data = {
            {"", "", "", "", "", ""},
            {"", "", "", "", "", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(new DefaultTableModel(
        	new Object[][] {
        		{"", "", "", "", ""},
        		{"", "", "", "", ""},
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
        		"ID", "Name", "Contact Number", "Email", "Unit"
        	}
        ));

        // Table Style
        table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        table.setRowHeight(40);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(255, 230, 150));
        table.setSelectionForeground(new Color(0, 0, 0));

        // Alternating Row Colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                }
                setHorizontalAlignment(SwingConstants.CENTER); // Center align text
                return c;
            }
        });

        // Table Header Style
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBackground(new Color(247, 247, 231));
        header.setForeground(Color.black);
        header.setReorderingAllowed(false);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBounds(295, 163, 1082, 514);
    }
    public class DatabaseConnection {
        public static void main(String[] args) {
            // Database credentials
            String url = "jdbc:mysql://localhost:3306/rental_system"; // Replace with your database name
            String username = "root"; // Replace with your MySQL username
            String password = ""; // Replace with your MySQL password

            try {
                // Load MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish connection
                Connection connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected to the database!");

                // Create a statement
                Statement statement = connection.createStatement();

                // Execute a query
                String query = "SELECT * FROM tenants"; // Replace with your table
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result
                while (resultSet.next()) {
                    System.out.println("Tenant Name: " + resultSet.getString("name"));
                }

                // Close the connection
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
