package Main;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;


public class homepage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCurrentDate;
    private JLabel lblOccupied;
    private JLabel lblVacant;
    private JLabel lblPaidAmount;
    private JLabel lblUnpaidAmount;
    private JProgressBar progressBarOccupied;
    private JProgressBar progressBarPaid;
    private JProgressBar progressBarUnpaid;
    private JLabel lblLastPayment;
    private JLabel lblNextPaymentDue;
    private JLabel lblLastPaymentDate;

    // Data models (simple versions for demo purposes)
    private int totalApartments = 10;
    private int occupiedApartments = 5;
    private int vacantApartments = totalApartments - occupiedApartments;
    private int totalPaid = 5000;
    private int totalUnpaid = 5000;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    homepage frame = new homepage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public homepage() {
        setTitle("Apartment Rental and Billing System");
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
        
     // Assuming you have a headerPanel where the logo is to be placed
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(20, 10, 190, 100); // Adjust these bounds according to your logo size and header layout
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

        // Main Panel for content (Available Apartments, Recent Paid Status, Calendar, and Payment)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(261, 124, 1152, 502); // Decrease height to fit Record of Payment
        contentPane.add(mainPanel);

        // Available Apartments Section (on the left)
        JPanel availableApartmentsPanel = new JPanel();
        availableApartmentsPanel.setBackground(Color.WHITE);
        availableApartmentsPanel.setBounds(0, 0, 487, 256); // Left side of the top section
        mainPanel.add(availableApartmentsPanel);
        availableApartmentsPanel.setLayout(null);

        JLabel lblApartmentsTitle = new JLabel("Available Apartments");
        lblApartmentsTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblApartmentsTitle.setBounds(20, 20, 351, 40);
        availableApartmentsPanel.add(lblApartmentsTitle);

        lblOccupied = new JLabel("Occupied: " + occupiedApartments + " / " + totalApartments);
        lblOccupied.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblOccupied.setBounds(20, 70, 400, 30);
        availableApartmentsPanel.add(lblOccupied);

        lblVacant = new JLabel("Vacant: " + vacantApartments + " / " + totalApartments);
        lblVacant.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblVacant.setBounds(20, 110, 400, 30);
        availableApartmentsPanel.add(lblVacant);

        progressBarOccupied = new JProgressBar();
        progressBarOccupied.setForeground(new Color(0, 128, 0)); // Green for occupied
        progressBarOccupied.setValue((occupiedApartments * 100) / totalApartments);
        progressBarOccupied.setStringPainted(true);
        progressBarOccupied.setBounds(20, 150, 400, 30);
        availableApartmentsPanel.add(progressBarOccupied);

        // Recent Paid Status Section (on the right)
        JPanel recentPaidPanel = new JPanel();
        recentPaidPanel.setBackground(Color.WHITE);
        recentPaidPanel.setBounds(486, 0, 666, 256); // Right side of the top section
        mainPanel.add(recentPaidPanel);
        recentPaidPanel.setLayout(null);

        JLabel lblPaidStatusTitle = new JLabel("Recent Paid Status");
        lblPaidStatusTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblPaidStatusTitle.setBounds(20, 20, 300, 40);
        recentPaidPanel.add(lblPaidStatusTitle);

        progressBarPaid = new JProgressBar();
        progressBarPaid.setForeground(new Color(0, 128, 0)); // Green for paid
        progressBarPaid.setValue((totalPaid * 100) / (totalPaid + totalUnpaid)); // Payment status
        progressBarPaid.setStringPainted(true);
        progressBarPaid.setBounds(20, 80, 400, 30);
        recentPaidPanel.add(progressBarPaid);

        lblPaidAmount = new JLabel("Paid: P" + totalPaid);
        lblPaidAmount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPaidAmount.setBounds(20, 120, 150, 30);
        recentPaidPanel.add(lblPaidAmount);

        progressBarUnpaid = new JProgressBar();
        progressBarUnpaid.setForeground(new Color(255, 0, 0)); // Red for unpaid
        progressBarUnpaid.setValue((totalUnpaid * 100) / (totalPaid + totalUnpaid));
        progressBarUnpaid.setStringPainted(true);
        progressBarUnpaid.setBounds(20, 150, 400, 30);
        recentPaidPanel.add(progressBarUnpaid);

        lblUnpaidAmount = new JLabel("Unpaid: P" + totalUnpaid);
        lblUnpaidAmount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblUnpaidAmount.setBounds(20, 180, 150, 30);
        recentPaidPanel.add(lblUnpaidAmount);

        // Current Date and Payment Record Section (on the bottom)
        JPanel currentDatePanel = new JPanel();
        currentDatePanel.setBackground(Color.WHITE);
        currentDatePanel.setBounds(0, 256, 487, 246); // Left side of the bottom section
        mainPanel.add(currentDatePanel);
        currentDatePanel.setLayout(null);

        lblCurrentDate = new JLabel("Current Date: " + getCurrentDate());
        lblCurrentDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCurrentDate.setBounds(20, 20, 300, 30);
        currentDatePanel.add(lblCurrentDate);

        // Record of Payment Section (on the right)
        JPanel paymentRecordPanel = new JPanel();
        paymentRecordPanel.setBackground(Color.WHITE);
        paymentRecordPanel.setBounds(487, 256, 665, 246); // Right side of the bottom section
        mainPanel.add(paymentRecordPanel);
        paymentRecordPanel.setLayout(null);

        JLabel lblPaymentRecordTitle = new JLabel("Record of Payment");
        lblPaymentRecordTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblPaymentRecordTitle.setBounds(20, 20, 350, 40);
        paymentRecordPanel.add(lblPaymentRecordTitle);

        lblLastPayment = new JLabel("Last Payment: P5,000");
        lblLastPayment.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblLastPayment.setBounds(20, 70, 350, 30);
        paymentRecordPanel.add(lblLastPayment);

        lblNextPaymentDue = new JLabel("Next Payment Due: P5,000");
        lblNextPaymentDue.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblNextPaymentDue.setBounds(20, 110, 350, 30);
        paymentRecordPanel.add(lblNextPaymentDue);

        lblLastPaymentDate = new JLabel("Paid on: 2024-11-15");
        lblLastPaymentDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblLastPaymentDate.setBounds(20, 150, 350, 30);
        paymentRecordPanel.add(lblLastPaymentDate);

        // Set up a timer to update the date every day
        Timer timer = new Timer(1000 * 60 * 60 * 24, e -> {
            lblCurrentDate.setText("Current Date: " + getCurrentDate());
        });
        timer.start();

        // Event Handlers for Buttons
        btnReservation.addActionListener(e -> {
            // Update data models based on new reservation
            occupiedApartments += 1;
            vacantApartments -= 1;
            updateAvailableApartmentsUI();
        });

        btnTenants.addActionListener(e -> {
            // Update tenant data
        });

       
       
    }

    // Method to get the current date
    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    // Method to update the UI with the latest available apartments info
    private void updateAvailableApartmentsUI() {
        lblOccupied.setText("Occupied: " + occupiedApartments + " / " + totalApartments);
        lblVacant.setText("Vacant: " + vacantApartments + " / " + totalApartments);
        progressBarOccupied.setValue((occupiedApartments * 100) / totalApartments);
    }

    // Method to update the UI with the latest paid status info
    private void updateRecentPaidStatusUI() {
        progressBarPaid.setValue((totalPaid * 100) / (totalPaid + totalUnpaid));
        lblPaidAmount.setText("Paid: P" + totalPaid);
        progressBarUnpaid.setValue((totalUnpaid * 100) / (totalPaid + totalUnpaid));
        lblUnpaidAmount.setText("Unpaid: P" + totalUnpaid);
    }
}
