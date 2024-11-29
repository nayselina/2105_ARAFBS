package componentsUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

import main.Apartments;
import main.Billing;
import main.Homepage;
import main.Ledger;
import main.Main;
import main.Pay;
import main.Tenants;

public class SidebarPanel extends JPanel {
	
	private JFrame parentFrame;
    private CustomButton homeButton;
    private CustomButton apartmentsButton;
    private CustomButton tenantsButton;
    private CustomButton rentBillsButton;
    private CustomButton payButton;
    private CustomButton ledgerButton;
    private CustomButton logoutButton;

    public SidebarPanel(JFrame parentFrame, String currentFrame) {
        // Set up the sidebar panel
    	this.parentFrame = parentFrame; // Store the parent frame
        this.setBackground(new Color(183, 183, 47)); // Sidebar color
        this.setLayout(null);
        this.setBounds(0, 129, 251, 671); // Set bounds

        // Resize handler
        parentFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = parentFrame.getHeight();
                setBounds(0, 129, 300, frameHeight - 129); // Adjust sidebar height
            }
        });
        
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setBackground(new Color(183, 183, 47));
        topLeftPanel.setBounds(0, 26, 251, 101);
        parentFrame.add(topLeftPanel);
        
        topLeftPanel.setLayout(null);
     // Add logo and label
        JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("/images/AptManager White.png")));
        lblLogo.setBounds(25, 32, 196, 41);
        
        topLeftPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            
                lblLogo.setBounds((getWidth()-196)/2, 32, 196, 41);
              
            }
        });
        
        topLeftPanel.add(lblLogo);
        
        ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/images/AptManager White.png"));
        Image img = logoIcon.getImage();
        Image resizedImage = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(resizedImage));
        
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int headerHeight = 26;
                topLeftPanel.setBounds(0, headerHeight, 300, topLeftPanel.getHeight());
            }
        });

        // Add buttons and define actions
        homeButton = addButton("Home", 0, currentFrame, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState();
            	
                Homepage homepage = new Homepage();
                homepage.setVisible(true);
                homepage.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });
        
        
        apartmentsButton = addButton("Apartments", 64, currentFrame,  new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState();
            	
                Apartments apartments = new Apartments();
                apartments.setVisible(true);
                apartments.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });

        tenantsButton = addButton("Tenants", 125, currentFrame,  new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState();
            	
                Tenants tenants = new Tenants();
                tenants.setVisible(true);
                tenants.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });

        rentBillsButton = addButton("Rent Bills", 186, currentFrame,  new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState(); 
            	
                Billing billing = new Billing();
                billing.setVisible(true);
                billing.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });

        payButton = addButton("Pay", 247, currentFrame,  new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState();
            	
                Pay pay = new Pay();
                pay.setVisible(true);
                pay.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });

        ledgerButton = addButton("Tenant Ledger", 308, currentFrame,  new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState();
            	
                Ledger ledger = new Ledger();
                ledger.setVisible(true);
                ledger.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });

        logoutButton = addButton("Logout", 369, currentFrame,  new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int previousState = parentFrame.getExtendedState(); 
            	
                Main login = new Main();
                login.setVisible(true);
                login.setExtendedState(previousState);
                parentFrame.dispose();
            }
        });
        
        Color originalColor = new Color(183, 183, 47);
        Color hoverColor = originalColor.darker();
        Color clickColor = hoverColor.darker();

        // Update button state based on the current frame
        setActiveButton(currentFrame);
        

        JLabel lblAva = new JLabel("AVAHOME");
        lblAva.setForeground(Color.WHITE);
        lblAva.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAva.setBounds(85, 650, 96, 14);
        this.add(lblAva);

        // Adjust logo and label on resize
        parentFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            
             //   lblLogo.setBounds((getWidth()-196)/2, getHeight() - 90, 196, 41);
                lblAva.setBounds((getWidth()-96)/2, getHeight() - 30, 96, 14);
            }
        });
        
        
        
        
    }

    private CustomButton addButton(String text, int yPos,  String currentFrame, ActionListener actionListener) {
        // Button color scheme
        Color originalColor = new Color(183, 183, 47);
        Color hoverColor = originalColor.darker();
        Color clickColor = hoverColor.darker();
        Color contentColor = new Color(240, 238, 226);
        Color nameColor = Color.BLACK;
        
     // Set the condition based on the current frame
        boolean isCurrentButton = text.equals(currentFrame);

        CustomButton button = new CustomButton(text, hoverColor, originalColor, clickColor, Color.WHITE , () -> !isCurrentButton);
        button.setBounds(0, yPos, 251, 61);
        button.setFont(new Font("Segoe UI", Font.BOLD, 25));
        button.setBackground(originalColor);
        button.setForeground(Color.WHITE);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   //   button.setBorderPainted(false); // Optional: remove border for cleaner look
        button.addActionListener(actionListener);
        
        
        parentFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Adjust the width based on the parent frame's width (optional: modify as needed)
                int frameWidth = parentFrame.getWidth();
                button.setBounds(0, yPos, 300, 61); // Adjust width (frameWidth - 50) to leave padding
            }
        });
        this.add(button);
        return button;
        

    }

    public void setActiveButton(String currentFrame) {
        // Reset all buttons to default appearance
        resetButton(homeButton);
        resetButton(apartmentsButton);
        resetButton(tenantsButton);
        resetButton(rentBillsButton);
        resetButton(payButton);
        resetButton(ledgerButton);
        resetButton(logoutButton);
        
        // Highlight the current button based on the frame name
        switch (currentFrame) {
            case "Home":
                highlightButton(homeButton);
                break;
            case "Apartments":
                highlightButton(apartmentsButton);
                break;
            case "Tenants":
                highlightButton(tenantsButton);
                break;
            case "Rent Bills":
                highlightButton(rentBillsButton);
                break;
            case "Pay":
                highlightButton(payButton);
                break;
            case "Tenant Ledger":
                highlightButton(ledgerButton);
                break;
            case "Login":
                highlightButton(logoutButton);
                break;
        }
       
    }

    private void highlightButton(CustomButton button) {
        button.setBorderPainted(true);
        button.setBorder(new LineBorder(Color.WHITE, 2));
    //    button.setForeground(button.getNameColor());
        button.setBackground(button.getClickColor());
    }

    private void resetButton(CustomButton button) {
        button.setBorderPainted(false);
    //    button.setForeground(Color.WHITE);
        button.setBackground(button.getOriginalColor());
    }   
 // Method to check if the Homepage frame is open and toggle its visibility
    private boolean isFrameOpen(JFrame frame) {
        return frame.isVisible(); // Return true if frame is visible, false otherwise
    }
}
