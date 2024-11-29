package dbConnection;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException; // Add this import
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.TenantDetails;
import model.TenantModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/arafbsdb";
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = ""; // Replace with your DB password

    // Singleton instance
    private static DatabaseConnection instance = null;
    private static Connection connection = null;

    // Private constructor to prevent instantiation
    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to create database connection.");
        }
    }

    // Method to get the singleton instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to get the connection
    public Connection getConnection() {
        return connection;
    }
    
    
    

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to close database connection.");
            }
        }
    }
    
    
    public int getTotalApartments() {
        int total = 0;
        String query = "SELECT COUNT(*) AS total FROM apartment";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt("total"); // Get total count
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to fetch total apartments.");
        }
        return total;
        
    }
    
    public void populateAvailableUnitCodes(JComboBox<String> comboBoxUnitCode) {
        String query = "SELECT unitCode, status FROM apartment";  // Query to fetch units and their statuses

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Clear the combo box before adding new items
            comboBoxUnitCode.removeAllItems();

            // Add an initial empty item (optional)
            comboBoxUnitCode.addItem("");  // Placeholder item

            // Loop through the result set to add available units
            while (rs.next()) {
                String unitCode = rs.getString("unitCode");
                String status = rs.getString("status");

                // Add to combo box only if the unit is available
                if ("Available".equals(status)) {
                    comboBoxUnitCode.addItem(unitCode);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   

    public String addTenant(String firstName, String lastName, String contactNo, String email, String unitCode,
            String strOccupants, String additionalInfo, String strRentStartDate, String strRentEndDate) {
			try {
			// 1. Parse occupants to integer
			int occupants;
			try {
			occupants = Integer.parseInt(strOccupants);
			} catch (NumberFormatException e) {
			return "Invalid occupants value. Please enter a valid number.";
			}
			
			// 2. Validate and parse dates
			java.sql.Date rentStartDate, rentEndDate;
			try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rentStartDate = new java.sql.Date(sdf.parse(strRentStartDate).getTime());
			rentEndDate = new java.sql.Date(sdf.parse(strRentEndDate).getTime());
			} catch (ParseException e) {
			return "Invalid date format. Please use 'yyyy-MM-dd'.";
			}
			
			// 3. Get unitID and rentAmount based on unitCode
			String unitQuery = "SELECT unitID, rentAmount FROM apartment WHERE unitCode = ?";
			PreparedStatement unitStmt = connection.prepareStatement(unitQuery);
			unitStmt.setString(1, unitCode);
			ResultSet unitRS = unitStmt.executeQuery();
			
			int unitID = 0;
			double rentAmount = 0.0;
			if (unitRS.next()) {
			unitID = unitRS.getInt("unitID");
			rentAmount = unitRS.getDouble("rentAmount");
			} else {
			return "Unit code not found. Please check and try again.";
			}
			
		//	System.out.println("Retrieved unitID: " + unitID);
		//	System.out.println("Retrieved rentAmount: " + rentAmount);
			
			// 4. Update apartment occupants and status
			String updateApartmentQuery = "UPDATE apartment SET occupants = ?, status = 'Occupied' WHERE unitCode = ?";
			PreparedStatement updateApartmentStmt = connection.prepareStatement(updateApartmentQuery);
			updateApartmentStmt.setInt(1, occupants);
			updateApartmentStmt.setString(2, unitCode);
			updateApartmentStmt.executeUpdate();
			
			// 5. Insert tenant data into tenant table
			String tenantQuery = "INSERT INTO tenant (firstName, lastName, contactNum, email, additionalInfo, unitID) " +
			                 "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement tenantStmt = connection.prepareStatement(tenantQuery, Statement.RETURN_GENERATED_KEYS);
			tenantStmt.setString(1, firstName);
			tenantStmt.setString(2, lastName);
			tenantStmt.setString(3, contactNo);
			tenantStmt.setString(4, email);
			tenantStmt.setString(5, additionalInfo);
			tenantStmt.setInt(6, unitID);
			int tenantRowsAffected = tenantStmt.executeUpdate();
			
			if (tenantRowsAffected == 0) {
			return "Failed to add tenant details.";
			}
			
			ResultSet tenantRS = tenantStmt.getGeneratedKeys();
			int tenantID = 0;
			if (tenantRS.next()) {
			tenantID = tenantRS.getInt(1);  // Get generated tenantID
			}
			
			// 6. Insert into rentContract table
			String rentContractQuery = "INSERT INTO rentalcontract (tenantID, unitID, rentStart, rentEnd) " +
			                        "VALUES (?, ?, ?, ?)";
			PreparedStatement rentContractStmt = connection.prepareStatement(rentContractQuery, Statement.RETURN_GENERATED_KEYS);
			rentContractStmt.setInt(1, tenantID);
			rentContractStmt.setInt(2, unitID);
			rentContractStmt.setDate(3, rentStartDate);
			rentContractStmt.setDate(4, rentEndDate);
			int rentContractRowsAffected = rentContractStmt.executeUpdate();
			
			if (rentContractRowsAffected == 0) {
			return "Failed to create rent contract.";
			}
			
			ResultSet rentContractRS = rentContractStmt.getGeneratedKeys();
			int rentalContractID = 0;
			if (rentContractRS.next()) {
			rentalContractID = rentContractRS.getInt(1);  // Get generated rentContractID
			}
			
			// 7. Insert into rentalHistory table
			String rentalHistoryQuery = "INSERT INTO rentalhistory (tenantID, unitID, rentalContractID) " +
			                        "VALUES (?, ?, ?)";
			PreparedStatement rentalHistoryStmt = connection.prepareStatement(rentalHistoryQuery);
			rentalHistoryStmt.setInt(1, tenantID);
			rentalHistoryStmt.setInt(2, unitID);
			rentalHistoryStmt.setInt(3, rentalContractID);
			rentalHistoryStmt.executeUpdate();
			
			// 8. Insert into bills table
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//currentTime + 1 month
		//	String dueDate = sdf.format(new java.util.Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000));  // Add 1 month to rent date
			// Use Calendar to add 1 month to rentStartDate
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(rentStartDate);
	        calendar.add(Calendar.MONTH, 1); // Add one month

	        // Get the dueDate after adding one month
	        java.sql.Date dueDate = new java.sql.Date(calendar.getTimeInMillis());

			String billsQuery = "INSERT INTO bills (tenantID, unitID, totalAmount, totalBalance, dueDate, status) " +
			                "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement billsStmt = connection.prepareStatement(billsQuery);
			billsStmt.setInt(1, tenantID);
			billsStmt.setInt(2, unitID);
			billsStmt.setDouble(3, rentAmount);
			billsStmt.setDouble(4, rentAmount);
			billsStmt.setDate(5, dueDate);
			billsStmt.setString(6, "Unpaid");
			billsStmt.executeUpdate();
			
			return "Tenant and related data saved successfully.";
			
			} catch (SQLException e) {
			e.printStackTrace();
			return "Error while adding tenant and related data: " + e.getMessage();
			}
			}
    
    
    public List<TenantModel> fetchTenants() {
        List<TenantModel> tenants = new ArrayList<>();
        String sql = "SELECT t.tenantID, CONCAT(t.firstName, ' ', t.lastName) AS tenantName, a.unitCode " +
                     "FROM tenant t " +
                     "JOIN apartment a ON t.unitID = a.unitID";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TenantModel tenant = new TenantModel(
                        rs.getInt("tenantID"),
                        rs.getString("tenantName"),
                        rs.getString("unitCode")
                );
                tenants.add(tenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }

    public TenantDetails fetchTenantDetails(int tenantID) {
    	String sql = "SELECT t.tenantID, CONCAT(t.firstName, ' ', t.lastName) AS tenantName, " +
                "t.contactNum, t.email, t.additionalInfo, a.unitCode, r.rentStart " +
                "FROM tenant t " +
                "JOIN apartment a ON t.unitID = a.unitID " +
                "JOIN rentalContract r ON t.tenantID = r.tenantID " +
                "WHERE t.tenantID = ?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tenantID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TenantDetails(
                            rs.getInt("tenantID"),
                            rs.getString("tenantName"),
                            rs.getString("contactNum"),
                            rs.getString("email"),
                            rs.getString("additionalInfo"),
                            rs.getString("unitCode"),
                            rs.getDate("rentStart")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
	public boolean deleteTenantAndUpdateUnitStatus(int tenantID) {
        String deleteTenantQuery = "DELETE FROM tenant WHERE tenantID = ?";
        String getUnitIdQuery = "SELECT unitID FROM tenant WHERE tenantID = ?";
        String updateUnitStatusQuery = "UPDATE apartment SET status = 'Available', occupants = NULL WHERE unitID = ?";

        // Using transaction for ensuring atomicity
        try {
            // Start transaction
            connection.setAutoCommit(false);

            // Get the unit ID associated with the tenant
            int unitID = -1;
            try (PreparedStatement getUnitStmt = connection.prepareStatement(getUnitIdQuery)) {
                getUnitStmt.setInt(1, tenantID);
                ResultSet rs = getUnitStmt.executeQuery();
                if (rs.next()) {
                    unitID = rs.getInt("unitID");
                }
            }

            // If unitID is found, proceed with deletion and update
            if (unitID != -1) {
                // Delete tenant
                try (PreparedStatement deleteStmt = connection.prepareStatement(deleteTenantQuery)) {
                    deleteStmt.setInt(1, tenantID);
                    int rowsDeleted = deleteStmt.executeUpdate();

                    // If tenant was deleted, update the apartment status
                    if (rowsDeleted > 0) {
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateUnitStatusQuery)) {
                            updateStmt.setInt(1, unitID);
                            int rowsUpdated = updateStmt.executeUpdate();

                            // If the apartment status is updated, commit the transaction
                            if (rowsUpdated > 0) {
                                connection.commit();  // Commit both delete and update
                                return true;
                            }
                        }
                    }
                }
            }

            // If any of the operations fail, rollback the transaction
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();  // In case of exception, rollback changes
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);  // Restore default commit behavior
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;  // Return false if the operation failed
    }
	
	public void populateTable(JTable tableRentBills) {
	/*	String query = "SELECT " +
                "b.billID, " +
                "CONCAT(t.firstName, ' ', t.lastName) AS tenantName, " +
                "CONCAT('₱', FORMAT(a.rentAmount, 2)) AS rentAmount, " +
                "IF(b.electricityBill IS NULL, 'Awaiting Data', CONCAT('₱', FORMAT(b.electricityBill, 2))) AS electricityBill, " +
                "IF(b.waterBill IS NULL, 'Awaiting Data', CONCAT('₱', FORMAT(b.waterBill, 2))) AS waterBill, " +
                "IF(b.totalAmount = 0, 'Paid', CONCAT('₱', FORMAT(b.totalAmount, 2))) AS totalAmount, " +
                "b.dueDate, " +
                "b.status, " +
                "IF(f.facilityBill IS NULL, 'Awaiting Data', CONCAT('₱', FORMAT(f.facilityBill, 2))) AS facilityBill " +
                "FROM bills b " +
                "JOIN tenant t ON b.tenantID = t.tenantID " +
                "JOIN apartment a ON b.unitID = a.unitID " +
                "LEFT JOIN facility f ON b.facilityID = f.facilityID";  // Use LEFT JOIN to include all bills   */
		
		String query = "SELECT "
		        + "b.billID, "
		        + "CONCAT(t.firstName, ' ', t.lastName) AS tenantName, "
		        + "CONCAT('₱', FORMAT(a.rentAmount, 2)) AS rentAmount, "
		        + "IF(b.electricityBill IS NULL, 'Awaiting Data', CONCAT('₱', FORMAT(b.electricityBill, 2))) AS electricityBill, "
		        + "IF(b.waterBill IS NULL, 'Awaiting Data', CONCAT('₱', FORMAT(b.waterBill, 2))) AS waterBill, "
		        + "CONCAT('₱', FORMAT("
		        + "(a.rentAmount + IFNULL(b.electricityBill, 0) + IFNULL(b.waterBill, 0) + IFNULL(f.facilityBill, 0)), 2)"
		        + ") AS totalAmount, "
		        + "b.dueDate, "
		        + "b.status, "
		        + "IF(f.facilityBill IS NULL, 'Awaiting Data', CONCAT('₱', FORMAT(f.facilityBill, 2))) AS facilityBill "
		        + "FROM bills b "
		        + "JOIN tenant t ON b.tenantID = t.tenantID "
		        + "JOIN apartment a ON b.unitID = a.unitID "
		        + "LEFT JOIN facility f ON b.facilityID = f.facilityID;";

	    

	    try (PreparedStatement pstmt = connection.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery()) {
	        DefaultTableModel model = (DefaultTableModel) tableRentBills.getModel();
	        model.setRowCount(0); // Clear the table
	        while (rs.next()) {
	            Object[] row = {
	                rs.getInt("billID"),
	                rs.getString("tenantName"),
	                rs.getString("rentAmount"),
	                rs.getString("electricityBill"),
	                rs.getString("waterBill"),
	                rs.getString("facilityBill"),
	                rs.getString("totalAmount"),
	                rs.getDate("dueDate"),
	                rs.getString("status")
	            };
	            model.addRow(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}  
	
/*	public void populateTable(JTable tableRentBills) {
	    // Correct SQL query as provided
	    String query = "SELECT " +
	            "b.billID, " +
	            "t.firstName, " +
	            "t.lastName, " +
	            "a.rentAmount, " +
	            "b.electricityBill, " +
	            "b.waterBill, " +
	            "f.facilityBill, " +
	            "b.totalAmount, " +
	            "b.dueDate, " +
	            "b.status " +
	            "FROM " +
	            "bills b " +
	            "JOIN tenant t ON b.tenantID = t.tenantID " +
	            "JOIN apartment a ON b.unitID = a.unitID " +
	            "JOIN facility f ON b.facilityID = f.facilityID";

	    try (PreparedStatement pstmt = connection.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery()) {

	        // Prepare table model
	        DefaultTableModel model = (DefaultTableModel) tableRentBills.getModel();
	        model.setRowCount(0); // Clear previous rows

	        // Populate the table with query results
	        while (rs.next()) {
	            // Handle NULL values for electricityBill, waterBill, and facilityBill
	            Double electricityBill = rs.getDouble("electricityBill");
	            Double waterBill = rs.getDouble("waterBill");
	            Double facilityBill = rs.getDouble("facilityBill");

	            // If any bill is NULL, set it to 0 (or a custom value like "N/A")
	            if (rs.wasNull()) {
	                electricityBill = 0.0;
	                waterBill = 0.0;
	                facilityBill = 0.0;
	            }

	            model.addRow(new Object[]{
	                rs.getInt("billID"),
	                rs.getString("firstName") + " " + rs.getString("lastName"), // Concatenate first and last name
	                rs.getDouble("rentAmount"), // Rent amount
	                electricityBill, // Electricity bill, handling NULL as 0
	                waterBill, // Water bill, handling NULL as 0
	                facilityBill, // Facility bill, handling NULL as 0
	                rs.getDouble("totalAmount"), // Total amount
	                rs.getDate("dueDate"), // Due date
	                rs.getString("status") // Status
	            });
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Failed to populate table with rent bills.");
	    }
	}  */		public boolean insertFacilityAndUpdateBill(String billIDStr, String electricityBillStr, String waterBillStr, String facilityName, String facilityBillStr) {
				// Parsing the string values to appropriate types
						int billID = Integer.parseInt(billIDStr);
						double electricityBill = Double.parseDouble(electricityBillStr);
						double waterBill = Double.parseDouble(waterBillStr);
						double facilityBill = Double.parseDouble(facilityBillStr);
						
						String insertFacilitySQL = "INSERT INTO facility (facilityName, facilityBill) VALUES (?, ?)";
						String updateBillSQL = "UPDATE bills SET electricityBill = ?, waterBill = ?, facilityID = ? WHERE billID = ?";
						
						boolean success = false;
						int facilityID = -1;
						
						try {
						// Begin transaction
						connection.setAutoCommit(false);
						
						// Insert into facility table
						try (PreparedStatement stmt = connection.prepareStatement(insertFacilitySQL, Statement.RETURN_GENERATED_KEYS)) {
						stmt.setString(1, facilityName);
						stmt.setDouble(2, facilityBill);
						int affectedRows = stmt.executeUpdate();
						
						if (affectedRows == 0) {
						throw new SQLException("Inserting facility failed, no rows affected.");
						}
						
						// Get the auto-generated facilityID
						try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
						facilityID = generatedKeys.getInt(1);
						} else {
						throw new SQLException("Inserting facility failed, no ID obtained.");
						}
						}
						}
						
						// Update the bills table with the facilityID
						try (PreparedStatement stmt = connection.prepareStatement(updateBillSQL)) {
						stmt.setDouble(1, electricityBill);
						stmt.setDouble(2, waterBill);
						stmt.setInt(3, facilityID);
						stmt.setInt(4, billID);
						
						int rowsUpdated = stmt.executeUpdate();
						if (rowsUpdated > 0) {
						success = true;
						} else {
						throw new SQLException("Updating bill failed, no rows affected.");
						}
						}
						
						// Commit the transaction
						connection.commit();
						
						} catch (SQLException e) {
						// Rollback transaction in case of an error
						try {
						if (connection != null) {
						connection.rollback();
						}
						} catch (SQLException ex) {
						ex.printStackTrace();
						}
						e.printStackTrace();
						} finally {
						// Reset auto-commit to true
						try {
						if (connection != null) {
						connection.setAutoCommit(true);
						}
						} catch (SQLException ex) {
						ex.printStackTrace();
						}
						}
						
						return success;
						}
	
	


}
