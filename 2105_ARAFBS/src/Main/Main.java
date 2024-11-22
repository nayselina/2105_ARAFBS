package Main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

// Import your Homepage class
 // Ensure this matches the actual package name of your Homepage class

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField username;
    private JPasswordField passwordField;
    private JTextField textField;

    // Correct credentials
    private final String DUMMY_USERNAME = "user";
    private final String DUMMY_PASSWORD = "security123";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 752);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245)); // Soft light gray background
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Header section
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(183, 183, 47)); // Accent color for header
        headerPanel.setBounds(0, 0, 1440, 50);
        contentPane.add(headerPanel);

        // Updated header text
        JLabel headerLabel = new JLabel("Apartment and Facilities Billing System");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Center panel for login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(319, 171, 800, 350); // Centering login panel
        contentPane.add(loginPanel);

        // Username input field
        username = new JTextField();
        username.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        username.setForeground(new Color(60, 60, 60)); // Dark gray text
        username.setBackground(Color.WHITE);
        username.setBounds(250, 139, 300, 40);
        username.setBorder(new LineBorder(new Color(183, 183, 47), 2, true)); // Accent border color
        loginPanel.add(username);

        // Password input field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passwordField.setForeground(new Color(60, 60, 60)); // Dark gray text
        passwordField.setBackground(Color.WHITE); // White background for input fields
        passwordField.setBounds(250, 190, 300, 40);
        passwordField.setBorder(new LineBorder(new Color(183, 183, 47), 2, true)); // Accent border color
        loginPanel.add(passwordField);

        // Show password checkbox
        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        showPassword.setForeground(new Color(60, 60, 60)); // Dark gray text
        showPassword.setBackground(new Color(240, 240, 240)); // Light gray background
        showPassword.setBounds(250, 237, 150, 20);
        loginPanel.add(showPassword);

        // Login button
        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setBackground(new Color(183, 183, 47)); // Accent green-yellow background
        loginButton.setBounds(250, 264, 300, 40);
        loginButton.setBorder(new LineBorder(new Color(183, 183, 47), 2, true)); // Accent border color
        loginButton.setOpaque(true); // Make button opaque for visible color
        loginButton.setFocusPainted(false); // Remove focus border
        loginButton.setBorderPainted(false); // Remove border highlight on focus
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Change cursor on hover
        loginPanel.add(loginButton);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(302, 28, 190, 100);
        loginPanel.add(lblNewLabel);
        ImageIcon logoIcon = new ImageIcon("C:/Users/YOJ/git/2105_ARAFBS/src/images/logo.png"); // Provide the correct path to your logo image
        lblNewLabel.setIcon(logoIcon);
        loginPanel.add(lblNewLabel);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = username.getText();
                String enteredPassword = new String(passwordField.getPassword());

                if (enteredUsername.equals(DUMMY_USERNAME) && enteredPassword.equals(DUMMY_PASSWORD)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Navigate to Homepage class
                    homepage homepage = new homepage();
                    homepage.setVisible(true);
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for the show password checkbox
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });

        // Footer section
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(183, 183, 47)); // Accent color for footer
        footerPanel.setBounds(0, 670, 1440, 50); // Positioned at the bottom of the frame
        contentPane.add(footerPanel);

        JLabel footerLabel = new JLabel("© 2024 AVA HOMES - All rights reserved.");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
    }
}
