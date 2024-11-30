package main;

import java.awt.Color;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
        		"Tenant ID", "Tenant Name", "Unit Rented", "" , ""
        	}
        ));
        scrollPane.setViewportView(tableTenants);
        
        tableTenants.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        
      //  JButton btnAddTenant = new JButton("Add Tenant");
        RoundedButton btnAddTenant = new RoundedButton("Add Tenant", 15);
        btnAddTenant.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AddTenantDialog addTenantDialog = new AddTenantDialog();
                
                // Set modal to block other windows while this dialog is open
                addTenantDialog.setModal(true);
                
                // Display the dialog
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
        
     //   JButton btnRefresh = new JButton("Refresh");
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
        
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = mainPanel.getWidth();
                btnAddTenant.setBounds(frameWidth - 215, 20, 100, 40); // Adjust sidebarPanel height
                btnRefresh.setBounds(frameWidth - 100, 20, 80, 40); // Adjust sidebarPanel height


            }
        });  
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                int frameWidth = getWidth();
                mainPanel.setBounds(351, 76, frameWidth - 401, frameHeight - 76); // Adjust sidebarPanel height

            }
        });  
        
    /*    this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameHeight = getHeight(); // Get the new frame height
                int frameWidth = getWidth();
                mainPanel.setBounds(401, 126, frameWidth - 451, frameHeight - 123); // Adjust sidebarPanel height

            }
        });  */
        
        loadTenantData();
	}
	
	private void loadTenantData() {
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
	            "Delete"
	        });
	    }

	    if (model.getColumnCount() >= 5) {
	        tableTenants.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
	        tableTenants.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());

	        tableTenants.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JButton("More"), this::showTenantDetails));
	        tableTenants.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JButton("Delete"), this::deleteTenant));
	    }
	}
	
	private void showTenantDetails(int tenantID) {
	    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
	    TenantDetails tenantDetails = dbConnection.fetchTenantDetails(tenantID);

	    if (tenantDetails != null) {
	    	
	    	new TenantDetailsFrame(tenantDetails);
	    	
	  /*      JOptionPane.showMessageDialog(this, "TENANT DETAILS\n\n" +
	            "Name: " + tenantDetails.getTenantName() + "\n\n" +
	            "Contact: " + tenantDetails.getContactNum() + "\n\n" +
	            "Email: " + tenantDetails.getEmail() + "\n\n" +
	            "Additional Info: " + tenantDetails.getAdditionalInfo() + "\n\n" +
	            "Unit Code: " + tenantDetails.getUnitCode() + "\n\n" +
	            "Rent Start: " + tenantDetails.getRentStart() + "\n\n"     
	        );       */
	    } else {
	        JOptionPane.showMessageDialog(this, "Details not found for Tenant ID: " + tenantID, "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
/*	private void deleteTenant(int tenantID) {
	    int confirm = JOptionPane.showConfirmDialog(this,
	            "Are you sure you want to delete this tenant?",
	            "Confirm Deletion",
	            JOptionPane.YES_NO_OPTION);

	    if (confirm == JOptionPane.YES_OPTION) {
	        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
	        boolean isDeleted = dbConnection.deleteTenant(tenantID);

	        if (isDeleted) { 
	        	
	        	int unitID = dbConnection.getUnitIdForTenant(tenantID);

	            // Now update the status of the unit to "Available"
	            boolean isUnitUpdated = dbConnection.updateUnitStatusToAvailable(unitID);

	            reattachButtonListeners();
	            JOptionPane.showMessageDialog(this, "Tenant deleted successfully.");
	        } else {
	            JOptionPane.showMessageDialog(this, "Failed to delete tenant.", "Error", JOptionPane.ERROR_MESSAGE);
	        }       
	    }
	}     */
	
	
	private void deleteTenant(int tenantID) {
	    // Ask for confirmation before proceeding with deletion
	    int confirm = JOptionPane.showConfirmDialog(this,
	            "Are you sure you want to delete this tenant?",
	            "Confirm Deletion",
	            JOptionPane.YES_NO_OPTION);

	    if (confirm == JOptionPane.YES_OPTION) {
	        // Get the DatabaseConnection instance
	        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
	        
	        // Call the method that deletes the tenant and updates the unit status
	        boolean isSuccess = dbConnection.deleteTenantAndUpdateUnitStatus(tenantID);

	        // Check if the operation was successful
	        if (isSuccess) {
	            reattachButtonListeners();
	            JOptionPane.showMessageDialog(this, "Tenant deleted and unit status updated successfully.");
	        } else {
	            JOptionPane.showMessageDialog(this, "Failed to delete tenant or update unit status.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void reattachButtonListeners() {
	    // Reattach listeners for "More" and "Delete" buttons
	    tableTenants.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JButton("More"), this::showTenantDetails));
	    tableTenants.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JButton("Delete"), this::deleteTenant));
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
	
	
	
	
}
