package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class reservation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel studioLabel, soloLabel, duoLabel, loftLabel, penthouseLabel;

    private String selectedUnitType, selectedPrice, selectedLeaseType;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                reservation frame = new reservation();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public reservation() {
        this("", "", "");  // Default empty values for unit type, price, and lease type
    }

    public reservation(String selectedUnitType, String selectedPrice, String selectedLeaseType) {
        setTitle("Reservation");
        setSize(1440, 752);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.selectedUnitType = selectedUnitType.isEmpty() ? "" : selectedUnitType;  // No default unit type
        this.selectedPrice = selectedPrice.isEmpty() ? "" : selectedPrice;  // No default price
        this.selectedLeaseType = selectedLeaseType.isEmpty() ? "" : selectedLeaseType;  // No default lease type

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar panel with all the buttons
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(255, 255, 255));
        sidebarPanel.setBounds(0, 124, 251, 785);
        contentPane.add(sidebarPanel);
        sidebarPanel.setLayout(null);

        // Homepage button
        JButton btnHomepage = new JButton("Homepage");
        btnHomepage.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnHomepage.setBounds(0, 58, 251, 58);
        btnHomepage.setBackground(new Color(255, 255, 255));
        btnHomepage.setForeground(new Color(0, 0, 0));
        btnHomepage.addActionListener(e -> {
            dispose();
            homepage homepageFrame = new homepage();
            homepageFrame.setVisible(true);
        });
        sidebarPanel.add(btnHomepage);

        // Apartments button
        JButton btnApartments = new JButton("Apartments");
        btnApartments.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnApartments.setBounds(0, 114, 251, 58);
        btnApartments.setBackground(new Color(255, 255, 255));
        btnApartments.setForeground(new Color(0, 0, 0));
        btnApartments.addActionListener(e -> {
            dispose();
            Apartments apartmentsFrame = new Apartments();
            apartmentsFrame.setVisible(true);
        });
        sidebarPanel.add(btnApartments);

        // Tenants button
        JButton btnTenants = new JButton("Tenants");
        btnTenants.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnTenants.setBounds(0, 170, 251, 58);
        btnTenants.setBackground(new Color(255, 255, 255));
        btnTenants.setForeground(new Color(0, 0, 0));
        btnTenants.addActionListener(e -> {
            dispose();
            Tenants tenantsFrame = new Tenants();
            tenantsFrame.setVisible(true);
        });
        sidebarPanel.add(btnTenants);

        // Billing button
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

        // Reservations button (current page)
        JButton btnReservation = new JButton("Reservations");
        btnReservation.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnReservation.setBounds(0, 283, 251, 58);
        btnReservation.setBackground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(0, 0, 0));
        sidebarPanel.add(btnReservation);

        // Reports button
        JButton btnReports = new JButton("Reports");
        btnReports.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnReports.setBounds(0, 335, 251, 58);
        btnReports.setBackground(new Color(255, 255, 255));
        btnReports.setForeground(new Color(0, 0, 0));
        btnReports.addActionListener(e -> {
            dispose();
            Reports reportsFrame = new Reports();
            reportsFrame.setVisible(true);
        });
        sidebarPanel.add(btnReports);

        // Settings button
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


        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(183, 183, 47));
        headerPanel.setBounds(0, 0, 1440, 122);
        contentPane.add(headerPanel);
        headerPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(20, 10, 190, 100);
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"); // Provide the correct path to your logo image
        lblNewLabel.setIcon(logoIcon);
        headerPanel.add(lblNewLabel);

        // Apartment selection panel
        JPanel selectionPanel = new JPanel();
        selectionPanel.setBounds(300, 150, 1100, 500);
        selectionPanel.setBackground(new Color(240, 238, 226));
        selectionPanel.setLayout(null);
        contentPane.add(selectionPanel);

        // Apartment types and images
        studioLabel = createApartmentOption(selectionPanel, "Studio Apartment", "C:/Users/YOJ/git/2105_ARAFBS/src/images/studiooo.jpg", 0, 50);
        soloLabel = createApartmentOption(selectionPanel, "Solo Suite", "C:/Users/YOJ/git/2105_ARAFBS/src/images/solo.jpg", 1, 260);
        duoLabel = createApartmentOption(selectionPanel, "Duo Deluxe", "C:/Users/YOJ/git/2105_ARAFBS/src/images/duo.jpg", 2, 470);
        loftLabel = createApartmentOption(selectionPanel, "Loft", "C:/Users/YOJ/git/2105_ARAFBS/src/images/loft.jpg", 3, 680);
        penthouseLabel = createApartmentOption(selectionPanel, "Penthouse", "C:/Users/YOJ/git/2105_ARAFBS/src/images/penthouse.jpg", 4, 890);

        // Highlight selected unit
        highlightSelectedUnit();

        // Next button setup
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        nextButton.setBounds(700, 670, 150, 50);
        nextButton.setBackground(new Color(128, 128, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> {
            dispose();
            reservationbilling billingFrame = new reservationbilling(selectedUnitType, selectedPrice, selectedLeaseType);
            billingFrame.setVisible(true);
        });
        contentPane.add(nextButton);
    }

    // Method to create apartment options with images and labels
    private JLabel createApartmentOption(JPanel panel, String type, String imagePath, int position, int xPosition) {
        JLabel typeLabel = new JLabel(type);
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        typeLabel.setBounds(xPosition, 300, 200, 30);
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        typeLabel.setForeground(Color.BLACK);
        panel.add(typeLabel);

        // Apartment image
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH);  // Resize image to avoid cropping
        icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(xPosition, 150, 200, 130); // Adjust bounds if needed
        panel.add(imageLabel);

        // Highlight the selected unit
        if (type.equals(selectedUnitType)) {
            typeLabel.setForeground(new Color (183,183,47));  // Highlight the selected unit in green
        }

        return typeLabel;
    }

    // Method to highlight the selected unit
    private void highlightSelectedUnit() {
        if (selectedUnitType.equals("Studio Apartment")) {
            studioLabel.setForeground(new Color (183,183,47));  // Highlight in green
        } else if (selectedUnitType.equals("Solo Suite")) {
            soloLabel.setForeground(new Color (183,183,47));  // Highlight in green
        } else if (selectedUnitType.equals("Duo Deluxe")) {
            duoLabel.setForeground(new Color (183,183,47));  // Highlight in green
        } else if (selectedUnitType.equals("Loft")) {
            loftLabel.setForeground(new Color (183,183,47));  // Highlight in green
        } else if (selectedUnitType.equals("Penthouse")) {
            penthouseLabel.setForeground(new Color (183,183,47));  // Highlight in green
        }
    }
}