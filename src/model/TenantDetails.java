package model;

import java.util.Date;

public class TenantDetails {
    private int tenantID;
    private String tenantName;
    private String contactNum;
    private String email;
    private String additionalInfo;
    private String unitCode;
    private Date rentStart;

    public TenantDetails(int tenantID, String tenantName, String contactNum, String email,
                         String additionalInfo, String unitCode, Date rentStart) {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.contactNum = contactNum;
        this.email = email;
        this.additionalInfo = additionalInfo;
        this.unitCode = unitCode;
        this.rentStart = rentStart;
    }

    // Getters
    public int getTenantID() { return tenantID; }
    public String getTenantName() { return tenantName; }
    public String getContactNum() { return contactNum; }
    public String getEmail() { return email; }
    public String getAdditionalInfo() { return additionalInfo; }
    public String getUnitCode() { return unitCode; }
    public Date getRentStart() { return rentStart; }
}


 

