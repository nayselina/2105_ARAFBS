
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TenantDetails;
import java.awt.Rectangle;

public class TenantDetailsFrame extends JFrame {
    public TenantDetailsFrame(TenantDetails tenantDetails) {
        setTitle("Tenant Details");
        setBounds(100, 100, 466, 381);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window
        

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBackground(Color.WHITE);
        
        JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(183, 183, 47));
		headerPanel.setBounds(0, 0, 450, 26);
		detailsPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblAptmanagerByAvahome = new JLabel("AptManager");
        lblAptmanagerByAvahome.setForeground(Color.WHITE);
        lblAptmanagerByAvahome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAptmanagerByAvahome.setBounds(184, 4, 77, 18);
        headerPanel.add(lblAptmanagerByAvahome);
        
        JLabel lblTenantDetails = new JLabel("TENANT DETAILS");
        lblTenantDetails.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTenantDetails.setBounds(14, 37, 167, 35);
        detailsPanel.add(lblTenantDetails);
        
        JPanel mainPanel = new JPanel();
    	mainPanel.setBackground(new Color(240, 238, 226));
    	mainPanel.setBounds(0, 85, 450, 252);
    	detailsPanel.add(mainPanel);
    	mainPanel.setLayout(null);
        

        // Add tenant details to the panel
        JLabel label = new JLabel("Name: " + tenantDetails.getTenantName());
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBounds(66, 30, 295, 15);
        mainPanel.add(label);
        JLabel label_1 = new JLabel("Contact: " + tenantDetails.getContactNum());
        label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label_1.setBounds(66, 65, 295, 15);
        mainPanel.add(label_1);
        JLabel label_2 = new JLabel("Email: " + tenantDetails.getEmail());
        label_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label_2.setBounds(66, 100, 295, 15);
        mainPanel.add(label_2);
        JLabel label_3 = new JLabel("Additional Info: " + tenantDetails.getAdditionalInfo());
        label_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label_3.setBounds(66, 135, 331, 15);
        mainPanel.add(label_3);
        JLabel label_4 = new JLabel("Unit Code: " + tenantDetails.getUnitCode());
        label_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label_4.setBounds(66, 170, 295, 15);
        mainPanel.add(label_4);
        JLabel label_5 = new JLabel("Rent Start: " + tenantDetails.getRentStart());
        label_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label_5.setBounds(66, 205, 295, 15);
        mainPanel.add(label_5);

        // Add the details panel to the center of the JFrame
        getContentPane().add(detailsPanel, BorderLayout.CENTER);

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.setBorderPainted(false);
        closeButton.setBackground(new Color(183, 183, 47));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, BorderLayout.SOUTH);

        setSize(466, 395);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
