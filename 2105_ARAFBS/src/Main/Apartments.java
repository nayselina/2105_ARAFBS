package Main;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Apartments extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> unitTypeDropdown;
    private JLabel priceLabel;

    public Apartments() {
        setTitle("Apartment Rental and Billing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 752);

        // Content panel setup
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Left sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 255, 255));
        sidebarPanel.setBounds(0, 124, 251, 785);
        contentPane.add(sidebarPanel);
        sidebarPanel.setLayout(null);

        // Navigation buttons in the sidebar
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


        // Top banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(183, 183, 47));
        bannerPanel.setBounds(0, 0, 1451, 122);
        contentPane.add(bannerPanel);
        bannerPanel.setLayout(null);
        
        
        
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(20, 10, 190, 100);
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"); // Provide the correct path to your logo image
        lblNewLabel.setIcon(logoIcon);
        bannerPanel.add(lblNewLabel);
        

        JLabel lblFindYourNextHome = new JLabel("Find Your Next Home");
        lblFindYourNextHome.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblFindYourNextHome.setBounds(300, 150, 400, 40);
        contentPane.add(lblFindYourNextHome);

        JLabel lblSearchForIdealOne = new JLabel("Search for your ideal one");
        lblSearchForIdealOne.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblSearchForIdealOne.setBounds(300, 190, 400, 30);
        contentPane.add(lblSearchForIdealOne);

        // Dropdown for Unit types
        String[] unitTypes = {"Studio Apartment", "Solo Suite", "Duo Deluxe", "Loft", "Penthouse"};
        unitTypeDropdown = new JComboBox<>(unitTypes);
        unitTypeDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        unitTypeDropdown.setBounds(300, 250, 245, 40);
        contentPane.add(unitTypeDropdown);

        // Price label (auto-updates based on unit type)
        priceLabel = new JLabel("Price: ");
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        priceLabel.setBounds(854, 250, 150, 40);
        contentPane.add(priceLabel);

        // Dropdown for Lease types
        String[] leaseTypes = {"Student Lease", "Fixed Term", "Short Term", "Month to Month", "Corporate Lease"};
        JComboBox<String> leaseTypeDropdown = new JComboBox<>(leaseTypes);
        leaseTypeDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        leaseTypeDropdown.setBounds(593, 250, 200, 40);
        contentPane.add(leaseTypeDropdown);

        // "Done" button to go to Reservation Panel
        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        doneButton.setBounds(1054, 250, 100, 40);
        contentPane.add(doneButton);
        
        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBackground(Color.WHITE);
        panel_3.setBounds(280, 134, 1117, 219);
        contentPane.add(panel_3);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6.setBounds(29, 86, 65, 63);
        panel_3.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\money.png"));
        lblNewLabel_7.setBounds(29, 176, 99, 75);
        panel_3.add(lblNewLabel_7);

        // Set price based on selected unit type
        unitTypeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUnit = (String) unitTypeDropdown.getSelectedItem();
                updatePriceLabel(selectedUnit);
            }
        });

        // Navigate to Reservation Panel on "Done" button click
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pass the selected unit type, price, and lease type to the constructor of the Reservation frame
                String selectedUnitType = (String) unitTypeDropdown.getSelectedItem();
                String selectedPrice = priceLabel.getText().replace("Price: ", "").trim();
                String selectedLeaseType = (String) leaseTypeDropdown.getSelectedItem();

                // Pass these values to the Reservation class constructor
                reservation reservationFrame = new reservation(selectedUnitType, selectedPrice, selectedLeaseType);
                reservationFrame.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    // Helper method to update the price label based on the unit type
    private void updatePriceLabel(String unitType) {
        String price = switch (unitType) {
            case "Studio Apartment" -> "$1,000";
            case "Solo Suite" -> "$1,500";
            case "Duo Deluxe" -> "$2,300";
            case "Loft" -> "$3,000";
            case "Penthouse" -> "$6,000";
            default -> "";
        };
        priceLabel.setText("Price: " + price);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Apartments frame = new Apartments();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}