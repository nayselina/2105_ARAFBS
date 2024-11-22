package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class reservationbilling extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Labels to display the selected unit details
    private JLabel unitTypeLabel;
    private JLabel priceLabel;
    private JLabel leaseTypeLabel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // For testing purposes, let's pass mock data
                    reservationbilling frame = new reservationbilling("Studio Apartment", "$1,000", "Fixed Term");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame with the passed unit details.
     */
    public reservationbilling(String selectedUnitType, String price, String leaseType) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 752);

        // Content panel setup
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Debugging: Print the values to check if they are correct
        System.out.println("Selected Unit Type: " + selectedUnitType);
        System.out.println("Price: " + price);
        System.out.println("Lease Type: " + leaseType);

        // Null check for price
        if (price == null || price.isEmpty()) {
            System.out.println("Price is null or empty.");
            price = "Not Available";  // Default text if price is missing
        }

        // Null check for leaseType
        if (leaseType == null || leaseType.isEmpty()) {
            System.out.println("Lease Type is null or empty.");
            leaseType = "Not Available";  // Default text if lease type is missing
        }

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
                reservation reservationFrame = new reservation(); // Open the Reservations frame
                reservationFrame.setVisible(true); // Set the Reservations frame visible
            }
        });
        sidebarPanel.add(btnReservation);
        
        JButton btnCompleteReservation = new JButton("Complete Reservation");
        btnCompleteReservation.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCompleteReservation.setBounds(1001, 600, 251, 40); // Adjust the position as needed
        btnCompleteReservation.setBackground(new Color(183, 183, 47)); // Optional color styling
        btnCompleteReservation.setForeground(new Color(0, 0, 0));

        // ActionListener for the button
        btnCompleteReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the message upon completion
                JOptionPane.showMessageDialog(null, "Reservation Completed!");
            }
        });

        contentPane.add(btnCompleteReservation); // Add the button to the content pane

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
        bannerPanel.setBounds(0, 0, 1440, 122);
        contentPane.add(bannerPanel);
        bannerPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(20, 10, 190, 100);
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"); // Provide the correct path to your logo image
        lblNewLabel.setIcon(logoIcon);
        bannerPanel.add(lblNewLabel);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBackground(Color.WHITE);
        panel_3.setBounds(285, 150, 564, 255);
        contentPane.add(panel_3);

        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6.setBounds(29, 86, 65, 63);
        panel_3.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\money.png"));
        lblNewLabel_7.setBounds(29, 176, 99, 75);
        panel_3.add(lblNewLabel_7);

        // Display selected lease type
        leaseTypeLabel = new JLabel("Lease Type: " + leaseType);
        leaseTypeLabel.setBounds(20, 97, 340, 40);
        panel_3.add(leaseTypeLabel);
        leaseTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Display selected price
        priceLabel = new JLabel("Price: " + price);
        priceLabel.setBounds(20, 134, 168, 40);
        panel_3.add(priceLabel);
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // Display selected unit type
        unitTypeLabel = new JLabel("Unit Type: " + selectedUnitType);
        unitTypeLabel.setBounds(20, 58, 217, 40);
        panel_3.add(unitTypeLabel);
        unitTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel lblUnitDetailsPreview = new JLabel("Unit Details Preview");
        lblUnitDetailsPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnitDetailsPreview.setForeground(Color.BLACK);
        lblUnitDetailsPreview.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblUnitDetailsPreview.setBounds(20, 11, 200, 30);
        panel_3.add(lblUnitDetailsPreview);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setLayout(null);
        panel_3_1.setBackground(Color.WHITE);
        panel_3_1.setBounds(285, 426, 564, 255);
        contentPane.add(panel_3_1);

        JLabel lblNewLabel_6_1 = new JLabel("Name: ");
        lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6_1.setBounds(20, 52, 60, 39);
        panel_3_1.add(lblNewLabel_6_1);

        JLabel lblNewLabel_7_1 = new JLabel("");
        lblNewLabel_7_1.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\money.png"));
        lblNewLabel_7_1.setBounds(313, 16, 99, 75);
        panel_3_1.add(lblNewLabel_7_1);

        textField_1 = new JTextField();
        textField_1.setBounds(20, 97, 254, 36);
        textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel_3_1.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Contact Number:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_8.setBounds(20, 134, 254, 40);
        panel_3_1.add(lblNewLabel_8);

        textField_2 = new JTextField();
        textField_2.setBounds(20, 176, 254, 36);
        textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel_3_1.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblEmail = new JLabel("Email Address:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(300, 51, 254, 40);
        panel_3_1.add(lblEmail);

        textField_3 = new JTextField();
        textField_3.setBounds(20, 257, 254, 36);
        textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel_3_1.add(textField_3);
        textField_3.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField_6.setColumns(10);
        textField_6.setBounds(284, 97, 254, 36);
        panel_3_1.add(textField_6);
        
        JLabel lblPersonalInformation = new JLabel("Personal Information");
        lblPersonalInformation.setHorizontalAlignment(SwingConstants.CENTER);
        lblPersonalInformation.setForeground(Color.BLACK);
        lblPersonalInformation.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPersonalInformation.setBounds(20, 16, 200, 30);
        panel_3_1.add(lblPersonalInformation);

        JLabel lblNewLabel_10 = new JLabel("Payment Information");
        lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabel_10.setBounds(20, 300, 187, 27);
        contentPane.add(lblNewLabel_10);

        // Price and Reservation Fee Section
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null);
        totalPanel.setBackground(Color.WHITE);
        totalPanel.setBounds(1001, 150, 413, 437);
        contentPane.add(totalPanel);

        // Price label in the total panel
        JLabel priceDisplayLabel = new JLabel("Price: " + price);
        priceDisplayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        priceDisplayLabel.setBounds(20, 67, 200, 30);
        totalPanel.add(priceDisplayLabel);

        // Reservation fee label in the total panel
        JLabel reservationFeeLabel = new JLabel("Reservation Fee: $100");
        reservationFeeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        reservationFeeLabel.setBounds(20, 95, 200, 30);
        totalPanel.add(reservationFeeLabel);

        // Total cost label in the total panel (calculated dynamically)
        double priceValue = Double.parseDouble(price.replace("$", "").replace(",", ""));
        double reservationFee = 100.0;
        double totalCost = priceValue + reservationFee;

        JLabel totalCostLabel = new JLabel("Total: $" + String.format("%.2f", totalCost));
        totalCostLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        totalCostLabel.setForeground(new Color(183, 183, 47)); // Highlighted color for total
        totalCostLabel.setBounds(20, 124, 200, 30);
        totalPanel.add(totalCostLabel);
        
        JLabel lblPayment = new JLabel("Payment");
        lblPayment.setBounds(0, 11, 153, 30);
        totalPanel.add(lblPayment);
        lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
        lblPayment.setForeground(Color.BLACK);
        lblPayment.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        JLabel lblNewLabel_6_1_1 = new JLabel("----------------------------------------------------------------------------");
        lblNewLabel_6_1_1.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1_1.setBounds(20, 148, 383, 39);
        totalPanel.add(lblNewLabel_6_1_1);
        
        JLabel lblPaymentMethod = new JLabel("Payment Method");
        lblPaymentMethod.setHorizontalAlignment(SwingConstants.CENTER);
        lblPaymentMethod.setForeground(Color.BLACK);
        lblPaymentMethod.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPaymentMethod.setBounds(0, 174, 200, 30);
        totalPanel.add(lblPaymentMethod);
        
        JLabel lblNewLabel_6_1_2 = new JLabel("Card Number:");
        lblNewLabel_6_1_2.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1_2.setBounds(20, 266, 101, 39);
        totalPanel.add(lblNewLabel_6_1_2);
        
        JLabel lblNewLabel_6_1_3 = new JLabel("Card Holder:");
        lblNewLabel_6_1_3.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1_3.setBounds(20, 227, 101, 39);
        totalPanel.add(lblNewLabel_6_1_3);
        
        JLabel lblNewLabel_6_1_3_1 = new JLabel("Security Code:");
        lblNewLabel_6_1_3_1.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1_3_1.setBounds(20, 314, 101, 39);
        totalPanel.add(lblNewLabel_6_1_3_1);
        
        JLabel lblNewLabel_6_1_3_1_1 = new JLabel("Exp:");
        lblNewLabel_6_1_3_1_1.setIcon(new ImageIcon("C:\\Users\\63906\\Downloads\\pay.png"));
        lblNewLabel_6_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1_3_1_1.setBounds(186, 314, 39, 39);
        totalPanel.add(lblNewLabel_6_1_3_1_1);
        
        textField_7 = new JTextField();
        textField_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField_7.setColumns(10);
        textField_7.setBounds(119, 219, 254, 36);
        totalPanel.add(textField_7);
        
        textField_8 = new JTextField();
        textField_8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField_8.setColumns(10);
        textField_8.setBounds(119, 269, 254, 36);
        totalPanel.add(textField_8);
        
        textField_9 = new JTextField();
        textField_9.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField_9.setColumns(10);
        textField_9.setBounds(119, 316, 57, 36);
        totalPanel.add(textField_9);
        
        textField_10 = new JTextField();
        textField_10.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField_10.setColumns(10);
        textField_10.setBounds(218, 314, 57, 36);
        totalPanel.add(textField_10);
        
     // Add this code in the reservationbilling class where you define the Complete Reservation button
        btnCompleteReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current reservationbilling frame
                dispose();

                // Open the Tenants frame
                Tenants tenantsFrame = new Tenants();
                tenantsFrame.setVisible(true);
            }
        });

    }
}