package main;

import java.awt.BorderLayout;
import java.awt.Color;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import componentsUI.Header;
import componentsUI.RoundedButton;
import componentsUI.BackgroundPanel;
import componentsUI.FrameDragUtility;
import componentsUI.RoundedPanel;
import componentsUI.SidebarPanel;
import dbConnection.DatabaseConnection;
import model.Apartment;

import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class Apartments extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableApartments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apartments frame = new Apartments();
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
	public Apartments() {
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
        getContentPane().add(header);
        
        SidebarPanel sidebar = new SidebarPanel(this, "Apartments");
        getContentPane().add(sidebar);
        
        
        RoundedPanel mainPanel = new RoundedPanel(30);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(301, 76, 949, 724);
        mainPanel.setLayout(null); // Absolute positioning 
        contentPane.add(mainPanel);
        
        BackgroundPanel photoPanel = new BackgroundPanel("/images/interior4.png");
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
        
        tableApartments = new JTable();
        tableApartments.setSelectionBackground(new Color(255, 230, 150));
        tableApartments.setRowHeight(30);
        tableApartments.setShowVerticalLines(false);
        tableApartments.setShowHorizontalLines(false);
        tableApartments.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tableApartments.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"No.", "Unit Code", "Unit Type", "Description", "Rent Amount", "Status"
        	}
        ));
        scrollPane.setViewportView(tableApartments);
        
        tableApartments.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                tableApartments.setBounds(0, 0, frameWidth - 401, frameHeight - 76); 

            }
        });  
        
        JTableHeader tblHeader = tableApartments.getTableHeader();
        tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblHeader.setBackground(new Color(247, 247, 231));
        tblHeader.setForeground(Color.black);
        tblHeader.setReorderingAllowed(false); 
        
        JLabel lblAllApartments = new JLabel("All Apartments");
        lblAllApartments.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblAllApartments.setBounds(15, 11, 265, 35);
        mainPanel.add(lblAllApartments);
        
        RoundedButton btnAssignTenant = new RoundedButton("Add Tenant", 15);
        btnAssignTenant.setText("Assign Tenant");
        btnAssignTenant.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AssignTenantDialog assignTenantDialog = new AssignTenantDialog();
                
                // Set modal to block other windows while this dialog is open
        		assignTenantDialog.setModal(true);
                
                // Display the dialog
        		assignTenantDialog.setVisible(true);
        	}
        });
        btnAssignTenant.setForeground(Color.WHITE);
        btnAssignTenant.setBorderPainted(false);
        btnAssignTenant.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAssignTenant.setBackground(new Color(183, 183, 47));
        btnAssignTenant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAssignTenant.setBounds(710, 15, 116, 30);
        mainPanel.add(btnAssignTenant);
        
        //   JButton btnRefresh = new JButton("Refresh");
        RoundedButton btnRefresh = new RoundedButton("Refresh", 15);
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		populateTable();
        		
        		
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
                btnAssignTenant.setBounds(frameWidth - 230, 20, 116, 40); // Adjust sidebarPanel height
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
        
        
        populateTable();
        
        tableApartments.getColumn("Description").setCellRenderer(new ButtonRenderer());
        tableApartments.getColumn("Description").setCellEditor(new ButtonEditor(new JCheckBox()));
        
 
    }
	
	private void populateTable() {
		DatabaseConnection dbConnection = new DatabaseConnection();
        List<Apartment> apartments = dbConnection.getAllApartments();
        DefaultTableModel model = (DefaultTableModel) tableApartments.getModel();
        model.setRowCount(0);  // Clear the table

        for (int i = 0; i < apartments.size(); i++) {
            Apartment apartment = apartments.get(i);
            Object[] row = new Object[]{
                apartment.getUnitID(),
                apartment.getUnitCode(),
                apartment.getUnitType(),
              //  "View Description",  // Text for the button
                apartment.getDescription(),
                apartment.getRentAmount(),
                apartment.getStatus()
            };
            model.addRow(row);
        }
    }
	
	public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("View Description");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
	
    public class ButtonEditor extends DefaultCellEditor {
        private String description;
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setText("View Description");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the apartment description for the corresponding row
                    int row = tableApartments.getSelectedRow();
                    String description = (String) tableApartments.getValueAt(row, 3);  // 3 is the "Description" column index
                    System.out.println("Description: " + description);  // Debugging line
                    showDescriptionFrame(description);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            description = (String) value;
            button.setText("View Description");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return description;
        }
    }
    
    private void showDescriptionFrame(String description) {
        JFrame descriptionFrame = new JFrame("Apartment Description");
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);  // Make the text area non-editable
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        descriptionFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        descriptionFrame.setSize(400, 300);
        descriptionFrame.setLocationRelativeTo(null);  // Center the frame
        descriptionFrame.setVisible(true);
    }


	
	
	
	
}
