package main;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import componentsUI.Header;
import componentsUI.FrameDragUtility;
import componentsUI.RoundedPanel;
import componentsUI.SidebarPanel;
import dbConnection.DatabaseConnection;
import main.Tenants.ButtonEditor;
import main.Tenants.ButtonRenderer;
import model.LedgerRecord;
import model.Tenant;
import model.TenantDetails;
import model.TenantModel;
import componentsUI.BackgroundPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Ledger extends JFrame {

	private static final long serialVersionUID = 1L;
	private BackgroundPanel photoPanel;
	private JPanel contentPane;
	private JTable tableLedgerTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ledger frame = new Ledger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ledger() {
		setTitle("Tenant Ledger");
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
        
        SidebarPanel sidebar = new SidebarPanel(this, "Tenant Ledger");
        getContentPane().add(sidebar);
        
        RoundedPanel mainPanel = new RoundedPanel(30);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(301, 76, 600, 724);  //former width 949
        mainPanel.setLayout(null); // Absolute positioning
        contentPane.add(mainPanel);
        
        photoPanel = new BackgroundPanel("/images/interior5.png");
        photoPanel.setBackground(Color.RED);
        photoPanel.setBounds(251, 26, 1049, 774);
        photoPanel.setLayout(null); // Absolute positioning
        contentPane.add(photoPanel);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                int frameWidth = getWidth();
                photoPanel.setBounds(300, 26, frameWidth - 300, frameHeight - 26); // Adjust sidebarPanel height

            }
        });  
        
        JPanel stPanel = new JPanel();
        stPanel.setBackground(Color.LIGHT_GRAY);
        stPanel.setBounds(0, 61, 600, 663); //former width 949
        mainPanel.add(stPanel);
        stPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // for empty border
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
        scrollPane.setBounds(0, 0, 600, 663);  //former width 949
        stPanel.add(scrollPane);
        
        tableLedgerTable = new JTable();
        tableLedgerTable.setRowHeight(30);
        tableLedgerTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"TenantID", "Tenants", "View Ledgers"
        	}
        ));
        tableLedgerTable.setSelectionBackground(new Color(255, 230, 150));
        tableLedgerTable.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tableLedgerTable.setShowVerticalLines(false);
        tableLedgerTable.setShowHorizontalLines(false);
        scrollPane.setViewportView(tableLedgerTable);
        
        tableLedgerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                int frameWidth = getWidth();
                stPanel.setBounds(0, 76, frameWidth - 751, frameHeight - 76);
                scrollPane.setBounds(0, 0, frameWidth - 751, frameHeight - 76); 
                tableLedgerTable.setBounds(0, 0, frameWidth - 751, frameHeight - 76); 

            }
        });  
        
     // Table Header Style
        JTableHeader tblHeader = tableLedgerTable.getTableHeader();
        tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tblHeader.setBackground(new Color(247, 247, 231));
        tblHeader.setForeground(Color.black);
        tblHeader.setReorderingAllowed(false);         
        
        
        JLabel lblTenantLedger = new JLabel("Tenant Ledger");
        lblTenantLedger.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTenantLedger.setBounds(15, 11, 265, 35);
        mainPanel.add(lblTenantLedger);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                int frameWidth = getWidth();
                mainPanel.setBounds(351, 76, frameWidth - 751, frameHeight - 76); // Adjust sidebarPanel height

            }
        });
        DefaultTableModel model = (DefaultTableModel) tableLedgerTable.getModel();
        loadTenantsData(model);
        
        
       
	}
	
	
/*	private void loadTenantsData(DefaultTableModel model) {
        // Query tenants from the database and populate the table
        try {
            // Use the DatabaseConnection to get the connection
            Connection connection = DatabaseConnection.getInstance().getConnection();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tenantID, firstName, lastName FROM tenant");
            while (rs.next()) {
                int tenantID = rs.getInt("tenantID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                
                // Concatenate firstName and lastName to create tenantName
                String tenantName = firstName + " " + lastName;
                Object[] row = {tenantID, tenantName, "View Ledger"};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listeners for the View Ledger button
        tableLedgerTable.getColumn("View Ledgers").setCellRenderer(new ButtonRenderer());
        tableLedgerTable.getColumn("View Ledgers").setCellEditor(new ButtonEditor(new JCheckBox()));
    }
	
	              */
	
	
	
	private void loadTenantsData(DefaultTableModel model) {
	    try {
	        List<Tenant> tenants = DatabaseConnection.getInstance().getTenants();
	        for (Tenant tenant : tenants) {
	            int tenantID = tenant.getTenantID();
	            String tenantName = tenant.getTenantName(); // Full name now
	            Object[] row = {tenantID, tenantName, "View Ledger"};
	            model.addRow(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Add action listeners for the "View Ledger" button
	    tableLedgerTable.getColumn("View Ledgers").setCellRenderer(new ButtonRenderer());
	    tableLedgerTable.getColumn("View Ledgers").setCellEditor(new ButtonEditor(new JCheckBox()));
	}

	        
	class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText((value == null) ? "View Ledger" : value.toString());
            return this;
        }
    }
	// Button editor for "View Ledger" button in JTable
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private int tenantID;
        private String fullName;
   

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "View Ledger" : value.toString();
            tenantID = (int) table.getValueAt(row, 0);
            fullName = (String) table.getValueAt(row, 1); // Get tenant Full Name from the table
            JButton button = new JButton(label);
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                 //   showLedgerFrame(tenantID);
                	
                	ViewLedgerFrame ledgerFrame = new ViewLedgerFrame(tenantID, fullName);
                }
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }
    }
    
    
  /*  private void showLedgerFrame(int tenantID) {
        // Create a new frame for displaying the ledger
        JFrame ledgerFrame = new JFrame("Tenant Ledger");
        JTable ledgerTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ledgerTable);

        // Create a table model for the ledger table
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Payment Date", "Bill ID", "Payment ID", "Payment Amount", "Balance After Payment"}
        );
        ledgerTable.setModel(model);

        // Query to get ledger details
        try {
            // Use the DatabaseConnection to get the connection
            Connection connection = DatabaseConnection.getInstance().getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT p.paymentDate, l.billID, l.paymentID, p.paymentAmount, l.balanceAfterPayment " +
                            "FROM ledger l " +
                            "JOIN payment p ON l.paymentID = p.paymentID " +
                            "WHERE l.tenantID = ?");
            stmt.setInt(1, tenantID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                        rs.getDate("paymentDate"),
                        rs.getInt("billID"),
                        rs.getInt("paymentID"),
                        rs.getDouble("paymentAmount"),
                        rs.getDouble("balanceAfterPayment")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set up the ledger frame
        ledgerFrame.setLayout(new BorderLayout());
        ledgerFrame.add(scrollPane, BorderLayout.CENTER);
        ledgerFrame.setSize(600, 400);
        ledgerFrame.setVisible(true);
    }                                     */
    
    private void showLedgerFrame(int tenantID) {
        // Create a new frame for displaying the ledger
        JFrame ledgerFrame = new JFrame("Tenant Ledger");
        JTable ledgerTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ledgerTable);

        // Create a table model and populate the ledger table
        DefaultTableModel ledgerModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Payment Date", "Bill ID", "Payment ID", "Payment Amount", "Balance After Payment"}
        );
        ledgerTable.setModel(ledgerModel);

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

        ledgerFrame.setLayout(new BorderLayout());
        ledgerFrame.add(scrollPane, BorderLayout.CENTER);
        ledgerFrame.setSize(600, 400);
        ledgerFrame.setVisible(true);
    }


	
	
	

}
