package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Settings extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public Settings() {
        setTitle("Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 752); // Fixed size

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(183, 183, 47)); // Set header color
        headerPanel.setBounds(0, 0, 1440, 114); // Full width, specific height
        contentPane.add(headerPanel);
        headerPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(20, 10, 190, 100);
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"); // Provide the correct path to your logo image
        lblNewLabel.setIcon(logoIcon);
        headerPanel.add(lblNewLabel);

        // Sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 255, 255)); // White background color
        sidebarPanel.setBounds(0, 124, 251, 600); // Adjusted height to fit content
        contentPane.add(sidebarPanel);
        sidebarPanel.setLayout(null);

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
    	        Settings settingsFrame = new Settings(); // Create an instance of the Reports frame
    	        settingsFrame.setVisible(true); // Display the Reports frame
    	    }
    	});
    	sidebarPanel.add(btnSettings);

        // Logout Button
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnLogout.setBounds(0, 448, 251, 58);
        btnLogout.setBackground(new Color(255, 255, 255));
        btnLogout.setForeground(new Color(255, 0, 0)); // Red color for logout
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current settings window
                Main loginFrame = new Main(); // Go to the main class (login screen)
                loginFrame.setVisible(true);
            }
        });
        sidebarPanel.add(btnLogout);
    
 // In your Settings class

    JTextArea aboutTextArea = new JTextArea();
    aboutTextArea.setBounds(261, 154, 1153, 500);
    contentPane.add(aboutTextArea);
    aboutTextArea.setBackground(new Color(255, 255, 255));
    aboutTextArea.setText("\n Welcome to the Apartment Rental and Billing System (ARAFBS).\n\n" +
    		"\n"+
        "This system is designed to streamline the management of apartment rentals, reservations, and billing for both property managers and tenants. It provides an easy-to-use interface that\n"
        + " allows users to browse apartment types, make reservations, manage tenant information, and process payments efficiently.\n\n" +
        "Key Features:\n" +
        "- Apartment Selection: Tenants can browse and select from various apartment types including Studio Apartments, Solo Suites, Duo Deluxe units, Lofts, and Penthouses.\n" +
        "\n"+
        "- Reservation Management: Property managers can handle tenant reservations, customize lease types, and set pricing.\n" +
        "\n"+
        "- Billing and Payment: Integrated payment system with transparent billing, including reservation fees and apartment-specific charges.\n" +
        "\n"+
        "- Tenant Management: Easy access to tenant information, including contact details and unit type selection.\n" +
        "\n"+
        "- User-Friendly Interface: A clean, simple, and intuitive interface to ensure an efficient experience for both administrators and tenants.\n\n" +
        "\n"+
        "This system helps property managers save time by automating routine tasks, while also providing tenants with a seamless reservation and payment process.\n\n" +
        "\n"+
        "For more information or assistance, please contact our support team.");
    
        aboutTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        aboutTextArea.setEditable(false);
}
}
