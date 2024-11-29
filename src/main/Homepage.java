package main;

import java.awt.EventQueue;



import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import componentsUI.CustomButton;
import componentsUI.FrameDragUtility;
import componentsUI.HoverIconButton;
import dbConnection.DatabaseConnection;
import componentsUI.Header;

import javax.swing.border.AbstractBorder;

import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;


public class Homepage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCurrentDate;
    private static boolean isHomepageOpen = false;
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
    int previousState = getExtendedState();
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Homepage frame = new Homepage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Homepage() {
        setTitle("Apartment Rentals and Facilities Billing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 800); // Fixed size
        setLocationRelativeTo(null); // Center the window
        setUndecorated(true); // Remove the border
        
        new FrameDragUtility(this);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 238, 226));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        Header header = new Header(this);
        getContentPane().add(header);

        // Sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(183, 183, 47)); // White background color
        sidebarPanel.setBounds(0, 129, 251, 671); // Adjusted height to fit content
        contentPane.add(sidebarPanel);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                sidebarPanel.setBounds(0, 129, 300, frameHeight - 129); // Adjust sidebarPanel height

            }
        });
        
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setBackground(new Color(183, 183, 47));
        topLeftPanel.setBounds(0, 26, 251, 101);
        contentPane.add(topLeftPanel);
        topLeftPanel.setLayout(null);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Assuming headerPanel has a fixed height (e.g., 26)
                int headerHeight = 26;
                // Adjust topLeftPanel to always be directly below the header
                topLeftPanel.setBounds(0, headerHeight, 300, topLeftPanel.getHeight());
            }
        });
        
        
        
        JLabel lblLogo = new JLabel("");
      	lblLogo.setIcon(new ImageIcon(Homepage.class.getResource("/images/AptManager White.png")));
      	lblLogo.setBounds(27, 32, 196, 41);
      	
      	this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the new height of the sidebarPanel
              //  int panelHeight = sidebarPanel.getHeight();
               int panelWidth = sidebarPanel.getWidth();
          	  
          
                // Update positions dynamically
                lblLogo.setBounds((panelWidth-196)/2, 32, 196, 41);
               
            }
        });
      	topLeftPanel.add(lblLogo);
      	
      	ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/images/AptManager White.png"));
        Image img = logoIcon.getImage();
        Image resizedImage = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(resizedImage));
        
        
        
        Color originalColor = new Color(183, 183, 47);
        Color hoverColor = originalColor.darker();
        Color clickColor = hoverColor.darker();
        Color contentColor = new Color(240, 238, 226);
        Color nameColor = Color.WHITE;
        
         
        
        
        // Buttons in the sidebar
        CustomButton btnHome = new CustomButton(
                "Home", hoverColor, originalColor, clickColor, nameColor, () -> !isHomepageOpen()
                );
        btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnHome.setBounds(0, 3, 251, 61);
        btnHome.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnHome.setBackground(new Color(183, 183, 47));
        btnHome.setForeground(Color.WHITE);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = getExtendedState();

                
                Homepage homepage = new Homepage();
                homepage.setVisible(true);
                homepage.setExtendedState(previousState);
                dispose();
            }
        });
     /*   btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isHomepageOpen()) {
                    btnHome.setBackground(hoverColor); // Hover color
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isHomepageOpen) {
                    btnHome.setBackground(originalColor); // Revert to original
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                btnHome.setBackground(clickColor); // Click color
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!isHomepageOpen()) {
                    btnHome.setBackground(hoverColor); // Hover color after click released
                }
            }
        });  */
        if (!isHomepageOpen()) {
            isHomepageOpen = true; // Set homepage as open
            setVisible(true); // Make the homepage visible
            btnHome.setBackground(clickColor); // Change button color to darker when homepage is open
            btnHome.setBorder(new LineBorder(Color.WHITE, 2));
        } else {
            isHomepageOpen = false; // Set homepage as closed
            setVisible(false); // Hide the homepage
            btnHome.setBackground(originalColor); // Revert to original button color
            btnHome.setBorderPainted(false); // Remove border
        }
        
        sidebarPanel.setLayout(null);
        sidebarPanel.add(btnHome);
        
        CustomButton btnApartments = new CustomButton(
                "Apartments", hoverColor, originalColor, clickColor, nameColor, () -> true
                );
        btnApartments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnApartments.setBorderPainted(false); // Optional: remove border for a cleaner look
        btnApartments.setBounds(0, 64, 251, 61);
        btnApartments.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnApartments.setBackground(new Color(183, 183, 47));
        btnApartments.setForeground(Color.WHITE);
        btnApartments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = getExtendedState();
                
                Apartments apartments = new Apartments();
                apartments.setVisible(true);
                apartments.setExtendedState(previousState);
                dispose();
            }
        });
        
        
                
        sidebarPanel.add(btnApartments); 
        
      
        CustomButton btnTenants = new CustomButton(
                "Tenants", hoverColor, originalColor, clickColor, nameColor, () -> true
                );
        btnTenants.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTenants.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int previousState = getExtendedState();
        		
        		Tenants tenants = new Tenants();
                tenants.setVisible(true);
                tenants.setExtendedState(previousState);
                dispose();
        	}
        });
        btnTenants.setBorderPainted(false); // Optional: remove border for a cleaner look
        btnTenants.setBounds(0, 125, 251, 61);
        btnTenants.setForeground(Color.WHITE);
        btnTenants.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnTenants.setBackground(new Color(183, 183, 47));
        sidebarPanel.add(btnTenants);
        
        
    	
    	
    	CustomButton btnBills = new CustomButton(
    			"Rent Bills", hoverColor, originalColor, clickColor, nameColor, () -> true
                );
    	btnBills.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	btnBills.setBorderPainted(false); // Optional: remove border for a cleaner look
    	btnBills.setBounds(0, 186, 251, 61);
    	btnBills.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			int previousState = getExtendedState();
    			
    			Billing billing = new Billing();
    			billing.setVisible(true);
    			billing.setExtendedState(previousState);
                dispose();
    		}
    	});
    	btnBills.setForeground(Color.WHITE);
    	btnBills.setFont(new Font("Segoe UI", Font.BOLD, 25));
    	btnBills.setBackground(new Color(183, 183, 47));
    	sidebarPanel.add(btnBills);
    	
    	
    	
    	CustomButton btnPayBill = new CustomButton(
    			"Pay", hoverColor, originalColor, clickColor, nameColor, () -> true
                );
    	btnPayBill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	btnPayBill.setBorderPainted(false); // Optional: remove border for a cleaner look
    	btnPayBill.setBounds(0, 247, 251, 61);
    	btnPayBill.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnPayBill.setBackground(new Color(183, 183, 47));
    	btnPayBill.setForeground(Color.WHITE);
    	btnPayBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = getExtendedState();
            	
            	Pay pay = new Pay();
    			pay.setVisible(true);
    			pay.setExtendedState(previousState);
                dispose();
               
            }
        });
    	        sidebarPanel.add(btnPayBill);
    	
    	
    	CustomButton btnTenantLedger = new CustomButton(
    			"Tenant Ledger", hoverColor, originalColor, clickColor, nameColor, () -> true
                );
    	btnTenantLedger.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	btnTenantLedger.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			int previousState = getExtendedState();
    			
    			Ledger ledger = new Ledger();
    			ledger.setVisible(true);
    			ledger.setExtendedState(previousState);
                dispose();
    		}
    	});
    	btnTenantLedger.setBorderPainted(false); // Optional: remove border for a cleaner look
    	btnTenantLedger.setBounds(0, 308, 251, 61);
    	btnTenantLedger.setForeground(Color.WHITE);
    	btnTenantLedger.setFont(new Font("Segoe UI", Font.BOLD, 25));
    	btnTenantLedger.setBackground(new Color(183, 183, 47));
    	sidebarPanel.add(btnTenantLedger);
    	
    	CustomButton btnLogout = new CustomButton(
    			"Logout", hoverColor, originalColor, clickColor, nameColor, () -> true
                );
    	btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {		
    			
    			int previousState = getExtendedState();
    			
    			Main login = new Main();
                login.setVisible(true);
                login.setExtendedState(previousState);
                dispose();
    		}
    	});
    	btnLogout.setBorderPainted(false); // Optional: remove border for a cleaner look
    	btnLogout.setBounds(0, 369, 251, 61);
    	btnLogout.setForeground(Color.WHITE);
    	btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 25));
    	btnLogout.setBackground(new Color(183, 183, 47));
    	sidebarPanel.add(btnLogout);
        
      	
          JLabel lblAva = new JLabel("AVAHOME");
          lblAva.setForeground(Color.WHITE);
          lblAva.setFont(new Font("Segoe UI", Font.BOLD, 14));
          lblAva.setBounds(89, 650, 76, 14);
          sidebarPanel.add(lblAva);
          
          this.addComponentListener(new ComponentAdapter() {
              @Override
              public void componentResized(ComponentEvent e) {
                  // Get the new height of the sidebarPanel
               	 int panelHeight = sidebarPanel.getHeight();
                 int panelWidth = sidebarPanel.getWidth();
            	  
            
                  // Update positions dynamically
              //    lblLogo.setBounds((panelWidth-196)/2, panelHeight - 90, 196, 41);
                  lblAva.setBounds((panelWidth-96)/2, panelHeight - 30, 96, 14);
                 
              }
          });
          
          this.addComponentListener(new ComponentAdapter() {
              @Override
              public void componentResized(ComponentEvent e) {
              	btnHome.setBounds(0, 3, 300, 61);
              	btnApartments.setBounds(0, 64, 300, 61);
              	btnTenants.setBounds(0, 125, 300, 61);
              	btnBills.setBounds(0, 186, 300, 61);
              	btnPayBill.setBounds(0, 247, 300, 61);
              	btnTenantLedger.setBounds(0, 308, 300, 61);
              	btnLogout.setBounds(0, 369, 300, 61);
              }
          });


        // Main Panel for content (Available Apartments, Recent Paid Status, Calendar, and Payment)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(null);
      //  mainPanel.setBounds(318, 124, 966, 502); // Decrease height to fit Record of Payment
        mainPanel.setBounds(301, 76, 949, 724);
        mainPanel.setLayout(null); // Absolute positioning
        contentPane.add(mainPanel);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                int frameWidth = getWidth();
                mainPanel.setBounds(351, 76, frameWidth - 401, frameHeight - 76); // Adjust sidebarPanel height

            }
        });

        // Available Apartments Section (on the left)
        JPanel availableApartmentsPanel = new JPanel();
        availableApartmentsPanel.setBackground(Color.WHITE);
        availableApartmentsPanel.setBounds(0, 222, 475, 256); // Left side of the top section
        mainPanel.add(availableApartmentsPanel);
        availableApartmentsPanel.setLayout(null);
        
        
        DatabaseConnection db = DatabaseConnection.getInstance();
        int totalApart = db.getTotalApartments();
        
        JLabel lblTotalApartments = new JLabel("Total Apartment Units:");
        lblTotalApartments.setBounds(23, 40, 351, 40);
        availableApartmentsPanel.add(lblTotalApartments);
        lblTotalApartments.setFont(new Font("Segoe UI", Font.BOLD, 30));
        
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(368, -12, 121, 133);
        availableApartmentsPanel.add(lblTotal);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 80));
        lblTotal.setText("" + totalApart);

        // Recent Paid Status Section (on the right)
        JPanel recentPaidPanel = new JPanel();
        recentPaidPanel.setBackground(Color.WHITE);
        recentPaidPanel.setBounds(475, 222, 474, 256); // Right side of the top section
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
        currentDatePanel.setBounds(0, 478, 475, 246); // Left side of the bottom section
        mainPanel.add(currentDatePanel);
        currentDatePanel.setLayout(null);

        lblCurrentDate = new JLabel("Current Date: " + getCurrentDate());
        lblCurrentDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCurrentDate.setBounds(20, 20, 300, 30);
        currentDatePanel.add(lblCurrentDate);

        // Record of Payment Section (on the right)
        JPanel paymentRecordPanel = new JPanel();
        paymentRecordPanel.setBackground(Color.WHITE);
        paymentRecordPanel.setBounds(475, 478, 474, 246); // Right side of the bottom section
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
        
        
       
        
                JLabel lblApartmentsTitle = new JLabel("Available Apartments");
                lblApartmentsTitle.setBounds(530, 24, 351, 40);
                mainPanel.add(lblApartmentsTitle);
                lblApartmentsTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
                
                        lblOccupied = new JLabel("Occupied: " + occupiedApartments + " / " + totalApartments);
                        lblOccupied.setBounds(511, 62, 400, 30);
                        mainPanel.add(lblOccupied);
                        lblOccupied.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                        
                                lblVacant = new JLabel("Vacant: " + vacantApartments + " / " + totalApartments);
                                lblVacant.setBounds(511, 96, 400, 30);
                                mainPanel.add(lblVacant);
                                lblVacant.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                                
                                        progressBarOccupied = new JProgressBar();
                                        progressBarOccupied.setBounds(511, 160, 400, 30);
                                        mainPanel.add(progressBarOccupied);
                                        progressBarOccupied.setForeground(new Color(0, 128, 0)); // Green for occupied
                                        progressBarOccupied.setValue((occupiedApartments * 100) / totalApartments);
                                        progressBarOccupied.setStringPainted(true);
        
      

        
        
        
        

        // Set up a timer to update the date every day
    /*    Timer timer = new Timer(1000 * 60 * 60 * 24, e -> {
            lblCurrentDate.setText("Current Date: " + getCurrentDate());
        });
        timer.start();   */
        
        

       
    }
    
    public boolean isHomepageOpen() {
    	return this.isVisible(); 
    }
    public boolean isApartmentsOpen() {
        // Implement logic to check if the homepage is open
        // For example, this could return true if the Homepage class instance is currently visible
    	return this.isVisible(); // Returns true if the frame is visible, false otherwise
    	

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
