package main;

import java.awt.BorderLayout;



import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbConnection.DatabaseConnection;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;

public class AddTenantDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtLname;
	private JTextField txtFname;
	private JTextField txtContactNo;
	private JTextField txtEmail;
	private JTextField txtOccupants;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddTenantDialog dialog = new AddTenantDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddTenantDialog() {
		setBounds(100, 100, 466, 750);
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
        
        	JPanel mainPanel = new JPanel();
        	mainPanel.setBackground(new Color(240, 238, 226));
        	mainPanel.setBounds(0, 85, 450, 593);
        	contentPanel.add(mainPanel);
        	mainPanel.setLayout(null);
        	
        	JLabel lblFname = new JLabel("First Name:");
        	lblFname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblFname.setBounds(34, 30, 69, 19);
        	
        	txtFname = new JTextField();
        	txtFname.setColumns(10);
        	txtFname.setBounds(183, 30, 215, 26);
        	mainPanel.add(txtFname);
        	mainPanel.add(lblFname);
        	
        	JLabel lblLname = new JLabel("Last Name:");
        	lblLname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblLname.setBounds(34, 80, 69, 19);
        	mainPanel.add(lblLname);
        	
        	txtLname = new JTextField();
        	txtLname.setBounds(183, 80, 215, 26);
        	mainPanel.add(txtLname);
        	txtLname.setColumns(10);
        	
        	JLabel lblContact = new JLabel("Contact No.");
        	lblContact.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblContact.setBounds(34, 132, 79, 19);
        	mainPanel.add(lblContact);
        	
        	txtContactNo = new JTextField();
        	txtContactNo.setColumns(10);
        	txtContactNo.setBounds(183, 142, 215, 26);
        	mainPanel.add(txtContactNo);
        	
        	JLabel lblEmail = new JLabel("Email:");
        	lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblEmail.setBounds(34, 188, 69, 19);
        	mainPanel.add(lblEmail);
        	
        	txtEmail = new JTextField();
        	txtEmail.setColumns(10);
        	txtEmail.setBounds(183, 188, 215, 26);
        	mainPanel.add(txtEmail);
        	
        	JLabel lblUnitCode = new JLabel("Unit Code:");
        	lblUnitCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblUnitCode.setBounds(34, 240, 69, 19);
        	mainPanel.add(lblUnitCode);
        	
        	JComboBox comboBoxUnitCode = new JComboBox();
        	comboBoxUnitCode.setModel(new DefaultComboBoxModel(new String[] {"", "SOL-101", "SOL-102", "SOL-103", "SOL-104", "SOL-105", "SOL-106", "SOL-107", "SOL-108", "COP-101", "COP-102", "COP-103", "COP-104", "COP-105", "COP-106", "COP-107", "COP-108", "FAM-101", "FAM-102", "FAM-103", "FAM-104", "FAM-105", "FAM-106", "FAM-107", "FAM-108"}));
        	comboBoxUnitCode.setBounds(183, 238, 215, 26);
        	mainPanel.add(comboBoxUnitCode);
        	
        	DatabaseConnection dbc = new DatabaseConnection();
        	dbc.populateAvailableUnitCodes(comboBoxUnitCode);
        	
        	JLabel lblAdditional = new JLabel("Additional Info:");
        	lblAdditional.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblAdditional.setBounds(34, 339, 109, 19);
        	mainPanel.add(lblAdditional);
        	
        	JTextArea txtAreaAdditional = new JTextArea();
        	txtAreaAdditional.setBounds(183, 334, 215, 94);
        	mainPanel.add(txtAreaAdditional);
        	
        	JLabel lblOccupants = new JLabel("No. Of Occupants:");
        	lblOccupants.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblOccupants.setBounds(34, 288, 119, 19);
        	mainPanel.add(lblOccupants);
        	
        	txtOccupants = new JTextField();
        	txtOccupants.setColumns(10);
        	txtOccupants.setBounds(183, 288, 215, 26);
        	mainPanel.add(txtOccupants);
        	
        	JLabel lblDateRented = new JLabel("Date Rented:");
        	lblDateRented.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblDateRented.setBounds(34, 457, 109, 19);
        	mainPanel.add(lblDateRented);
        	
        	JDateChooser dateRentedChooser = new JDateChooser();
            dateRentedChooser.setBounds(183, 457, 215, 26);
            mainPanel.add(dateRentedChooser);
            
            JLabel lblRentEnd = new JLabel("Rent End:");
        	lblRentEnd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        	lblRentEnd.setBounds(34, 509, 109, 19);
        	mainPanel.add(lblRentEnd);
            
            // Replace textField_7 (Rent End) with JDateChooser
            JDateChooser rentEndChooser = new JDateChooser();
            rentEndChooser.setBounds(183, 509, 215, 26);
            mainPanel.add(rentEndChooser);
            
            JLabel lblAddTenant = new JLabel("ADD TENANT");
            lblAddTenant.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            lblAddTenant.setBounds(14, 37, 167, 35);
            contentPanel.add(lblAddTenant);
     
        
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAdd = new JButton("ADD");
				btnAdd.setBorderPainted(false);
				btnAdd.setBackground(new Color(183, 183, 47));
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String firstName = txtFname.getText();
				        String lastName = txtLname.getText();
				        String contactNo = txtContactNo.getText();
				        String email = txtEmail.getText();
				        String additionalInfo = txtAreaAdditional.getText();
				        String unitCode = comboBoxUnitCode.getSelectedItem().toString();
				        String strOccupants = txtOccupants.getText();
				        
				        

				       Date rentStartDate = dateRentedChooser.getDate();
				       String strRentStartDate = null;
				       if (rentStartDate != null) {
		                    // Format the date to yyyy-MM-dd
		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		                    strRentStartDate = sdf.format(rentStartDate);  // Converts the Date object to a string in the desired format
		                }
				       
				       Date rentEndDate = rentEndChooser.getDate();
				       String strRentEndDate = null;
				       if (rentEndDate != null) {
		                    // Format the date to yyyy-MM-dd
		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		                    strRentEndDate = sdf.format(rentEndDate);  // Converts the Date object to a string in the desired format
		                }
				        
				        DatabaseConnection dbc = new DatabaseConnection();
		                String result =  dbc.addTenant(firstName, lastName, contactNo, email, unitCode, strOccupants, additionalInfo, strRentStartDate, strRentEndDate);

		                // Display success or error message based on result
		                if (result.equals("Tenant and related data saved successfully.")) {
		                    JOptionPane.showMessageDialog(AddTenantDialog.this,
		                            result,  // Success message
		                            "Success", JOptionPane.INFORMATION_MESSAGE); // Show as Information
		                } else {
		                    JOptionPane.showMessageDialog(AddTenantDialog.this,
		                            result,  // Error or failure message
		                            "Error", JOptionPane.ERROR_MESSAGE);  // Show as Error
		                }  

		                dispose(); // Close dialog after showing the message
		            }
		        });
					
				
				btnAdd.setActionCommand("OK");
				buttonPane.add(btnAdd);
				getRootPane().setDefaultButton(btnAdd);
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
