package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class MakePaymentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MakePaymentDialog dialog = new MakePaymentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MakePaymentDialog() {
		setBounds(100, 100, 466, 381);
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
		
		JLabel lblAptmanagerByAvahome = new JLabel("AptManager");
        lblAptmanagerByAvahome.setForeground(Color.WHITE);
        lblAptmanagerByAvahome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAptmanagerByAvahome.setBounds(184, 4, 77, 18);
        headerPanel.add(lblAptmanagerByAvahome);
        
        JLabel lblPayBill = new JLabel("PAY BILL");
        lblPayBill.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPayBill.setBounds(14, 37, 167, 35);
        contentPanel.add(lblPayBill);
        
        JPanel mainPanel = new JPanel();
    	mainPanel.setBackground(new Color(240, 238, 226));
    	mainPanel.setBounds(0, 85, 450, 252);
    	contentPanel.add(mainPanel);
    	mainPanel.setLayout(null);
    	
    	JLabel lblBillID = new JLabel("Bill ID:");
    	lblBillID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblBillID.setBounds(34, 30, 69, 19);
    	mainPanel.add(lblBillID);
    	
    	JLabel lblEnterAmount = new JLabel("Enter Amount:");
    	lblEnterAmount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblEnterAmount.setBounds(34, 80, 121, 19);
    	mainPanel.add(lblEnterAmount);
    	
    	JTextField txtBillID = new JTextField();
    	txtBillID.setColumns(10);
    	txtBillID.setBounds(183, 30, 215, 26);
    	mainPanel.add(txtBillID);
    	
    	JTextField txtElectricity = new JTextField();
    	txtElectricity.setBounds(183, 80, 215, 26);
    	mainPanel.add(txtElectricity);
    	txtElectricity.setColumns(10);
    	
    	JDateChooser paymentDateChooser = new JDateChooser();
    	paymentDateChooser.setBounds(183, 133, 215, 26);
    	mainPanel.add(paymentDateChooser);
    	
    	JLabel lblPaymentDate = new JLabel("Payment Date:");
    	lblPaymentDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	lblPaymentDate.setBounds(34, 133, 121, 19);
    	mainPanel.add(lblPaymentDate);
    	
        
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnPay = new JButton("PAY");
				btnPay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnPay.setBorderPainted(false);
				btnPay.setBackground(new Color(183, 183, 47));
				btnPay.setActionCommand("OK");
				buttonPane.add(btnPay);
				getRootPane().setDefaultButton(btnPay);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(183, 183, 47));
				cancelButton.setBorderPainted(false);
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
