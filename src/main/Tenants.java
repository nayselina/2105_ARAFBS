package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import componentsUI.Header;
import componentsUI.RoundedButton;
import componentsUI.FrameDragUtility;
import componentsUI.RoundedPanel;
import componentsUI.SidebarPanel;
import componentsUI.BackgroundPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dbConnection.DatabaseConnection;
import model.TenantDetails;
import model.TenantModel;

import javax.swing.table.TableCellEditor;

import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tenants extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private BackgroundPanel photoPanel;
    private JTable tableTenants;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tenants frame = new Tenants();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Tenants() {
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
        header.setBounds(0, 0, 1300, 26);
        getContentPane().add(header);

        SidebarPanel sidebar = new SidebarPanel(this, "Tenants");
        sidebar.setBounds(0, 129, 251, 671);
        getContentPane().add(sidebar);

        RoundedPanel mainPanel = new RoundedPanel(30);
        mainPanel.setBounds(301, 76, 949, 724);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null); // Absolute positioning
        contentPane.add(mainPanel);

        photoPanel = new BackgroundPanel("/images/interior1.png");
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
        stPanel.setBounds(0, 61, 949, 663);
        mainPanel.add(stPanel);
        stPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // for empty border
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
        scrollPane.setBounds(0, 0, 949, 663);
        stPanel.add(scrollPane);

        tableTenants = new JTable();
        tableTenants.setSelectionBackground(new Color(255, 230, 150));

        tableTenants.setRowHeight(30);
        tableTenants.setShowVerticalLines(false);
        tableTenants.setShowHorizontalLines(false);
        tableTenants.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tableTenants.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Tenant ID", "Tenant Name", "Unit Rented", "More", "Delete", "Invoice" // Added "Invoice" column
                }
        ));
        scrollPane.setViewportView(tableTenants);

        tableTenants.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 238, 226));
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
                stPanel.setBounds(0, 76, frameWidth - 401, frameHeight - 76);
                scrollPane.setBounds(0, 0, frameWidth - 401, frameHeight - 76);
                tableTenants.setBounds(0, 0, frameWidth - 401, frameHeight - 76);
            }
        });

        // Table Header Style
        JTableHeader tblHeader = tableTenants.getTableHeader();
        tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tblHeader.setBackground(new Color(247, 247, 231));
        tblHeader.setForeground(Color.black);
        tblHeader.setReorderingAllowed(false);

        JLabel lblTenants = new JLabel("Tenants");
        lblTenants.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTenants.setBounds(15, 11, 265, 35);
        mainPanel.add(lblTenants);

        RoundedButton btnAddTenant = new RoundedButton("Add Tenant", 15);
        btnAddTenant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddTenantDialog addTenantDialog = new AddTenantDialog();
                addTenantDialog.setModal(true);
                addTenantDialog.setVisible(true);
            }
        });
        btnAddTenant.setForeground(Color.WHITE);
        btnAddTenant.setBorderPainted(false);
        btnAddTenant.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAddTenant.setBackground(new Color(183, 183, 47));
        btnAddTenant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddTenant.setBounds(726, 15, 100, 30);
        mainPanel.add(btnAddTenant);

        RoundedButton btnRefresh = new RoundedButton("Refresh", 15);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTenantData();
            }
        });
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRefresh.setBackground(new Color(183, 183, 47));
        btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRefresh.setBounds(840, 15, 80, 30);
        mainPanel.add(btnRefresh);
        
        RoundedButton btnAddTenant_1 = new RoundedButton("Tenant History", 15);
        btnAddTenant_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showTenantHistory();
        	}
        });
        btnAddTenant_1.setForeground(Color.WHITE);
        btnAddTenant_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAddTenant_1.setBorderPainted(false);
        btnAddTenant_1.setBackground(new Color(183, 183, 47));
        btnAddTenant_1.setBounds(589, 16, 127, 30);
        mainPanel.add(btnAddTenant_1);

        loadTenantData();
    }

    public void loadTenantData() {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        List<TenantModel> tenantsList = dbConnection.fetchTenants();

        DefaultTableModel model = (DefaultTableModel) tableTenants.getModel();

        // Clear existing rows
        model.setRowCount(0);

        for (TenantModel tenant : tenantsList) {
            model.addRow(new Object[]{
                    tenant.getTenantID(),
                    tenant.getTenantName(),
                    tenant.getUnitCode(),
                    "More",
                    "Delete",
                    "Invoice"  // Add the new Invoice button
            });
        }

        if (model.getColumnCount() >= 6) { // Updated to check for 6 columns
            tableTenants.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
            tableTenants.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
            tableTenants.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer()); // Renderer for the Invoice button

            tableTenants.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JButton("More"), this::showTenantDetails));
            tableTenants.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JButton("Delete"), this::deleteTenant));
            tableTenants.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JButton("Invoice"), this::generateInvoice)); // Editor for Invoice button
        }
    }

    

   

   

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private Consumer<Integer> action;

        public ButtonEditor(JButton button, Consumer<Integer> action) {
            this.button = button;
            this.action = action;
            button.addActionListener(e -> {
                JTable table = (JTable) button.getParent();
                int row = table.getSelectedRow();
                int tenantID = (int) table.getValueAt(row, 0);
                action.accept(tenantID); // Call the method with tenantID
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }
    
    private void generateInvoice(int tenantID) {
        String sql = """
            SELECT CONCAT(t.firstName, ' ', t.lastName) AS tenantName, 
                   a.unitCode, a.rentAmount 
            FROM tenant t
            JOIN apartment a ON t.unitID = a.unitID
            WHERE t.tenantID = ?
        """;

        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, tenantID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tenantName = rs.getString("tenantName");
                    String unitCode = rs.getString("unitCode");
                    double rentAmount = rs.getDouble("rentAmount");

                    // Replace these with actual database queries or computations.
                    double electricityBill = 200.0;
                    double waterBill = 150.0;
                    double facilityBill = 100.0;
                    double totalAmount = rentAmount + electricityBill + waterBill + facilityBill;

                    // Generate and display the receipt
                    String receipt = generateReceipt(tenantName, unitCode, rentAmount, electricityBill, waterBill, facilityBill, totalAmount);

                    JTextArea textArea = new JTextArea(receipt);
                    textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

                    JOptionPane.showMessageDialog(this, scrollPane, "Invoice for Tenant: " + tenantName, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No details found for Tenant ID: " + tenantID, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving invoice details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateReceipt(String tenantName, String unitCode, double rentAmount, double electricityBill, double waterBill, double facilityBill, double totalAmount) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("----------- Invoice -------------\n");
        receipt.append("Tenant Name: ").append(tenantName).append("\n");
        receipt.append("Unit Rented: ").append(unitCode).append("\n");
        receipt.append("Rent Amount: ₱").append(String.format("%.2f", rentAmount)).append("\n");
        receipt.append("Electricity Bill: ₱").append(String.format("%.2f", electricityBill)).append("\n");
        receipt.append("Water Bill: ₱").append(String.format("%.2f", waterBill)).append("\n");
        receipt.append("Facility Bill: ₱").append(String.format("%.2f", facilityBill)).append("\n");
        receipt.append("Total Amount: ₱").append(String.format("%.2f", totalAmount)).append("\n");
        receipt.append("--------------------------------\n");
        receipt.append("Thank you for your payment!\n");
        return receipt.toString();
    }

    private void showTenantDetails(int tenantID) {
        String sql = """
            SELECT t.tenantID, CONCAT(t.firstName, ' ', t.lastName) AS tenantName, 
                   t.contactNum, t.email, t.additionalInfo, a.unitCode 
            FROM tenant t
            JOIN apartment a ON t.unitID = a.unitID
            WHERE t.tenantID = ?
        """;

        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, tenantID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Retrieve tenant details
                    String tenantName = rs.getString("tenantName");
                    String contactNum = rs.getString("contactNum");
                    String email = rs.getString("email");
                    String additionalInfo = rs.getString("additionalInfo");
                    String unitCode = rs.getString("unitCode");

                    // Generate detailed information
                    String details = generateTenantDetails(tenantName, contactNum, email, additionalInfo, unitCode);

                    // Display in a dialog
                    JTextArea textArea = new JTextArea(details);
                    textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

                    JOptionPane.showMessageDialog(this, scrollPane, "Details for Tenant: " + tenantName, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No details found for Tenant ID: " + tenantID, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving tenant details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String generateTenantDetails(String tenantName, String contactNum, String email, String additionalInfo, String unitCode) {
        StringBuilder details = new StringBuilder();
        details.append("----------- Tenant Details -----------\n");
        details.append("Name: ").append(tenantName).append("\n");
        details.append("Contact Number: ").append(contactNum).append("\n");
        details.append("Email: ").append(email).append("\n");
        details.append("Unit Code: ").append(unitCode).append("\n");
        details.append("Additional Info: ").append(additionalInfo).append("\n");
        details.append("--------------------------------------\n");
        return details.toString();
    }


    private void deleteTenant(int tenantID) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this tenant?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            boolean isDeleted = dbConnection.deleteTenantAndUpdateUnitStatus(tenantID);

            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Tenant deleted successfully.");
                loadTenantData(); // Refresh the table after deletion
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete tenant.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private List<Object[]> fetchTenantHistory() {
        String sql = """
            SELECT tenantID, firstName, lastName, contactNum, email, additionalInfo, unitID
            FROM tenanthistory
        """;

        List<Object[]> tenantHistory = new ArrayList<>();

        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tenantHistory.add(new Object[]{
                    rs.getInt("tenantID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("contactNum"),
                    rs.getString("email"),
                    rs.getString("additionalInfo"),
                    rs.getInt("unitID")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching tenant history: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return tenantHistory;
    }
    
    private void showTenantHistory() {
        List<Object[]> tenantHistory = fetchTenantHistory();

        // Define column names
        String[] columnNames = { "Tenant ID", "First Name", "Last Name", "Contact No.", "Email", "Additional Info", "Unit ID" };

        // Populate data into table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Object[] row : tenantHistory) {
            model.addRow(row);
        }

        // Create a table and add it to a scrollable pane
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);

        // Display in a dialog
        JOptionPane.showMessageDialog(this, scrollPane, "Tenant History", JOptionPane.INFORMATION_MESSAGE);
    }


}
