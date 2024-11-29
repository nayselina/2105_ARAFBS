package dbConnection;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException; // Add this import
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

import model.TenantDetails;
import model.TenantModel;

import java.sql.*;
import java.util.ArrayList;
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
			String dueDate = sdf.format(new java.util.Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000));  // Add 1 month to rent date
			String billsQuery = "INSERT INTO bills (tenantID, unitID, totalAmount, totalBalance, dueDate, status) " +
			                "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement billsStmt = connection.prepareStatement(billsQuery);
			billsStmt.setInt(1, tenantID);
			billsStmt.setInt(2, unitID);
			billsStmt.setDouble(3, rentAmount);
			billsStmt.setDouble(4, rentAmount);
			billsStmt.setString(5, dueDate);
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
    
    public boolean deleteTenant(int tenantID) {
        String query = "DELETE FROM tenant WHERE tenantID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tenantID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
            return false;
       
    }

	public int getUnitIdForTenant(int tenantID) {
		// TODO Auto-generated method stub
		int unitID = -1;
	    String query = "SELECT unitID FROM tenant WHERE tenantID = ?";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, tenantID);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            unitID = resultSet.getInt("unitID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return unitID;
	}

	public boolean updateUnitStatusToAvailable(int unitID) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
	    String query = "UPDATE apartment SET status = 'Available' WHERE unitID = ?";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, unitID);
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            isSuccess = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isSuccess;
	}
	
	public boolean deleteTenantAndUpdateUnitStatus(int tenantID) {
        String deleteTenantQuery = "DELETE FROM tenant WHERE tenantID = ?";
        String getUnitIdQuery = "SELECT unitID FROM tenant WHERE tenantID = ?";
        String updateUnitStatusQuery = "UPDATE apartment SET status = 'Available', occupants = 0 WHERE unitID = ?";

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
   
}
