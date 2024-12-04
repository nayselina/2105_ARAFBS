package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import model.LedgerRecord;
import dbConnection.DatabaseConnection;

public class ViewLedgerFrame extends JFrame {
    private int tenantID;
    private String tenantName;
    private String fullName;
   
    private JTable ledgerTable;
    
    private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

    public ViewLedgerFrame(int tenantID, String fullName) {
        this.tenantID = tenantID;
     //   this.tenantName = firstName + " " + lastName; // Concatenate first and last name
        this.fullName = fullName;
        initialize();
    }

    private void initialize() {
        setTitle("Tenant Ledger - " + fullName);
        setBounds(100, 100, 600, 381);
        getContentPane().setLayout(null);
		setLocationRelativeTo(null); // Center the window
	
		JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 722, 671);
        getContentPane().add(panel);
        
        JLabel lblRentalLedger = new JLabel("RENTAL LEDGER");
        lblRentalLedger.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblRentalLedger.setBounds(14, 37, 167, 35);
        panel.add(lblRentalLedger);
   

        // Create the ledger table
        ledgerTable = new JTable();
        ledgerTable.setRowHeight(20);
        ledgerTable.setSelectionBackground(new Color(255, 230, 150));
        ledgerTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ledgerTable.setShowVerticalLines(false);
        ledgerTable.setShowHorizontalLines(false);
        JScrollPane scrollPane = new JScrollPane(ledgerTable);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // for empty border
        scrollPane.setBounds(10, 150, 702, 491);

        // Create a table model and populate the ledger table
        DefaultTableModel ledgerModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Payment Date", "Bill ID", "Payment ID", "Payment Amount", "Balance After Payment"}
        );
        ledgerTable.setModel(ledgerModel);
        
        ledgerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 238, 226) );
                }
                setHorizontalAlignment(SwingConstants.CENTER); // Center align text
                return c;
            }
        });
        
        JTableHeader tblHeader = ledgerTable.getTableHeader();
        tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblHeader.setBackground(new Color(247, 247, 231));
        tblHeader.setForeground(Color.black);
        tblHeader.setReorderingAllowed(false); 

        // Fetch ledger data for the tenant
        List<LedgerRecord> ledgerData = DatabaseConnection.getInstance().getLedgerData(tenantID);
        for (LedgerRecord record : ledgerData) {
            Object[] row = {
                    record.getPaymentDate(),
                    record.getBillID(),
                    record.getPaymentID(),
                    record.getPaymentAmount(),
                    record.getBalanceAfterPayment()
            };
            ledgerModel.addRow(row);
        }
        panel.setLayout(null);
        panel.add(scrollPane);

        // Add scrollPane to center of the frame
     //   getContentPane().add(scrollPane);
        
        JLabel label = new JLabel("Tenant ID: " + tenantID);
        label.setBounds(14, 111, 75, 20);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     //   getContentPane().add(label);
        panel.add(label);
        JLabel label_1 = new JLabel("Tenant Name: " + fullName);
        label_1.setBounds(14, 83, 160, 20);
        label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
     //   getContentPane().add(label_1);
        panel.add(label_1);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 722, 26);
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(183, 183, 47));
        panel.add(headerPanel);
        
        JLabel lblAptmanagerByAvahome = new JLabel("AptManager");
        lblAptmanagerByAvahome.setForeground(Color.WHITE);
        lblAptmanagerByAvahome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAptmanagerByAvahome.setBounds(322, 4, 77, 18);
        headerPanel.add(lblAptmanagerByAvahome);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorderPainted(false);
        closeButton.setBackground(new Color(183, 183, 47));
        closeButton.setBounds(0, 648, 722, 23);
        panel.add(closeButton);
        
        

        // Set frame properties
        setSize(738, 710);
        setLocationRelativeTo(null);  // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
