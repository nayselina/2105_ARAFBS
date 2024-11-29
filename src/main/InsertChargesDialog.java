package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dbConnection.DatabaseConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertChargesDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertChargesDialog dialog = new InsertChargesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertChargesDialog() {
		setBounds(100, 100, 466, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); // Center the window
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(183, 183, 47));
		headerPanel.setBounds(0, 0, 450, 26);
		contentPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblAptmanagerByAvahome_1 = new JLabel("AptManager");
        lblAptmanagerByAvahome_1.setForeground(Color.WHITE);
        lblAptmanagerByAvahome_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAptmanagerByAvahome_1.setBounds(184, 4, 77, 18);
        headerPanel.add(lblAptmanagerByAvahome_1);
        
        JLabel lblInsertCharges = new JLabel("INSERT CHARGES");
        lblInsertCharges.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblInsertCharges.setBounds(14, 37, 167, 35);
        contentPanel.add(lblInsertCharges);
        
        JPanel mainPanel = new JPanel();
    	mainPanel.setBackground(new Color(240, 238, 226));
    	mainPanel.setBounds(0, 85, 450, 327);
    	contentPanel.add(mainPanel);
    	mainPanel.setLayout(null);
    	
    	JLabel lblBillID = new JLabel("Bill ID:");
    	lblBillID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblBillID.setBounds(34, 30, 69, 19);
    	mainPanel.add(lblBillID);
    	
    	JLabel lblElectricity = new JLabel("Electricity Bill:");
    	lblElectricity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblElectricity.setBounds(34, 80, 121, 19);
    	mainPanel.add(lblElectricity);
    	
    	JLabel lblWater = new JLabel("Water Bill:");
    	lblWater.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblWater.setBounds(34, 132, 79, 19);
    	mainPanel.add(lblWater);
    	
    	JLabel lblFacilityName = new JLabel("Facility Name:");
    	lblFacilityName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblFacilityName.setBounds(34, 188, 95, 19);
    	mainPanel.add(lblFacilityName);
    	
    	JTextField txtElectricity = new JTextField();
    	txtElectricity.setBounds(183, 80, 215, 26);
    	mainPanel.add(txtElectricity);
    	txtElectricity.setColumns(10);
    	
    	JTextField txtBillID = new JTextField();
    	txtBillID.setColumns(10);
    	txtBillID.setBounds(183, 30, 215, 26);
    	mainPanel.add(txtBillID);
    	
    	JTextField txtWater = new JTextField();
    	txtWater.setColumns(10);
    	txtWater.setBounds(183, 130, 215, 26);
    	mainPanel.add(txtWater);
    	
    	JTextField txtFacilityName = new JTextField();
    	txtFacilityName.setColumns(10);
    	txtFacilityName.setBounds(183, 186, 215, 26);
    	mainPanel.add(txtFacilityName);
    	
    	JLabel lblFacilityBill = new JLabel("Facility Bill:");
    	lblFacilityBill.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblFacilityBill.setBounds(34, 240, 69, 19);
    	mainPanel.add(lblFacilityBill);
    	
    	JTextField txtFacilityBill = new JTextField();
    	txtFacilityBill.setColumns(10);
    	txtFacilityBill.setBounds(183, 240, 215, 26);
    	mainPanel.add(txtFacilityBill);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnInsert = new JButton("INSERT");
				btnInsert.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        // Get the input data as strings from the text fields
				        String billIDStr = txtBillID.getText();
				        String electricityBillStr = txtElectricity.getText();
				        String waterBillStr = txtWater.getText();
				        String facilityName = txtFacilityName.getText();
				        String facilityBillStr = txtFacilityBill.getText();

				        // Create a DatabaseConnection instance
				        DatabaseConnection dbConnection = new DatabaseConnection();

				        // Call the combined method to insert into the facility table and update the bill
				        boolean success = dbConnection.insertFacilityAndUpdateBill(billIDStr, electricityBillStr, 
				                                                                  waterBillStr, facilityName, 
				                                                                  facilityBillStr);
				        
				        if (success) {
				            JOptionPane.showMessageDialog(InsertChargesDialog.this, "Charges inserted successfully.");
				            
				        } else {
				            JOptionPane.showMessageDialog(InsertChargesDialog.this, "Failed to insert charges.");
				        }
				        dispose();

				       
				    }
				});

				btnInsert.setBorderPainted(false);
				btnInsert.setBackground(new Color(183, 183, 47));
				btnInsert.setActionCommand("OK");
				buttonPane.add(btnInsert);
				getRootPane().setDefaultButton(btnInsert);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBorderPainted(false);
				cancelButton.setBackground(new Color(183, 183, 47));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
