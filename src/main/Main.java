package main;


import componentsUI.PlaceholderTextField;

import componentsUI.RoundedButton;
import componentsUI.PlaceholderPasswordField;
import componentsUI.BackgroundPanel;
import componentsUI.ChangingBackgroundPanel;
import componentsUI.CustomCheckBoxIcon;
import componentsUI.FrameDragUtility;
import componentsUI.RoundedPanel;
import componentsUI.RoundedPlaceholderPasswordField;
import componentsUI.RoundedPlaceholderTextField;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

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
import java.awt.GridLayout;
import componentsUI.HoverIconButton;
import java.awt.Cursor;


public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private ChangingBackgroundPanel contentPane;
    private JTextField username;
    private JPasswordField pwdPassword;
    
    private int currentImageIndex = 0; // Track the current image index
    private static final List<String> imagePaths = Arrays.asList(
            "/images/interior1.png",  // Initial image
            "/images/interior2.png",  // Add your 4 additional images here
            "/images/interior3.png",
            "/images/interior4.png",
            "/images/interior5.png"
    );

    // File path for properties
    private static final String PROPERTIES_FILE_PATH = "resources/user.properties";

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 800);
        contentPane = new ChangingBackgroundPanel(imagePaths.get(currentImageIndex));
        contentPane.setBackground(new Color(245, 245, 245)); // Soft light gray background
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null); // Center the window
        setUndecorated(true); // Remove the border
        
        new FrameDragUtility(this);
        
        
    
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Update background image
                currentImageIndex = (currentImageIndex + 1) % imagePaths.size();
                contentPane.setBackgroundImage(imagePaths.get(currentImageIndex)); // Update background
            }
        }, 0, 5000); // Initial delay 0, repeat every 5000 milliseconds (5 seconds)
    
        
      
        // Load stored username and password
        Properties properties = loadCredentials();
        
        Color originalColor = new Color(183, 183, 47);
        Color hoverColor = originalColor.darker();
        Color clickColor = hoverColor.darker();
        

        // Center panel for login form
        RoundedPanel loginPanel = new RoundedPanel(30);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(null);
        loginPanel.setBounds(422, 104, 446, 508); 
        loginPanel.setPreferredSize(new Dimension(446, 508));
        contentPane.add(loginPanel);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current frame dimensions
                int frameWidth = getWidth();
                int frameHeight = getHeight();

                // Get the dimensions of the login panel
                int panelWidth = loginPanel.getPreferredSize().width;
                int panelHeight = loginPanel.getPreferredSize().height;

                // Calculate the x and y coordinates to center the login panel
                int x = (frameWidth - panelWidth) / 2;
                int y = (frameHeight - panelHeight) / 2;

                // Set the login panel's position
                loginPanel.setBounds(x, y, panelWidth, panelHeight);
            }
        });

        // Username input field
        RoundedPlaceholderTextField username = new RoundedPlaceholderTextField("username", 15, originalColor);
   //     username.setText("Username");
        username.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        username.setForeground(new Color(60, 60, 60)); // Dark gray text
        username.setBackground(Color.WHITE);
        username.setBounds(78, 188, 300, 35);
    //    username.setBorder(new LineBorder(new Color(183, 183, 47), 2, true)); // Accent border color
        loginPanel.add(username);
        
    /*    Font plainFont = new Font("Segoe UI", Font.PLAIN, 18);
        Font italicFont = new Font("Segoe UI", Font.ITALIC, 18); // Italic font for placeholder
        
        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Keep the placeholder text if the field is empty
                if (username.getText().equals("Username")) {
                    username.selectAll();  // Select all text for easy deletion
                    
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // If the field is empty, restore the placeholder text
                if (username.getText().isEmpty()) {
                    username.setText("Username");
                   
                }
            }
        });       
        
        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // If the text is still "username" and the user starts typing, remove it
                if (username.getText().equals("Username")) {
                    username.setText("");
                    username.setFont(plainFont);
                }
            }
        });              */
        

        // Password input field
        pwdPassword = new RoundedPlaceholderPasswordField("password",15,originalColor);
        pwdPassword.setEchoChar((char) 0); // Show text initially as placeholder
     //   pwdPassword.setText("Password"); // Placeholder text
        pwdPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pwdPassword.setForeground(new Color(60, 60, 60)); // Dark gray text
        pwdPassword.setBackground(Color.WHITE); // White background for input fields
        pwdPassword.setBounds(78, 241, 300, 35);
        pwdPassword.setBorder(new LineBorder(new Color(183, 183, 47), 2, true)); // Accent border color
        loginPanel.add(pwdPassword);
        
        JCheckBox showPassword = new JCheckBox("Show password");
        showPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        showPassword.setForeground(new Color(60, 60, 60)); // Dark gray text
        showPassword.setBackground(Color.WHITE); 
        showPassword.setIcon(new CustomCheckBoxIcon());  // implements the custom checkbox
        showPassword.setBounds(78, 294, 150, 20);
        
        showPassword.setOpaque(false); // Make checkbox background transparent
        showPassword.setFocusPainted(false); // Remove focus paint that may cause unwanted effects
        showPassword.setBorder(null); // Remove default border
        
        
        loginPanel.add(showPassword);
        
        
        
     // Action listener for the show password checkbox
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    pwdPassword.setEchoChar((char) 0); // Show password
                } else {
                    pwdPassword.setEchoChar('\u2022'); // Hide password
                }
            }
        });
        
  /*      pwdPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(pwdPassword.getPassword()).equals("Password")) {
                    pwdPassword.selectAll();  // Select all text for easy deletion
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(pwdPassword.getPassword()).isEmpty()) {
                    pwdPassword.setText("Password");
                    pwdPassword.setEchoChar((char) 0); // Show placeholder text
                }
            }
        });  
        
        pwdPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (new String(pwdPassword.getPassword()).equals("Password")) {
                    pwdPassword.setText("");
                    pwdPassword.setEchoChar('\u2022'); // Mask with dots after typing
                    pwdPassword.setFont(plainFont);
                }
            }
        });         */
        
        
        pwdPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (new String(pwdPassword.getPassword()).isEmpty()) {
                 //   pwdPassword.setText("");
                    pwdPassword.setEchoChar('\u2022'); // Mask with dots after typing
                 //   pwdPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                }
            }
        }); 


        // Login button
        RoundedButton loginButton = new RoundedButton("Log In", 15);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setBackground(new Color(183, 183, 47)); // Accent green-yellow background
        loginButton.setBounds(78, 345, 300, 35);
     //   loginButton.setBorder(new LineBorder(new Color(183, 183, 47), 2, true)); // Accent border color
     //   loginButton.setOpaque(true); // Make button opaque for visible color
    //    loginButton.setFocusPainted(false); // Remove focus border
    //    loginButton.setBorderPainted(false); // Remove border highlight on focus
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Change cursor on hover
        loginPanel.add(loginButton);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(Main.class.getResource("/images/AptManager-default.png")));
        lblLogo.setBounds(52, 43, 334, 69);
        loginPanel.add(lblLogo);
        
        ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/images/AptManager-default.png"));
        Image img = logoIcon.getImage();
        Image resizedImage = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(resizedImage));
        
        JLabel lblNewLabel = new JLabel("Apartment Rentals System");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabel.setBounds(136, 110, 186, 14);
        loginPanel.add(lblNewLabel);
        
     // Add KeyListener for both username and password fields
        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Trigger the login action when Enter is pressed
                    loginButton.doClick(); // Simulate a button click
                }
            }
        });

        pwdPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Trigger the login action when Enter is pressed
                    loginButton.doClick(); // Simulate a button click
                }
            }
        });

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = username.getText();
                String enteredPassword = new String(pwdPassword.getPassword());

                if (enteredUsername.equals(properties.getProperty("username")) && enteredPassword.equals(properties.getProperty("password"))) {
                 //   JOptionPane.showMessageDialog(null, "Login successful!");
                	int previousState = getExtendedState();
                    // Navigate to Homepage class
                    Homepage homepage = new Homepage();
                    homepage.setVisible(true);
                    homepage.setExtendedState(previousState);
                
                    dispose(); // Close the login window
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }    
            }
        });
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(89, 89, 22));
        headerPanel.setBounds(0, 0, 1300, 26);
        contentPane.add(headerPanel);
        
        JLabel lblAptmanagerByAvahome_1 = new JLabel("AptManager by AVAHOME v1.0");
        lblAptmanagerByAvahome_1.setForeground(Color.WHITE);
        lblAptmanagerByAvahome_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAptmanagerByAvahome_1.setBounds(543, 2, 208, 20);
        headerPanel.add(lblAptmanagerByAvahome_1);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current frame width
                int frameWidth = getWidth();

                // Update the header panel width
                headerPanel.setBounds(0, 0, frameWidth, 24);

                // Center the label within the header panel
                int labelWidth = lblAptmanagerByAvahome_1.getPreferredSize().width;
                int labelX = (frameWidth - labelWidth) / 2; // Center horizontally

                lblAptmanagerByAvahome_1.setBounds(labelX, 0, labelWidth, 20);
            }
        });
        
        JPanel minMaxClosePanel = new JPanel();
        minMaxClosePanel.setBounds(1210, 0, 90, 26);
        headerPanel.add(minMaxClosePanel);
        minMaxClosePanel.setLayout(new GridLayout(0, 3, 0, 0));
        this.addComponentListener(new ComponentAdapter() {                    
            @Override
            public void componentResized(ComponentEvent e) {
                // Adjust the position of the footer panel dynamically
                int frameHeight = getHeight();
                int frameWidth = getWidth();
                minMaxClosePanel.setBounds(frameWidth - 90, 0, 90, 26);
            }
        });
        
        
        HoverIconButton btnMaximize = new HoverIconButton("/images/maximize.png", "/images/light_maximize.png", "/images/light_maximize.png");
        btnMaximize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMaximize.setIcon(new ImageIcon(Homepage.class.getResource("/images/maximize.png")));
        btnMaximize.setBackground(clickColor);
        btnMaximize.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                    setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
                } else {
                    setExtendedState(JFrame.NORMAL); // Restore to normal size if already maximized
                }
        	}
        });
        minMaxClosePanel.setLayout(new GridLayout(0, 3, 0, 0));
        
        HoverIconButton btnMinimize = new HoverIconButton("/images/minimize.png", "/images/light_minimize.png", "/images/light_minimize.png");
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMinimize.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setState(JFrame.ICONIFIED); // Minimize the window
        	}
        });
        btnMinimize.setIcon(new ImageIcon(Homepage.class.getResource("/images/minimize.png")));
        btnMinimize.setBackground(clickColor);
        minMaxClosePanel.add(btnMinimize);
        minMaxClosePanel.add(btnMaximize);
        
        HoverIconButton btnClose = new HoverIconButton("/images/close.png", "/images/red_close.png", "/images/red_close.png");
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0); // Close the application
        	}
        });
        
        
        btnClose.setIcon(new ImageIcon(Homepage.class.getResource("/images/close.png")));
        btnClose.setBackground(clickColor);
        minMaxClosePanel.add(btnClose);

        // Footer section
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(clickColor); 
        footerPanel.setBounds(0, 776, 1300, 24); // Positioned at the bottom of the frame
        contentPane.add(footerPanel);
        footerPanel.setLayout(null);
        
        this.addComponentListener(new ComponentAdapter() {                    
            @Override
            public void componentResized(ComponentEvent e) {
                // Adjust the position of the footer panel dynamically
                int frameHeight = getHeight();
                int frameWidth = getWidth();
                footerPanel.setBounds(0, frameHeight - 24, frameWidth, 24);
            }
        });
        
        
        JLabel footerLabel = new JLabel("© 2024 AVAHOME - All rights reserved");
        footerLabel.setBounds(10, 0, 258, 20);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerLabel.setForeground(Color.WHITE); 
        footerPanel.add(footerLabel);
        
    }

    // Method to load credentials from the properties file
    private Properties loadCredentials() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Method to save credentials to the properties file
    private void saveCredentials(String username, String password) {
        Properties properties = new Properties();
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        try (FileOutputStream outputStream = new FileOutputStream(PROPERTIES_FILE_PATH)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
