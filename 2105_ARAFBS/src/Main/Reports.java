package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Reports extends JFrame {

    private static final long serialVersionUID = 1L;

    // Panel components
    private JPanel contentPane;
    private JTextField tenantNameField, apartmentField, nameField, amountField, dueDateField, billingIDField, dateField;
    private JComboBox<String> paymentStatusDropdown;
    private DefaultTableModel tableModel;
    private int currentPage = 0; // Track the current page
    private int rowsPerPage = 5; // Number of rows per page

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Reports frame = new Reports();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Reports() {
        setTitle("Reports - Tenant Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 800);
        setLocationRelativeTo(null);

        // Main content pane
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Sidebar Panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.WHITE);
        sidebarPanel.setBounds(0, 124, 251, 600);
        sidebarPanel.setLayout(null);
        contentPane.add(sidebarPanel);

        // Sidebar buttons
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

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(183, 183, 47));
        headerPanel.setBounds(0, 0, 1440, 120);
        headerPanel.setLayout(null);
        contentPane.add(headerPanel);

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png");
        logoLabel.setIcon(logoIcon);
        logoLabel.setBounds(27, 11, 191, 100);
        headerPanel.add(logoLabel);

        // Filters Panel
        JPanel filtersPanel = new JPanel();
        filtersPanel.setBounds(260, 140, 1150, 63);
        filtersPanel.setBackground(new Color(247, 247, 231));
        filtersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPane.add(filtersPanel);

        JLabel filterTitle = new JLabel("Filter By:");
        filterTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        filtersPanel.add(filterTitle);

        // Tenant Name Filter
        filtersPanel.add(new JLabel("Tenant Name:"));
        tenantNameField = new JTextField(15);
        filtersPanel.add(tenantNameField);

        // Apartment Number Filter
        filtersPanel.add(new JLabel("Apartment No:"));
        apartmentField = new JTextField(10);
        filtersPanel.add(apartmentField);

        // Payment Status Filter
        filtersPanel.add(new JLabel("Payment Status:"));
        paymentStatusDropdown = new JComboBox<>(new String[]{"All", "Paid", "Pending"});
        filtersPanel.add(paymentStatusDropdown);

        JButton filterButton = new JButton("Filter");
        filterButton.setBackground(new Color(183, 183, 47));
        filterButton.setForeground(Color.WHITE);
        filtersPanel.add(filterButton);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(260, 210, 1000, 300);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPane.add(tablePanel);

        // Table
        String[] columnNames = {"Tenant Name", "Apartment Number", "Rent Amount", "Payment Status"};
        Object[][] data = new Object[5][4]; // Initialize with blank data

        tableModel = new DefaultTableModel(data, columnNames);
        JTable reportTable = new JTable(tableModel);

        // Table Styling
        reportTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        reportTable.setRowHeight(30);
        reportTable.setGridColor(new Color(200, 200, 200));
        reportTable.setShowGrid(true);
        reportTable.setSelectionBackground(new Color(183, 183, 47));
        reportTable.setSelectionForeground(Color.WHITE);

        JTableHeader tableHeader = reportTable.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableHeader.setBackground(new Color(183, 183, 47));
        tableHeader.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(reportTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Billing Info Panel
        JPanel billingInfoPanel = new JPanel();
        billingInfoPanel.setBounds(260, 520, 1000, 120);
        billingInfoPanel.setBackground(Color.WHITE);
        billingInfoPanel.setLayout(new GridLayout(2, 6, 20, 10));
        billingInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPane.add(billingInfoPanel);

        // Add fields to Billing Info Panel
        addBillingInfoField(billingInfoPanel, "Billing ID:", billingIDField = new JTextField());
        addBillingInfoField(billingInfoPanel, "Name:", nameField = new JTextField());
        addBillingInfoField(billingInfoPanel, "Date:", dateField = new JTextField());
        addBillingInfoField(billingInfoPanel, "Amount:", amountField = new JTextField());
        addBillingInfoField(billingInfoPanel, "Due Date:", dueDateField = new JTextField());
        addBillingInfoField(billingInfoPanel, "Status:", paymentStatusDropdown = new JComboBox<>(new String[]{"Paid", "Unpaid", "Overdue"}));
    }

    private void addBillingInfoField(JPanel panel, String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(label);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(field);
    }
}
